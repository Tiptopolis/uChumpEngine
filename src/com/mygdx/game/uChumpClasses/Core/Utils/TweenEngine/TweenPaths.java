package com.mygdx.game.uChumpClasses.Core.Utils.TweenEngine;

import com.mygdx.game.uChumpClasses.Core.Utils.TweenEngine.Paths.CatmullRom;
import com.mygdx.game.uChumpClasses.Core.Utils.TweenEngine.Paths.Linear;

/**
 * Collection of built-in paths.
 *
 * @author Aurelien Ribon | http://www.aurelienribon.com/
 */
public interface TweenPaths {
	public static final Linear linear = new Linear();
	public static final CatmullRom catmullRom = new CatmullRom();
}