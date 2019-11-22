package com.mygdx.game.uChumpClasses.UI;

import java.util.ArrayList;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.mygdx.game.uChumpClasses.Core.iEntity;
import com.mygdx.game.uChumpClasses.Core.uEntity;
import com.mygdx.game.uChumpClasses.Core.Event.uDirector;
import com.mygdx.game.uChumpClasses.Core.Event.uEvent;
import com.mygdx.game.uChumpClasses.Core.Utils.AppInjector;
import com.mygdx.game.uChumpClasses.UI.Heirarchy.UiOverseer;
import com.mygdx.game.uChumpClasses.UI.Heirarchy.iNode;
import com.mygdx.game.uChumpClasses.UI.Heirarchy.uNode;
import com.mygdx.game.uChumpClasses.UI.Util.uSketcher;
import com.mygdx.game.uChumpClasses.UI.Widgets.iMenu;

import static com.mygdx.game.uChumpClasses.UI.Heirarchy.UiOverseer.*;
import static com.mygdx.game.uChumpClasses.uApp.*;
import static com.mygdx.game.uChumpClasses.uAppUtils.*;
import static com.mygdx.game.uChumpClasses.Core.Input.uMouse.*;

public class uLayer extends Group implements iLayer {

	public String name = "New uLayer";
	public String fullName = "";

	public iSpace owner;
	public iScreen screen;
	public uNode Node;

	public ArrayList<iEntity> Members = new ArrayList<iEntity>();

	public uSketcher Sketcher;
	public uDirector Director;
	public UiOverseer UI;


	public uLayer(iSpace owner) {
		this.owner = owner;
		this.screen = owner.getScreen();
		this.Node = new uNode(this);
		this.owner.addLayer(this);
		

		this.Sketcher = AppInjector.getInjector().getInstance(uSketcher.class);
		this.Director = AppInjector.getInjector().getInstance(uDirector.class);
		this.UI = AppInjector.getInjector().getInstance(UiOverseer.class);

		this.UI.addNode(this.Node);
		
		this.init();
	}

	@Override
	public void init() {
	}

	@Override
	public void draw(float deltaTime) {

		this.staticSortRear();
		this.staticSortFront();

		Sketcher.end();

		for (iEntity e : this.Members) {
			Sketcher.beginSketch();
			e.draw(deltaTime);
			Sketcher.endSketch();
			Sketcher.beginBatch();
			e.drawT(deltaTime);
			Sketcher.endBatch();

		}

	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void dispose() {
		for (iEntity e : this.Members) {
			e.dispose();
		}
		this.Members.clear();
	}

	@Override
	public void enter() {
		this.Node.isActive = true;
		for (iEntity e : this.Members) {
			e.enter();
		}
		this.Director.registerEventHandler(this);

	}

	@Override
	public void exit() {
		this.Node.isActive = false;
		for (iEntity e : this.Members) {
			e.exit();
		}
		// this.Director.deregisterEventHandler(this); //causing crash on screen change,
		// need to figure out how to clear out current registered event handlers

	}

	@Override
	public void invalidateOver(Actor actor) {
		if (actor instanceof iEntity || actor instanceof Group) {
			iEntity temp = (iEntity) actor;
			for (iEntity w : this.Members) {

				if (w != temp) {
					w.mouseExit();

				}
			}

		}
	}

	@Override
	public void outClick(iEntity entity) {
		if (entity != null) {
			//println(entity.getName() + " OutClick");
		}
		for (iEntity w : this.Members) {
			w.outClick(entity);
		}
	}

	//
	// Static Member Sort
	//

	@Override
	public void staticSortFront() {
		iWidget temp;
		ArrayList<iWidget> tmp = new ArrayList<iWidget>();

		for (iEntity e : this.Members) {

			if (e instanceof iWidget) {
				temp = (iWidget) e;
				if (temp.isStatic() && temp.getStaticType()) {
					// println(e.getName());
					tmp.add(temp);
				}
			}
		}

		for (iWidget w : tmp) {
			 w.sortFront();
			//this.removeMember((iEntity) w);
			//this.addMember((iEntity) w);
			//w.toFront();
		}

		tmp.clear();
	}

	@Override
	public void staticSortRear() {
		iWidget temp;
		ArrayList<iWidget> tmp = new ArrayList<iWidget>();
		for(iEntity e : this.Members)
		{
			if (e instanceof iWidget) {
				temp = (iWidget) e;
				if (!temp.isStatic()) {
					// println(e.getName());
					tmp.add(temp);
				}
			}
		}
		for (iWidget w : tmp) {
			//((iEntity) w).sortFront();
			//this.removeMember((iEntity) w);
			//this.addMember((iEntity) w);
			//w.toFront();
			w.sortFront();
		}

		tmp.clear();
		
	}

	//
	// INPUT
	//

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

	//
	// MEMBERS
	//

	@Override
	public iSpace getOwner() {
		return this.owner;
	}

	@Override
	public void addMember(iEntity entity) {
		this.Members.add(entity);
		this.Node.addChild(entity.getNode());
	}

	@Override
	public void removeMember(iEntity entity) {
		this.Members.remove(entity);
		this.Node.removeChild(entity.getNode());
	}

	@Override
	public void removeMember(String name) {
		if (this.getMember(name) != null) {
			this.removeMember(this.getMember(name));
		}
	}

	@Override
	public iEntity getMember(String name) {
		for (iEntity e : this.Members) {
			if (e.getName() == name) {
				return e;
			}
		}
		return null;
	}

	@Override
	public ArrayList<iEntity> getMembers() {
		return this.Members;
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
	public iNode getNode() {
		return this.Node;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
		this.updateFullName();
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

		String g = (line + "-[" + this.name + "]");
		int p = g.length();
		String x = "_";
		String n = "";
		for (int i = p; i < 25; i++) {
			n = n + x;
		}

		println(line + "-[" + this.name + "]" + n + "[DEPTH: " + this.Node.getDepth() + "]");

	}

}
