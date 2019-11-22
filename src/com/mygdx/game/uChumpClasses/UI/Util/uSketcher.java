package com.mygdx.game.uChumpClasses.UI.Util;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.SnapshotArray;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.google.inject.Singleton;
//import com.mygdx.game.uChumpClasses.UI.uLayer;
import com.mygdx.game.uChumpClasses.Core.Utils.Fonts.uFontReference;

import static com.mygdx.game.uChumpClasses.uAppUtils.*;

@Singleton
public class uSketcher implements Disposable {

	public String name;
	// uLayer layer;
	public ShapeRenderer sketcher;
	public uPolyRenderer poly;
	public SpriteBatch batch;
	public FreeTypeFontGenerator fontGen;
	public final BitmapFont defaultFont;
	public BitmapFont font;

	public final float pixoff = 0.375f;

	public int strokeWeight = 1;
	public int textWidth = 16;
	public int textHeight = 16;
	public int textSize = 16;

	public Color uColor = new Color(Color.BLACK);// save for utility purposes
	public Color tColor = new Color(Color.BLACK);

	public Color uBackground = new Color(Color.BLACK);
	public Color tBackground = new Color(Color.BLACK);

	public Color uFill = new Color(Color.BLACK);
	public Color tFill = new Color(Color.BLACK);

	public Color uStroke = new Color(Color.BLACK);
	public Color tStroke = new Color(Color.BLACK);

	public Color uTint = new Color(Color.BLACK);
	public Color tTint = new Color(Color.BLACK);

	public Color uNull = new Color(0, 0, 0, 0);

	//
	public boolean doFill = true;
	public boolean doStroke = true;

	public boolean doShapeBatch = false;
	public boolean doPolyBatch = false;
	public boolean doSpriteBatch = false;
	//

	public uSketcher() {
		Gdx.gl.glEnable(GL20.GL_BLEND);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		sketcher = new ShapeRenderer();
		sketcher.setAutoShapeType(true);
		poly = new uPolyRenderer();
		batch = new SpriteBatch();
		font = new BitmapFont();
		defaultFont = new BitmapFont();
		// fontGen = new FreeTypeFontGenerator();

		// Gdx.gl.glDisable(GL20.GL_BLEND);
	}

	public void beginSketch() {
		doShapeBatch = true;
		sketcher.begin(ShapeType.Filled);
	}

	public void beginPoly() {
		doPolyBatch = true;
		poly.begin(ShapeType.Filled);
	}

	public void beginBatch() {
		doSpriteBatch = true;
		batch.begin();
	}

	public void endSketch() {
		doShapeBatch = false;
		sketcher.end();
	}

	public void endPoly() {
		doPolyBatch = false;
		poly.end();

	}

	public void endBatch() {
		doSpriteBatch = false;
		batch.end();
	}

	/*
	 * public void update(uLayer layer) { // Gdx.gl.glDisable(GL20.GL_BLEND); //
	 * Gdx.gl.glClearColor(0, 0, 0, 0); // Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	 * // Gdx.gl.glEnable(GL20.GL_BLEND); // Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA,
	 * GL20.GL_ONE_MINUS_SRC_ALPHA);
	 * 
	 * strokeWeight(1);
	 * 
	 * if (layer.stretch == false) {
	 * 
	 * // sketcher.setProjectionMatrix(layer.getViewport().getCamera().combined);
	 * sketcher.setProjectionMatrix(layer.guiViewport.getCamera().combined);
	 * poly.setProjectionMatrix(layer.guiViewport.getCamera().combined);
	 * batch.setProjectionMatrix(layer.guiViewport.getCamera().combined);
	 * 
	 * }
	 * 
	 * }
	 */

	public SpriteBatch getBatch() {
		return this.batch;
	}

	public void update(Viewport viewport) {
		strokeWeight(1);

		sketcher.setProjectionMatrix(viewport.getCamera().combined);
		poly.setProjectionMatrix(viewport.getCamera().combined);
		batch.setProjectionMatrix(viewport.getCamera().combined);
	}

	public void update(Matrix4 matrix) {
		// sketcher.setProjectionMatrix(layer.getViewport().getCamera().combined);
		strokeWeight(1);

		sketcher.setProjectionMatrix(matrix);
		poly.setProjectionMatrix(matrix);
		batch.setProjectionMatrix(matrix);
	}

