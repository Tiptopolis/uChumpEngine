package com.mygdx.game.uChumpClasses.UI.Drawable;

import com.mygdx.game.uChumpClasses.UI.uCamera;

public class dVirtualRect extends uDrawable{

	public dVirtualRect(uCamera owner)
	{
		this.width = owner.getViewportWidth();
		this.height = owner.getViewportHeight();
		this.x = owner.getX();
		this.y = owner.getY();
	}
	
	public void resize(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public void reposition(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	@Override
	public boolean contains(float x, float y)
	{
		return this.x <= x && this.x + this.width >= x && this.y <= y && this.y + this.height >= y;		
	}
	
}
