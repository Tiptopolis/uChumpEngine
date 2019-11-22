package com.mygdx.game.uChumpClasses.UI.Drawable;

import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.uChumpClasses.Core.iObject;
import com.mygdx.game.uChumpClasses.UI.Util.uSketcher;

import static com.badlogic.gdx.math.Intersector.*;

import com.badlogic.gdx.math.Intersector;

import static com.mygdx.game.uChumpClasses.uAppUtils.*;


public class dPolygon extends uDrawable implements iDrawable {

	

	iObject owner;

	
	public float rotation;
	public float scaleX = 1, scaleY = 1;
	public int points;
	public float vertices[];

	public dPolygon(int x, int y, int radius, int points) {
		super();
		this.x = x;
		this.y = y;
		this.width = radius;
		this.height = radius;
		this.points = points;
		this.vertices = uSketcher.calculateVertices((int)x, (int)y, (int)width, (int)height, points, 0, 1);
	}

	public dPolygon(int x, int y, int width, int height, int points) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.points = points;
		this.vertices = uSketcher.calculateVertices(x, y, width, height, points, 0, 1);
		
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

		if (appearance.doFill == false)
			drawer.noFill();
		if (appearance.doStroke == false)
			drawer.noStroke();		
		
		drawer.polygon(vertices);

	}

	public void resize(int width, int height) {

	}

	public void reposition(int x, int y) {

	}
	
	public void rotate(float degrees)
	{
		this.rotation = degrees;
		this.vertices = uSketcher.calculateVertices((int)x, (int)y, (int)width, (int)height, points, rotation, 1);
	}
	
	
	public boolean contains(float x, float y)
	{		
		
		
		if(isPointInPolygon(this.vertices, 0, this.points*2 , x, y) == true) return true;
		else return false;
	}

	

}
