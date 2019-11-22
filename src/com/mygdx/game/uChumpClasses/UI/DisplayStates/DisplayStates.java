package com.mygdx.game.uChumpClasses.UI.DisplayStates;

import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.graphics.Color;
import com.mygdx.game.uChumpClasses.Core.uEntity;

import static com.mygdx.game.uChumpClasses.uAppUtils.*;

public class DisplayStates {

	public uEntity owner;
	public boolean statesEnabled = true;

	public dState currentState;

	public DisplayStates(uEntity owner) {
		this.owner = owner;
	}

	public List<dState> dSTATES = new LinkedList<dState>() {
		{
			// pull from pre-defined list //zApp.Definitions.DisplayStates.[X]States
			add(new dState("NULL", Color.WHITE, Color.GRAY));
			// add(new dState("MouseOver", Color.YELLOW, Color.GRAY, 2));
		}
	};

	public void handleState() {

		// setCurrentState();
		if (statesEnabled == true) {
			if (this.currentState == null)
				this.currentState = getState("NULL");

			// println(owner + ":::" +currentState.name);
			this.owner.body.appearance.setFill(currentState.dFill);
			this.owner.body.appearance.setStroke(currentState.dStroke);
			this.owner.body.appearance.uStrokeWeight = currentState.weight;
		}

	}

	/*
	 * public void setCurrentState() { if (owner.mouseOver == true) { currentState =
	 * getState("MouseOver"); } else if (owner.mouseOver == false) { currentState =
	 * getState("NULL"); } }
	 */

	public void setState(String name) {
		this.currentState = getState(name);
	}

	public dState getCurrentState() {
		return this.currentState;
	}

	public dState getState(String name) {
		for (dState d : dSTATES) {
			if (d.name == name)
				return d;

		}
		return null; // problem with null here
	}

	public void clear() {
		this.dSTATES.clear();
		this.dSTATES.add(new dState("NULL", Color.WHITE, Color.GRAY));
	}

	public void addState(String name, Color fill, Color stroke, int weight) {
		this.dSTATES.add(new dState(name, fill, stroke, weight));
	}

	public void addState(String name, Color fill, Color stroke) {
		this.dSTATES.add(new dState(name, fill, stroke));
	}

	public void removeState(String name) {
		dState temp = getState(name);
		this.dSTATES.remove(temp);
	}

	public void alterState(String name, Color fill, Color stroke, int weight) {
		dState temp = getState(name);
		if (temp == null) {
			addState(name, fill, stroke, weight);
		} else {
			dSTATES.remove(getState(name));
			addState(name, fill, stroke, weight);
		}
	}

	public void alterState(String name, Color fill, Color stroke) {
		dState temp = getState(name);
		if (temp == null) {
			this.addState(name, fill, stroke);
		} else
			this.dSTATES.remove(getState(name));

		this.addState(name, fill, stroke);
	}

	// ADD METHOD TO PRINT ALL dSTATES

}
