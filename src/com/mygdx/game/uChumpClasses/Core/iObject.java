package com.mygdx.game.uChumpClasses.Core;

import com.mygdx.game.uChumpClasses.Core.Event.iEventCaster;
import com.mygdx.game.uChumpClasses.Core.Event.iEventObserver;

public interface iObject extends iEventObserver, iEventCaster{

	
	
	
	
	
	public String getName();
	public String getFullName();
	public void updateFullName();
	public void setName(String name);
	
	public void printDebug();
	
}
