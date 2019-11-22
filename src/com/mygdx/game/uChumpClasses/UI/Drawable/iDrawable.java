package com.mygdx.game.uChumpClasses.UI.Drawable;

import com.badlogic.gdx.math.Shape2D;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;
import com.mygdx.game.uChumpClasses.UI.Util.uSketcher;

public interface iDrawable extends Shape2D, Disposable{

	
	void draw(uSketcher drawer);
	void resize(int width, int height);
	void reposition(float x, float y);
	
	public boolean contains(Vector2 point);
	public boolean contains(float x, float y);
	
	
	//new addition, textFieldTest
	public float getLeftWidth ();

	public void setLeftWidth (float leftWidth);

	public float getRightWidth ();

	public void setRightWidth (float rightWidth);

	public float getTopHeight ();

	public void setTopHeight (float topHeight);

	public float getBottomHeight ();

	public void setBottomHeight (float bottomHeight);

	public float getMinWidth ();

	public void setMinWidth (float minWidth);

	public float getMinHeight ();

	public void setMinHeight (float minHeight); 
}
