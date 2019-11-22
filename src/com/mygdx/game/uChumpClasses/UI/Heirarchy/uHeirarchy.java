package com.mygdx.game.uChumpClasses.UI.Heirarchy;

import static com.mygdx.game.uChumpClasses.uApp.Director;

import java.util.ArrayList;

public class uHeirarchy {

	
	public ArrayList<iNode> NodeList = new ArrayList<iNode>(); // all nodes that exist
	public ArrayList<iNode> RootNodes = new ArrayList<iNode>(); // all top-level nodes
	
	public uHeirarchy()
	{
		
	}
	
	public void addNode(iNode node) {
		this.NodeList.add(node);
		node.updateDepth();
	}

	public void addRoot(iNode node) {
		this.RootNodes.add(node);
	}

	public iNode getNode(String fullName) {

		for (iNode n : this.NodeList) {
			if (n.getFullName() == fullName) {
				return n;
			}
		}

		return null;
	}

	public iNode getNode(vNode owner) {
		if (owner.getNode() != null) {
			return owner.getNode();
		}
		return null;
	}

	public void removeNode(String fullName) {
		if (this.NodeList.contains(this.getNode(fullName))) {
			this.removeNode(this.getNode(fullName));
		}
	}

	public void removeNode(iNode node) {
		if (this.NodeList.contains(node)) {
			this.NodeList.remove(node);

		}
	}

	public void clearDirector() {
		ArrayList<iNode> list = new ArrayList<iNode>();

		// maybe make list rootList inside root nodes?
		for (iNode n : this.RootNodes) {
			list.addAll(n.getDescendants());
		}

		for (// GET ROOT

		iNode x : list) {
			Director.deregisterEventHandler(x);
		}

		list.clear();
	}
	
	
}
