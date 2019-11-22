package com.mygdx.game.xApp.Screens.BasicGrid1;

import com.mygdx.game.uChumpClasses.UI.iSpace;
import com.mygdx.game.uChumpClasses.UI.uLayer;
import com.mygdx.game.uChumpClasses.UI.Util.eTextH;
import com.mygdx.game.uChumpClasses.UI.Util.eTextV;
import com.mygdx.game.uChumpClasses.UI.Widgets.uTextButton;

public class ControlLayer extends uLayer {

	public uTextButton lastCreatedButton;

	public ControlLayer(iSpace owner) {
		super(owner);
		this.setName("ControlLayer");
	}

	@Override
	public void init() {
		super.init();

		this.lastCreatedButton = new uTextButton(this, 16, 100, 64, 48);
		this.lastCreatedButton.setText("Toggle\nGrid");
		this.lastCreatedButton.setAllignment(eTextH.CENTER, eTextV.CENTER);//by line formatting
		this.lastCreatedButton.setMarginBuffer(4);
		this.lastCreatedButton.setFont("pkmnrsi");
	}

}
