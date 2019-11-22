package com.mygdx.game.uChumpClasses.UI;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.uChumpClasses.Core.Event.uDirector;
import com.mygdx.game.uChumpClasses.Core.Input.uHit;
import com.mygdx.game.uChumpClasses.Core.Input.uInputListener;
import com.mygdx.game.uChumpClasses.Core.Utils.AppInjector;
import com.mygdx.game.uChumpClasses.UI.Drawable.dVirtualRect;
import com.mygdx.game.uChumpClasses.UI.Drawable.iDrawable;
import com.mygdx.game.uChumpClasses.UI.Drawable.uDrawable;
import com.mygdx.game.uChumpClasses.UI.Util.uCameraAdapter;
import com.mygdx.game.uChumpClasses.UI.Widgets.uRect;

import static com.mygdx.game.uChumpClasses.uAppUtils.*;

public class uCamera extends uCameraAdapter implements iCamera {

	public String name;
	public iSpace owner;

	public Camera camera = new OrthographicCamera();;
	public Viewport viewport;

	public Vector2 position = new Vector2(0, 0);

	public uHit hit; // test
	public uInputListener listener;
	public dVirtualRect body;

	private uDirector Director;

	public uCamera(iSpace owner) {
		this.owner = owner;
		this.name = ".Camera";
		this.Director = AppInjector.getInjector().getInstance(uDirector.class);

		this.viewport = new ScreenViewport(this.camera);
		((OrthographicCamera) this.camera).setToOrtho(false, owner.getWidth(), owner.getHeight());
		((ScreenViewport) this.viewport).setUnitsPerPixel(1);//
		this.body = new dVirtualRect(this);
		this.buildInputListener();

	}

	@Override
	public void resize(int width, int height) {
		this.camera.viewportWidth = width;
		this.camera.viewportHeight = height;
		this.viewport.update(width, height);
		this.camera.update(true);
		this.body.resize(width, height);
		this.hit = new uHit(this);
	}

	@Override
	public void reposition(int x, int y) {
		this.position.set(x, y);
		this.body.reposition(x, y);
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

	@Override
	public void camOn() {
		this.owner.getScreen().addActor(this);
	}

	@Override
	public void camOff() {
		this.remove();
	}

	@Override
	public Actor hit(float x, float y, boolean touchable) {
		if (touchable && this.getTouchable() != Touchable.enabled)
			return null;
		if (!isVisible())
			return null;
		if (body == null)
			return null;
		if (this.hit == null)
			return null;

		return (Actor) this.hit.hit(x, y, touchable); //
	}

	@Override
	public void buildInputListener() {
		this.listener = new /* ClickListener() */ uInputListener(this) {
			public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
				super.enter(event, x, y, pointer, fromActor);
				if (isOver())
					mouseEnter();
			}

			public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor) {
				super.exit(event, x, y, pointer, fromActor);
				if (isOver() == false)
					mouseExit();

			}

			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				if (super.touchDown(event, x, y, pointer, button) == true) {
					onClick();

					return true;
				} else

					return false;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {				
				super.touchUp(event, x, y, pointer, button);
				
				onRelease();

			}

			public void clicked(InputEvent event, float x, float y) {
				if (getTapCount() > 1) {
					// println(getName() + " DoubleClick");
					onDouble();
				}
			}

		};
		this.addListener(listener);
	}

	@Override
	public void onClick() {
		// println("VOID_CLICK");
		this.castEvent("VOID_CLICK");
		this.getSpace().getScreen().invalidateOver(this);
	}

	@Override
	public iSpace getSpace() {
		return this.owner;
	}

	@Override
	public uDrawable getBody() {
		return this.body;
	}

	@Override
	public void castEvent(String event) {

		this.Director.sendEvent(event, this);

	}

}
