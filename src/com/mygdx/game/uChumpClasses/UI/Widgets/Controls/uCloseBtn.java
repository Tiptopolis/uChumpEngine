package com.mygdx.game.uChumpClasses.UI.Widgets.Controls;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.uChumpClasses.Core.Input.uHit;
import com.mygdx.game.uChumpClasses.UI.iLayer;
import com.mygdx.game.uChumpClasses.UI.uWidget;
import com.mygdx.game.uChumpClasses.UI.Drawable.dRect;
import com.mygdx.game.uChumpClasses.UI.Widgets.iContainer;

public class uCloseBtn extends uWidget{

	public iContainer container;
	
	
	public uCloseBtn(iContainer container) {
		super(container.getParentLayer(), container.getName() + ".CloseBtn");

		this.container = container;
		this.container.addControl(this);
		
		this.setSize(16, 16);
		//move ControlBuffer from uDragbar to iContainerWidget
		this.setPosition(((Actor) container).getX() + container.getWidth() - 24, ((Actor) container).getY() + container.getHeight() - 16);
		this.setBounds(this.getX(), this.getY(), this.getWidth(), this.getHeight());
		this.show();

		this.init();
	}

	@Override
	public void resize(int width, int height) {
		this.setSize(16, 16);
		this.setPosition(((Actor) container).getX() + container.getWidth() - 24, ((Actor) container).getY() + container.getHeight() - 16);
		this.setBounds(this.getX(), this.getY(), this.getWidth(), this.getHeight());
	}
	
	@Override
	public void moveBy(float x, float y) {		
				if (x != 0 || y != 0) {
					//this.setX(this.getX() + (x - container.dragOffset.x));
					//this.setY(this.getY() + (y + container.dragOffset.y)); //PROBLEM! container != dragable :/ cast?					
					//this.body.reposition(body.x + (x - container.dragOffset.x), body.y + (y + container.dragOffset.y));
					//this.positionChanged();
					float a = 0;
					float b = 0;
					if(this.container instanceof iDragable)
					{
						a = ((iDragable)container).getDragOffset().x;
						b = ((iDragable)container).getDragOffset().y;
					}
					
					
					this.setX(this.getX() + (x - a));
					this.setY(this.getY() + (y + b));
					this.body.reposition(body.x + (x - a), body.y + (y - b));					
					this.positionChanged();
				}
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
		this.toFront(); // separate to proper sorting code & invalidateOver
		this.layer.getMembers().remove(this);
		this.layer.getMembers().add(this);
		this.castEvent("BTN_CLICK");
	}
	
	
}
