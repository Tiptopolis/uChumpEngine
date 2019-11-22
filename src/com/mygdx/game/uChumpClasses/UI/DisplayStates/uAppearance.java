package com.mygdx.game.uChumpClasses.UI.DisplayStates;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.mygdx.game.uChumpClasses.UI.Drawable.uDrawable;

import static com.mygdx.game.uChumpClasses.uAppUtils.*;


public class uAppearance {

	// the purpose of separating out color stuff into a 'uAppearance' component
	// is to allow all custom drawables to use the same methods

	// colorMap?
	public ArrayList<Color> ColorList = new ArrayList<Color>();//maybe not
	
	public uDrawable parent;

	public Color uColor = new Color(); // main reference color
	public Color tColor = new Color(); // target color, for transitions
	public Color uStroke = new Color();
	public Color tStroke = new Color();
	public Color uFill = new Color();
	public Color tFill = new Color();
	
	public int uStrokeWeight = 1;

	// booleans
	public boolean doFill = true;
	public boolean doStroke = true;

	public boolean doTransColor = false;
	public boolean doTransStroke = false;
	public boolean doTransFill = false;

	public boolean doFadeColor = false;
	public boolean doFadeStroke = false;
	public boolean doFadeFill = false;

	// trans factors
	public final float tFactor = 0.1f;// generic trans factor, may remove

	public float tColorFactor = 0.1f;
	public float tFillFactor = 0.1f;
	public float tStrokeFactor = 0.1f;

	public float tStrokeFade = 0.1f; // 0-1
	public float tFillFade = 0.1f; // 0-1
	public float tColorFade = 0.1f; // 0-1

	public uAppearance() {

		//uColor = Color.BLACK;
		//tColor = Color.BLACK;
		//ColorList.add(uColor);
		//ColorList.add(tColor);

		//uStroke = Color.BLACK;
		//tStroke = Color.BLACK;
		//ColorList.add(uStroke);
		//ColorList.add(tStroke);

		//uFill = Color.BLACK;
		//tFill = Color.BLACK;
		//ColorList.add(uFill);
		//ColorList.add(tFill);

	}
	
	public uAppearance(uDrawable parent)
	{
		this.parent = parent;
	}

	// GET-SET

	// Set uColor
	public void setColor(Color c) {
		// this.uColor.set(c);
		this.uColor.r = c.r;
		this.uColor.g = c.g;
		this.uColor.b = c.b;
		this.uColor.a = c.a;
		this.setStroke(uColor);
		this.setFill(uColor);
		this.doFill = true;
		this.doStroke = true;
	}

	public void setColor(Color c, float alpha) {
		this.uColor.set(c.r, c.g, c.b, alpha);
		this.uStroke.set(uColor);
		this.uFill.set(uColor);
		this.doFill = true;
		this.doStroke = true;
	}

	public void setColor(int r, int g, int b) {
		this.uColor.a = 1f;
		this.uColor.r = (float) r / 255f;
		this.uColor.g = (float) g / 255f;
		this.uColor.b = (float) b / 255f;
		this.uStroke.set(uColor);
		this.uFill.set(uColor);
		this.doFill = true;
		this.doStroke = true;
	}

	public void setColor(float r, float g, float b) {
		this.uColor.a = 1;
		this.uColor.r = r / 255f;
		this.uColor.g = g / 255f;
		this.uColor.b = b / 255f;
		this.uStroke.set(uColor);
		this.uFill.set(uColor);
		this.doFill = true;
		this.doStroke = true;
	}

	public void setColor(int r, int g, int b, int a) {
		this.uColor.a = (float) a / 255f;
		this.uColor.r = (float) r / 255f;
		this.uColor.g = (float) g / 255f;
		this.uColor.b = (float) b / 255f;
		this.uStroke.set(uColor);
		this.uFill.set(uColor);
		this.doFill = true;
		this.doStroke = true;
	}

	public void setColor(float r, float g, float b, float a) {
		this.uColor.a = a / 255f;
		this.uColor.r = r / 255f;
		this.uColor.g = g / 255f;
		this.uColor.b = b / 255f;
		this.uStroke.set(uColor);
		this.uFill.set(uColor);
		this.doFill = true;
		this.doStroke = true;
	}

	// Get uColor
	public Color getColor() {
		return this.uColor;
	}

	// Set uFill
	public void setFill(Color c) {

		this.uFill.set(c);
		this.doFill = true;
	}

	public void setFill(Color c, float alpha) {

		this.uFill.set(c.r, c.g, c.b, alpha);
		this.doFill = true;
	}

	public void setFill(int r, int g, int b) {
		this.uFill.a = 1f;
		this.uFill.r = (float) r / 255f;
		this.uFill.g = (float) g / 255f;
		this.uFill.b = (float) b / 255f;
		this.doFill = true;

	}

	public void setFill(float r, float g, float b) {
		this.uFill.a = 1;
		this.uFill.r = r / 255f;
		this.uFill.g = g / 255f;
		this.uFill.b = b / 255f;
		this.doFill = true;

	}

