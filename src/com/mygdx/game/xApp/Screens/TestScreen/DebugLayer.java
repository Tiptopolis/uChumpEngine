package com.mygdx.game.xApp.Screens.TestScreen;

import static com.mygdx.game.uChumpClasses.uAppUtils.Width;

import com.mygdx.game.uChumpClasses.UI.iSpace;
import com.mygdx.game.uChumpClasses.UI.uLayer;
import com.mygdx.game.uChumpClasses.UI.Widgets.uButton;

public class DebugLayer extends uLayer {

	public uButton lastCreatedButton;

	public DebugLayer(iSpace owner) {
		super(owner);
		this.setName("DebugLayer");	}

	@Override
	public void init() {
		super.init();
		this.lastCreatedButton = new uButton(this, 32, 32, 32, 32);
		this.lastCreatedButton.setName("ScreenLast");

		this.lastCreatedButton = new uButton(this, Width - 64, 32, 32, 32);
		this.lastCreatedButton.setName("ScreenNext");
	}

}
