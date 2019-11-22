package com.mygdx.game.uChumpClasses.UI;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.mygdx.game.uChumpClasses.Core.iEntity;
import com.mygdx.game.uChumpClasses.Core.Event.iEventCaster;
import com.mygdx.game.uChumpClasses.Core.Event.iEventObserver;
import com.mygdx.game.uChumpClasses.UI.Heirarchy.vNode;

public interface iScreen extends iSpace, vNode, iEventObserver, iEventCaster{
	public void init(); //handles initial creation specifics

	/** Called when this screen becomes the current screen for a {@link Game}. */
	public void show ();
	
	/** Called when the screen should render itself.
	 * @param delta The time in seconds since the last render. */
	public void render (float delta);

	/** @see ApplicationListener#resize(int, int) */
	public void resize (int width, int height);

	/** @see ApplicationListener#pause() */
	public void pause ();

	/** @see ApplicationListener#resume() */
	public void resume ();

	/** Called when this screen is no longer the current screen for a {@link Game}. */
	public void hide ();

	/** Called when this screen should release all resources. */
	public void dispose ();
	
	public void addPrehide(iEntity entity); //<<TESTING
	public void prehide();
}
