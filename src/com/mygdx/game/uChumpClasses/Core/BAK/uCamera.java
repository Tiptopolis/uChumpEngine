package com.mygdx.game.uChumpClasses.Core.BAK;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.uChumpClasses.Core.Event.uEvent;
import com.mygdx.game.uChumpClasses.Core.Input.uHit;
import com.mygdx.game.uChumpClasses.Core.Input.uInputListener;
import com.mygdx.game.uChumpClasses.UI.Heirarchy.iNode;

public class uCamera implements iCamera {

	public String name;
	public iSpace owner;

	public Camera camera = new OrthographicCamera();;
	public Viewport viewport;

	public Vector2 position = new Vector2(0, 0);
	
	public uHit hit; //test
	public uInputListener listener;

	public uCamera(iSpace owner) {
		this.owner = owner;
		this.name = ".Camera";

		this.viewport = new ScreenViewport(this.camera);
		((OrthographicCamera) this.camera).setToOrtho(false, owner.getWidth(), owner.getHeight());
		((ScreenViewport) this.viewport).setUnitsPerPixel(1);//

	}

	@Override
	public void resize(int width, int height) {
		this.camera.viewportWidth = width;
		this.camera.viewportHeight = height;
		this.viewport.update(width, height);
		this.camera.update(true);

	}

	@Override
	public void reposition(int x, int y) {
		this.position.set(x, y);
	}

	@Override
	public void dispose() {
		this.owner = null;
		this.camera = null;
		this.viewport = null;
	}

	@Override
	public Camera getCamera() {

		return this.camera;

	}

	@Override
	public Viewport getViewport() {
		return this.viewport;
	}

	@Override
	public Vector2 getPosition() {
		return this.position;
	}

	@Override
	public float getViewportWidth() {
		return this.camera.viewportWidth;
	}

	@Override
	public float getViewportHeight() {
		return this.camera.viewportHeight;

	}


}