	public void update(int w, int h) {
		// sketcher.setProjectionMatrix(layer.getViewport().getCamera().combined);
		strokeWeight(1);

		Matrix4 matrix = new Matrix4();
		matrix.setToOrtho2D(0, 0, w, h);
		sketcher.setProjectionMatrix(matrix);
		poly.setProjectionMatrix(matrix);
		batch.setProjectionMatrix(matrix);
	}

	public void resize(int width, int height) {

	}

	@Override
	public void dispose() {

		sketcher.dispose();
		poly.dispose();
		batch.dispose();
		font.dispose();

	}

	////////////

	// *********** //
	// COLOR STUFF //
	// *********** //

	//
	// Background
	//

	public void background() {
		uBackground = new Color(Color.BLACK);
		backgroundRect();

	}

	public void background(Color c) {
		uBackground = c;
		backgroundRect();

	}

	public void background(int r, int g, int b) {
		uBackground.a = 1f;
		uBackground.r = (float) r / 255f;
		uBackground.g = (float) g / 255f;
		uBackground.b = (float) b / 255f;
		backgroundRect();

	}

	public void background(float r, float g, float b) {
		uBackground.a = 1f;
		uBackground.r = r / 255f;
		uBackground.g = g / 255f;
		uBackground.b = b / 255f;
		backgroundRect();

	}

	public void background(int r, int g, int b, int a) {
		uBackground.a = (float) a / 255f;
		uBackground.r = (float) r / 255f;
		uBackground.g = (float) g / 255f;
		uBackground.b = (float) b / 255f;
		backgroundRect();

	}

	public void background(float r, float g, float b, float a) {
		uBackground.a = a / 255f;
		uBackground.r = r / 255f;
		uBackground.g = g / 255f;
		uBackground.b = b / 255f;
		backgroundRect();

	}

	public void background(int gray) {
		uBackground.a = 1f;
		uBackground.r = (float) gray / 255f;
		uBackground.g = (float) gray / 255f;
		uBackground.b = (float) gray / 255f;
		backgroundRect();

	}

	public void background(float gray) {
		uBackground.a = 1f;
		uBackground.r = gray / 255f;
		uBackground.g = gray / 255f;
		uBackground.b = gray / 255f;
		backgroundRect();

	}

	public void background(int gray, int alpha) {
		uBackground.a = (float) alpha / 255f;
		uBackground.r = (float) gray / 255f;
		uBackground.g = (float) gray / 255f;
		uBackground.b = (float) gray / 255f;
		backgroundRect();

	}

	public void background(float gray, float alpha) {
		uBackground.a = alpha / 255f;
		uBackground.r = gray / 255f;
		uBackground.g = gray / 255f;
		uBackground.b = gray / 255f;
		backgroundRect();

	}

	private void backgroundRect() {
		sketcher.begin(ShapeRenderer.ShapeType.Filled);
		sketcher.setColor(uBackground);
		sketcher.rect(0, 0, Width, Height);
		sketcher.end();
	}

	//
	// Fill
	//

	public void noFill() {
		doFill = false;
	}

	public void fill() {
		doFill = true;
		uFill.set(Color.BLACK);
	}

	public void fill(Color c) {
		doFill = true;
		uFill.set(c);
	}

	public void fill(float r, float g, float b) {
		doFill = true;
		uFill.a = 1f;
		uFill.r = (((int) r & 0x00ff0000) >>> 16) / 255f;
		uFill.g = (((int) g & 0x0000ff00) >>> 8) / 255f;
		uFill.b = (((int) b & 0x000000ff)) / 255f;

	}

	public void fill(int r, int g, int b) {
		doFill = true;
		uFill.a = 1f;
		uFill.r = (float) r / 255f;
		uFill.g = (float) g / 255f;
		uFill.b = (float) b / 255f;
	}

	public void fill(float r, float g, float b, float a) {
		doFill = true;
		uFill.a = a / 255f;
		uFill.r = r / 255f;
		uFill.g = g / 255f;
		uFill.b = b / 255f;
	}

	public void fill(int r, int g, int b, int a) {
		doFill = true;
		uFill.a = a / 255f;
		uFill.r = r / 255f;
		uFill.g = g / 255f;
		uFill.b = b / 255f;
	}

