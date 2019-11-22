package com.mygdx.game.uChumpClasses.Core.AppState;

import java.util.ArrayList;

import com.mygdx.game.uChumpClasses.UI.iScreen;

public class uAppScene {

	//app or game meta-state
	//analogous to Screen, and is assigned to one or more
	//where uScreen handles the UI of a scene, AppScene handles the "state" context
	//XCOM: Geoscape scene: Base Screen, Research Screen, Workshop Screen, Soldiers Screen, etc
	//all of these screens still progress the Geoscape scene's time
	
	//public ArrayList<uSceneState> States = new ArrayList<uSceneState>();
	public uAppOverseer Overseer;
	
	public eSceneStates State = eSceneStates.START;
	
	
	public uAppScene(uAppOverseer overseer)
	{
		this.Overseer = overseer;
	}
	
	 
	
	
}
