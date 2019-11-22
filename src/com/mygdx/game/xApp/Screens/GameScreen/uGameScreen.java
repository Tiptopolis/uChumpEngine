package com.mygdx.game.xApp.Screens.GameScreen;

import com.mygdx.game.uChumpClasses.uApp;
import com.mygdx.game.uChumpClasses.Core.Utils.AppInjector;
import com.mygdx.game.uChumpClasses.UI.uScreen;
import com.mygdx.game.uChumpClasses.UI.Util.uFrameBufferHandler;
import com.mygdx.game.xApp.Screens.TestScreen.DebugLayer;


public class uGameScreen extends uScreen {

	public GameLayer gameLayer;
	public DebugLayer debugLayer;
	
	public GameHandler gameHandler;
	
	
	public uGameScreen(uApp owner) {
		super(owner);
	}
	
	@Override
	public void init() {
		this.setName("GameScreen");
		
		this.debugLayer = new DebugLayer(this);
		this.gameLayer = new GameLayer(this);		
		this.gameHandler = AppInjector.getInjector().getInstance(GameHandler.class);
		
		
		
		
		
	}
	
	@Override
	public void render(float delta)
	{
		gameHandler.update();
		super.render(delta);
	}
}
