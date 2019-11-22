package com.mygdx.game.uChumpClasses.Core.Input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import static com.mygdx.game.uChumpClasses.uAppUtils.*;


public class uMouse {
	
	//import static com.mygdx.game.uChumpClasses.Core.Input.uMouse.*;

	public static float mouseX;
	public static float mouseY;
	public static Vector2 mousePos = new Vector2(0,0);
	
	public static float mousePrevX;
	public static float mousePrevY;
	public static Vector2 mousePrev = new Vector2(0,0);
	
	public static Vector2 mouseDelta = new Vector2(0,0);
	public static Vector2 mouseDeltaAbs = new Vector2(0,0);
	
	public static void update()
	{
		
		mousePrevX = mouseX;
		mousePrevY = mouseY;
		mouseX = Gdx.input.getX();
		mouseY = Height - Gdx.input.getY();
		
		mousePos.set(mouseX,mouseY);
		mousePrev.set(mousePrevX, mousePrevY);
		
		mouseDelta.set(mousePos.x-mousePrev.x, mousePos.y-mousePrev.y);
		mouseDeltaAbs.set(Math.abs(mouseDelta.x), Math.abs(mouseDelta.y));
		
	}
	
}