	public void fill(float gray) {
		doFill = true;
		uFill.a = 1f;
		uFill.r = gray / 255f;
		uFill.g = gray / 255f;
		uFill.b = gray / 255f;
	}

	public void fill(int gray) {
		doFill = true;
		uFill.a = 1f;
		uFill.r = (float) gray / 255f;
		uFill.g = (float) gray / 255f;
		uFill.b = (float) gray / 255f;
	}

	public void fill(int gray, int alpha) {
		doFill = true;
		uFill.a = (float) alpha / 255f;
		uFill.r = (float) gray / 255f;
		uFill.g = (float) gray / 255f;
		uFill.b = (float) gray / 255f;
	}

	public void fill(float gray, float alpha) {
		doFill = true;
		uFill.a = alpha / 255f;
		uFill.r = gray / 255f;
		uFill.g = gray / 255f;
		uFill.b = gray / 255f;
	}

	public void fill(Color gray, int alpha) {
		doFill = true;
		uFill.a = (float) alpha / 255f;
		uFill.r = (float) gray.r / 255f;
		uFill.g = (float) gray.g / 255f;
		uFill.b = (float) gray.b / 255f;
	}

	public void fill(Color gray, float alpha) {
		doFill = true;
		uFill.a = alpha / 255f;
		uFill.r = gray.r / 255f;
		uFill.g = gray.g / 255f;
		uFill.b = gray.b / 255f;
	}

	//
	// Stroke
	//

	public void noStroke() {
		doStroke = false;
	}

	public void stroke() {
		doStroke = true;
		uStroke.set(Color.BLACK);
	}

	public void stroke(Color c) {
		doStroke = true;
		uStroke.set(c);
	}

	public void stroke(float r, float g, float b) {
		doStroke = true;
		uStroke.a = 1f;
		uStroke.r = (((int) r & 0x00ff0000) >>> 16) / 255f;
		uStroke.g = (((int) g & 0x0000ff00) >>> 8) / 255f;
		uStroke.b = (((int) b & 0x000000ff)) / 255f;

	}

	public void stroke(int r, int g, int b) {
		doStroke = true;
		uStroke.a = 1f;
		uStroke.r = (float) r / 255f;
		uStroke.g = (float) g / 255f;
		uStroke.b = (float) b / 255f;
	}

	public void stroke(float r, float g, float b, float a) {
		doStroke = true;
		uStroke.a = a / 255f;
		uStroke.r = r / 255f;
		uStroke.g = g / 255f;
		uStroke.b = b / 255f;
	}

	public void stroke(int r, int g, int b, int a) {
		doStroke = true;
		uStroke.a = a / 255f;
		uStroke.r = r / 255f;
		uStroke.g = g / 255f;
		uStroke.b = b / 255f;
	}

	public void stroke(float gray) {
		doStroke = true;
		uStroke.a = 1f;
		uStroke.r = gray / 255f;
		uStroke.g = gray / 255f;
		uStroke.b = gray / 255f;
	}

	public void stroke(int gray) {
		doStroke = true;
		uStroke.a = 1f;
		uStroke.r = (float) gray / 255f;
		uStroke.g = (float) gray / 255f;
		uStroke.b = (float) gray / 255f;
	}

	public void stroke(int gray, int alpha) {
		doStroke = true;
		uStroke.a = (float) alpha / 255f;
		uStroke.r = (float) gray / 255f;
		uStroke.g = (float) gray / 255f;
		uStroke.b = (float) gray / 255f;
	}

	public void stroke(float gray, float alpha) {
		doStroke = true;
		uStroke.a = alpha / 255f;
		uStroke.r = gray / 255f;
		uStroke.g = gray / 255f;
		uStroke.b = gray / 255f;
	}

	public void stroke(Color gray, int alpha) {
		doStroke = true;
		uStroke.a = (float) alpha / 255f;
		uStroke.r = (float) gray.r / 255f;
		uStroke.g = (float) gray.g / 255f;
		uStroke.b = (float) gray.b / 255f;
	}

	public void stroke(Color gray, float alpha) {
		doStroke = true;
		uStroke.a = alpha / 255f;
		uStroke.r = gray.r / 255f;
		uStroke.g = gray.g / 255f;
		uStroke.b = gray.b / 255f;
	}

