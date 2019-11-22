package com.mygdx.game.xApp.Screens.TestScreen;

import com.mygdx.game.uChumpClasses.Core.Event.uEvent;
import com.mygdx.game.uChumpClasses.UI.iSpace;
import com.mygdx.game.uChumpClasses.UI.uLayer;
import com.mygdx.game.uChumpClasses.UI.Util.eMenuDirH;
import com.mygdx.game.uChumpClasses.UI.Util.eMenuDirV;
import com.mygdx.game.uChumpClasses.UI.Widgets.iText;
import com.mygdx.game.uChumpClasses.UI.Widgets.uButton;
import com.mygdx.game.uChumpClasses.UI.Widgets.uMenuBtn;
import com.mygdx.game.uChumpClasses.UI.Widgets.uRect;
import com.mygdx.game.uChumpClasses.UI.Widgets.uTextButton;

import static com.mygdx.game.uChumpClasses.uAppUtils.*;

import com.badlogic.gdx.graphics.Color;

public class ControlLayer extends uLayer {

	public uRect lastCreatedRect;
	public uButton lastCreatedButton;
	public iText lastCreatedText;
	public uMenuBtn lastCreatedMenu;

	public ControlLayer(iSpace owner) {
		super(owner);
		this.setName("ControlLayer");
	}

	public void init() {
		super.init();

		this.lastCreatedRect = new uRect(this, 32, (Height / 2) + 32, 32, 32);
		this.lastCreatedRect = new uRect(this, 48, (Height / 2) + 48, 32, 32);
		this.lastCreatedRect = new uRect(this, 64, (Height / 2) + 64, 32, 32);

		this.lastCreatedButton = new uButton(this, 32, 128, 32, 32);
		this.lastCreatedButton.setName("testButton");



		this.lastCreatedButton = new uTextButton(this, Width - 96, 128, 64, 32);
		this.lastCreatedButton.states.alterState("NULL", Color.WHITE, Color.GREEN, 1);
		this.lastCreatedButton.states.alterState("MouseOver", Color.WHITE, Color.GREEN, 2);		
		this.lastCreatedText = (iText) lastCreatedButton;
		this.lastCreatedText.setText("BUTTON");
		// this.lastCreatedText.setAllignmentH(eTextHorizontal.RIGHT);
		// this.lastCreatedText.setAllignmentV(eTextVertical.TOP);

		this.lastCreatedMenu = new uMenuBtn(this, 32, Height - 64, 64, 32, eMenuDirH.RIGHT, eMenuDirV.DOWN, "MENU");
		this.lastCreatedMenu.addItem("QUIT");
		this.lastCreatedMenu.addItem("Test2");
		this.lastCreatedMenu.addItem("Test3");
		this.lastCreatedMenu.addItem("Test4");
		this.lastCreatedMenu.addItem("Test5");
		
		this.lastCreatedMenu.refreshMenu();
	}

	@Override
	public boolean handleEvent(uEvent event) {
		boolean handled = false;

		switch (event.getEvent()) {

		default:
			break;
		}

		return handled;
	}

}
