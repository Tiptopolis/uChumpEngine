package com.mygdx.game.uChumpClasses.UI.Widgets;

import static com.mygdx.game.uChumpClasses.uAppUtils.*;
import static com.mygdx.game.uChumpClasses.Core.Input.uMouse.*;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.mygdx.game.uChumpClasses.Core.iEntity;
import com.mygdx.game.uChumpClasses.Core.Event.uEvent;
import com.mygdx.game.uChumpClasses.Core.Input.uHit;
import com.mygdx.game.uChumpClasses.Core.Utils.AppInjector;
import com.mygdx.game.uChumpClasses.UI.iCamera;
import com.mygdx.game.uChumpClasses.UI.iLayer;
import com.mygdx.game.uChumpClasses.UI.uCamera;
import com.mygdx.game.uChumpClasses.UI.uLayer;
import com.mygdx.game.uChumpClasses.UI.uScreen;
import com.mygdx.game.uChumpClasses.UI.uWidget;
import com.mygdx.game.uChumpClasses.UI.Drawable.dRect;
import com.mygdx.game.uChumpClasses.UI.Heirarchy.iNode;
import com.mygdx.game.uChumpClasses.UI.Heirarchy.uNode;
import com.mygdx.game.uChumpClasses.UI.Util.uFrameBufferHandler;
import com.mygdx.game.uChumpClasses.UI.Widgets.Controls.iDragable;
import com.mygdx.game.uChumpClasses.UI.Widgets.Controls.uCloseBtn;
import com.mygdx.game.uChumpClasses.UI.Widgets.Controls.uDragbar;

public class uPanel extends uWidget implements iContainer, iDragable {

	public uCamera Camera;

	public int vWidth = 500;
	public int vHeight = 500;

	public Vector2 mOffset = new Vector2(0, 0);

	public ArrayList<iLayer> Layers = new ArrayList<iLayer>();
	public ArrayList<uWidget> uControls = new ArrayList<uWidget>();

	public uLayer DefaultLayer;

	public FrameBuffer buffer = null; // adHoc works, now need to chunkify the world
	public TextureRegion view = null;
	private float m_fboScaler = 1.0f; //
	private boolean m_fboEnabled = true;

	public boolean dragLock = false;
	public boolean dragable = true;
	public boolean selfDrag = false;
	public Vector2 dragPoint = new Vector2(0, 0);
	public Vector2 dragOffset = new Vector2(0, 0);
	public DragListener drag;
	public int controlBuffer = 8; // for control positioning

	public boolean resizable = false;

	public uFrameBufferHandler BufferHandler;

	public uPanel(iLayer layer, int x, int y, int width, int height) {
		super(layer, "New uPanel");

		this.BufferHandler = AppInjector.getInjector().getInstance(uFrameBufferHandler.class);
		this.setPosition(x, y);
		this.setSize(width, height);
		this.setBounds(this.getX(), this.getY(), this.getWidth(), this.getHeight());
		this.DefaultLayer = new uLayer(this);
		this.DefaultLayer.setName("DefaultLayer");

		this.Camera = new uCamera(this);
		this.Camera.resize((int) this.getWidth() - 2, (int) this.getHeight() - 2);

		this.init();
	}

	@Override
	public void init() {
		super.init();
		this.resetBuffer();
		this.show();
	}

	@Override
	public void draw(float deltaTime) {

		this.translateOffset();
		super.draw(deltaTime);

	}

	@Override
	public void drawT(float deltaTime) {
		if (this.body != null && this.Node.isActive() == true) {
			Sketcher.batch.draw(view, (int) (this.getX() + 2), (int) (this.getY() + 2));
		}

	}

	@Override
	public void drawBuffer(float deltaTime) {
		if (this.Node.isActive) {

			if (this.buffer == null) {
				this.resetBuffer();
			}

			this.buffer.begin();

			Gdx.gl.glClearColor(0f, 0f, 0f, 0f);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

			this.Camera.reposition(0, 0); // <<INTERNAL OFFSET TEST; works just fine

			view.setRegion((int) this.Camera.getPosition().x, (int) this.Camera.getPosition().y,
					(int) this.Camera.getViewportWidth(), (int) this.Camera.getViewportHeight());
			view.flip(false, true);
			Sketcher.update(this.buffer.getWidth(), this.buffer.getHeight()); // changes projection to buffer

			// for (iLayer l : this.Layers) {

			// l.draw(deltaTime);

			// }
			this.bufferContents(deltaTime);

			this.buffer.end();

		}
	}

	private void bufferContents(float deltaTime)// allows intercept of what actually gets drawn to buffer
	{
		for (iLayer l : this.Layers) {

			l.draw(deltaTime);

		}
	}

	@Override
	public void resetBuffer() {
		buffer = new FrameBuffer(Format.RGBA8888, (int) (this.vWidth * m_fboScaler), (int) (this.vHeight * m_fboScaler),
				false);

		view = new TextureRegion(buffer.getColorBufferTexture(), (int) this.Camera.getPosition().x,
				(int) this.Camera.getPosition().y, (int) this.getWidth(), (int) this.getHeight());

		view.flip(false, true);
	}