	public void setFill(int r, int g, int b, int a) {
		this.uFill.a = (float) a / 255f;
		this.uFill.r = (float) r / 255f;
		this.uFill.g = (float) g / 255f;
		this.uFill.b = (float) b / 255f;
		this.doFill = true;

	}

	public void setFill(float r, float g, float b, float a) {
		this.uFill.a = a / 255f;
		this.uFill.r = r / 255f;
		this.uFill.g = g / 255f;
		this.uFill.b = b / 255f;
		this.doFill = true;

	}

	// Get uFill
	public Color getFill() {
		return this.uFill;
	}
	
	public void noFill()
	{
		this.doFill = false;
	}

	// Set Stroke
	public void setStroke(Color c) {

		this.uStroke.set(c);
		this.doStroke = true;
	}

	public void setStroke(Color c, float alpha) {

		this.uStroke.set(c.r, c.g, c.b, alpha);
		this.doStroke = true;
	}

	public void setStroke(int r, int g, int b) {
		this.uStroke.a = 1f;
		this.uStroke.r = (float) r / 255f;
		this.uStroke.g = (float) g / 255f;
		this.uStroke.b = (float) b / 255f;
		this.doStroke = true;

	}

	public void setStroke(float r, float g, float b) {
		this.uStroke.a = 1;
		this.uStroke.r = r / 255f;
		this.uStroke.g = g / 255f;
		this.uStroke.b = b / 255f;
		this.doStroke = true;

	}

	public void setStroke(int r, int g, int b, int a) {
		this.uStroke.a = (float) a / 255f;
		this.uStroke.r = (float) r / 255f;
		this.uStroke.g = (float) g / 255f;
		this.uStroke.b = (float) b / 255f;
		this.doStroke = true;

	}

	public void setStroke(float r, float g, float b, float a) {
		this.uStroke.a = a / 255f;
		this.uStroke.r = r / 255f;
		this.uStroke.g = g / 255f;
		this.uStroke.b = b / 255f;
		this.doStroke = true;

	}

	// Get Stroke
	public Color getStroke() {
		return this.uStroke;
	}
	
	public void noStroke()
	{
		this.doStroke = false;
	}

	// Set Alpha
	public void setAlpha(Color c, float t)// 0-1
	{
		c.set(c.r, c.g, c.b, t);// ????
	}

	public void setAlphaColor(float t) {
		this.uColor.set(uColor.r, uColor.g, uColor.b, t);
		
	}

	public void setAlphaFill(float t) {
		this.uFill.set(uFill.r, uFill.g, uFill.b, t);
	}

	public void setAlphaStroke(float t) {
		this.uStroke.set(uStroke.r, uStroke.g, uStroke.b, t);
	}

	public float getAlpha(Color c) {
		return c.a;
	}

	// SET TRANS COLORS
	// Set transColor
	public void setTransColor(Color c) {
		this.tColor.set(c);
		this.setTransStroke(uColor);
		this.setTransFill(uColor);
		doTransColor = true;
	}

	public void setTransColor(Color c, float alpha) {//the speed is off
		this.tColor.set(c.r, c.g, c.b, alpha);
		//this.tFill.set(tColor);
		//this.tStroke.set(tColor);
		doTransColor = true;
	
		//doTransFill = true;
		//doTransStroke = true;
	}

	public void setTransColor(int r, int g, int b) {
		this.tColor.r = (float) r / 255f;
		this.tColor.g = (float) g / 255f;
		this.tColor.b = (float) b / 255f;
		this.tColor.a = 1f;
		this.tFill.set(tColor);
		this.tStroke.set(tColor);
		//doTransColor = true;
		doTransFill = true;
		doTransStroke = true;
	}

	public void setTransColor(float r, float g, float b) {
		this.tColor.r = r / 255f;
		this.tColor.g = g / 255f;
		this.tColor.b = b / 255f;
		this.tColor.a = 1f;
		this.tFill.set(tColor);
		this.tStroke.set(tColor);
		//doTransColor = true;
		doTransFill = true;
		doTransStroke = true;
	}

	public void setTransColor(int r, int g, int b, int a) {
		this.tColor.r = (float) r / 255f;
		this.tColor.g = (float) g / 255f;
		this.tColor.b = (float) b / 255f;
		this.tColor.a = (float) a / 255f;
		this.tFill.set(tColor);
		this.tStroke.set(tColor);
		//doTransColor = true;
		doTransFill = true;
		doTransStroke = true;
	}

	public void setTransColor(float r, float g, float b, float a) {
		this.tColor.r = r / 255f;
		this.tColor.g = g / 255f;
		this.tColor.b = b / 255f;
		this.tColor.a = a / 255f;
		this.tFill.set(tColor);
		this.tStroke.set(tColor);
		//doTransColor = true;
		doTransFill = true;
		doTransStroke = true;
	}

	// Get TransColor
	public Color getTransColor() {
		return this.tColor;
	}

	// Set transFill
	public void setTransFill(Color c) {
		tFill.set(c);
		doTransFill = true;
	}

