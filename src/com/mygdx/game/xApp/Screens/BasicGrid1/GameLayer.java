package com.mygdx.game.xApp.Screens.BasicGrid1;

import com.mygdx.game.uChumpClasses.Core.AppState.uAppOverseer;
import com.mygdx.game.uChumpClasses.Core.Utils.AppInjector;
import com.mygdx.game.uChumpClasses.UI.iSpace;
import com.mygdx.game.uChumpClasses.UI.uLayer;
import com.mygdx.game.uChumpClasses.UI.Widgets.uImgButton;
import com.mygdx.game.uChumpClasses.UI.Widgets.uPanel;
import com.mygdx.game.xApp.Screens.BasicGrid1.Imp.GridHandler;

import static com.mygdx.game.uChumpClasses.uApp.*;
import static com.mygdx.game.uChumpClasses.uAppUtils.*;

public class GameLayer extends uLayer {

	public uAppOverseer Overseer;

	public uPanel GameSpace;
	public uPanel testPanel;
	public uImgButton testButton;
	public GridHandler gridHandler; //only in layer for testing

	public GameLayer(iSpace owner) {
		super(owner);
		this.setName("GameLayer");

	}

	public void init() {
		super.init();

		//UI.textureAtlas.linkPath("map/", true);
		//UI.textureAtlas.loadFiles("map/");

		this.Overseer = AppInjector.getInjector().getInstance(uAppOverseer.class);
		this.GameSpace = new uPanel(this, 0, 0, 300, 300) {
			@Override
			public void onClick() {
				super.onClick();
				this.space.sortLayer(this.layer);
				this.castEvent("PANEL_VOID_CLICK");
				println("GAME_VOID_CLICK");
			}
		};
		this.GameSpace.setName("GameSpace");
		this.GameSpace.setPosition(0 + (this.screen.getWidth() / 2) - (GameSpace.getWidth() / 2),
				0 + (this.screen.getHeight() / 2) - (GameSpace.getHeight() / 2));
		this.GameSpace.states.clear();

		this.testPanel = new uPanel(this.GameSpace.DefaultLayer, 32, 32, 64, 64);
		this.testPanel.setSelfDrag(true);

		this.testButton = new uImgButton(this.GameSpace.DefaultLayer, 32, 32, 32, 32);
		this.testButton.setTexture("TileTemplate5");		
		this.testButton.isStatic = true;			

		this.gridHandler = new GridHandler(this.GameSpace);
	}
	
	@Override
	public void draw(float deltaTime)
	{
		super.draw(deltaTime);
		this.gridHandler.update();
		
	}

}
