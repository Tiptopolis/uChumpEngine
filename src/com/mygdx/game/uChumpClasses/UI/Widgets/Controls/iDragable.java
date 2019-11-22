package com.mygdx.game.uChumpClasses.UI.Widgets.Controls;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.uChumpClasses.UI.iWidget;

public interface iDragable extends iWidget{

	
	public void dragLock(boolean lock);//external <new, testing
	
	public void setDragable(boolean dragable);
	public void setSelfDrag(boolean dragable);
	
	public boolean isDragable();
	public void doDrag(float x, float y);
	public void setDragPoint();
	
	public void setDragPoint(float x, float y, float ox, float oy); //external <new, testing
	public Vector2 getDragOffset();
	
	
}
