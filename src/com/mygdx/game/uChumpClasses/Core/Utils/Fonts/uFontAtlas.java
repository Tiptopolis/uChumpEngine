package com.mygdx.game.uChumpClasses.Core.Utils.Fonts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import static com.mygdx.game.uChumpClasses.uAppUtils.*;

public class uFontAtlas {

	public String rootDefault = "./bin/default/";
	public String rootPath = "data/fonts/";

	ArrayList<uFontReference> ReferenceList = new ArrayList<uFontReference>();

	public uFontAtlas() {
		this.loadFiles(rootPath);
		this.printDebug();
	}

	public void loadFiles(String directory) {
		HashMap<String, String> rawMap = (HashMap<String, String>) mapFiles(directory);

		for (Map.Entry<String, String> entry : rawMap.entrySet()) {

			uFontReference newLink = new uFontReference(entry.getKey(), entry.getValue());
			this.ReferenceList.add(newLink);

		}
	}

	private Map<String, String> mapFiles(String directory) {

		FileHandle dir = Gdx.files.internal(rootDefault + rootPath);// FKN WORKS!!!

		Map<String, String> getFiles = new HashMap<String, String>();

		for (FileHandle t : dir.list()) {

			String actFile = (rootPath + t.file().getName());

			if (actFile.toLowerCase().endsWith(".ttf")) {
				getFiles.put(t.file().getName(), actFile); // remove .ext from entry?
			}
		}

		return getFiles;
	}

	public uFontReference getFontLink(String name) {
		for (uFontReference l : this.ReferenceList) {
			if (l.name.equalsIgnoreCase(name) || l.namePath.equalsIgnoreCase(name) || l.fullPath.equalsIgnoreCase(name)) // can search with or without .ttf extension
			{
				return l;
			}
		}
		return null;
	}
	
	
	public void printDebug()
	{
		println("-----------------");
		for (uFontReference l : this.ReferenceList) // FKN WORKS
		{
			
			println(l.name); // no ext
			println(l.namePath); // ext
			println(l.fullPath); // path
		}
		println("-----------------");
	}
}
