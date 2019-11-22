package com.mygdx.game.uChumpClasses.UI.DisplayStates;

import com.badlogic.gdx.graphics.Color;

public class dState {

	public String name;
	public Color dFill;
	public Color dStroke;
	public int weight;
	
	public dState(String name, Color fill, Color stroke)
	{
		this.name = name;
		this.dFill = fill;
		this.dStroke = stroke;
		this.weight = 1;
	}
	
	public dState(String name, Color fill, Color stroke, int weight)
	{
		this.name = name;
		this.dFill = fill;
		this.dStroke = stroke;
		this.weight = weight;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public Color getFill()
	{
		return this.dFill;
	}
	
	public Color getStroke()
	{
		return this.dStroke;
	}
	
	public int getWeight()
	{
		return this.weight;
	}
}
