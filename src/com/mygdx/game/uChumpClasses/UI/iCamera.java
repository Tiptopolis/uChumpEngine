package com.mygdx.game.uChumpClasses.UI;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.uChumpClasses.Core.iEntity;

public interface iCamera{

	public void resize(int width, int height);
	public void reposition(int x, int y);
	public void dispose();
	
	public iSpace getSpace();
	public Camera getCamera();
	public Viewport getViewport();
	public Vector2 getPosition();
	
	public float getViewportWidth();
	public float getViewportHeight();
	
	public void camOn();
	public void camOff();

	
}
