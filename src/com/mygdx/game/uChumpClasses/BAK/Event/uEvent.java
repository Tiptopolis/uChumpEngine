package com.mygdx.game.uChumpClasses.BAK.Event;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.uChumpClasses.Core.iObject;

import static com.mygdx.game.uChumpClasses.uAppUtils.*;


public class uEvent implements iEvent, Comparable<uEvent> {

	//for Custom Events
	
	public static final int HIGH_PRIORITY = 1;
	public static final int DEFAULT_PRIORITY = -1;

	//maybe replace with "Type"?
	private String event; // local, global, system, ui, world, whatever. defines event index to use
	private int scopeID;
	private long time;
	private long priority;
	//private Actor actor;//change to iNode?
	private iObject caster;

	/**
	 * Populate event with default priority.
	 * 
	 * @param id
	 * @param node
	 * @param time
	 */
	public void populate(String event, int scope, iObject caster, long time) {
		// When no priority is specified we adopt the time-stamp.
		populate(event, scope, caster, time, time);
	}

	/**
	 * Populate data event from system event.
	 * 
	 * @param id   Event id.
	 * @param node Associated event actor.
	 * @param time Event time.
	 */
	public void populate(String event, int scope, iObject caster, long time, long priority) {
		// Copy fields.
		this.event = event;
		
		this.scopeID = scope;

		this.caster = caster;

		this.time = time;

		if (priority == DEFAULT_PRIORITY) {
			this.priority = time;
		} else {
			this.priority = priority;
		}

	}

	/**
	* Get event scope.
	*
	*@return The scope.
	*/
	
	public String getEvent()
	{
		return event;
	}
	
	/**
	 * Get event id.
	 * 
	 * @return The id.
	 */
	public int getId() {
		return scopeID;
	}

	/**
	 * Get actor.
	 * 
	 * @return The Actor.
	 */
	public iObject getCaster() {
		return caster;
	}

	/**
	 * Get timestamp.
	 * 
	 * @return
	 */
	public long getTime() {
		return time;
	}

	/**
	 * Return priority value.
	 * 
	 * @return The value.
	 */
	public long getPriority() {
		return priority;
	}

	/**
	 * Set priority.
	 * 
	 * @param priority
	 */
	public void setPriority(int priority) {
		this.priority = priority;
	}

	/**
	 * Implements comparison.
	 * 
	 * The lower the priority value the higher the
	 */
	@Override
	public int compareTo(uEvent o) {
		if (priority < o.getPriority()) {
			return -1;
		} else if (priority > o.getPriority()) {
			return 1;
		}

		return 0;
	}

}
