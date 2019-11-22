package com.mygdx.game.uChumpClasses.UI.Widgets;

import com.mygdx.game.uChumpClasses.Core.Input.uHit;
import com.mygdx.game.uChumpClasses.Core.Utils.AppInjector;
import com.mygdx.game.uChumpClasses.Core.Utils.Textures.uTextureReference;
import com.mygdx.game.uChumpClasses.UI.iLayer;
import com.mygdx.game.uChumpClasses.UI.Drawable.dImageRect;
import com.mygdx.game.uChumpClasses.UI.Drawable.dRect;
import com.mygdx.game.uChumpClasses.UI.Heirarchy.UiOverseer;

import static com.mygdx.game.uChumpClasses.uAppUtils.*;

import com.badlogic.gdx.graphics.Color;

public class uImgButtonX extends uButton {

	public UiOverseer UI;
	public uTextureReference texture;

	public uImgButtonX(iLayer layer, int x, int y, int width, int height) {
		super(layer, x, y, width, height);
	}

	@Override
	public void init() {
		this.UI = AppInjector.getInjector().getInstance(UiOverseer.class);

		super.init();
	}

	@Override
	public void drawT(float deltaTime) {
		if (this.texture != null) {
			this.Sketcher.image(this.texture.texture, this.getX(), this.getY(), this.getWidth(), this.getHeight());
			
		}
	}
	
	@Override
	public void handleDisplayState() {
		if (this.hit.mouseOver == true && this.hit.selected == false)
			this.states.setState("MouseOver");
		else {
			this.states.setState("NULL");
		}

		this.states.handleState();
	}

	@Override
	public void buildDisplayStates() {
		this.states.alterState("NULL", Color.WHITE, Color.RED, 1);
		this.states.addState("MouseOver", Color.WHITE, Color.BLUE, 2);
	}

	@Override
	public void buildBody() {
		this.body = new dImageRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
		this.hit = new uHit(this);
	}

	public void setTexture(String get) {
		if (this.UI.textureAtlas.getTexture(get) != null) {

			this.texture = this.UI.textureAtlas.getTexture(get);
		}
	
	}	
	
}
