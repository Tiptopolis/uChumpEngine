package com.mygdx.game.uChumpClasses.UI;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.uChumpClasses.uApp;
import com.mygdx.game.uChumpClasses.Core.iEntity;
import com.mygdx.game.uChumpClasses.Core.Event.uDirector;
import com.mygdx.game.uChumpClasses.Core.Event.uEvent;
import com.mygdx.game.uChumpClasses.Core.Utils.AppInjector;
import com.mygdx.game.uChumpClasses.UI.Heirarchy.UiOverseer;
import com.mygdx.game.uChumpClasses.UI.Heirarchy.iNode;
import com.mygdx.game.uChumpClasses.UI.Heirarchy.uNode;
import com.mygdx.game.uChumpClasses.UI.Util.uFrameBufferHandler;
import com.mygdx.game.uChumpClasses.UI.Util.uSketcher;
import com.mygdx.game.uChumpClasses.UI.Widgets.iMenu;

import static com.mygdx.game.uChumpClasses.UI.Heirarchy.UiOverseer.*;
import static com.mygdx.game.uChumpClasses.uApp.*;
import static com.mygdx.game.uChumpClasses.uAppUtils.*;
import static com.mygdx.game.uChumpClasses.Core.Input.uMouse.*;

public class uScreen extends Stage implements iScreen {

	public uApp owner;
	public String name = "New uScreen";
	public String fullName = "";

	public boolean currentScreen = false;

	protected int vWidth = Width;
	protected int vHeight = Height;
	public Vector2 mOffset = new Vector2(0, 0);

	public ArrayList<iLayer> Layers = new ArrayList<iLayer>();
	public ArrayList<iEntity> uChildren = new ArrayList<iEntity>();
	public ArrayList<iEntity> preHide = new ArrayList<iEntity>(); // <<testing, weird panel hide issue

	public uNode Node;
	public iCamera Camera;
	//public InputMultiplexer Multiplexer;

	public FrameBuffer buffer = null; // adHoc works, now need to chunkify the world
	public TextureRegion view = null;
	private float m_fboScaler = 1.0f; //
	private boolean m_fboEnabled = true;

	public uSketcher Sketcher;
	public uFrameBufferHandler BufferHandler;
	public uDirector Director;
	public UiOverseer UI;

	// public uDirector Director;

	public uScreen(uApp owner) {
		this.owner = owner;
		this.owner.Screens.add(this);

		this.Node = new uNode(this);

		this.Camera = new uCamera(this);
		this.Camera.camOn();
		//this.Multiplexer = new InputMultiplexer();
		//this.Multiplexer.addProcessor(this);
		

		this.BufferHandler = AppInjector.getInjector().getInstance(uFrameBufferHandler.class);
		this.Sketcher = AppInjector.getInjector().getInstance(uSketcher.class);
		this.Director = AppInjector.getInjector().getInstance(uDirector.class);
		this.UI = AppInjector.getInjector().getInstance(UiOverseer.class);
		
		this.UI.addNode(this.Node);//UI.Heirarchy.addNode();
		this.UI.addRoot(this.Node);


		this.init();
	}

	public void init() {
		this.resetBuffer();
	}

	@Override
	public void render(float delta) {
		this.translateOffset();

		Sketcher.beginBatch();
		Sketcher.batch.draw(view, 0, 0);
		Sketcher.endBatch();

		super.act();
	}

