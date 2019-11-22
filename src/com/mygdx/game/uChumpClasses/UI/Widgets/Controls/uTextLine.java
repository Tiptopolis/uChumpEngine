package com.mygdx.game.uChumpClasses.UI.Widgets.Controls;

import com.badlogic.gdx.math.Vector2;

public class uTextLine {

	public String text;
	public String displayText;

	public Vector2 textAnchor = new Vector2(0, 0);
	public Vector2 textBounds = new Vector2(0, 0);
	public Vector2 displayBounds = new Vector2(0,0);
	//public Vector2 displayBounds = new Vector2(0, 0);

	public uTextLine(String text) {
		this.text = text;
		this.displayText = text;
	}

	public void setAnchor(Vector2 anchor) {
		//this.textAnchor = anchor;
		this.textAnchor.set(anchor.x, anchor.y);
	}
	
	public void setAnchor(float x, float y)
	{
		this.textAnchor.x = x;
		this.textAnchor.y = y;
	}

	public void setTextBounds(Vector2 bounds) {
		this.textBounds.set(bounds.x, bounds.y);
	}

	@Override
	public String toString() {
		return this.text;
	}

}
