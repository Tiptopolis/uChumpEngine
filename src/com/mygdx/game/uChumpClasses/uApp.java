package com.mygdx.game.uChumpClasses;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.mygdx.game.uChumpClasses.Core.iObject;
import com.mygdx.game.uChumpClasses.Core.AppState.uAppOverseer;
import com.mygdx.game.uChumpClasses.Core.Event.iEventCaster;
import com.mygdx.game.uChumpClasses.Core.Event.iEventObserver;
import com.mygdx.game.uChumpClasses.Core.Event.uDirector;
import com.mygdx.game.uChumpClasses.Core.Event.uEvent;
import com.mygdx.game.uChumpClasses.Core.Input.uKeyboard;
import com.mygdx.game.uChumpClasses.Core.Input.uMouse;
import com.mygdx.game.uChumpClasses.Core.Utils.AppInjector;
import com.mygdx.game.uChumpClasses.UI.iScreen;
import com.mygdx.game.uChumpClasses.UI.Heirarchy.UiOverseer;
import com.mygdx.game.uChumpClasses.UI.Util.uSketcher;

import static com.mygdx.game.uChumpClasses.uAppUtils.*;
import static com.mygdx.game.uChumpClasses.Core.Input.uMouse.*;

public class uApp extends InputAdapter implements ApplicationListener, iEventObserver, iEventCaster, iObject {

	public static uApp instance;
	
	public iScreen screen;
	public ArrayList<iScreen> Screens = new ArrayList<iScreen>();

	public static uSketcher Sketcher;
	public static uMouse Mouse;
	public static uKeyboard Keyboard;	
	public static uDirector Director;
	
	public UiOverseer UI;
	public static uAppOverseer APP;
	
	public InputMultiplexer Multiplexer;//


	public float DeltaTime;

	public uApp() {
		this.Multiplexer = new InputMultiplexer();//
		this.Multiplexer.addProcessor(this);//
		instance = this;
	}

	@Override
	public void create() {
		Gdx.input.setInputProcessor(this.Multiplexer);//
		Sketcher = AppInjector.getInjector().getInstance(uSketcher.class);
		UI = AppInjector.getInjector().getInstance(UiOverseer.class);
		Director = AppInjector.getInjector().getInstance(uDirector.class);
		Mouse = new uMouse();
		Keyboard = new uKeyboard();
		Director.registerEventHandler(this);
		this.APP = AppInjector.getInjector().getInstance(uAppOverseer.class);

	}

	@Override
	public void render() {
		this.DeltaTime = Gdx.graphics.getDeltaTime();
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		Director.update();
		UI.update(DeltaTime);
		Mouse.update();

		if (this.screen != null) {
			screen.render(this.DeltaTime);
		}

		// this.castEvent("SYSTEM_TICK");

	}

	@Override
	public void pause() {
		if (screen != null)
			screen.pause();
	}

	@Override
	public void resume() {
		if (screen != null)
			screen.resume();
	}

	@Override
	public void resize(int width, int height) {
		if (screen != null)
			screen.resize(width, height);
	}

	public void dispose() {
		if (screen != null)
			screen.dispose();
		
		UI.dispose();

	}

	//
	// SCREEN
	//

	public void setScreen(iScreen screen) {

		if (this.screen != null)
			this.screen.hide();
		this.screen = screen;
		if (this.screen != null) {
			this.screen.show();
			this.screen.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
			this.screen.prehide();
		}
		UI.clearDirector();

		println("");
		println("CHANGING SCREEN TO: " + screen.getName());
		println("");
	}

	public iScreen getScreen() {
		return this.screen;
	}

	@Override
	public void castEvent(String event) {
		Director.sendEvent(event, this);
	}

	@Override
	public boolean handleEvent(uEvent event) {

		switch (event.getEvent()) {

		case"SYSTEM_EXIT":
			Gdx.app.exit();
			break;
		
		default:
			break;
		}

		return false;
	}

	//
	//
	//

	public void nextScreen() {
		int index = this.Screens.indexOf(this.screen);
		iScreen target = this.Screens.get(index);
		// println(index);

		if (index == (this.Screens.size() - 1)) {
			target = this.Screens.get(0);
			// println(index);
		} else if (index != (this.Screens.size() - 1)) {
			target = this.Screens.get(index + 1);
		}

		this.setScreen(target);
	}

	public void prevScreen() {
		int index = this.Screens.indexOf(this.screen);
		iScreen target;

		if (index != 0) {
			target = this.Screens.get(index - 1);

		} else if (index == 0) {
			target = this.Screens.get(this.Screens.size() - 1);
		} else
			target = this.screen;
		this.setScreen(target);

	}
	
	public static InputMultiplexer getMultiplexer()
	{
		return instance.Multiplexer;
	}

	//
	//
	//

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getFullName() {
		// TODO Auto-generated method stub
		return null;
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