	@Override
	public void resize(int width, int height) {
		this.Camera.resize(width, height);
		this.setSize(width, height);
		this.body.width = width;
		this.body.height = height;
		this.resetBuffer();

		for (uWidget w : this.uControls) {
			w.resize((int) this.getWidth(), (int) this.getHeight());
		}
	}

	@Override
	public void reposition(int x, int y) {
		// Vector2 temp = new Vector2(this.getX(), this.getY());
		this.setPosition(x, y);
		this.body.x = x;
		this.body.y = y;
		this.resize((int) this.getWidth(), (int) this.getHeight());
	}

	@Override
	public void moveBy(float x, float y) {
		// super.moveBy(x, y);
		if (x != 0 || y != 0) {
			this.setX(this.getX() + (x - this.dragOffset.x));
			this.setY(this.getY() + (y - this.dragOffset.y));
			this.body.reposition(this.getX(), this.getY());
			this.positionChanged();

			for (uWidget w : this.uControls) {
				// w.reposition((int) x, (int) y);
				w.moveBy(x, y);
			}
		}
	}

	@Override
	public void show() {
		super.show();
		this.BufferHandler.registerSpace(this);

		for (uWidget w : this.uControls) {
			w.show();
		}

		/*iLayer tempL;// done to correct ComcurrentModificationException
		for (int i = 0; i < this.Layers.size(); i++) {
			tempL = this.Layers.get(i);
			for (int j = 0; j < tempL.getMembers().size(); j++) {
				tempL.getMembers().get(j).show();
			}

		}*/

		ArrayList<iEntity> tempE = new ArrayList<iEntity>();
		ArrayList<iLayer> tempL = new ArrayList<iLayer>();
		tempL.addAll(this.Layers);

		for (iLayer l : tempL) {
			tempE.addAll(l.getMembers());
			{
				for (iEntity e : tempE) {
					e.show();
				}
			}
		}

		this.sortFront();

	}

	@Override
	public void hide() {
		// if (this.Node.isActive == true) {
		super.hide();
		for (uWidget w : this.uControls) {
			w.hide();
		}
		for (iLayer l : this.Layers) {
			for (iEntity e : l.getMembers()) {
				e.hide();
			}
		}
		// }

	}

	@Override
	public void close() {
		this.BufferHandler.deregisterSpace(this);
		this.Director.deregisterEventHandler(this);

		for (uWidget w : this.uControls) {
			if (layer.getMembers().contains(w)) {
				layer.getMembers().remove(w);
			}
			if (screen.uChildren.contains(w)) {
				screen.uChildren.remove(w);
			}
		}

		this.Layers.clear();
		// this.uChildren.clear();
		this.uControls.clear();

		this.layer.getMembers().remove(this);
		this.screen.uChildren.remove(this);

		this.Node.close();// not sure if itll work

		this.dispose();
	}

	@Override
	public void enter() {
		super.enter();
	}

	@Override
	public void exit() {
		super.exit();
	}

	//
	// iContainerWidget
	//

	@Override
	public iLayer getParentLayer() {
		return this.layer;
	}

	@Override
	public void addControl(uWidget control) {
		if (!this.uControls.contains(control))
			this.uControls.add(control);
	}

	//
	// iSPACE
	//

	@Override
	public float getWidth() {
		return super.getWidth();
	}

	public float getHeight() {
		return super.getHeight();
	}

	@Override
	public int getSpaceWidth() {
		return this.vWidth;
	}

	@Override
	public int getSpaceHeight() {
		return this.vHeight;
	}

	@Override
	public uScreen getScreen() {
		return this.screen;
	}

	@Override
	public iCamera getCam() {
		return this.Camera;
	}

	@Override
	public void translateOffset() {
		Vector2 screenPos = new Vector2(this.getX(), this.getY());
		Vector2 mouseOff = new Vector2(mouseX - screenPos.x, mouseY - screenPos.y);
		Vector2 worldOff = new Vector2(mouseOff.x + this.Camera.getPosition().x,
				mouseOff.y + this.Camera.getPosition().y);
		this.mOffset.set(worldOff.x, worldOff.y);
	}

	@Override
	public Vector2 getOffset() {
		return this.mOffset;
	}

	//
	// INPUT
	//

	@Override
	public boolean mouseInside() {

		if (this.hit.hit(mouseX, mouseX, this.isTouchable()) == this)
			return true;

		return false;
	}

	@Override
	public void invalidateOver(Actor actor) {
		super.invalidateOver(this);
		for (iLayer l : this.Layers) {
			l.invalidateOver(actor);

		}
	}

	@Override
	public void sortFront() {
		// super.sortFront();
		this.layer.removeMember(this);
		this.layer.addMember(this);
		this.toFront();

		for (uWidget w : this.uControls) {
			w.sortFront();
		}

		ArrayList<iEntity> temp = new ArrayList<iEntity>();

		for (iLayer l : this.Layers) {
			temp.addAll(l.getMembers());
			for (iEntity e : temp) {
				l.getMembers().remove(e);
				l.getMembers().add(e);
				e.toFront();

			}
		}
		// this.space.sortLayers(this.layer);

	}

