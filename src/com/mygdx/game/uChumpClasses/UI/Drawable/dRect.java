package com.mygdx.game.uChumpClasses.UI.Drawable;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.uChumpClasses.Core.iObject;
import com.mygdx.game.uChumpClasses.UI.Util.uSketcher;


public class dRect extends uDrawable implements iDrawable {

	//public uAppearance Appearance;

	iObject owner;

	public dRect(float x, float y, float width, float height/* , DrawLayer parent */) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		// this.parent = parent;

	}

	public dRect(int x, int y, int width, int height/* , DrawLayer parent */) {
		super();
		this.x = (float) x;
		this.y = (float) y;
		this.width = (float) width;
		this.height = (float) height;
		// this.parent = parent;

	}

	public dRect(Rectangle r) {
		super();
		this.x = r.x;
		this.y = r.y;
		this.width = r.width;
		this.height = r.height;

	}

	public dRect(dRect r) {
		super();
		this.x = r.x;
		this.y = r.y;
		this.width = r.width;
		this.height = r.height;

	}

	public void draw(uSketcher drawer) {

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

		drawer.rect((int) x, (int) y, (int) width, (int) height); // must be INT
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
