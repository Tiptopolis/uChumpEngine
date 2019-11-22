package com.mygdx.game.xApp.Screens.GameScreen;

import com.badlogic.gdx.graphics.Color;
import com.mygdx.game.uChumpClasses.Core.uEntity;
import com.mygdx.game.uChumpClasses.Core.Input.uHit;
import com.mygdx.game.uChumpClasses.Core.Utils.AppInjector;
import com.mygdx.game.uChumpClasses.UI.iLayer;
import com.mygdx.game.uChumpClasses.UI.Drawable.dEllipse;
import com.mygdx.game.uChumpClasses.UI.Drawable.dRect;

public class GameEntity extends uEntity {

	public GameHandler gameHandler;

	public GameEntity(iLayer layer, int x, int y) {
		super(layer, "GenericEntity");
		this.setPosition(x, y);
		this.setSize(32, 32);
		this.setBounds(this.getX(), this.getY(), this.getWidth(), this.getHeight());
		this.gameHandler = AppInjector.getInjector().getInstance(GameHandler.class);
		this.show();
		this.init();
	}

	@Override
	public void handleDisplayState() {
		if (this.hit.mouseOver == true && this.hit.selected == false)
			this.states.setState("MouseOver");
		else if (this.hit.mouseOver == false && this.hit.selected == true)
			this.states.setState("Selected");
		else if (this.hit.mouseOver == true && this.hit.selected == true)
			this.states.setState("SelectedOver");
		else
			this.states.setState("NULL");

		this.states.handleState();
	}

	@Override
	public void buildDisplayStates() {
		this.states.alterState("NULL", Color.WHITE, Color.GRAY, 1);
		this.states.addState("MouseOver", Color.WHITE, Color.DARK_GRAY, 2);
		this.states.addState("Selected", Color.WHITE, Color.YELLOW, 1);
		this.states.addState("SelectedOver", Color.WHITE, Color.YELLOW, 2);
	}

	@Override
	public void buildBody() {
		this.body = new dEllipse(this.getX(), this.getY(), this.getWidth(), this.getHeight());
		this.hit = new uHit(this);
	}

	@Override
	public void onClick() {
		super.onClick();
		this.screen.outClick(this);
		this.space.sortLayer(this.layer);
		if (!this.hit.selected) {
			this.gameHandler.select(this);
		}
		else if(this.hit.selected)
		{
			
		}
	}

}
