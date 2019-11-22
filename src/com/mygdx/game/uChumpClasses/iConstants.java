package com.mygdx.game.uChumpClasses;

import com.badlogic.gdx.math.MathUtils;

public interface iConstants {
	static public final int X = 0;
	static public final int Y = 1;
	static public final int Z = 2;

	static final float PI = (float) Math.PI;
	static final float HALF_PI = (float) (Math.PI / 2.0);
	static final float THIRD_PI = (float) (Math.PI / 3.0);
	static final float QUARTER_PI = (float) (Math.PI / 4.0);

	static final float TWO_PI = (float) (2.0 * Math.PI);
	static final float TAU = (float) (2.0 * Math.PI);

	static final float DEG_TO_RAD = PI / 180.0f;
	static final float RAD_TO_DEG = 180.0f / PI;

	static final String WHITESPACE = " \t\n\r\f\u00A0";

	static public final float uPI = 3.1415927f; // short PI
	// static public final float PI = 3.14159265358979323846f;
	static public final float uPI2 = uPI * 2;
	// static public final float PI2 = PI * 2;

	static public final float uPHIr = 1.6180339f; // short PHI ratio
	static public final float PHIr = 1.61803398874989484820f;

	static public final float test = (float) Math.sqrt((float) 2);
	static public final float PHI = (float) (Math.sqrt(5) + 1) / 2 - 1;

	static public final float uGOLDEN = 3.8832223f; // short Golden ratio
	static public final float GOLDEN = (float) ((Math.sqrt(5) - 1) * 0.5) * MathUtils.PI2;

	static public final float GOLDANGLE = PHIr; // alias

	static public final float uE = 2.7182818f; // short E
}
