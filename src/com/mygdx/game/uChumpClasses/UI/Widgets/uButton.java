package com.mygdx.game.uChumpClasses.UI.Widgets;

import com.badlogic.gdx.graphics.Color;
import com.mygdx.game.uChumpClasses.Core.Input.uHit;
import com.mygdx.game.uChumpClasses.UI.iLayer;
import com.mygdx.game.uChumpClasses.UI.uWidget;
import com.mygdx.game.uChumpClasses.UI.Drawable.dRect;

import static com.mygdx.game.uChumpClasses.uAppUtils.*;


public class uButton extends uWidget {

	public uButton(iLayer layer, int x, int y, int width, int height) {
		super(layer, "New uButton");
		this.setPosition(x, y);
		this.setSize(width, height);
		this.setBounds(this.getX(), this.getY(), this.getWidth(), this.getHeight());
		
		this.show();
		this.init();
	}
	
	@Override
	public void init() {
		super.init();

	}
	
	@Override
	public void draw(float deltaTime)
	{
		super.draw(deltaTime);
	}
	
	
	
	@Override
	public void handleDisplayState() {
		if (this.hit.mouseOver == true && this.hit.selected == false)
			this.states.setState("MouseOver");
		else {
			this.states.setState("NULL");
		}

		this.states.handleState();
	}

	@Override
	public void buildDisplayStates() {
		this.states.alterState("NULL", Color.WHITE, Color.RED, 1);
		this.states.addState("MouseOver", Color.WHITE, Color.BLUE, 2);
	}

	@Override
	public void buildBody() {
		this.body = new dRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
		this.hit = new uHit(this);
	}

	@Override
	public void onClick() {		
		/*if(!this.isStatic)
		{
		this.toFront(); // separate to proper sorting code & invalidateOver
		this.layer.getMembers().remove(this);
		this.layer.getMembers().add(this);
		}*/
		//this.sortFront();
		super.onClick();
		this.castEvent("BTN_CLICK");	
	}
	
	@Override
	public void castEvent(String event)
	{
		super.castEvent(event);
	}

}
