package com.mygdx.game.uChumpClasses.UI.Util;

import java.util.ArrayList;

import com.google.inject.Singleton;
import com.mygdx.game.uChumpClasses.UI.iSpace;

import static com.mygdx.game.uChumpClasses.uAppUtils.*;

@Singleton
public class uFrameBufferHandler {

	public ArrayList<iSpace> RegisteredSpaces = new ArrayList<iSpace>();

	public void registerSpace(iSpace source) {
		if (this.RegisteredSpaces.contains(source) != true) {
			this.RegisteredSpaces.add(source);
		}
	}

	public void deregisterSpace(iSpace source) {
		if (this.RegisteredSpaces.contains(source) == true) {

			this.RegisteredSpaces.remove(source);
		}
	}

	public void update(float deltaTime) {
		for (iSpace s : this.RegisteredSpaces) {

			s.drawBuffer(deltaTime);
		}

	}

	public boolean checkRegistered(iSpace source) {
		if (this.RegisteredSpaces.contains(source)) {
			return true;
		}
		return false;
	}

}
