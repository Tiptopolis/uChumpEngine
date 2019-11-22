package com.mygdx.game.uChumpClasses.Core;

import java.util.ArrayList;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.mygdx.game.uChumpClasses.Core.Event.uDirector;
import com.mygdx.game.uChumpClasses.Core.Event.uEvent;
import com.mygdx.game.uChumpClasses.Core.Input.uHit;
import com.mygdx.game.uChumpClasses.Core.Input.uInputListener;
import com.mygdx.game.uChumpClasses.Core.Utils.AppInjector;
import com.mygdx.game.uChumpClasses.UI.iLayer;
import com.mygdx.game.uChumpClasses.UI.iSpace;
import com.mygdx.game.uChumpClasses.UI.uScreen;
import com.mygdx.game.uChumpClasses.UI.DisplayStates.DisplayStates;
import com.mygdx.game.uChumpClasses.UI.Drawable.iDrawable;
import com.mygdx.game.uChumpClasses.UI.Drawable.uDrawable;
import com.mygdx.game.uChumpClasses.UI.Heirarchy.iNode;
import com.mygdx.game.uChumpClasses.UI.Heirarchy.uNode;
import com.mygdx.game.uChumpClasses.UI.Util.uSketcher;

import static com.mygdx.game.uChumpClasses.uAppUtils.*;

public class uEntity extends Actor implements iEntity {

	public String name = "";
	public String fullName = "";

	public uNode Node;

	public iSpace space;
	public iLayer layer;
	public uScreen screen;

	public uDrawable body;
	public DisplayStates states;

	public uInputListener listener;
	public uHit hit;

	protected uSketcher Sketcher;
	protected uDirector Director;

	public uEntity(iLayer layer, String name) {

		this.name = name;
		this.layer = layer;
		this.space = layer.getOwner();
		this.screen = space.getScreen();
		this.Node = new uNode(this);

		this.states = new DisplayStates(this);

		this.Sketcher = AppInjector.getInjector().getInstance(uSketcher.class);
		this.Director = AppInjector.getInjector().getInstance(uDirector.class);

		this.screen.addActor(this);
		this.layer.addMember(this);

	}

	public void init() {
		this.buildBody();
		this.buildDisplayStates();
		this.buildInputListener();
		this.updateFullName();
		this.Director.registerEventHandler(this);

	}

	@Override
	public void draw(float deltaTime) {
		// body.appearance.setFill(Color.RED); //works, setup displayStates
		if (this.body != null && this.Node.isActive()) {
			this.handleDisplayState();
			this.body.draw(Sketcher);
		}
		this.updateFullName();
		

	}

	@Override
	public void drawT(float deltaTime) {
		// TODO Auto-generated method stub

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setSize(float width, float height) {
		super.setSize(width, height);
		if (this.body != null)
			this.body.resize((int) width, (int) height);
	}

	@Override
	public void reposition(int x, int y) {
		this.setX(x);
		this.setY(y);
		if (this.body != null)
			this.body.reposition(x, y);
	}

	@Override
	public void setPosition(float x, float y) {
		super.setPosition(x, y);
		if (this.body != null)
			this.body.reposition(x, y);
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		this.enter();

	}

	@Override
	public void hide() {
		this.exit();

		// eventRouter

	}

	@Override
	public void close() {
		this.Director.deregisterEventHandler(this);
	}

	@Override
	public void toFront() {
		super.toFront();
	}

	@Override
	public void sortFront() {

	}

	//
	// INPUT
	//

	@Override
	public Actor hit(float x, float y, boolean touchable) {
		if (touchable && this.getTouchable() != Touchable.enabled)
			return null;
		if (!isVisible())
			return null;
		if (!this.Node.isActive)
			return null;
		if (body == null)
			return null;
		if (this.hit == null)
			return null;

		return (Actor) this.hit.hit(x, y, touchable); //
	}

	@Override
	public void mouseEnter() {
		
		this.hit.mouseOver = true;
	}

	@Override
	public void mouseExit() {
		this.hit.mouseOver = false;

	}

	@Override
	public void onClick() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onDouble() {

	}

	@Override
	public void onRelease() {
		// TODO Auto-generated method stub

	}

	@Override
	public void outClick(iEntity entity) {

	}

	@Override
	public iNode getNode() {
		// TODO Auto-generated method stub
		return this.Node;
	}

	@Override
	public void enter() {
		this.Node.isActive = true;
		this.Director.registerEventHandler(this);
		if (this.listener != null)
			this.listener.active = true;
	}

	@Override
	public void exit() {
		this.Node.isActive = false;
		if (this.listener != null)
			this.listener.active = false;

	}

	@Override
	public void invalidateOver(Actor actor) {

	}

	//
	//
	//

	@Override
	public void handleDisplayState() {
		// TODO Auto-generated method stub

	}

	@Override
	public void buildDisplayStates() {
		// TODO Auto-generated method stub

	}

	@Override
	public void buildInputListener() {
		this.listener = new /* ClickListener() */ uInputListener(this) {
			public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
				super.enter(event, x, y, pointer, fromActor);
				if (isOver() && isActive())
					mouseEnter();
			}

			public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor) {
				super.exit(event, x, y, pointer, fromActor);
				if (isOver() == false && isActive())
					mouseExit();

			}

			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				if (super.touchDown(event, x, y, pointer, button) == true && isActive()) {
					onClick();
					return true;
				} else
					return false;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

				super.touchUp(event, x, y, pointer, button);
				onRelease();

			}

			public void clicked(InputEvent event, float x, float y) {
				if (getTapCount() > 1 && isActive()) {
					// println(getName() + " DoubleClick");
					onDouble();
				}
			}

		};
		this.addListener(listener);
	}

	@Override
	public void buildBody() {
		// TODO Auto-generated method stub

	}

	@Override
	public iSpace getSpace() {
		return this.space;
	}

	@Override
	public iDrawable getBody() {
		return this.body;
	}

	//
	// EVENTS
	//

	@Override
	public boolean handleEvent(uEvent event) {
		return false;
	}

	@Override
	public void castEvent(String event) {
		this.updateFullName();
		this.Director.sendEvent(event, this);
		// println(event);
	}

	//
	// iOBJECT
	//

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String getFullName() {
		this.updateFullName();

		return this.fullName;
	}

	@Override
	public void updateFullName() {
		String temp = "";
		String s = ".";
		ArrayList<String> tmp = new ArrayList<String>();
		iNode subject = this.Node;
		// int count = 0;
		boolean rootReached = false;

		while (rootReached == false) {
			if (subject.isChild()) {
				subject = subject.getParentNode();
				tmp.add(subject.getName());

				// count++;
			} else
				tmp.add(subject.getName());
			rootReached = true;
		}

		for (int i = tmp.size() - 1; i >= 0; i--) {
			temp = (tmp.get(i) + s);
		}

		this.fullName = temp + this.getName();
	}

	@Override
	public void printDebug() {
		String prefixLine = " ";
		String line = "";
		for (int i = 0; i < this.Node.getDepth(); i++) {
			line += prefixLine;
		}

		String g = line + "-" + this.name;
		int p = g.length();
		String x = " ";
		String n = "";
		for (int i = p; i < 25; i++) {
			n = n + x;
		}

		println(line + "-" + this.name + n + "[DEPTH: " + this.Node.getDepth() + "] : {Z: " + this.getZIndex() + "}");
	}

}
