package com.mygdx.game.xApp.Screens.GameScreen;

import com.mygdx.game.uChumpClasses.UI.iSpace;
import com.mygdx.game.uChumpClasses.UI.uLayer;
import com.mygdx.game.uChumpClasses.UI.Widgets.uPanel;

import static com.mygdx.game.uChumpClasses.uAppUtils.*;


public class GameLayer extends uLayer{

	
	public uPanel GameField;
	public GameEntity lastCreatedEntity;
	
	public GameLayer(iSpace owner) {
		super(owner);
		this.setName("GameLayer");	
		}
	
	

	@Override
	public void init()
	{
		super.init();
		
		this.GameField = new uPanel(this,0,0,300,300);
		this.GameField.setName("GameField");
		this.GameField.setPosition(0+(this.screen.getWidth()/2)-(GameField.getWidth()/2), 0+(this.screen.getHeight()/2)-(GameField.getHeight()/2));
		this.lastCreatedEntity = new GameEntity(this.GameField.DefaultLayer,32,32);
		this.lastCreatedEntity = new GameEntity(this.GameField.DefaultLayer,50,50);
		this.lastCreatedEntity = new GameEntity(this.GameField.DefaultLayer,61,42);


		
	}
	
}
