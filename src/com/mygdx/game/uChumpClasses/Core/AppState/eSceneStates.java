package com.mygdx.game.uChumpClasses.Core.AppState;

public enum eSceneStates implements iSceneStates{

	START,//initiate opening procedures,,no draw
	RUN, //time keeps going, will perform a "TICK",,draw	
	HOLD,// time keeps going, will not perform a "TICK",,draw
	PAUSE,//time does not keep going, will not perform a "TICK",,draw
	BACKGROUND,//time keeps going, will perform a "TICK",,no draw
	STOP;//execute closing procedures,,no draw	
}
