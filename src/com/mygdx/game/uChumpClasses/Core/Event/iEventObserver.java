package com.mygdx.game.uChumpClasses.Core.Event;


public interface iEventObserver {
	//event listener
	
		/**
		 * Handle controller events.
		 * 
		 * @param event
		 *            The actor event.
		 * 
		 * @return True if handled.
		 */
		public boolean handleEvent(uEvent event);
		
}
