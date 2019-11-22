package com.mygdx.game.xApp.Screens.GameScreen;

import java.util.ArrayList;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.google.inject.Singleton;
import com.mygdx.game.uChumpClasses.Core.Event.uDirector;
import com.mygdx.game.uChumpClasses.Core.Event.uEvent;
import com.mygdx.game.uChumpClasses.Core.Utils.AppInjector;
import com.mygdx.game.uChumpClasses.UI.Heirarchy.UiOverseer;
import com.mygdx.game.uChumpClasses.UI.Heirarchy.iNode;
import com.mygdx.game.uChumpClasses.UI.Heirarchy.uNode;
import com.mygdx.game.uChumpClasses.UI.Heirarchy.vNode;
import com.mygdx.game.uChumpClasses.UI.Widgets.uPanel;

import static com.mygdx.game.uChumpClasses.UI.Heirarchy.UiOverseer.*;
import static com.mygdx.game.uChumpClasses.uAppUtils.*;

@Singleton
public class GameHandler implements vNode {

	public uNode Node;

	ArrayList<GameEntity> Selection = new ArrayList<GameEntity>();
	// expand to states, states act like screens in that only 1 at a time is active
	

	public uDirector Director;
	public UiOverseer UI;


	public GameHandler(uPanel gamefield) {

		this.Node = new uNode(this);
		UI.addNode(this.Node);
		UI.addRoot(this.Node);
		
		this.Director = AppInjector.getInjector().getInstance(uDirector.class);
		this.Director.registerEventHandler(this);
		this.UI = AppInjector.getInjector().getInstance(UiOverseer.class);


	}

	public void update() {
		// println("GameHandler TICK");
		//println("+++");
		//println(this.Selection);
	}

	public void select(GameEntity entity) {
		// keyHandler
		this.clearSelection();
		this.Selection.add(entity);
		entity.hit.selected = true;
	}
	
	public void clearSelection()
	{
		for (GameEntity g : this.Selection) {
			g.hit.selected = false;
		}
		this.Selection.clear();
	}

	@Override
	public boolean handleEvent(uEvent event) {

		boolean handled = false;

		if (event.getEvent() == "VOID_CLICK" || event.getEvent() == "PANEL_VOID_CLICK") {
			println("VOID_CLICK");		
			this.clearSelection();
			handled = true;
		}

		return handled;

	}

	@Override
	public void castEvent(String event) {
		// TODO Auto-generated method stub

	}

	@Override
	public iNode getNode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void enter() {
		// TODO Auto-generated method stub

	}

	@Override
	public void exit() {
		// TODO Auto-generated method stub

	}

	@Override
	public void invalidateOver(Actor actor) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "GameRoot";
	}

	@Override
	public String getFullName() {
		// TODO Auto-generated method stub
		return "GameRoot";
	}

	@Override
	public void updateFullName() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub

	}

	@Override
	public void printDebug() {
		// TODO Auto-generated method stub

	}

}
