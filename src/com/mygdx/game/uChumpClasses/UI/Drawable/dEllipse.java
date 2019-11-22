package com.mygdx.game.uChumpClasses.UI.Drawable;

import com.badlogic.gdx.math.Ellipse;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.uChumpClasses.Core.iObject;
import com.mygdx.game.uChumpClasses.UI.Util.uSketcher;

import static com.mygdx.game.uChumpClasses.uAppUtils.*;

public class dEllipse extends uDrawable implements iDrawable {

	

	public dEllipse(float x, float y, float width, float height) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public dEllipse(int x, int y, int width, int height) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public dEllipse(Ellipse e) {
		super();
		this.x = e.x;
		this.y = e.y;
		this.width = e.width;
		this.height = e.height;
	}

	public dEllipse(dEllipse e) {
		super();
		this.x = e.x;
		this.y = e.y;
		this.width = e.width;
		this.height = e.height;
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
		//drawer.strokeWeight(this.appearance.uStrokeWeight);//test, use in others if it works

		if (appearance.doFill == false)
			drawer.noFill();
		if (appearance.doStroke == false)
			drawer.noStroke();

		drawer.ellipse((int) this.x, (int) this.y, (int) this.width, (int) this.height);
		//drawer.strokeWeight(1);

	}
		

	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		this.width = width;
		this.height = height;
	}

	public void reposition(int x, int y) {
		this.x = x;
		this.y = y;
	}

	//
	// Utils
	//

	@Override
	public boolean contains(float x, float y) {
		
		x = x - (this.x + this.width/2);
		y = y - (this.y + this.height/2);
		
		return (x * x) / (width * 0.5f * width * 0.5f) + (y * y) / (height * 0.5f * height * 0.5f) <= 1.0f;

		// x = (int) x;
		// y = (int) y;
		// float cX = this.x + (this.width / 2);
		// float cY = this.y + (this.height / 2);

		// float radX = this.width / 2;
		// float radY = this.height / 2;

		// return ((x - cX) * (x - cX)) / (radX * radX) <= 1;

	}


}
