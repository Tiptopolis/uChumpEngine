package com.mygdx.game.uChumpClasses.Core.Utils.Textures;

import java.util.LinkedHashMap;
import java.util.Map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class uSpriteSheet {

	public uTextureReference link;
	public Texture texture;

	public int cellX, cellY;
	// public int[][] cellMap;
	public Map<Vector2, TextureRegion> cellMap = new LinkedHashMap<Vector2, TextureRegion>();

	public uSpriteSheet(uTextureReference link, int cellX, int cellY) {
		this.link = link;
		this.texture = link.texture;
		this.cellX = cellX;
		this.cellY = cellY;
		// this.cellMap = new int[this.texture.getWidth() /
		// cellX][this.texture.getHeight() / cellY];

		this.init();
	}

	public void init() {
		TextureRegion temp;
		Vector2 index;

		for (int x = 0; x < (this.texture.getWidth() / cellX); x++) {
			for (int y = 0; y < (this.texture.getWidth() / cellY); y++) {
				temp = new TextureRegion(this.texture, x * cellX, y * cellY);
				index = new Vector2(x * cellX, y * cellY);
				this.cellMap.put(index, temp);

			}
		}

	}

	public TextureRegion getCell(int x, int y) {
		Vector2 index = new Vector2(x, y);

		return this.cellMap.get(index);
	}
	
	

}
