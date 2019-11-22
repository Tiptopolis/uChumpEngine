package com.mygdx.game.uChumpClasses.Core.Event;

import com.badlogic.gdx.scenes.scene2d.Actor;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.google.inject.Singleton;
import com.mygdx.game.uChumpClasses.Core.iObject;
import com.mygdx.game.uChumpClasses.Core.Utils.TweenEngine.Tween;
import com.mygdx.game.uChumpClasses.Core.Utils.TweenEngine.Tweens.ActorAccessor;
import com.mygdx.game.uChumpClasses.Core.Utils.TweenEngine.Tweens.GroupAccessor;

import static com.mygdx.game.uChumpClasses.uAppUtils.*;

@Singleton
public class uDirector {

	private uEventSource eventSource;

	// UI event handler

	/**
	 * Construct Director elements.
	 * 
	 */
	public uDirector() {

		// Director maintains event source.
		eventSource = new uEventSource();

		// Set up tween default configuration.
		Tween.registerAccessor(Actor.class, new ActorAccessor());
		Tween.registerAccessor(Group.class, new GroupAccessor());
	}

	public void update() {
		// Update events.
		eventSource.update();
	}

	/**
	 * Send event to observers.
	 * 
	 * @param event The event name.
	 * @param scope The event's scope of affect
	 * @param actor The associated actor.
	 */
	public void sendEvent(String event, int scope, iObject caster) {

		eventSource.sendEvent(event, scope, caster, uEvent.DEFAULT_PRIORITY);
	}

	public void sendEvent(int id, iObject caster) {
		this.sendEvent("", id, caster);
	}

	public void sendEvent(String event, iObject caster) {
		this.sendEvent(event, 0, caster);
	}

	public void sendEvent(String event, iObject caster, int priority) {
		eventSource.sendEvent(event, 0, caster, priority);
	}

	/**
	 * Send event to observers.
	 * 
	 * @param id       The event id.
	 * @param actor    The associated actor.
	 * @param priority Adopt a priority value. Must be > 0.
	 */
	public void sendEvent(int scope, iObject caster, int priority) {

		eventSource.sendEvent("", scope, caster, priority);
	}

	public void sendEvent(String event, int scope, iObject caster, int priority) {

		eventSource.sendEvent(event, scope, caster, priority);
	}

	/**
	 * Add event observer event handler.
	 * 
	 * DO NOT PUT THIS INTO THE CONSTRUCTOR. IT MUST GO INTO THE "ENTER" HANDLER.
	 * 
	 * @param observer The event observer.
	 */
	public void registerEventHandler(iEventObserver observer) {

		eventSource.addObserver(observer);
	}

	/**
	 * Remove event observer event handler.
	 * 
	 * DO NOT FORGET TO PUT THIS INTO THE "EXIT" HANDLER IF YOU HAVE MATCHING
	 * "REGISTER" IN THE ENTER HANDLER.
	 * 
	 * @param observer The event observer.
	 */
	public void deregisterEventHandler(iEventObserver observer) {
		eventSource.removeObserver(observer);
	}

	/**
	 * Clear all handlers.
	 * 
	 */
	public void clearEventHandlers() {
		// Clear all event subscriptions.
		eventSource.clear();
	}

	public void setEventSource(uEventSource eventSource) {
		this.eventSource = eventSource;
	}

}

//local events are handled by their appropriate doodads