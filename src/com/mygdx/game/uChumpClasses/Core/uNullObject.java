package com.mygdx.game.uChumpClasses.Core;

import com.mygdx.game.uChumpClasses.Core.Event.uEvent;

public class uNullObject implements iObject {

	public String name = "NULL_OBJECT";
	
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "NULL_OBJECT";
	}

	@Override
	public void setName(String name) {
	}

	@Override
	public String getFullName() {
		// TODO Auto-generated method stub
		return "NULL_OBJECT";
	}

	@Override
	public void updateFullName() {
		// TODO Auto-generated method stub
	}

	@Override
	public void printDebug() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean handleEvent(uEvent event) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void castEvent(String event) {
		// TODO Auto-generated method stub
		
	}

}
