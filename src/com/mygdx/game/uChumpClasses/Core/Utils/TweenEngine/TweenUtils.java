package com.mygdx.game.uChumpClasses.Core.Utils.TweenEngine;

import com.mygdx.game.uChumpClasses.Core.Utils.TweenEngine.Equations.Back;
import com.mygdx.game.uChumpClasses.Core.Utils.TweenEngine.Equations.Bounce;
import com.mygdx.game.uChumpClasses.Core.Utils.TweenEngine.Equations.Circ;
import com.mygdx.game.uChumpClasses.Core.Utils.TweenEngine.Equations.Cubic;
import com.mygdx.game.uChumpClasses.Core.Utils.TweenEngine.Equations.Elastic;
import com.mygdx.game.uChumpClasses.Core.Utils.TweenEngine.Equations.Expo;
import com.mygdx.game.uChumpClasses.Core.Utils.TweenEngine.Equations.Linear;
import com.mygdx.game.uChumpClasses.Core.Utils.TweenEngine.Equations.Quad;
import com.mygdx.game.uChumpClasses.Core.Utils.TweenEngine.Equations.Quart;
import com.mygdx.game.uChumpClasses.Core.Utils.TweenEngine.Equations.Quint;
import com.mygdx.game.uChumpClasses.Core.Utils.TweenEngine.Equations.Sine;

/**
 * Collection of miscellaneous utilities.
 *
 * @author Aurelien Ribon | http://www.aurelienribon.com/
 */
public class TweenUtils {
	private static TweenEquation[] easings;

	/**
	 * Takes an easing name and gives you the corresponding TweenEquation.
	 * You probably won't need this, but tools will love that.
	 *
	 * @param easingName The name of an easing, like "Quad.INOUT".
	 * @return The parsed equation, or null if there is no match.
	 */
	public static TweenEquation parseEasing(String easingName) {
		if (easings == null) {
			easings = new TweenEquation[] {Linear.INOUT,
				Quad.IN, Quad.OUT, Quad.INOUT,
				Cubic.IN, Cubic.OUT, Cubic.INOUT,
				Quart.IN, Quart.OUT, Quart.INOUT,
				Quint.IN, Quint.OUT, Quint.INOUT,
				Circ.IN, Circ.OUT, Circ.INOUT,
				Sine.IN, Sine.OUT, Sine.INOUT,
				Expo.IN, Expo.OUT, Expo.INOUT,
				Back.IN, Back.OUT, Back.INOUT,
				Bounce.IN, Bounce.OUT, Bounce.INOUT,
				Elastic.IN, Elastic.OUT, Elastic.INOUT
			};
		}

		for (int i=0; i<easings.length; i++) {
			if (easingName.equals(easings[i].toString()))
				return easings[i];
		}

		return null;
	}
}