	// strokeWeight()
	public void strokeWeight() {
		strokeWeight = 1;
	}

	public void strokeWeight(float s) {
		strokeWeight = (int) s;
	}

	public void strokeWeight(int s) {
		strokeWeight = s;
	}

	// ********** //
	// DRAW STUFF //
	// ********** //

	//
	// POINT
	//

	public void point(int x, int y, int z) {
		confirmSketchBegin();
		sketcher.set(ShapeRenderer.ShapeType.Line);
		sketcher.setColor(uFill);
		sketcher.point(x, y, z);
	}

	//
	// LINE
	//

	public void line(int x1, int y1, int x2, int y2) {
		// Gdx.gl.glLineWidth(strokeWeight);
		confirmSketchBegin();
		Gdx.gl.glLineWidth(strokeWeight);
		sketcher.set(ShapeType.Line);
		sketcher.setColor(uFill);
		// Sketcher.line(x1, y1, x2, y2);
		sketcher.rectLine(x1, y1, x2, y2, strokeWeight);

	}

	public void line(Vector2 v1, Vector2 v2) {
		// Gdx.gl.glLineWidth(strokeWeight);
		confirmSketchBegin();
		sketcher.set(ShapeType.Line);
		sketcher.setColor(uFill);
		sketcher.rectLine(v1, v2, strokeWeight);
	}

	//
	// RECT
	//

	public void rectTest(int x, int y, int w, int h)// test
	{
		sketcher.set(ShapeType.Filled);
		sketcher.setColor(uFill);
		sketcher.rect(pixoff + x, pixoff + y, w, h);
	}

	public void rect(int x, int y, int w, int h) {
		confirmSketchBegin();
		sketcher.set(ShapeType.Filled);
		if (doFill == true) {

			sketcher.setColor(uFill);
			sketcher.rect(pixoff + x, pixoff + y, w, h);

		}

		if (doStroke == true) {
			sketcher.setColor(uStroke);
			sketcher.rect(x, y + h - strokeWeight, w, (strokeWeight));// top
			sketcher.rect(x, y, w, strokeWeight);// bottom
			sketcher.rect(x, y, strokeWeight, h);// left
			sketcher.rect(x + w - strokeWeight, y, strokeWeight, h);// right
		}
	}

	public void rect(float x, float y, float w, float h) {
		confirmSketchBegin();
		sketcher.set(ShapeType.Filled);
		if (doFill == true) {

			sketcher.setColor(uFill);
			sketcher.rect(pixoff + x, pixoff + y, w, h);

		}

		if (doStroke == true) {
			sketcher.setColor(uStroke);
			sketcher.rect(x, y + h - strokeWeight, w, (strokeWeight));// top
			sketcher.rect(x, y, w, strokeWeight);// bottom
			sketcher.rect(x, y, strokeWeight, h);// left
			sketcher.rect(x + w - strokeWeight, y, strokeWeight, h);// right
		}
	}

	public void rect(Vector2 position, Vector2 size) {
		// DO NOT USE
		confirmSketchBegin();
		sketcher.set(ShapeType.Filled);
		if (doFill == true) {

			sketcher.setColor(uFill);
			sketcher.rect(pixoff + position.x, pixoff + position.y, size.x, size.y);

		}

		if (doStroke == true) {
			sketcher.setColor(uStroke);
			sketcher.rect(position.x, position.y + size.y, size.x + strokeWeight, (strokeWeight));// bottom
			sketcher.rect(position.x, position.y, size.x, strokeWeight);// top
			sketcher.rect(position.x, position.y, strokeWeight, size.y);// left
			sketcher.rect(position.x + size.x, position.y, strokeWeight, size.y + strokeWeight);// right
		}
	}

	//
	// SQUARE
	//

	public void rect(float x, float y, float d) {
		rect(x, y, d, d);
	}

	public void rect(int x, int y, int d) {
		rect(x, y, d, d);
	}

	public void square(float x, float y, float d) {
		rect(x, y, d, d);
	}

	public void square(int x, int y, int d) {
		rect(x, y, d, d);
	}

	// from uRect

	//
	// ARC
	//

	//
	// ELLIPSE
	//

