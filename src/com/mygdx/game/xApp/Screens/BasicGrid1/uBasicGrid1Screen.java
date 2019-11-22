package com.mygdx.game.xApp.Screens.BasicGrid1;

import com.mygdx.game.uChumpClasses.uApp;
import com.mygdx.game.uChumpClasses.Core.AppState.uAppOverseer;
import com.mygdx.game.uChumpClasses.Core.Utils.AppInjector;
import com.mygdx.game.uChumpClasses.UI.uScreen;
import com.mygdx.game.xApp.Screens.BasicGrid1.Imp.GridHandler;
import com.mygdx.game.xApp.Screens.TestScreen.DebugLayer;

public class uBasicGrid1Screen extends uScreen{

	public GameLayer gameLayer;
	public ControlLayer controlLayer;
	public DebugLayer debugLayer;

	public uAppOverseer gameOverseer;
	public static GridHandler gridHandler;//for testing purposes
	
	public uBasicGrid1Screen(uApp owner) {
		super(owner);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() {
		this.setName("GridScreen1");
		this.gameOverseer = AppInjector.getInjector().getInstance(uAppOverseer.class);

		this.gameLayer = new GameLayer(this);
		this.controlLayer = new ControlLayer(this);
		this.debugLayer = new DebugLayer(this);
	
	}
	
	@Override
	public void render(float deltaTime)
	{
		this.gameOverseer.update(deltaTime);
		super.render(deltaTime);
		
		
	}
	
}
