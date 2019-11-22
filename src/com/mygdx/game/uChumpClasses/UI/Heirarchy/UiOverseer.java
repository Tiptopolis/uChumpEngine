package com.mygdx.game.uChumpClasses.UI.Heirarchy;

import java.util.ArrayList;

import com.google.inject.Singleton;
import com.mygdx.game.uChumpClasses.Core.iObject;
import com.mygdx.game.uChumpClasses.Core.Utils.AppInjector;
import com.mygdx.game.uChumpClasses.Core.Utils.Fonts.uFontAtlas;
import com.mygdx.game.uChumpClasses.Core.Utils.Textures.uTextureAtlas;
import com.mygdx.game.uChumpClasses.UI.iScreen;
import com.mygdx.game.uChumpClasses.UI.iSpace;
import com.mygdx.game.uChumpClasses.UI.Util.uFrameBufferHandler;

import static com.mygdx.game.uChumpClasses.uAppUtils.*;
import static com.mygdx.game.uChumpClasses.uApp.*;

@Singleton
public class UiOverseer {

	// import static com.mygdx.game.uChumpClasses.UI.Heirarchy.UiOverseer.*;

	public uFrameBufferHandler BufferHandler;
	// oversees UI heirarchy;
	// handles parent-child relationships, FrameBufferHandler
	public uFontAtlas fontAtlas;
	public uTextureAtlas textureAtlas;

	// offload all node-related stuff to a uHeirarchy
	public ArrayList<iNode> NodeList = new ArrayList<iNode>(); // all nodes that exist
	public ArrayList<iNode> RootNodes = new ArrayList<iNode>(); // all top-level nodes

	public UiOverseer() {
		BufferHandler = AppInjector.getInjector().getInstance(uFrameBufferHandler.class);
		this.fontAtlas = new uFontAtlas();
		this.textureAtlas = new uTextureAtlas();
	}

	public void update(float deltaTime) {
		this.BufferHandler.update(deltaTime);
	}
	
	public void dispose()
	{
		
		this.textureAtlas.dispose();
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

	public void printDebug() {
		println("======{[NODE HEIRARCHY}]=====");
		for (iNode r : this.RootNodes) {
			r.printDebug();

		}
	}

}
