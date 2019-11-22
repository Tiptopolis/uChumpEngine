package com.mygdx.game.uChumpClasses.Core.Input;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.uChumpClasses.Core.iEntity;
import com.mygdx.game.uChumpClasses.Core.uEntity;
import com.mygdx.game.uChumpClasses.UI.uCamera;

public class uHit {
	public iEntity owner; //originally uEntity
	public uCamera ownerCam;

	public boolean mouseInside = false; //mouse is inside, but over a member/child of this; for stack-select
	public boolean mouseOver = false; //mouse over this specifically
	public boolean selected = false;
	public boolean focus = false;
	public boolean drag = false;
	

	public uHit(iEntity owner) {
		this.owner = owner;
	}

	public iEntity hit(float x, float y, boolean touchable) { //originally Actor return

		x = this.owner.getSpace().getOffset().x; // owner.space
		y = this.owner.getSpace().getOffset().y;

		return  ((this.owner.getBody().contains(x, y) && this.owner.getSpace().mouseInside()) ? this.owner : null); //owner.body

	}
}
