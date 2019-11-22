package com.mygdx.game.uChumpClasses.Core.Event;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.uChumpClasses.Core.iObject;

import static com.mygdx.game.uChumpClasses.uAppUtils.*;


public class uEventSource {
	//event handler
		private final static int OBSERVERS_CAPACITY = 10;

		private final static int MAX_EVENTS = 100;

		private ArrayList<iEventObserver> observers;
		private LinkedList<uEvent> pool;
		private PriorityQueue<uEvent> events;

		/**
		 * Create observer list.
		 * 
		 */
		public uEventSource()
		{
			observers = new ArrayList<iEventObserver>(OBSERVERS_CAPACITY);

			pool = new LinkedList<uEvent>();
			events = new PriorityQueue<uEvent>(MAX_EVENTS);

			// Initialise the event pool.
			for (int index = 0; index < MAX_EVENTS; index++)
			{
				pool.add(new uEvent());
			}
		}

		/**
		 * Update any observers.
		 * 
		 */
		public void update()
		{
			if (events.size() > 0)
			{
				uEvent event = events.poll();

				if (event != null)
				{
					boolean handled = false;

					int size = observers.size();
					for (int index = 0; index < size; index++)
					{
						handled = observers.get(index).handleEvent(event);

						if (handled)
							break;
					}

					// Place data event back onto pool.
					pool.add(event);
				}
			}
		}

		/**
		 * Add observer.
		 * 
		 * @param observer
		 *            The target observer.
		 */
		public synchronized void addObserver(iEventObserver observer)
		{
			if (!observers.contains(observer))
			{
				observers.add(observer);
			}
		}

		/**
		 * Remove observer.
		 * 
		 * @param observer
		 *            Target event observer.
		 */
		public synchronized void removeObserver(iEventObserver observer)
		{
			if (observers.contains(observer))
			{
				observers.remove(observer);
			}
		}

		/**
		 * Enqueue event
		 * 
		 * @param id
		 *            The event id.
		 * @param actor
		 *            The associated actor.
		 */
		public void sendEvent(String event, int scope, iObject caster, int priority)
		{
			processEvent(event, scope, caster, priority);
		}

		/**
		 * Map event data to pooled event.
		 * 
		 * Place pooled event on to broadcast queue.
		 * 
		 * @param id
		 *            The event id.
		 * @param actor
		 *            The associated actor.
		 */
		private void processEvent(String eventName, int scope, iObject caster, int priority)
		{
			uEvent event = pool.poll();

			if (event == null)
			{
				event = new uEvent();

				Gdx.app.log("ActorEventSource", "Warning, having to create pooled event, consider making the inital pool size larger, " + pool.size());
			}

			event.populate(eventName, scope, caster, System.currentTimeMillis(), priority);

			events.add(event);
			
		}

		/**
		 * Clear structures.
		 * 
		 */
		public void clear()
		{
			observers.clear();
			events.clear();
		}

}
