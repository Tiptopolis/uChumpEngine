package com.mygdx.game.xApp.Screens.MainScreen;

import com.mygdx.game.uChumpClasses.uApp;
import com.mygdx.game.uChumpClasses.UI.uScreen;
import com.mygdx.game.xApp.Screens.TestScreen.DebugLayer;

public class uMainScreen extends uScreen{

	public DebugLayer debugLayer;

	
	public uMainScreen(uApp owner) {
		super(owner);
	}

	@Override
	public void init() {
		this.setName("MainScreen");
		
		this.debugLayer = new DebugLayer(this);


	}
	
}
