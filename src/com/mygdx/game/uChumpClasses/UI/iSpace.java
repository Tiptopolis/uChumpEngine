package com.mygdx.game.uChumpClasses.UI;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.uChumpClasses.Core.iEntity;
import com.mygdx.game.uChumpClasses.Core.iObject;
import com.mygdx.game.uChumpClasses.UI.Heirarchy.vNode;

public interface iSpace extends vNode{

	
	public void drawBuffer(float deltaTime);
	public void resetBuffer();
	
	public float getWidth();
	public float getHeight();
	public int getSpaceWidth();
	public int getSpaceHeight();
	
	public uScreen getScreen();
	public iCamera getCam();
	public void translateOffset();
	public Vector2 getOffset();
	public boolean mouseInside();
	
	public void invalidateOver(Actor actor);
	public void outClick(iEntity entity);
		
	public void addLayer(iLayer layer);
	public void removeLayer(String name);
	public void removeLayer(iLayer layer);
	public iLayer getLayer(String name);
	public ArrayList<iLayer> getLayers();
	public void sortLayer(iLayer layer);
	

	
	
}