	// problem where strokeWeight only correctly effects first ellipse drawn
	// use the compound drawing method instead of ShapeType.line
	public void ellipse(int x, int y, int w, int h) {
		confirmSketchBegin();
		Gdx.gl.glLineWidth(strokeWeight);
		if (doFill == true) {
			sketcher.set(ShapeRenderer.ShapeType.Filled);
			sketcher.setColor(uFill);
			sketcher.ellipse(pixoff + x, pixoff + y, w, h);
		}

		if (doStroke == true) {
			sketcher.set(ShapeRenderer.ShapeType.Line);
			sketcher.setColor(uStroke);
			sketcher.ellipse(pixoff + x, pixoff + y, pixoff + w, pixoff + h);
		}
	}

	public void ellipse(float x, float y, float w, float h) {
		confirmSketchBegin();
		Gdx.gl.glLineWidth(strokeWeight);
		if (doFill == true) {
			sketcher.set(ShapeType.Filled);
			sketcher.setColor(uFill);
			sketcher.ellipse(pixoff + x, pixoff + y, w, h);
		}

		if (doStroke == true) {
			sketcher.set(ShapeType.Line);
			sketcher.setColor(uStroke);
			sketcher.ellipse(pixoff + x, pixoff + y, pixoff + w, pixoff + h);
		}
	}

	public void ellipse(Vector2 position, Vector2 size) {
		confirmSketchBegin();
		Gdx.gl.glLineWidth(strokeWeight);
		if (doFill == true) {
			sketcher.set(ShapeRenderer.ShapeType.Filled);
			sketcher.setColor(uFill);
			sketcher.ellipse(pixoff + position.x, pixoff + position.y, size.x, size.y);
		}

		if (doStroke == true) {
			sketcher.set(ShapeRenderer.ShapeType.Line);
			sketcher.setColor(uStroke);
			sketcher.ellipse(pixoff + position.x, pixoff + position.y, pixoff + size.x, pixoff + size.y);
		}
	}

	// from uEllipse

	//
	// CIRCLE
	//

	public void ellipse(int x, int y, int r) {
		ellipse(x, y, r, r);
	}

	public void ellipse(float x, float y, float r) {
		ellipse(x, y, r, r);
	}

	public void circle(int x, int y, int r) {
		ellipse(x, y, r, r);
	}

	public void circle(float x, float y, float r) {
		ellipse(x, y, r, r);
	}

	//
	// n-POLYGON
	//
	// calcVert: int x, int y, int radius, int points, float angle, float scaleX,
	// float scaleY, float snap

	// quick polygon
	public void polygon(float[] vertices) {

		end();

		Gdx.gl.glLineWidth(strokeWeight);
		if (doFill == true) {
			poly.begin(ShapeRenderer.ShapeType.Filled);
			poly.setColor(uFill);
			poly.polygon(vertices);
			poly.end();
		}

		if (doStroke == true) {
			sketcher.begin(ShapeRenderer.ShapeType.Line);
			sketcher.setColor(uStroke);
			sketcher.polygon(vertices);
			sketcher.end();
		}
		confirmSketchBegin();
	}

	// regular polygon
	public void polygon(int x, int y, int radius, int points) {

		end();

		float[] vertices = calculateVertices(x, y, radius, points, 0, 1, 1, 1);
		Gdx.gl.glLineWidth(strokeWeight);
		if (doFill == true) {
			poly.begin(ShapeRenderer.ShapeType.Filled);
			poly.setColor(uFill);
			poly.polygon(vertices);
			poly.end();
		}

		if (doStroke == true) {

			sketcher.begin(ShapeRenderer.ShapeType.Line);
			sketcher.setColor(uStroke);
			sketcher.polygon(vertices);
			// sketcher.end();
		}

		confirmSketchBegin();
	}

	// elliptical polygon, scaleX, scaleY
	public void polygon(int x, int y, int radius, int points, float scaleX, float scaleY) {

		end();

		float[] vertices = calculateVertices(x, y, radius, points, 0, scaleX, scaleY, 1);
		Gdx.gl.glLineWidth(strokeWeight);
		if (doFill == true) {

			poly.begin(ShapeType.Filled);
			poly.setColor(uFill);
			poly.polygon(vertices);
			poly.end();
		}

		if (doStroke == true) {

			sketcher.begin(ShapeRenderer.ShapeType.Line);
			sketcher.setColor(uStroke);
			sketcher.polygon(vertices);
			// sketcher.end();
		}
		confirmSketchBegin();

	}

