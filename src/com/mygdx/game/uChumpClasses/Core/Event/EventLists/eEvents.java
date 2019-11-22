package com.mygdx.game.uChumpClasses.Core.Event.EventLists;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

public enum eEvents implements eEvent {

	// probably 100% useless
	// use this to construct a tree diagram of valid events as research?

	SYSTEM_TICK("SYSTEM_TICK"),
	TEST1("TEST1"),
	TEST2("TEST2"),	
	TEST3("TEST3");

	private final String event;
	private int index;

	private eEvents(String event) {
		this.event = event;
		
	}

	@Override
	public String get() {
		return this.event;
	}

	private static Map<String, eEvent> map = new TreeMap<String, eEvent>();

	static {
		for (eEvent event : values()) {
			map.put(event.get(), event);
		}
	}

	public static eEvent eventFor(String event) {
		return map.get(event);
	}

	public static void addNewEvent(eEvent event) {
		if (!map.containsKey(event.get())) {			
			map.put(event.get(), event);
			
		}
	}
	
	public static Collection<eEvent> events()
	{
		return map.values();
	}

}
