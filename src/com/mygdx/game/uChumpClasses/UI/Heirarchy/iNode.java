package com.mygdx.game.uChumpClasses.UI.Heirarchy;

import java.util.ArrayList;

import com.mygdx.game.uChumpClasses.Core.iObject;

public interface iNode extends iObject{

	
	public void enter();
	public void exit();
	public void close();

	public vNode getOwner();
	public boolean isActive();
	public void setActive(boolean active);
	
	public void addChild(iNode node);
	public void removeChild(iNode node);
	public void removeChild(String name);
	public iNode getChild(String name);
	public boolean isChild();	

	public void setParent(iNode node);
	public void removeParent();
	public iNode getParentNode();
	public boolean isParent();
	public ArrayList<iNode> getChildren();
	public boolean hasSiblings();
	public ArrayList<iNode> getSiblings();
	
	public ArrayList<iNode> getDescendants();
	public void addDescendant(iNode node);
	public iNode getRootNode();

	
	public int getDepth();
	public void updateDepth();
	
	
	public void printDebug();
	
}
