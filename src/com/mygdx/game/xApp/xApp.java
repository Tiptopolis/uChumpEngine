package com.mygdx.game.xApp;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.uChumpClasses.uApp;
import com.mygdx.game.uChumpClasses.Core.Event.uEvent;
import com.mygdx.game.uChumpClasses.Core.Event.EventLists.eEvent;
import com.mygdx.game.uChumpClasses.Core.Event.EventLists.eEvents;
import com.mygdx.game.uChumpClasses.UI.iScreen;
import com.mygdx.game.uChumpClasses.UI.Events.UiEvents;
import com.mygdx.game.xApp.Screens.BasicGrid1.uBasicGrid1Screen;
import com.mygdx.game.xApp.Screens.CalculatorScreen.uCalculatorScreen;
import com.mygdx.game.xApp.Screens.GameScreen.uGameScreen;
import com.mygdx.game.xApp.Screens.MainScreen.uMainScreen;
import com.mygdx.game.xApp.Screens.TestScreen.uTestScreen;

import static com.mygdx.game.uChumpClasses.uAppUtils.*;

import java.util.EnumSet;

public class xApp extends uApp {

	public uTestScreen TestScreen;
	public uMainScreen MainScreen;
	// public uGameScreen GameScreen;
	public iScreen lastCreatedScreen;

	public xApp() {
		super();
	}

	@Override
	public void create() {
		// float dT = Gdx.graphics.getDeltaTime();
		// println(dT);
		super.create();
		this.TestScreen = new uTestScreen(this);
		this.MainScreen = new uMainScreen(this);
		this.lastCreatedScreen = new uBasicGrid1Screen(this);
		this.lastCreatedScreen = new uCalculatorScreen(this);

		this.setScreen(TestScreen);

		UI.printDebug();
		println("");
		EnumSet.allOf(UiEvents.class).forEach(event -> eEvents.addNewEvent(event));
		// EnumSet.allOf(eEvents.class).forEach(event -> println(event.get()));
		println(eEvents.events());

		// dT = (Gdx.graphics.getDeltaTime() - dT);
		// println("CREATION TIME: " + dT);
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void dispose() {
		super.dispose();
	}

	@Override
	public boolean handleEvent(uEvent event) {
		boolean handled = false;
		super.handleEvent(event);
		switch (event.getEvent()) {

		case "BTN_CLICK":
			if (event.getCaster().getName() == "ScreenNext") {
				this.nextScreen();
				break;
			}

			if (event.getCaster().getName() == "ScreenLast") {
				this.prevScreen();
				break;
			}

			break;

		default:
			break;
		}

		return handled;
	}

}
