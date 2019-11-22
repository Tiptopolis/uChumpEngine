package com.mygdx.game.uChumpClasses.Core.Utils.Fonts;

public class uFontReference {
	
	//used to get prefab data about Fonts
	//specifically aspect ratio

	public String name; //name without extension
	public String namePath; //name with extension
	public String fullPath; //data/fonts/etc
	
	public float aspectRatio = 0.5f;
	
	
	public uFontReference(String name, String fullPath)
	{
		
		String temp = name;
		if(name.contains("."))
		{
			temp = name.substring(0, name.lastIndexOf('.'));
		}
		
		this.name = temp; //no ext
		this.namePath = name; //ext
		this.fullPath = fullPath;
	}
	

	
	//set aspect ratio from prefab list
	
}
