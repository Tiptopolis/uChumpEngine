package com.mygdx.game.xApp.Screens.TestScreen;

import com.mygdx.game.uChumpClasses.uApp;
import com.mygdx.game.uChumpClasses.Core.iEntity;
import com.mygdx.game.uChumpClasses.Core.Event.uEvent;
import com.mygdx.game.uChumpClasses.UI.uScreen;
import com.mygdx.game.uChumpClasses.UI.Heirarchy.iNode;

import static com.mygdx.game.uChumpClasses.uAppUtils.*;

import java.util.ArrayList;


public class uTestScreen extends uScreen {

	public UiLayer uiLayer;
	public ControlLayer controlLayer;
	public DebugLayer debugLayer;

	public uTestScreen(uApp owner) {
		super(owner);
		
	}

	@Override
	public void init() {
		this.setName("TestScreen");
		
		this.uiLayer = new UiLayer(this);
		this.controlLayer = new ControlLayer(this);
		this.debugLayer = new DebugLayer(this);
	
	}
	


}