	@Override
	public void drawBuffer(float deltaTime) {
		// called from uFrameBufferHandler
		this.Camera.reposition(0, 0);

		if (this.buffer == null)
			this.resetBuffer();

		this.buffer.begin();
		Gdx.gl.glClearColor(0f, 0f, 0f, 0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// render to buffer
		view.setRegion((int) this.Camera.getPosition().x, (int) this.Camera.getPosition().y,
				(int) this.Camera.getViewportWidth(), (int) this.Camera.getViewportHeight());
		view.flip(false, true);
		Sketcher.update(this.buffer.getWidth(), this.buffer.getHeight()); // changes projection to buffer

		for (iLayer l : this.Layers) {

			l.draw(deltaTime);
		}

		Sketcher.update((int) this.getWidth(), (int) this.getHeight());

		this.buffer.end();
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
	public void show() {
		this.currentScreen = true;
		//Gdx.input.setInputProcessor(this.Multiplexer);
		this.owner.Multiplexer.addProcessor(this);//
		this.BufferHandler.registerSpace(this);
		this.Director.registerEventHandler(this);
		// preHide widgets?
		this.enter();
	}

	@Override
	public void hide() {
		// save cache
		this.currentScreen = false;
		this.BufferHandler.deregisterSpace(this);
		this.owner.Multiplexer.removeProcessor(this);
		// this.Director.deregisterEventHandler(this);
		this.exit();
	}

	public void preHide() {
		for (iEntity i : this.preHide) {
			i.hide();
			//if (i instanceof iMenu) {
				//((iMenu) i).refreshMenu();
			//}
		}
	}

	@Override
	public void resize(int width, int height) {
		this.vWidth = width;
		this.vHeight = height;
		this.Camera.resize(width, height);
		this.Camera.reposition(0, 0);
		this.resetBuffer();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		for (iLayer l : this.Layers) {
			l.dispose();
		}

		this.buffer.dispose();
		this.buffer = null;
		this.view = null;
		//this.Multiplexer = null;
		this.Layers.clear();

	}

	@Override
	public void translateOffset() {
		Vector2 screenPos = new Vector2(0, 0);
		Vector2 mouseOff = new Vector2(mouseX - screenPos.x, mouseY - screenPos.y);
		Vector2 worldOff = new Vector2(mouseOff.x + this.Camera.getPosition().x,
				mouseOff.y + this.Camera.getPosition().y);
		this.mOffset.set(worldOff.x, worldOff.y);
	}

	@Override
	public Vector2 getOffset() {
		return this.mOffset;
	}

	@Override
	public boolean mouseInside() {
		return true;
	}

	//
	//
	//

	@Override
	public void enter() {

		this.Node.isActive = true;
		int size = Layers.size();
		for (int i = 0; i < size; i++) {
			Layers.get(i).enter();
		}
	}

	@Override
	public void exit() {
		this.Node.isActive = false;
		this.outClick(null);
		// this.invalidateOver(null);
		int size = Layers.size();
		for (int i = 0; i < size; i++) {
			Layers.get(i).exit();
		}
	}

	//
	//
	//

	@Override
	public float getWidth() {
		return this.vWidth;
	}

	public float getHeight() {
		return this.vHeight;
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
	public iCamera getCam() {
		return this.Camera;
	}

	@Override
	public uScreen getScreen() {
		return this;
	}

	@Override
	public iNode getNode() {
		// TODO Auto-generated method stub
		return this.Node;
	}

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
			// this.Node.removeChild(this.Node.getChild(name));
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
					e.toFront();

				}
			}
		}
	}

	@Override
	public void invalidateOver(Actor actor) {
		for (iLayer l : this.Layers) {
			l.invalidateOver(actor);
		}
	}

	@Override
	public void outClick(iEntity entity) {
		for (iLayer l : this.Layers) {
			l.outClick(entity);
		}
	}

	//
	// EVENTS
	//

	@Override
	public boolean handleEvent(uEvent event) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void castEvent(String event) {
		// TODO Auto-generated method stub

	}

	//
	// iOBJECT
	//

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
		this.updateFullName();
		this.Node.setName(name);
	}

	@Override
	public String getFullName() {
		return this.fullName;
	}

	@Override
	public void updateFullName() {
		if (!this.Node.isChild()) {
			this.fullName = "root." + this.getName();
		}
	}

	@Override
	public void addPrehide(iEntity entity) // <<TESTING
	{
		this.preHide.add(entity);
	}

	@Override
	public void prehide() {
		for (iEntity e : this.preHide) {
			e.hide();
		}
	}

	@Override
	public void printDebug() {
		String g = ("={[" + this.name + "]}");
		int p = g.length();
		String x = "=";
		String n = "";
		for (int i = p; i < 25; i++) {
			n = n + x;
		}

		println("={[" + this.name + "]}" + n + "{ROOT}");
	}

}
