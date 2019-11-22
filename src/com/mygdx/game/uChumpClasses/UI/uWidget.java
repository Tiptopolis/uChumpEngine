package com.mygdx.game.uChumpClasses.UI;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.uChumpClasses.Core.iEntity;
import com.mygdx.game.uChumpClasses.Core.uEntity;
import com.mygdx.game.uChumpClasses.UI.Widgets.iContainer;

import static com.mygdx.game.uChumpClasses.uAppUtils.*;

public class uWidget extends uEntity implements iWidget {

	public boolean isStatic = false;
	public boolean staticType = false; // false for rear, true for front

	public uWidget(iLayer layer, String name) {
		super(layer, name);
	}

	@Override
	public void init() {
		super.init();
	}

	@Override
	public void draw(float deltaTime) {
		super.draw(deltaTime);
	}

	@Override
	public boolean isStatic() {
		// TODO Auto-generated method stub
		return this.isStatic;
	}

	@Override
	public void setStatic(boolean stat) {
		this.isStatic = stat;
	}

	@Override
	public void setStaticType(boolean type) {

		this.staticType = type;

	}

	@Override
	public boolean getStaticType() {
		return this.staticType;
	}

	@Override
	public iLayer getParentLayer() {
		return this.layer;
	}

	@Override
	public void invalidateOver(Actor actor) {

		this.screen.invalidateOver(this);
		if (this.layer.getOwner() instanceof iContainer) {
			this.layer.invalidateOver(this);
		}

	}

	@Override
	public void sortFront() {
		if (this.space instanceof iContainer) {
			((iEntity) this.space).sortFront();
		}
		if (!this.isStatic()) {
			this.layer.removeMember(this);
			this.layer.addMember(this);
			this.toFront();
		}
	}

	@Override
	public void onClick() {
		super.onClick();
		this.sortFront();
		this.screen.outClick(this);
		this.space.sortLayer(this.layer);
		if (this.space instanceof iContainer) {

			((uWidget) this.space).onClick();
		}

	}

	@Override
	public void onRelease() {
		this.invalidateOver(this);
	}

	@Override
	public void mouseEnter() {
		super.mouseEnter();
		this.invalidateOver(this);
	}

	@Override
	public void mouseExit() {
		super.mouseExit();
	}

}
