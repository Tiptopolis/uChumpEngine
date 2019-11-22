package com.mygdx.game.xApp.Screens.TestScreen;

import com.badlogic.gdx.graphics.Color;
import com.mygdx.game.uChumpClasses.UI.iSpace;
import com.mygdx.game.uChumpClasses.UI.uLayer;
import com.mygdx.game.uChumpClasses.UI.uWidget;
import com.mygdx.game.uChumpClasses.UI.Widgets.uButton;
import com.mygdx.game.uChumpClasses.UI.Widgets.uLabel;
import com.mygdx.game.uChumpClasses.UI.Widgets.uPanel;
import com.mygdx.game.uChumpClasses.UI.Widgets.uRect;
import com.mygdx.game.uChumpClasses.UI.Widgets.uTextButton;

import static com.mygdx.game.uChumpClasses.uAppUtils.*;

public class UiLayer extends uLayer {

	// Active, pop-up UI, Menus/Panels
	public uWidget lastCreatedWidget;
	public uRect lastCreatedRect;
	public uPanel lastCreatedPanel;
	public uButton lastCreatedButton;
	public uLabel lastCreatedLabel;

	public boolean preInitBS = true;

	public UiLayer(iSpace owner) {
		super(owner);
		this.setName("UiLayer");
	}

	public void init() {
		super.init();

		this.lastCreatedPanel = new uPanel(this, 100, 100, 200, 100);
		this.lastCreatedPanel.name = "Panel1";
		this.lastCreatedRect = new uRect(lastCreatedPanel.DefaultLayer, 0, 0, 32, 32);
		this.lastCreatedRect.name = "RED";
		this.lastCreatedRect.states.alterState("NULL", Color.RED, Color.BLACK);
		this.lastCreatedRect.states.alterState("MouseOver", Color.RED, Color.GRAY, 2);		

		this.lastCreatedRect = new uRect(lastCreatedPanel.DefaultLayer, 16, 16, 32, 32);
		this.lastCreatedRect.name = "GREEN";
		this.lastCreatedRect.states.alterState("NULL", Color.GREEN, Color.BLACK);
		this.lastCreatedRect.states.alterState("MouseOver", Color.GREEN, Color.GRAY, 2);

		this.lastCreatedRect = new uRect(lastCreatedPanel.DefaultLayer, 32, 32, 32, 32);
		this.lastCreatedRect.name = "BLUE";
		this.lastCreatedRect.states.alterState("NULL", Color.BLUE, Color.BLACK);
		this.lastCreatedRect.states.alterState("MouseOver", Color.BLUE, Color.GRAY, 2);
		// this.lastCreatedRect.test = true;

		this.lastCreatedPanel.setSelfDrag(true);
		this.lastCreatedPanel.show();// makes it visible

		// PANEL 2

		this.lastCreatedPanel = new uPanel(this, 150, 120, 200, 100);
		this.lastCreatedPanel.name = "Panel2";
		this.lastCreatedRect = new uRect(lastCreatedPanel.DefaultLayer, 0, 0, 32, 32);
		this.lastCreatedRect.name = "RED";
		this.lastCreatedRect.states.alterState("NULL", Color.RED, Color.BLACK);
		this.lastCreatedRect.states.alterState("MouseOver", Color.RED, Color.GRAY, 2);

		this.lastCreatedRect = new uRect(lastCreatedPanel.DefaultLayer, 16, 16, 32, 32);
		this.lastCreatedRect.name = "GREEN";
		this.lastCreatedRect.states.alterState("NULL", Color.GREEN, Color.BLACK);
		this.lastCreatedRect.states.alterState("MouseOver", Color.GREEN, Color.GRAY, 2);

		this.lastCreatedRect = new uRect(lastCreatedPanel.DefaultLayer, 32, 32, 32, 32);
		this.lastCreatedRect.name = "BLUE";
		this.lastCreatedRect.states.alterState("NULL", Color.BLUE, Color.BLACK);
		this.lastCreatedRect.states.alterState("MouseOver", Color.BLUE, Color.GRAY, 2);

		lastCreatedPanel.buildControls(); // << needs controlBuffer, dragging in wrong direction, clean up dragging
											// functionality

		this.lastCreatedPanel.show();
		this.screen.addPrehide(lastCreatedPanel);

		this.lastCreatedLabel = new uLabel(this, 64, 0, "TEXT HERE", false);
		this.lastCreatedLabel = new uLabel(this, 256, 0, "TEXT HERE", true);

	}

	@Override
	public void draw(float deltaTime) {

		super.draw(deltaTime);

	}

}