	// elliptical polygon, width&height
	public void polygon(int x, int y, int width, int height, int points) {

		end();

		float[] vertices = calculateVertices(x, y, width, height, points, 0, 1);
		Gdx.gl.glLineWidth(strokeWeight);
		if (doFill == true) {
			poly.begin(ShapeType.Filled);
			// poly.set(ShapeType.Filled);
			poly.setColor(uFill);
			poly.polygon(vertices);
			poly.end();
		}

		if (doStroke == true) {

			sketcher.begin(ShapeType.Line);
			sketcher.setColor(uStroke);
			sketcher.polygon(vertices);
			// sketcher.end();
		}

		confirmSketchBegin();
		// confirmPolyBegin();

	}

	//
	// Hex
	//

	public void hex(int x, int y, int radius, boolean pointUp) {
		int r = 0;
		if (pointUp == true)
			r = 1;

		float[] vertices = calculateVertices(x, y, radius, 6, 0, 1, 1, r);
		Gdx.gl.glLineWidth(strokeWeight);
		if (doFill == true) {
			poly.set(ShapeRenderer.ShapeType.Filled);
			poly.setColor(uFill);
			poly.polygon(vertices);
		}

		if (doStroke == true) {

			sketcher.set(ShapeRenderer.ShapeType.Line);
			sketcher.setColor(uStroke);
			sketcher.polygon(vertices);
		}

	}

	public void hex(int x, int y, int radius, float scale, boolean pointUp) {
		int r = 0;
		if (pointUp == true)
			r = 1;

		float[] vertices = calculateVertices(x, y, radius, 6, 0, scale, scale, r);
		Gdx.gl.glLineWidth(strokeWeight);
		if (doFill == true) {
			poly.set(ShapeRenderer.ShapeType.Filled);
			poly.setColor(uFill);
			poly.polygon(vertices);
		}

		if (doStroke == true) {

			sketcher.set(ShapeRenderer.ShapeType.Line);
			sketcher.setColor(uStroke);
			sketcher.polygon(vertices);
		}
	}

	//
	// TEXTURE
	//

	public void image(Texture texture, int x, int y) {
		confirmBatchBegin();
		batch.draw(texture, x, y);

	}

	public void image(Texture texture, float x, float y) {
		confirmBatchBegin();
		batch.draw(texture, x, y);

	}
	
	public void image(Texture texture, float x, float y, float width, float height) {
		confirmBatchBegin();
		batch.draw(texture, x, y,width,height);

	}

	//
	// TEXT
	//

	public void setFont(BitmapFont font) {
		this.font = font;
	}

	public BitmapFont genFont(String filename, int size) {
		BitmapFont newFont;

		fontGen = new FreeTypeFontGenerator(Gdx.files.internal("data/fonts/" + filename));
		FreeTypeFontParameter param = new FreeTypeFontParameter();
		param.size = size;
		param.borderWidth = 0;

		newFont = fontGen.generateFont(param);
		fontGen.dispose();

		return newFont;
	}

	public BitmapFont genFont(String filename, int size, int border) {
		BitmapFont newFont;

		fontGen = new FreeTypeFontGenerator(Gdx.files.internal("data/fonts/" + filename));
		FreeTypeFontParameter param = new FreeTypeFontParameter();
		param.size = size;
		param.borderWidth = border;

		newFont = fontGen.generateFont(param);

		fontGen.dispose();
		return newFont;
	}
	
	public BitmapFont genFont(uFontReference font, int size)	
	{
		BitmapFont newFont;
		fontGen = new FreeTypeFontGenerator(Gdx.files.internal(font.fullPath));
		FreeTypeFontParameter param = new FreeTypeFontParameter();
		param.size = size;

		newFont = fontGen.generateFont(param);

		fontGen.dispose();
		
		return newFont;
	}

	public BitmapFont regenFont(BitmapFont font, int size) {

		BitmapFont newFont;

		fontGen = new FreeTypeFontGenerator(font.getData().getFontFile());
		FreeTypeFontParameter param = new FreeTypeFontParameter();
		param.size = size;

		newFont = fontGen.generateFont(param);

		fontGen.dispose();
		font.dispose();

		return newFont;
	}
	
