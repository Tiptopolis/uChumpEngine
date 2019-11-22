package com.mygdx.game.uChumpClasses.UI.Drawable;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.uChumpClasses.UI.DisplayStates.uAppearance;
import com.mygdx.game.uChumpClasses.UI.Util.uSketcher;



public class uDrawable implements iDrawable {

	public float x, y;
	public float width, height;
	public float rotation;
	public float scaleX = 1, scaleY = 1;

	public uAppearance appearance;

	public uDrawable() {
		appearance = new uAppearance(this);
	}

	@Override
	public boolean contains(Vector2 point) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean contains(float x, float y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void draw(uSketcher drawer) {
		// TODO Auto-generated method stub

	}

	@Override
	public void resize(int width, int height) {
		this.width = width;
		this.height = height;
	}

	@Override
	public void reposition(float x, float y) {
		this.x = x;
		this.y = y;

	}
	

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	////////////
	//////////// coppied from libgdx' native Drawable interface
	////////////
	
	@Override
	public float getLeftWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setLeftWidth(float leftWidth) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public float getRightWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setRightWidth(float rightWidth) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public float getTopHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setTopHeight(float topHeight) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public float getBottomHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setBottomHeight(float bottomHeight) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public float getMinWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setMinWidth(float minWidth) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public float getMinHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setMinHeight(float minHeight) {
		// TODO Auto-generated method stub
		
	}


}
