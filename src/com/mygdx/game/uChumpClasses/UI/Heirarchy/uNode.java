package com.mygdx.game.uChumpClasses.UI.Heirarchy;

import java.util.ArrayList;

import com.mygdx.game.uChumpClasses.Core.iObject;
import com.mygdx.game.uChumpClasses.Core.Event.uEvent;

import static com.mygdx.game.uChumpClasses.uAppUtils.*;

public class uNode implements iNode {

	// virtual Node class, put in an object you want to act as a node

	public vNode owner;
	public iNode ParentNode;
	public iNode root;
	public boolean isActive = false;
	public ArrayList<iNode> ChildNodes = new ArrayList<iNode>();
	public ArrayList<iNode> DescendantNodes = new ArrayList<iNode>();
	public int depth;

	public uNode(vNode owner) {
		this.owner = owner;
	}

	@Override
	public vNode getOwner() {
		return this.owner;
	}

	@Override
	public boolean isActive() {
		return this.isActive;
	}
	
	@Override
	public void setActive(boolean active)
	{
		this.isActive = active;
	}

	@Override
	public String getName() {
		return owner.getName();
	}

	@Override
	public String getFullName() {
		owner.updateFullName();
		return owner.getFullName();
	}

	@Override
	public void updateFullName() {

	}

	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub

	}

	@Override
	public void enter() {
		this.owner.enter();
	}

	@Override
	public void exit() {
		this.owner.exit();
	}

	@Override
	public void close() {
		this.getParentNode().removeChild(this);
		this.setParent(null);

		if (this.isParent()) {
			for (iNode n : this.getChildren()) {
				this.removeChild(n);
			}
		}
		this.ChildNodes.clear();

	}

	@Override
	public void addChild(iNode node) {
		if (!this.ChildNodes.contains(node)) {
			{
				this.ChildNodes.add(node);
				node.setParent(this);
				this.addDescendant(node);
			}

		}
	}

	@Override
	public void removeChild(iNode node) {
		if (this.ChildNodes.contains(node)) {
			this.ChildNodes.remove(node);
			node.setParent(null);
		}
	}

	@Override
	public void removeChild(String name) {
		if (this.getChild(name) != null) {
			this.removeChild(getChild(name));
		}

	}

	@Override
	public iNode getChild(String name) {
		for (iNode n : this.ChildNodes) {
			if (n.getName() == name)
				return n;
		}
		return null;
	}

	@Override
	public boolean isChild() {
		if (this.ParentNode != null)
			return true;
		else
			return false;

	}

	@Override
	public void setParent(iNode node) {
		if(this.ParentNode != null)
		{
			this.removeParent();
		}
		this.ParentNode = node;
		//do not use to update/add anything else
	}

	@Override
	public void removeParent() {
		this.ParentNode.removeChild(this);
		this.ParentNode = null;
	}

	@Override
	public iNode getParentNode() {
		return this.ParentNode;
	}

	@Override
	public boolean isParent() {
		if (this.ChildNodes.isEmpty())
			return false;
		else
			return true;
	}

	@Override
	public ArrayList<iNode> getChildren() {
		if (this.isParent()) {
			return this.ChildNodes;
		} else
			return null;
	}

	@Override
	public boolean hasSiblings() {
		if (this.getParentNode() == null)
			return false;
		if (this.getParentNode().getChildren().size() > 1)
			return true;
		else
			return false;
	}

	@Override
	public ArrayList<iNode> getSiblings() {
		if (this.hasSiblings()) {
			return this.getParentNode().getChildren();
		} else
			return null;
	}

	@Override
	public ArrayList<iNode> getDescendants() {
		return this.DescendantNodes;
	}

	@Override
	public void addDescendant(iNode node) {

		this.DescendantNodes.add(node);
		if (this.isChild() && this.getParentNode().getChildren().contains(node) == false) {
			this.getParentNode().addDescendant(node);
		}
	}

	@Override
	public iNode getRootNode() {
		return this.root;
	}

	@Override
	public void updateDepth() {
		int count = 0;
		boolean rootReached = false;
		iNode subject = this;

		while (rootReached == false) {
			if (subject.isChild()) {
				subject = subject.getParentNode();
				count++;
			} else
				rootReached = true;

		}

		this.depth = count;
	}

	@Override
	public int getDepth() {
		this.updateDepth();
		return this.depth;
	}

	@Override
	public void printDebug() {
		owner.printDebug();
		if (this.isParent()) {

			for (iNode n : this.ChildNodes) {
				// println(n);
				n.printDebug();
			}
		}

	}

	@Override
	public boolean handleEvent(uEvent event) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void castEvent(String event) {
		// TODO Auto-generated method stub

	}

}
