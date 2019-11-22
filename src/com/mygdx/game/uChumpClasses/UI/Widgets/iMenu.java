package com.mygdx.game.uChumpClasses.UI.Widgets;

import com.mygdx.game.uChumpClasses.UI.iWidget;

public interface iMenu extends iWidget{

	
	public void showMenu();
	public void hideMenu();
	
	public void addItem(String item);
	public void removeItem(String item);
	public uTextButton getItem(String item);
	public void refreshMenu();
	
	
}
