package com.mygdx.game.uChumpClasses.Core.Input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import static com.mygdx.game.uChumpClasses.uAppUtils.*;

public class uKeyboard {

	// import static com.mygdx.game.uChumpClasses.Core.Input.uKeyboard.*;

	public static boolean up = false;
	public static boolean down = false;
	public static boolean left = false;
	public static boolean right = false;
	public static boolean shift = false;
	public static boolean ctrl = false;

	public static void update() {
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
			left = true;
		else
			left = false;
		
		//if(left)
			//println("LEFT PRESSED");

	}

}
