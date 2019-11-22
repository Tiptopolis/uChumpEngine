package com.mygdx.game.uChumpClasses.UI.Heirarchy;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.uChumpClasses.Core.iObject;

public interface vNode extends iObject{
	
	//interface for anything that owns a node
	
	public iNode getNode();
	public void enter();
	public void exit();
	public void invalidateOver(Actor actor);

}
