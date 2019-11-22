package com.mygdx.game.xApp.Screens.CalculatorScreen;

import com.mygdx.game.uChumpClasses.uApp;
import com.mygdx.game.uChumpClasses.UI.uScreen;
import com.mygdx.game.xApp.Screens.TestScreen.DebugLayer;

import static com.mygdx.game.uChumpClasses.uAppUtils.*;

public class uCalculatorScreen extends uScreen {

	public DebugLayer debugLayer;
	public UiLayer uiLayer;

	public uCalculatorScreen(uApp owner) {
		super(owner);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() {
		this.setName("Calculator");
		this.uiLayer = new UiLayer(this);
		this.debugLayer = new DebugLayer(this);

	}

}