	public Vector2 getGlyphSize(BitmapFont bitmapFont, String value)
	{
		Vector2 temp = new Vector2(0,0);
	    GlyphLayout glyphLayout = new GlyphLayout();
	    glyphLayout.setText(bitmapFont, value);
	    
	    temp.set(glyphLayout.width, glyphLayout.height);
		return temp;
	}

	public void textSize(int s)// do not use these yet
	{
		textWidth = s;
		textHeight = s;
		textSize = s;
	}

	public void textSize(int x, int y) {
		textWidth = x;
		textHeight = y;
	}

	public void setTextWidth(int s) {
		textWidth = s;
	}

	public void setTextHeight(int s) {
		textHeight = s;
	}

	public void text(String txt, int x, int y) {
		this.font.setColor(this.uFill);
		this.font.draw(batch, txt, x, y);
	}

	public void text(String txt, float x, float y) {
		this.font.setColor(this.uFill);
		this.font.draw(batch, txt, x, y);
	}

	public void text(String txt, int x, int y, BitmapFont font) {
		this.font.setColor(this.uFill);
		font.draw(this.batch, txt, x, y);
	}

	public void text(String txt, float x, float y, BitmapFont font) {
		// doSpriteBatch = true;
		// confirmBatchBegin();
		this.font.setColor(this.uFill);
		font.draw(this.batch, txt, x, y);
	}

	// ******* //
	// UTILITY //
	// ******* //

	// plygon
	public static float[] calculateVertices(int x, int y, int radius, int points, float angle, float scaleX,
			float scaleY, float snap) {
		float vertices[] = new float[points * 2];

		float a = (360f / points); // normal rotation
		float b = a / 2; // arc rotation, rotates by segment
		// snap is the arc rotation offset: rotates by 1 'arc segment'; may not use?
		// in future if something better comes up
		// ints for even# shapes, +.05f for odd# shapes

		// multiply all by scale

		int j = 0;
		for (int i = 0; i < points * 2; i += 2) {
			float angle_deg = (a * j) - (angle + (b * snap));
			float angle_rad = uPI / 180 * angle_deg;

			float sx = (float) ((x + radius * Math.cos(angle_rad)) * scaleX);
			float sy = (float) ((y + radius * Math.sin(angle_rad)) * scaleY);

			vertices[i] = sx;
			vertices[i + 1] = sy;
			j++;

		}

		return vertices;

	}

	public static float[] calculateVertices(int x, int y, int width, int height, int points, float angle, float snap) {
		float vertices[] = new float[points * 2];

		float a = (360f / points); // normal rotation
		float b = a / 2; // arc rotation, rotates by segment
		// snap is the arc rotation offset: rotates by 1 'arc segment'; may not use?
		// in future if something better comes up
		// ints for even# shapes, +.05f for odd# shapes

		// multiply all by scale

		int j = 0;
		for (int i = 0; i < points * 2; i += 2) {
			float angle_deg = (a * j) - (angle + (b * snap));
			float angle_rad = uPI / 180 * angle_deg;

			float sx = (float) (x + (width / 2) * Math.cos(angle_rad));
			float sy = (float) (y + (height / 2) * Math.sin(angle_rad));

			vertices[i] = sx;
			vertices[i + 1] = sy;
			j++;

		}

		return vertices;

	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void confirmSketchBegin() {
		if (doShapeBatch == false) {
			beginSketch();
		}
	}

	public void confirmSketchEnd() {
		if (doShapeBatch == true) {
			endSketch();
		}
	}

	public void confirmPolyBegin() {
		if (doPolyBatch == false) {
			beginPoly();
		}
	}

	public void confirmPolyEnd() {
		if (doPolyBatch == true) {
			endPoly();
		}
	}

	public void confirmBatchBegin() {
		if (doSpriteBatch == false) {
			beginBatch();
		}
	}

	public void confirmBatchEnd() {
		if (doSpriteBatch == true) {
			endBatch();
		}
	}

	public void end() {
		confirmSketchEnd();
		confirmPolyEnd();
		confirmBatchEnd();

	}

}
