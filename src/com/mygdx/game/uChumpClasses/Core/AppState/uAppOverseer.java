package com.mygdx.game.uChumpClasses.Core.AppState;

import com.google.inject.Singleton;
import com.mygdx.game.uChumpClasses.uApp;
import com.mygdx.game.uChumpClasses.UI.iSpace;


import static com.mygdx.game.uChumpClasses.uAppUtils.*;

import com.badlogic.gdx.InputAdapter;


@Singleton
public class uAppOverseer extends InputAdapter{

	public uApp App;
		
	public float startTime;
	//gamestates
	//current gamestate
	//{ControlContext}

	
	public uAppOverseer()
	{
		this.App = uApp.instance;
	
	}
	
	public void update(float deltaTime)
	{
		//println(deltaTime);
		//println(this.App);
	}

	

	
}
