package com.mygdx.game.uChumpClasses.UI;

import java.util.ArrayList;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.uChumpClasses.Core.iEntity;
import com.mygdx.game.uChumpClasses.Core.iObject;
import com.mygdx.game.uChumpClasses.Core.uEntity;
import com.mygdx.game.uChumpClasses.Core.Event.iEventObserver;
import com.mygdx.game.uChumpClasses.UI.Heirarchy.vNode;

public interface iLayer extends InputProcessor, vNode, iEventObserver{

	public void init();
	public void draw(float deltaTime);
	public void resize(int width, int height);
	public void dispose();
	
	public void invalidateOver(Actor actor);
	public void outClick(iEntity entity);
	
	public iSpace getOwner();
	
	public void addMember(iEntity entity);
	public void removeMember(iEntity entity);
	public void removeMember(String name);
	public iEntity getMember(String name);
	public ArrayList<iEntity> getMembers();
	
	public void staticSortFront();
	public void staticSortRear();
	
	
	
	
	
}
