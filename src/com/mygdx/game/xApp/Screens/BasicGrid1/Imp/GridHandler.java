package com.mygdx.game.xApp.Screens.BasicGrid1.Imp;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Input.Keys;
import com.mygdx.game.uChumpClasses.UI.Widgets.uPanel;

import static com.mygdx.game.uChumpClasses.uApp.*;
import static com.mygdx.game.uChumpClasses.uAppUtils.*;


public class GridHandler extends InputAdapter{

	
	
	
	public GridHandler(uPanel field)
	{
		
	}
	
	public void update()
	{
		//println("GAME UPDATE TICK");
	}
	
	//INPUT
	
	@Override
	public boolean keyDown(int keycode) {
		boolean handled = false;

		switch (keycode) {
		case Keys.DPAD_UP:
			Keyboard.up = true;
			handled = true;
			break;

		case Keys.DPAD_DOWN:
			Keyboard.down = true;
			handled = true;
			break;
		case Keys.DPAD_LEFT:
			Keyboard.left = true;
			handled = true;
			break;

		case Keys.DPAD_RIGHT:
			Keyboard.right = true;
			handled = true;
			break;

		case Keys.SHIFT_LEFT:
		case Keys.SHIFT_RIGHT:
			Keyboard.shift = true;
			handled = true;
			break;
		}

		return handled;
	}

	@Override
	public boolean keyUp(int keycode) {
		boolean handled = false;

		switch (keycode) {
		case Keys.DPAD_UP:
			Keyboard.up = false;
			handled = true;
			break;

		case Keys.DPAD_DOWN:
			Keyboard.down = false;
			handled = true;
			break;
		case Keys.DPAD_LEFT:
			Keyboard.left = false;
			handled = true;
			break;

		case Keys.DPAD_RIGHT:
			Keyboard.right = false;
			handled = true;
			break;
		case Keys.SHIFT_LEFT:
		case Keys.SHIFT_RIGHT:
			Keyboard.shift = false;
			handled = true;
			break;
		}

		return handled;
	}
}