	@Override
	public void mouseEnter() {
		super.mouseEnter();
	}

	@Override
	public void mouseExit() {
		super.mouseExit();
	}

	@Override
	public void onClick() {

		if (this.selfDrag) {
			this.setDragPoint();
			this.dragLock = true;
		}
		super.onClick();
		this.space.sortLayer(this.layer);
		this.castEvent("PANEL_VOID_CLICK");
	}

	@Override
	public void onRelease() {
		super.onRelease();
		if (this.selfDrag) {
			this.dragLock = false;
		}
	}

	@Override
	public void buildInputListener() {
		super.buildInputListener();
		this.drag = new DragListener() {
			@Override
			public void drag(InputEvent event, float x, float y, int pointer) {
				doDrag(x, y);

			}
		};
		this.addListener(drag);
	}

	//
	// iDRAGABLE
	//

	@Override
	public void dragLock(boolean lock) {
		this.dragLock = lock;
	}

	@Override
	public void setDragable(boolean dragable) {
		this.dragable = dragable;
	}

	@Override
	public void setSelfDrag(boolean dragable) {
		this.selfDrag = dragable;
	}

	@Override
	public boolean isDragable() {
		return this.dragable;
	}

	public void doDrag(float x, float y) {
		if (this.dragLock == true && this.dragable == true) {

			this.moveBy(x, y);

		}
	}

	public void setDragPoint() {
		dragPoint.set(mouseX, mouseY);
		dragOffset.set(dragPoint.x - this.getX(), dragPoint.y - this.getY());
	}

	public void setDragPoint(float x, float y, float ox, float oy) {
		this.dragPoint.set(x, y);
		this.dragOffset.set(ox, oy);
	}

	@Override
	public Vector2 getDragOffset() {
		return this.dragOffset;
	}

	// Resize

	//
	// LAYERS
	//

	@Override
	public void addLayer(iLayer layer) {
		this.Layers.add(layer);
		this.Node.addChild(layer.getNode());
	}

	@Override
	public void removeLayer(String name) {
		if (this.getLayer(name) != null) {
			this.removeLayer(this.getLayer(name));
		}
	}

	@Override
	public void removeLayer(iLayer layer) {
		if (this.Layers.contains(layer) && layer != null) {
			this.Layers.remove(layer);
			this.Node.removeChild(layer.getNode());
		}
	}

	@Override
	public iLayer getLayer(String layer) {

		for (iLayer l : this.Layers) {
			if (l.getName() == layer) {
				return l;
			}
		}

		return null;
	}

	@Override
	public ArrayList<iLayer> getLayers() {
		return this.Layers;
	}

	@Override
	public void sortLayer(iLayer layer) {
		uLayer temp;

		for (int i = this.Layers.indexOf(layer) + 1; i < this.Layers.size(); i++) {
			temp = (uLayer) this.Layers.get(i);
			if (temp != null) {
				temp.toFront();
				this.Layers.remove(temp);
				this.Layers.add(temp);

				for (iEntity e : temp.Members) {
					// println(e.getName() + "XXX");
					e.toFront();

				}
			}
		}
	}

	//
	// BUILD
	//

	@Override
	public void buildControls() {

		uDragbar d = new uDragbar(this, true);
		this.addControl(d);

		uCloseBtn b = new uCloseBtn(this);
		this.addControl(b);

	}

	@Override
	public void handleDisplayState() {
		if (this.mouseInside() == true && this.hit.mouseOver == true && this.hit.selected == false)
			this.states.setState("MouseOver");
		// add MouseInside
		else if (this.mouseInside() == true && this.hit.mouseOver == false)
			this.states.setState("MouseInside");
		else if (this.hit.mouseOver == false && this.hit.selected == true)
			this.states.setState("Selected");
		else if (this.hit.mouseOver == true && this.hit.selected == true)
			this.states.setState("SelectedOver");
		else
			this.states.setState("NULL");

		this.states.handleState();
	}

	@Override
	public void buildDisplayStates() {
		this.states.alterState("NULL", Color.WHITE, Color.DARK_GRAY, 2);
		this.states.addState("MouseOver", Color.WHITE, Color.RED, 2);
		this.states.addState("MouseInside", Color.WHITE, Color.RED, 1);
		this.states.addState("Selected", Color.WHITE, Color.YELLOW, 1);
		this.states.addState("SelectedOver", Color.WHITE, Color.YELLOW, 2);
	}

	public void buildBody() {
		this.body = new dRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
		this.hit = new uHit(this);
	}

	//
	//
	//

	@Override
	public boolean handleEvent(uEvent event) {
		boolean handled = false;
		switch (event.getEvent()) {

		case "BTN_CLICK":
			if (this.uControls.contains(event.getCaster()) && event.getCaster() instanceof uCloseBtn) {
				this.hide();
			}

			if (event.getCaster().getName() == "testButton") {
				this.show();
			}

			break;

		default:
			break;
		}

		return handled;
	}

}
