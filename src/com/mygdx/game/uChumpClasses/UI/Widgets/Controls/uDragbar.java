package com.mygdx.game.uChumpClasses.UI.Widgets.Controls;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.mygdx.game.uChumpClasses.Core.iEntity;
import com.mygdx.game.uChumpClasses.Core.iObject;
import com.mygdx.game.uChumpClasses.Core.Input.uHit;
import com.mygdx.game.uChumpClasses.UI.iLayer;
import com.mygdx.game.uChumpClasses.UI.iWidget;
import com.mygdx.game.uChumpClasses.UI.uWidget;
import com.mygdx.game.uChumpClasses.UI.Drawable.dRect;
import com.mygdx.game.uChumpClasses.UI.Widgets.iContainer;

import static com.mygdx.game.uChumpClasses.uAppUtils.*;
import static com.mygdx.game.uChumpClasses.Core.Input.uMouse.*;

public class uDragbar extends uWidget {

	public iDragable dragged;

	public Vector2 dragPoint = new Vector2(0, 0);
	public Vector2 dragOffset = new Vector2(0, 0);

	public DragListener drag;
	public int cBuffer = 0;

	public uDragbar(iDragable dragged, boolean buffer) {
		super(((iWidget) dragged).getParentLayer(), ((iObject) dragged).getName() + ".Dragbar");
		this.dragged = dragged;

		if (buffer == true) {
			cBuffer = 8;
		}

		this.setWidth(((Actor) dragged).getWidth()-(this.cBuffer*4));
		this.setHeight(16);
		this.setPosition(((Actor) dragged).getX()+this.cBuffer,
				((Actor) dragged).getY() + ((Actor) dragged).getHeight() - this.getHeight());
		this.setBounds(this.getX(), this.getY(), this.getWidth(), this.getHeight());

		this.show();
		this.init();
	}

	@Override
	public void init() {
		super.init();
		((iEntity) this.dragged).resize((int) ((Actor) this.dragged).getWidth(),
				(int) ((Actor) this.dragged).getHeight());
	}

	@Override
	public void draw(float deltaTime) {
		super.draw(deltaTime);

	}

	@Override
	public void resize(int width, int height) {
		this.setWidth(width-(this.cBuffer*3));
		this.setHeight(16);
		this.setPosition(((Actor) dragged).getX()+this.cBuffer,
				((Actor) dragged).getY() + ((Actor) dragged).getHeight() - this.getHeight());
		this.body.reposition(this.getX(), this.getY());

	}

	@Override
	public void moveBy(float x, float y) {
		if (x != 0 || y != 0) {
			this.setX(this.getX() + (x - this.dragOffset.x));
			this.setY(this.getY() + (y + this.dragOffset.y));
			this.body.reposition(body.x + (x - this.dragOffset.x), body.y + (y + this.dragOffset.y));
			this.positionChanged();
		}
	}

	@Override
	public void reposition(int x, int y) {
		((Actor) this.dragged).moveBy(x, y);
	}

	//
	// DRAG
	//

	public void doDrag(float x, float y) {
		((Actor) this.dragged).moveBy(x, y);
	}

	public void setDragPoint() {
		this.dragPoint.set(mouseX, mouseY);
		this.dragOffset.set(mouseX - this.getX(), mouseY - this.getY());
		this.dragged.setDragPoint(this.dragPoint.x, this.dragPoint.y, this.dragOffset.x, (this.dragOffset.y) * (-1));

	}

	//
	//
	//

	@Override
	public void onClick() {
		super.onClick();
		((iEntity) this.dragged).onClick();
		this.dragged.dragLock(true);
		this.setDragPoint();


	}

	@Override
	public void onRelease() {
		super.onRelease();
		this.dragged.dragLock(false);

		this.setX(((Actor) this.dragged).getX()+this.cBuffer);
		this.setY(((Actor) this.dragged).getY() + ((Actor) this.dragged).getHeight() - this.getHeight());

		this.body.reposition(this.getX(), this.getY());

	}

	@Override
	public void buildBody() {
		this.body = new dRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
		this.hit = new uHit(this);
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

	@Override
	public void handleDisplayState() {
		if (this.hit.mouseOver == true && this.hit.selected == false)
			this.states.setState("MouseOver");
		else if (this.hit.mouseOver == false)
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

}
