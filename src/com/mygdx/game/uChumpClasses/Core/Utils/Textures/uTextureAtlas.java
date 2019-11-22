package com.mygdx.game.uChumpClasses.Core.Utils.Textures;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;

import static com.mygdx.game.uChumpClasses.uAppUtils.*;

public class uTextureAtlas {

	// .pngs only for now

	public String rootDefault = "./bin/default/";
	public String rootPath = "data/graphics/";

	public ArrayList<String> SubPaths = new ArrayList<String>();// use this to add on folders to pull image files from
	public ArrayList<uTextureReference> ReferenceList = new ArrayList<uTextureReference>();
	//public ArrayList<uSpriteSheet> SpriteList = new ArrayList<uSpriteSheet>();

	public uTextureAtlas() {
		this.linkPath("sprites/", false);
		this.linkPath("tiles/", false);
		this.loadFiles();
		println("------TEXTURES LOADED-------");
		for (uTextureReference t : this.ReferenceList) {
			println(t.name);
		}

	}

	public void loadFiles() {
		println("=====LOADING TEXTURES=====");
		this.ReferenceList.clear();
		for (String path : this.SubPaths) {
			HashMap<String, String> rawMap = (HashMap<String, String>) this.mapFiles(path);
			for (Map.Entry<String, String> entry : rawMap.entrySet()) {
				Texture newT = new Texture(Gdx.files.internal(entry.getValue()));
				uTextureReference newLink = new uTextureReference(entry.getKey(), entry.getValue());
				newLink.texture = newT;
				this.ReferenceList.add(newLink);
				println(">" + newLink.name);
				println(">>" + entry.getValue());
				newT.dispose();
			}

		}

	}

	public void loadFiles(String path) {

		println("=====LOADING TEXTURES=====");

		HashMap<String, String> rawMap = (HashMap<String, String>) this.mapFiles(this.rootPath + path);

		for (Map.Entry<String, String> entry : rawMap.entrySet()) {

			Texture newT = new Texture(Gdx.files.internal(entry.getValue()));
			uTextureReference newLink = new uTextureReference(entry.getKey(), entry.getValue());
			if (this.checkDuplicates(newLink)) {
				this.ReferenceList.add(newLink);
				newLink.texture = newT;
				newT.dispose();
				println(">" + newLink.name);
				println(">>" + entry.getValue());
			} else {
				newLink.dispose();
			}

		}

	}

	private Map<String, String> mapFiles(String directory) {
		FileHandle dir = Gdx.files.internal(this.rootDefault + directory);// FKN WORKS!!!
		Map<String, String> getFiles = new HashMap<String, String>();

		for (FileHandle t : dir.list()) {
			String actFile = (directory + t.file().getName());

			if (actFile.toLowerCase().endsWith(".png")) {
				getFiles.put(t.file().getName(), actFile); // remove .ext from entry?
			}
		}

		return getFiles;
	}

	public void dispose() {
		for (uTextureReference r : this.ReferenceList) {

			r.dispose();
		}
	}

	public boolean checkDuplicates(uTextureReference ref) {
		if (this.ReferenceList.contains(ref)) {
			return false;
		}

		for (uTextureReference r : this.ReferenceList) {
			if (r.equals(ref)) {
				return false;
			}
		}

		return true;
	}

	public void linkPath(String path, boolean reload) {
		this.SubPaths.add(this.rootPath + path);

		if (reload) {
			this.loadFiles(path);
		}
	}

	public uTextureReference getTexture(String name) {
		for (uTextureReference t : this.ReferenceList) {
			if (t.name.equals(name) || t.namePath.equals(name) || t.fullPath.equals(name)) {
				return t;
			}
		}
		return null;
	}

	public void createSpriteSheet(uTextureReference input, int x, int y) {
		//this.SpriteList.add(new uSpriteSheet(input, x, y));
	}

	public void createSpriteSheet(String input, int x, int y) {
		this.createSpriteSheet(this.getTexture(input), x, y);
	}

}
