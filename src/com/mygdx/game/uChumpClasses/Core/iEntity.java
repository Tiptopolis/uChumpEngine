package com.mygdx.game.uChumpClasses.Core;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.uChumpClasses.Core.Event.iEventCaster;
import com.mygdx.game.uChumpClasses.Core.Event.iEventObserver;
import com.mygdx.game.uChumpClasses.UI.iSpace;
import com.mygdx.game.uChumpClasses.UI.Drawable.iDrawable;
import com.mygdx.game.uChumpClasses.UI.Drawable.uDrawable;
import com.mygdx.game.uChumpClasses.UI.Heirarchy.iNode;
import com.mygdx.game.uChumpClasses.UI.Heirarchy.vNode;

public interface iEntity extends vNode, iEventObserver, iEventCaster{

	// a "physical" object

	
	public void draw(float deltaTime);
	public void drawT(float deltaTime);
	public void resize(int width, int height);
	public void reposition(int x, int y);	
	public void moveBy(float x, float y);
	public void dispose();
	
	public void show();
	public void hide();
	public void close();
	public void toFront();
	public void sortFront();	
	
	public Actor hit(float x, float y, boolean touchable);
	
	public void mouseEnter();
	public void mouseExit();
	public void onClick();
	public void onRelease();
	public void onDouble();
	public void outClick(iEntity entity);
	
	public void invalidateOver(Actor actor);
	
	public void handleDisplayState();
	public void buildDisplayStates();
	public void buildInputListener();
	public void buildBody();
	
	public iSpace getSpace();
	public iDrawable getBody();
	
}
