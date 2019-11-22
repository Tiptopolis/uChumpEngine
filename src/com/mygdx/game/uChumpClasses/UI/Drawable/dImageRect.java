package com.mygdx.game.uChumpClasses.UI.Drawable;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.uChumpClasses.Core.iObject;
import com.mygdx.game.uChumpClasses.UI.Util.uSketcher;

import static com.mygdx.game.uChumpClasses.uAppUtils.*;

public class dImageRect extends uDrawable implements iDrawable {

	// public uAppearance Appearance;

	iObject owner;
	// public Texture texture = new
	// Texture(Gdx.files.internal("data/graphics/tiles/TileTemplate5.png"));
	public Texture texture;
	public TextureRegion render;
	// image file;
	// need to build a preloaded dictionary of images
	// pretty sure the problem ultimately lies here
	//
	// need TextureAtlas
	// loading new textures is a heavy-weight opperation
	// reusing them isnt

	public dImageRect(float x, float y, float width, float height/* , DrawLayer parent */) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		// this.parent = parent;

	}

	public dImageRect(int x, int y, int width, int height/* , DrawLayer parent */) {
		super();
		this.x = (float) x;
		this.y = (float) y;
		this.width = (float) width;
		this.height = (float) height;
		// this.parent = parent;

	}

	public dImageRect(Rectangle r) {
		super();
		this.x = r.x;
		this.y = r.y;
		this.width = r.width;
		this.height = r.height;

	}

	public dImageRect(dImageRect r) {
		super();
		this.x = r.x;
		this.y = r.y;
		this.width = r.width;
		this.height = r.height;

	}

	public dImageRect(Texture texture, float x, float y) {
		this.x = x;
		this.y = y;

		this.texture = texture;
		this.width = texture.getWidth();
		this.height = texture.getHeight();

	}

	public dImageRect(Texture texture, int x, int y) {
		this(texture, (float) x, (float) y);
	}

	public dImageRect(float x, float y) {
		this.x = x;
		this.y = y;
		this.width = this.texture.getWidth();
		this.height = this.texture.getHeight();
		this.texture = null; // still doesnt draw the image, massive slowdown
		// this.texture = new
		// Texture(Gdx.files.internal("data/graphics/tiles/TileTemplate5.png"));
		// still not loading the image
	}

	public void draw(uSketcher drawer) {

		// reserve this stuff for filters
		if (appearance.doTransColor == true)// elliminate this, source it to stroke & fill
			appearance.transColor();
		if (appearance.doTransFill == true)
			appearance.transFill();
		if (appearance.doTransStroke == true)
			appearance.transStroke();

		drawer.fill(appearance.uFill);
		drawer.stroke(appearance.uStroke);
		drawer.strokeWeight(appearance.uStrokeWeight);//

		if (appearance.doFill == false)
			drawer.noFill();
		if (appearance.doStroke == false)
			drawer.noStroke();

		// println(this.texture);//
		if (this.texture != null)
			drawer.image(this.texture, this.x, this.y);// maybe works?
		else
			drawer.rect((int) x, (int) y, (int) width, (int) height); // must be INT
	}

	public void resize(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public void reposition(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public boolean contains(float x, float y) {
		return this.x <= x && this.x + this.width >= x && this.y <= y && this.y + this.height >= y;
	}

	public void setImage(Texture texture) {
		this.texture = texture;
	}

	@Override
	public void dispose() {
		if (this.texture != null)
			this.texture.dispose();
		super.dispose();

	}

}
