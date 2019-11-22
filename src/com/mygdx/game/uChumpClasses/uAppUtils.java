package com.mygdx.game.uChumpClasses;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.graphics.Texture;

import static com.mygdx.game.uChumpClasses.Core.Utils.uCastingUtils.*;

import java.util.LinkedHashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class uAppUtils implements iConstants {

	// import static com.mygdx.game.uChumpClasses.uAppUtils.*;

	public final static float pixoff = 0.375f;

	// Size/Projection stuff
	public static int Width = Gdx.graphics.getWidth();
	public static int Height = Gdx.graphics.getHeight();
	public static int Width_Half = Width / 2;
	public static int Height_Half = Height / 2;

	public static float AspectRatio = Width / Height;

	public static boolean ForceMaxSizeWidth;
	public static boolean ForceMaxSizeHeight;

	public static void update() {
		Width = Gdx.graphics.getWidth();
		Height = Gdx.graphics.getHeight();
		Width_Half = Width / 2;
		Height_Half = Height / 2;

		AspectRatio = Width / Height;
	}

	public static void resize() {
		int tmpW = Width;
		int tmpH = Height;

		Width = Gdx.graphics.getWidth();
		Height = Gdx.graphics.getHeight();
		AspectRatio = Width / Height;

	}

	// *********** //
	// PRINT STUFF //
	// *********** //

	static public void print(byte what) {
		System.out.print(what);
		System.out.flush();
	}

	static public void print(boolean what) {
		System.out.print(what);
		System.out.flush();
	}

	static public void print(char what) {
		System.out.print(what);
		System.out.flush();
	}

	static public void print(int what) {
		System.out.print(what);
		System.out.flush();
	}

	static public void print(long what) {
		System.out.print(what);
		System.out.flush();
	}

	static public void print(float what) {
		System.out.print(what);
		System.out.flush();
	}

	static public void print(double what) {
		System.out.print(what);
		System.out.flush();
	}

	static public void print(String what) {
		System.out.print(what);
		System.out.flush();
	}

	/**
	 * @param variables list of data, separated by commas
	 */
	static public void print(Object... variables) {
		StringBuilder sb = new StringBuilder();
		for (Object o : variables) {
			if (sb.length() != 0) {
				sb.append(" ");
			}
			if (o == null) {
				sb.append("null");
			} else {
				sb.append(o.toString());
			}
		}
		System.out.print(sb.toString());
	}

	// PRINTLN
	static public void println() {
		System.out.println();
	}

	static public void println(byte what) {
		System.out.println(what);
		System.out.flush();
	}

	static public void println(boolean what) {
		System.out.println(what);
		System.out.flush();
	}

	static public void println(char what) {
		System.out.println(what);
		System.out.flush();
	}

	static public void println(int what) {
		System.out.println(what);
		System.out.flush();
	}

	static public void println(long what) {
		System.out.println(what);
		System.out.flush();
	}

	static public void println(float what) {
		System.out.println(what);
		System.out.flush();
	}

	static public void println(double what) {
		System.out.println(what);
		System.out.flush();
	}

	static public void println(String what) {
		System.out.println(what);
		System.out.flush();
	}

	static public void println(Object... variables) {
//		    System.out.println("got " + variables.length + " variables");
		print(variables);
		println();
	}

	static public void println(Object what) {
		if (what == null) {
			System.out.println("null");
		} else if (what.getClass().isArray()) {
			printArray(what);
		} else {
			System.out.println(what.toString());
			System.out.flush();
		}
	}

	static public void printArray(Object what) {
		if (what == null) {
			// special case since this does fuggly things on > 1.1
			System.out.println("null");

		} else {
			String name = what.getClass().getName();
			if (name.charAt(0) == '[') {
				switch (name.charAt(1)) {
				case '[':
					// don't even mess with multi-dimensional arrays (case '[')
					// or anything else that's not int, float, boolean, char
					System.out.println(what);
					break;

				case 'L':
					// print a 1D array of objects as individual elements
					Object poo[] = (Object[]) what;
					for (int i = 0; i < poo.length; i++) {
						if (poo[i] instanceof String) {
							System.out.println("[" + i + "] \"" + poo[i] + "\"");
						} else {
							System.out.println("[" + i + "] " + poo[i]);
						}
					}
					break;

				case 'Z': // boolean
					boolean zz[] = (boolean[]) what;
					for (int i = 0; i < zz.length; i++) {
						System.out.println("[" + i + "] " + zz[i]);
					}
					break;

				case 'B': // byte
					byte bb[] = (byte[]) what;
					for (int i = 0; i < bb.length; i++) {
						System.out.println("[" + i + "] " + bb[i]);
					}
					break;

				case 'C': // char
					char cc[] = (char[]) what;
					for (int i = 0; i < cc.length; i++) {
						System.out.println("[" + i + "] '" + cc[i] + "'");
					}
					break;

				case 'I': // int
					int ii[] = (int[]) what;
					for (int i = 0; i < ii.length; i++) {
						System.out.println("[" + i + "] " + ii[i]);
					}
					break;

				case 'J': // int
					long jj[] = (long[]) what;
					for (int i = 0; i < jj.length; i++) {
						System.out.println("[" + i + "] " + jj[i]);
					}
					break;

				case 'F': // float
					float ff[] = (float[]) what;
					for (int i = 0; i < ff.length; i++) {
						System.out.println("[" + i + "] " + ff[i]);
					}
					break;

				case 'D': // double
					double dd[] = (double[]) what;
					for (int i = 0; i < dd.length; i++) {
						System.out.println("[" + i + "] " + dd[i]);
					}
					break;

				default:
					System.out.println(what);
				}
			} else { // not an array
				System.out.println(what);
			}
		}
		System.out.flush();
	}

	// ********** //
	// UTILITIES //
	// ********** //

	// ********** //
	// MATH STUFF //
	// ********** //
	// (x-a)*(x-b)<0
	static public boolean isPointBetween(Vector2 pointA, Vector2 lowLimit, Vector2 upLimit, Vector2 lowMod,
			Vector2 upMod) {
		if (upMod == null)
			upMod = new Vector2(0, 0);
		if (lowMod == null)
			lowMod = new Vector2(0, 0);
		if ((pointA.x - (lowLimit.x - lowMod.x)) * (pointA.x - (upLimit.x + upMod.x)) < 0
				&& (pointA.y - (lowLimit.y - lowMod.y)) * (pointA.y - (upLimit.y + upMod.y)) < 0) {
			return true;
		} else {
			return false;
		}
	}

	// (x-a)*(x-b)<0
	static public boolean isPointInArea(Vector2 pointA, Vector2 lowLimit, Vector2 upLimit, Vector2 lowMod,
			Vector2 upMod) {
		if (upMod == null)
			upMod = new Vector2(0, 0);
		if (lowMod == null)
			lowMod = new Vector2(0, 0);
		if ((pointA.x - (lowLimit.x - lowMod.x)) * (pointA.x - ((lowLimit.x + upLimit.x) + upMod.x)) < 0
				&& (pointA.y - (lowLimit.y - lowMod.y)) * (pointA.y - ((lowLimit.y + upLimit.y) + upMod.y)) < 0) {
			return true;
		} else {
			return false;
		}
	}

	static public final float radians(float degrees) {
		return degrees * DEG_TO_RAD;
	}

	static public final float degrees(float radians) {
		return radians * RAD_TO_DEG;
	}

	static public final float map(float value, float start1, float stop1, float start2, float stop2) {
		float outgoing = start2 + (stop2 - start2) * ((value - start1) / (stop1 - start1));
		String badness = null;
		if (outgoing != outgoing) {
			badness = "NaN (not a number)";
		} else if (outgoing == Float.NEGATIVE_INFINITY || outgoing == Float.POSITIVE_INFINITY) {
			badness = "infinity";
		}
		if (badness != null) {
			final String msg = String.format("map(%s, %s, %s, %s, %s) called, which returns %s", nf(value), nf(start1),
					nf(stop1), nf(start2), nf(stop2), badness);
		}
		return outgoing;
	}

	// ************** //
	// CUSTOM UTILITY //
	// ************** //

	public static Vector2 downConv(Vector3 vector) {
		Vector2 temp = new Vector2(vector.x, vector.y);
		return temp;
	}

	public static Vector3 upConv(Vector2 vector) {
		Vector3 temp = new Vector3(vector.x, vector.y, 0);
		return temp;
	}

	public static int getWidth() {
		return Width;
	}

	public static int getHeight() {
		return Height;
	}

	public static float colorDist(Color current, Color target) {
		return (float) Math.sqrt(Math.pow(current.r - target.r, 2) + Math.pow(current.g - target.g, 2)
				+ Math.pow(current.b - target.b, 2));
	}

	public static boolean isColor(Color current, Color target, float t) {
		if (colorDist(current, target) < t / 2) {
			return true;
		} else
			return false;

	}

	// length of longest word in string
	

	public static int LongestWordLength(String str) {
		int n = str.length();
		int res = 0, curr_len = 0;
		for (int i = 0; i < n; i++) {
			// If current character is not
			// end of current word.
			if (str.charAt(i) != ' ')
				curr_len++;

			// If end of word is found
			else {
				res = Math.max(res, curr_len);
				curr_len = 0;
			}
		}

		// We do max one more time to consider
		// last word as there won't be any space
		// after last word.
		return Math.max(res, curr_len);
	}

	public static int LongestLineLength(String str) {
		int n = str.length();
		int res = 0, curr_len = 0;
		for (int i = 0; i < n; i++) {
			// If current character is not
			// end of current word.
			if (str.charAt(i) != '\n')
				curr_len++;

			// If end of word is found
			else {
				res = Math.max(res, curr_len);
				curr_len = 0;
			}
		}

		// We do max one more time to consider
		// last word as there won't be any space
		// after last word.
		return Math.max(res, curr_len);
	}

	public static int NumberOfLines(String str) {
		Matcher m = Pattern.compile("\r\n|\r|\n").matcher(str);
		int lines = 1;
		while (m.find()) {
			lines++;
		}
		return lines;
	}

	public static String[] SplitIntoLines(String str) {

		String[] result = str.split("\r\n|\r|\n");

		return result;
	}

	public static String StripVowels(String str) {
		// removes all vowels except the ones at the beginning of a string
		// expand to parse by line
		// seperate string into Lines[], shorten lines, add lines back to result

		char first = str.charAt(0);
		StringBuilder temp = new StringBuilder(str);
		temp.deleteCharAt(0);
		String str1 = temp.toString();
		String result = str1.replaceAll("[AEUOUaeiou]", "");
		result = RemoveDuplicateChars(result); //<for convenience, not permanent
		//eventually, pass size factors and progressively trim vowels & duplicates

		return (first + result);
	}
	
	public static String RemoveLeadingZeros(String str)
	{
		StringBuilder sb = new StringBuilder(str);
	    while (sb.length() > 1 && sb.charAt(0) == '0') {
	        sb.deleteCharAt(0);
	    }
	    return sb.toString();
	}
	
	public static String RemoveTrailingZeros(String str)
	{
		StringBuilder sb = new StringBuilder(str);
	    while (sb.length() > 1 && sb.charAt(sb.length() - 1) == '0') {
	        sb.setLength(sb.length() - 1);
	    }
	    return sb.toString();
	}

	public static String RemoveDuplicateChars(String str) {

		String result = "";
		
		LinkedHashSet<Character> lhs = new LinkedHashSet<>();
		for (int i = 0; i < str.length(); i++)
			lhs.add(str.charAt(i));

		for(Character ch : lhs)
		{
			result = (result + ch);
		}
		
		return result;
	}

	// rounding

	public static int roundTo(float number, int round) {
		return round * (Math.round(number / round));
	}

	public static int roundUp(float number, int round) {
		return (int) (round * (Math.ceil(Math.abs(number / round))));
	}

	public static int roundDown(float number, int round) {
		return (int) (round * (Math.floor(Math.abs(number / round))));
	}

	// Font Generator
	public static BitmapFont generateFont(int size, String fontPath) {
		// float scale = 1.0f * Gdx.graphics.getWidth() / Game.WIDTH;
		float scale = 1.0f;// add layer param, get scale from layer.width
		if (scale < 1)
			scale = 1.0f;

		FreeTypeFontGenerator.FreeTypeFontParameter params = new FreeTypeFontGenerator.FreeTypeFontParameter();
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(fontPath));

		params.size = (int) (size * scale);

		BitmapFont font = generator.generateFont(params);
		font.getData().setScale((float) (1.0 / scale));
		font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

		generator.dispose();
		// println(fontPath + " generated successfully;");

		return font;
	}
}