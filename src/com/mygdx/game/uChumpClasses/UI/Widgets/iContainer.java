package com.mygdx.game.uChumpClasses.UI.Widgets;

import com.mygdx.game.uChumpClasses.UI.iSpace;
import com.mygdx.game.uChumpClasses.UI.iWidget;
import com.mygdx.game.uChumpClasses.UI.uWidget;

public interface iContainer extends iWidget, iSpace {
	
	
	public void addControl(uWidget control); //iControlWidget	
	public void buildControls();
	
	
	
	// add/get members/children

}
