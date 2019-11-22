package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.uChumpClasses.uApp;
import com.mygdx.game.uChumpClasses.uAppUtils;
import com.mygdx.game.uChumpClasses.Core.Input.uKeyboard;
import com.mygdx.game.uChumpClasses.Core.Input.uMouse;
import com.mygdx.game.xApp.xApp;

import static com.mygdx.game.uChumpClasses.uAppUtils.*;
import static com.mygdx.game.uChumpClasses.Core.Input.uMouse.*;
import static com.mygdx.game.uChumpClasses.Core.Input.uKeyboard.*;

public class uChumpEngine extends ApplicationAdapter {

	public static uChumpEngine instance;
	public static uApp MainApp;

	@Override
	public void create() {
		instance = this;
		
		MainApp = new xApp();
		if (MainApp != null)
			MainApp.create();
	}

	@Override
	public void render() {
		uAppUtils.update();
		uMouse.update();
		uKeyboard.update();
		if (MainApp != null)
			MainApp.render();
	}
	
	@Override
	public void resize(int width, int height) {
		uAppUtils.resize();
		MainApp.resize(width, height);

	}

	@Override
	public void dispose() {
		
		if (MainApp != null)
		{			
			MainApp.dispose();
		}
		
	}
}