	public void setTransFill(Color c, float alpha) {
		tFill.set(c.r, c.g, c.b, alpha);
		doTransFill = true;
	}

	public void setTransFill(float r, float g, float b) {
		tFill.r = r / 255f;
		tFill.g = g / 255f;
		tFill.b = b / 255f;
		tFill.a = 1f;
		doTransFill = true;
	}

	public void setTransFill(int r, int g, int b) {
		tFill.r = (float) r / 255f;
		tFill.g = (float) g / 255f;
		tFill.b = (float) b / 255f;
		tFill.a = 1f;
		doTransFill = true;
	}

	public void setTransFill(float r, float g, float b, float a) {
		tFill.r = r / 255f;
		tFill.g = g / 255f;
		tFill.b = b / 255f;
		tFill.a = a / 255f;
		doTransFill = true;
		doTransFill = true;
	}

	public void setTransFill(int r, int g, int b, int a) {
		tFill.r = (float) r / 255f;
		tFill.g = (float) g / 255f;
		tFill.b = (float) b / 255f;
		tFill.a = (float) a / 255f;
		doTransFill = true;
	}

	// Get transFill
	public Color getTransFill() {
		return tFill;
	}

	// Set transStroke
	public void setTransStroke(Color c) {
		tStroke.set(c);
		doTransStroke = true;
	}

	public void setTransStroke(Color c, float alpha) {
		tStroke.r = c.r;
		tStroke.g = c.g;
		tStroke.b = c.b;
		tStroke.a = alpha;
		doTransStroke = true;
	}

	public void setTransStroke(float r, float g, float b) {
		tStroke.r = r / 255f;
		tStroke.g = g / 255f;
		tStroke.b = b / 255f;
		tStroke.a = 1f;
		doTransStroke = true;
	}

	public void setTransStroke(int r, int g, int b) {
		tStroke.r = (float) r / 255f;
		tStroke.g = (float) g / 255f;
		tStroke.b = (float) b / 255f;
		tStroke.a = 1f;
		doTransStroke = true;
	}

	public void setTransStroke(float r, float g, float b, float a) {
		tStroke.r = r / 255f;
		tStroke.g = g / 255f;
		tStroke.b = b / 255f;
		tStroke.a = a / 255f;
		doTransStroke = true;

	}

	public void setTransStroke(int r, int g, int b, float a) {
		tStroke.r = (float) r / 255f;
		tStroke.g = (float) g / 255f;
		tStroke.b = (float) b / 255f;
		tStroke.a = (float) a / 255f;
		doTransStroke = true;
	}

	// Get transStroke
	public Color getTransStroke() {
		return tStroke;
	}

	/*
	 * public Color colerp(Color current, Color target, float t) { Color colorX =
	 * target; Color colerC = current; if (Color.colorIs(colerC, colorX, t) ==
	 * false) {
	 * 
	 * colerC = colerC.lerp(colorX, t / 20); return clamp(); } else { return colorX;
	 * } }
	 */

	// DO TRANSITION
	// uColor
	public void transColor() {
		if (isColor(uColor, tColor, tColorFactor) == false) {
			//uColor.lerp(tColor, tColorFactor / 20);
			uColor.lerp(tColor, tColorFactor);
			this.uStroke.set(uColor);
			this.uFill.set(uColor);
		} else {
			uColor.set(tColor);
			this.uStroke.set(uColor);
			this.uFill.set(uColor);
			doTransColor = false;
		}

	}

	public void transColor(float f) {
		tColorFactor = f;
		if (isColor(uColor, tColor, tColorFactor) == false) {
			//uColor.lerp(tColor, tColorFactor / 20);
			uColor.lerp(tColor, tColorFactor / 20);
			this.uStroke.set(uColor);
			this.uFill.set(uColor);
		} else {
			uColor.set(tColor);
			this.uStroke.set(uColor);
			this.uFill.set(uColor);
			doTransColor = false;
		}

	}

	// uFill
	public void transFill() {
		if (isColor(uFill, tFill, tFillFactor) == false) {
			uFill.lerp(tFill, tFillFactor / 20);
		} else {
			uFill.set(tFill);
			doTransFill = false;
		}
	}

	public void transFill(float f) {
		tFillFactor = f;
		if (isColor(uFill, tFill, tFillFactor) == false) {
			uFill.lerp(tFill, tFillFactor / 20);
		} else {
			uFill.set(tFill);
			doTransFill = false;
		}
	}

	// uStroke
	public void transStroke() {
		if (isColor(uStroke, tStroke, tStrokeFactor) == false) {
			uStroke.lerp(tStroke, tStrokeFactor/20);
		} else {
			uStroke.set(tStroke);
			doTransStroke = false;
		}
	}

	public void transStroke(float f) {
		tStrokeFactor = f;
		if (isColor(uStroke, tStroke, tStrokeFactor) == false) {
			uStroke.lerp(tStroke, tStrokeFactor/20);
		} else {
			uStroke.set(tStroke);
			doTransStroke = false;
		}
	}

	// add methods for stopping trans
	// Fade

}