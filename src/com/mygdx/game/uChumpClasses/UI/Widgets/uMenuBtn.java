package com.mygdx.game.uChumpClasses.UI.Widgets;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.uChumpClasses.Core.iEntity;
import com.mygdx.game.uChumpClasses.Core.Event.uEvent;
import com.mygdx.game.uChumpClasses.UI.iLayer;
import com.mygdx.game.uChumpClasses.UI.uWidget;
import com.mygdx.game.uChumpClasses.UI.Util.eMenuDirH;
import com.mygdx.game.uChumpClasses.UI.Util.eMenuDirV;
import com.mygdx.game.uChumpClasses.UI.Util.eTextH;
import com.mygdx.game.uChumpClasses.UI.Widgets.Controls.uMenu;

import static com.mygdx.game.uChumpClasses.uAppUtils.*;

public class uMenuBtn extends uTextButton implements iMenu {

	// button to expand menu

	// public uMenu Menu;
	public uPanel Menu;
	public boolean open = false;
	public eMenuDirH dirH;
	public eMenuDirV dirV;
	private Vector2 menuAnchor = new Vector2(0, 0);
	// sub-item size is set by text-size+4

	public ArrayList<uTextButton> Items = new ArrayList<uTextButton>();
	public int itemSize = 8;
	
	public uTextButton lastAddedItem;

	public uMenuBtn(iLayer layer, int x, int y, int width, int height, eMenuDirH dirH, eMenuDirV dirV, String text) {
		super(layer, x, y, width, height);
		this.dirH = dirH;
		this.dirV = dirV;
	}

	@Override
	public void init() {
		super.init();
		this.setName("New MenuBtn");
		this.setText("MENU");
		this.buildMenu();

	}

	@Override
	public void onClick() {
		this.toFront(); // separate to proper sorting code & invalidateOver
		this.layer.getMembers().remove(this);
		this.layer.getMembers().add(this);
		this.castEvent("MENUBTN_CLICK");
		this.toggleOpen();
	}

	@Override
	public void outClick(iEntity entity) {
		if (entity == null) {
			this.hideMenu();
			return;
		}

		if (entity != this && this.Node.getDescendants().contains(entity.getNode()) == false) {
			if (this.open) {
				this.hideMenu();
			}

		}
	}

	public void buildMenu() {
		// this.Menu = new uMenu(this);
		this.Menu = new uPanel(this.layer, 0, 0, (int) (this.getWidth() * 2), (int) (this.getHeight() * 3)) {
			@Override
			public boolean handleEvent(uEvent event) {
				return false;
			}

			@Override
			public void onClick() {
				super.onClick();
				this.space.sortLayer(this.layer);
				this.castEvent("MENUPANEL_CLICK");
			}
		};

		this.screen.addPrehide(this.Menu);

		this.Node.addChild(this.Menu.getNode());

	}

	public void toggleOpen() {
		if (this.open) {
			this.hideMenu();

		} else {
			this.showMenu();

		}
	}

	public void showMenu() {
		this.refreshMenu();
		this.open = true;
		this.Menu.show();
		this.setText("OPEN");
	}

	public void hideMenu() {
		this.open = false;
		this.Menu.hide();
		this.setText("CLOSE");
	}

	public void refreshMenu() {

		// resize menu & re-anchor

		//println("ITEMS: " + this.Items.size());
		this.Menu.setSize(this.Menu.getWidth(), this.Items.size() * (this.textSize + 4) + 4);
		this.Menu.Camera.setPosition(0, 0);
		this.anchorX();
		this.anchorY();
		//println("----------");
		this.Menu.reposition((int) menuAnchor.x, (int) menuAnchor.y);

		for (uTextButton t : this.Items) {
			t.setPosition(t.getX(), (this.Items.indexOf(t) * t.getHeight()));
		}

	}

	private void anchorX() {
		if (this.dirH == eMenuDirH.LEFT) {
			this.menuAnchor.set(this.getX(), this.menuAnchor.y);
		}

		if (this.dirH == eMenuDirH.RIGHT) {
			this.menuAnchor.set(this.getX(), this.menuAnchor.y);
		}
	}

	private void anchorY() {
		if (this.dirV == eMenuDirV.UP) {
			this.menuAnchor.set(this.menuAnchor.x, this.getY() + this.getHeight());
		}

		if (this.dirV == eMenuDirV.DOWN) {
			this.menuAnchor.set(this.menuAnchor.x, this.getY() - Menu.getHeight());
		}
	}

	@Override
	public void addItem(String name) {

		uTextButton newItem = new uTextButton(this.Menu.DefaultLayer, 0, 0, (int) this.Menu.getWidth() - 4,
				this.textSize + 4);
		newItem.setName(name);
		newItem.setText(name);
		newItem.setAllignmentH(eTextH.LEFT);
		this.Items.add(newItem);
		this.lastAddedItem = newItem;
		this.refreshMenu();
	}
	
	

	@Override
	public void removeItem(String item) {
		if (this.getItem(item) != null) {
			this.Items.remove(this.getItem(item));
		}
	}

	@Override
	public uTextButton getItem(String item) {
		for (uTextButton t : this.Items) {
			if (t.getName() == item) {
				return t;
			} else
				return null;
		}
		return null;
	}

	//
	// EVENT
	//

	public boolean handleEvent(uEvent event) {
		boolean handled = false;

	
		switch (event.getEvent()) {

		case "VOID_CLICK":
			this.hideMenu();
			break;
		
		case "MENUPANEL_CLICK":
			this.hideMenu();
			break;
			
		case "BTN_CLICK":
			if(this.Items.contains(event.getCaster()))
			{
				println(event.getCaster().getName());
				this.hideMenu();
				this.castEvent("");
			}
			break;
			
		default:
			break;

		}

		return handled;
	}

}
