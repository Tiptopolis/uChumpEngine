package com.mygdx.game.uChumpClasses.UI.Widgets;

import com.mygdx.game.uChumpClasses.Core.Utils.AppInjector;
import com.mygdx.game.uChumpClasses.Core.Utils.Textures.uTextureReference;
import com.mygdx.game.uChumpClasses.UI.iLayer;
import com.mygdx.game.uChumpClasses.UI.Heirarchy.UiOverseer;

import static com.mygdx.game.uChumpClasses.uAppUtils.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class uImgButton extends uButton {

	public UiOverseer UI;
	public uTextureReference reference;
	public Texture texture;
	public TextureRegion region;

	public uImgButton(iLayer layer, int x, int y, int width, int height) {
		super(layer, x, y, width, height);
	}

	@Override
	public void init() {
		this.UI = AppInjector.getInjector().getInstance(UiOverseer.class);
		this.region = new TextureRegion();
		super.init();
	}

	@Override
	public void drawT(float deltaTime) {
		if (this.reference != null) {
			this.Sketcher.image(this.texture, this.getX(), this.getY(), this.getWidth(), this.getHeight());
			// this.Sketcher.batch.draw(this.region, this.getX(), this.getY(),
			// this.getWidth()+64, this.getHeight()+64);

			// this.Sketcher.image(this.texture, this.getX(), this.getY(), this.getWidth(),
			// this.getHeight());
			// this.Sketcher.batch.draw(this.region, this.getX(), this.getY(),
			// this.getWidth(), this.getHeight());

		}
	}

	public void setTexture(String get) {

		if (this.UI.textureAtlas.getTexture(get) != null) {
			this.reference = this.UI.textureAtlas.getTexture(get);
			// println("<<: "+ reference.name);
			// println("<<: "+ reference.namePath);
			// println("<<: "+ reference.fullPath);

			this.texture = new Texture(Gdx.files.internal(reference.fullPath));

			// this.texture = new Texture(Gdx.files.internal("badlogic.jpg"));//debug, works
			// Texture(Gdx.files.internal("data/graphics/tiles/TileTemplate5.png"));//debug, works
		}

	}

}
