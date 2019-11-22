package com.mygdx.game.uChumpClasses.UI;


public interface iWidget {

	public boolean isStatic();
	public void setStatic(boolean stat);
	public void setStaticType(boolean type);
	public boolean getStaticType(); //false is rear, true is front //<temp testing stuff
	
	public void toFront();
	public void sortFront();
	
	public iLayer getParentLayer(); //if is inside a container, returns local layer

}
