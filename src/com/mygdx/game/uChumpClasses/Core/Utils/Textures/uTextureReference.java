package com.mygdx.game.uChumpClasses.Core.Utils.Textures;

import static com.mygdx.game.uChumpClasses.uAppUtils.println;

import com.badlogic.gdx.graphics.Texture;

public class uTextureReference {

	public String name;
	public String namePath;
	public String ext;
	public String fullPath;
	public Texture texture;

	public uTextureReference(String name, String fullPath) {

		String temp = name;
		if (name.contains(".")) {
			temp = name.substring(0, name.lastIndexOf('.'));
			this.ext = name.substring(name.lastIndexOf(".") + 1);
		}

		this.name = temp; // no ext
		this.namePath = name; // ext
		this.fullPath = fullPath;

	}

	public void dispose()
	{
		this.texture.dispose();
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (!(o instanceof uTextureReference)) {
			return false;
		}
		
		uTextureReference r = (uTextureReference) o;
		
		return this.fullPath == r.fullPath;
	}

	@Override
	public String toString()
	{
		return this.name;
	}
	
}
