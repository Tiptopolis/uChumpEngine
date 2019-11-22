package com.mygdx.game.uChumpClasses.UI.Events;

import com.mygdx.game.uChumpClasses.Core.Event.EventLists.eEvent;

public enum UiEvents implements eEvent{

	VOID_CLICK("VOID_CLICK"), //click on open space
	BTN_CLICK("BTN_CLICK"); //click on any button type
	
	
	
	public String event;
	
	private UiEvents(String event)
	{
		this.event = event;
	}
	
	@Override
	public String get() {
		return this.event;
	}



}
