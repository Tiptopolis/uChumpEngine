package com.mygdx.game.uChumpClasses.UI.Widgets;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.uChumpClasses.Core.Utils.AppInjector;
import com.mygdx.game.uChumpClasses.Core.Utils.Fonts.uFontReference;
import com.mygdx.game.uChumpClasses.UI.iLayer;
import com.mygdx.game.uChumpClasses.UI.Heirarchy.UiOverseer;
import com.mygdx.game.uChumpClasses.UI.Util.eTextH;
import com.mygdx.game.uChumpClasses.UI.Util.eTextV;
import com.mygdx.game.uChumpClasses.UI.Widgets.Controls.uTextLine;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static com.mygdx.game.uChumpClasses.uAppUtils.*;
import static com.mygdx.game.uChumpClasses.UI.Heirarchy.UiOverseer.*;

public class uTextButton extends uButton implements iText {

	public String text;
	public String displayText;
	public Color textColor;
	public int textSize;

	public ArrayList<uTextLine> Lines;

	public BitmapFont font;
	public String defaultFont;
	public String fontName;

	public eTextH horizontal;
	public eTextV vertical;
	public Vector2 textAnchor; // origin of text
	public Vector2 textBounds; // box around total text
	public Vector2 displayTxtBounds;

	// so, establish text area as a box
	// ((Lines*LengthOfLongestLine)*textSize)+buffer
	public int marginBuffer;
	public int lineBuffer;

	public UiOverseer UI;

	public uTextButton(iLayer layer, int x, int y, int width, int height) {
		super(layer, x, y, width, height);

	}

	@Override
	public void init() {

		this.UI = AppInjector.getInjector().getInstance(UiOverseer.class);

		this.name = "New TextButton";
		this.horizontal = eTextH.CENTER;
		this.vertical = eTextV.CENTER;

		this.Lines = new ArrayList<uTextLine>();
		this.Lines.add(new uTextLine("New TextButton"));

		this.textSize = 16;
		this.textAnchor = new Vector2(0, textSize);
		this.textBounds = new Vector2(0, 0); // box around total text
		this.displayTxtBounds = new Vector2(0, 0); // box around formatted text
		this.text = "BUTTON";
		this.displayText = text;
		this.marginBuffer = 4;
		this.lineBuffer = 4;
		textColor = Color.DARK_GRAY;

		this.defaultFont = "ARIAL.TTF";
		this.fontName = defaultFont;

		super.init();

		this.font = Sketcher.genFont(defaultFont, this.textSize);
		this.formatText();
		this.updateFullName();

	}

	@Override
	public void drawT(float deltaTime) {
		this.Sketcher.fill(this.textColor);
		this.Sketcher.setFont(this.font);
		// this.Sketcher.text(this.displayText, this.textAnchor.x, this.textAnchor.y);
		for (uTextLine line : this.Lines) {
			this.Sketcher.text(line.displayText, line.textAnchor.x, line.textAnchor.y);
		}

	}

	@Override
	public void reposition(int x, int y) {
		super.reposition(x, y);
		if (this.textAnchor != null) {
			this.textAnchor.set(this.getX(), this.getY());
			this.formatText();
		}
	}

	@Override
	public void setPosition(float x, float y) {
		super.setPosition(x, y);
		if (this.textAnchor != null) {
			this.textAnchor.set(this.getX(), this.getY());
			this.formatText();
		}
	}

	@Override
	public void dispose() {
		super.dispose();
	}

	//
	//
	//

	@Override
	public void setAllignment(eTextH h, eTextV v) {
		this.horizontal = h;
		this.vertical = v;
		this.formatText();

	}

	@Override
	public void setAllignmentH(eTextH h) {
		this.horizontal = h;
		this.formatText();
	}

	@Override
	public void setAllignmentV(eTextV v) {
		this.vertical = v;
		this.formatText();
	}

	@Override
	public eTextH getAllignmentH() {
		return this.horizontal;
	}

	@Override
	public eTextV getAllignmentV() {
		return this.vertical;
	}

	//
	// TEXT
	//

	@Override
	public void setText(String text) {
		this.Lines.clear();
		this.text = text;
		this.displayText = text;
		this.textAnchor.set(this.getX(), this.getY());
		this.textBounds.set(Sketcher.getGlyphSize(this.font, this.text));
		String[] splitLines = SplitIntoLines(this.text);
		for (String s : splitLines) {
			uTextLine newLine = new uTextLine(s);
			newLine.setTextBounds(Sketcher.getGlyphSize(this.font, s));
			newLine.displayBounds.set(newLine.textBounds.x, newLine.textBounds.y);
			newLine.setAnchor(this.textAnchor);

			this.Lines.add(newLine);
		}

		this.formatText();
		this.updateFullName();
	}

	// @Override
	public void setFont(String name) {
		// get from FontAtlas in UiOverseer
		uFontReference link = this.UI.fontAtlas.getFontLink(name);
		if(link!= null)
		{
			this.font.dispose();
			this.font = Sketcher.genFont(link.namePath, this.textSize);
		}
		
		this.formatText();
	}
	
	public void setFont(BitmapFont font)
	{
		this.font = font;
	}

	@Override
	public String getText() {
		return this.text;
	}

	@Override
	public void setTextSize(int size) {
		this.textSize = size;
		this.font.dispose();
		BitmapFont newFont = Sketcher.genFont(fontName, this.textSize);
		this.font = newFont;
		this.formatText();
	}

	@Override
	public void setMarginBuffer(int buffer) {
		this.marginBuffer = buffer;
		this.formatText();
	}

	public void formatText() {
		this.textBounds = Sketcher.getGlyphSize(this.font, this.text);
		this.displayTxtBounds.set(this.textBounds.x, this.textBounds.y);
		
		for (uTextLine line : this.Lines) {
			this.shortenText(line);
			this.allignText(line);
		}

	}

	public void shortenText(uTextLine line) {

		if (line.textBounds.x + (marginBuffer * 2) > this.getWidth()) {
			line.displayText = StripVowels(line.text);
			line.displayBounds = Sketcher.getGlyphSize(this.font, line.displayText);

		}

	}

	public void allignText(uTextLine line) {

		int offset = 0;

		for (int i = this.Lines.indexOf(line); i > 0; i--) {

			offset = (int) (offset + this.Lines.get(i).textBounds.y);
			if (i > 0) {
				offset = offset + this.lineBuffer;
			}

		}

		switch (this.horizontal) {
		case LEFT:

			this.textAnchor.set(this.getX(), this.textAnchor.y);
			line.setAnchor(this.textAnchor.x + this.marginBuffer, line.textAnchor.y);

			break;

		case RIGHT:
			this.textAnchor.set(this.getX(), this.textAnchor.y);
			line.setAnchor(this.textAnchor.x, line.textAnchor.y);

			line.setAnchor(line.textAnchor.x + this.getWidth(), line.textAnchor.y);
			line.setAnchor(line.textAnchor.x - line.displayBounds.x, line.textAnchor.y);
			line.setAnchor(line.textAnchor.x - this.marginBuffer, line.textAnchor.y);

			break;

		case CENTER:
			this.textAnchor.set(this.getX(), this.textAnchor.y);
			line.setAnchor(this.textAnchor.x, line.textAnchor.y);
			line.setAnchor(line.textAnchor.x + (this.getWidth() / 2), line.textAnchor.y);
			line.setAnchor(line.textAnchor.x - (line.displayBounds.x / 2), line.textAnchor.y);

			break;
		}


		switch (this.vertical) {
		case BOTTOM:
			this.textAnchor.set(this.textAnchor.x, this.getY() + line.textBounds.y);// works-v
			line.setAnchor(line.textAnchor.x, this.textAnchor.y + offset + this.lineBuffer);

			break;

		case CENTER:
			this.textAnchor.set(this.textAnchor.x, this.getY() + line.textBounds.y);
			line.setAnchor(line.textAnchor.x, this.textAnchor.y);

			line.setAnchor(line.textAnchor.x, line.textAnchor.y + (this.getHeight() / 2));
			line.setAnchor(line.textAnchor.x, line.textAnchor.y - (this.displayTxtBounds.y / 2));
			line.setAnchor(line.textAnchor.x, line.textAnchor.y + offset + (this.lineBuffer * (this.Lines.size() - 1)));

			break;

		case TOP:
			this.textAnchor.set(this.textAnchor.x, this.getY() + line.textBounds.y);
			line.setAnchor(line.textAnchor.x, this.textAnchor.y);

			line.setAnchor(line.textAnchor.x, line.textAnchor.y + this.getHeight());
			line.setAnchor(line.textAnchor.x, line.textAnchor.y - this.displayTxtBounds.y);
			line.setAnchor(line.textAnchor.x, line.textAnchor.y + offset);
			line.setAnchor(line.textAnchor.x, line.textAnchor.y - this.lineBuffer);
			line.setAnchor(line.textAnchor.x, line.textAnchor.y + ((this.lineBuffer * (this.Lines.size() - 1)) / 2));

			break;
		}

	}

	public void debugT() {
		this.setTextSize(32);

		// this.UI.fontAtlas.printDebug(); //

		uFontReference temp = this.UI.fontAtlas.getFontLink(this.fontName);
		println(temp.name);

		this.marginBuffer = 4;

		this.setText("LINE1\nLINE2\nLINE3");

		this.setTextSize(32);

		this.setSize(this.textBounds.x, this.textBounds.x);

		this.setPosition(this.textAnchor.x, this.textAnchor.y);
		this.formatText();

		int total = 0;
		for (uTextLine line : this.Lines) {
			total = (int) (total + line.textBounds.y);
		}

		println("==========");
		for (uTextLine line : this.Lines) {
			println("TextAnchor: " + this.textAnchor);
			println("LineAnchor: " + line.textAnchor);

			println("BoxOrigin: " + this.getX() + " , " + this.getY());
			println("BoxBounds: " + this.getWidth() + " , " + this.getHeight());
			println("TextBounds: " + this.textBounds);
			println("DispBounds: " + this.displayTxtBounds);
			println("LineBounds: " + line.textBounds);
			println("TOTAL: " + total);

			println("");
			println(line + "  >" + this.getHeight() + " : " + this.displayTxtBounds.y + " ; " + line.textBounds.y);
			println("");
		}
		println("==========");

	}

	public void allignTextX(uTextLine line) {

		int offset = 0;
		int revOffset = 0;

		// println("");
		// println(line + " OFFSET: " + offset + " : : " + finalOffset);

		for (int i = this.Lines.indexOf(line); i > 0; i--) {

			offset = (int) (offset + this.Lines.get(i).textBounds.y);
			if (i > 0) {
				offset = offset + this.lineBuffer;
			}

		}
		
		switch (this.horizontal) {
		case LEFT:

			this.textAnchor.set(this.getX(), this.textAnchor.y);
			line.setAnchor(this.textAnchor.x + this.marginBuffer, line.textAnchor.y);

			break;

		case RIGHT:
			this.textAnchor.set(this.getX(), this.textAnchor.y);
			line.setAnchor(this.textAnchor.x, line.textAnchor.y);

			line.setAnchor(line.textAnchor.x + this.getWidth(), line.textAnchor.y);
			line.setAnchor(line.textAnchor.x - line.textBounds.x, line.textAnchor.y);
			line.setAnchor(line.textAnchor.x - this.marginBuffer, line.textAnchor.y);

			break;

		case CENTER:
			this.textAnchor.set(this.getX(), this.textAnchor.y);
			line.setAnchor(this.textAnchor.x, line.textAnchor.y);
			line.setAnchor(line.textAnchor.x + (this.getWidth() / 2), line.textAnchor.y);
			line.setAnchor(line.textAnchor.x - (line.textBounds.x / 2), line.textAnchor.y);

			break;
		}

		// get that textAnchor(0,0) on point, then set lineAnchor off of it

		switch (this.vertical) {
		case BOTTOM:
			this.textAnchor.set(this.textAnchor.x, this.getY() + line.textBounds.y);// works-v
			line.setAnchor(line.textAnchor.x, this.textAnchor.y + offset + this.lineBuffer);

			break;

		case CENTER:
			this.textAnchor.set(this.textAnchor.x, this.getY() + line.textBounds.y);
			line.setAnchor(line.textAnchor.x, this.textAnchor.y);

			line.setAnchor(line.textAnchor.x, line.textAnchor.y + (this.getHeight() / 2));
			line.setAnchor(line.textAnchor.x, line.textAnchor.y - (this.displayTxtBounds.y / 2));
			line.setAnchor(line.textAnchor.x, line.textAnchor.y + offset + (this.lineBuffer * (this.Lines.size() - 1)));

			break;

		case TOP:
			this.textAnchor.set(this.textAnchor.x, this.getY() + line.textBounds.y);
			line.setAnchor(line.textAnchor.x, this.textAnchor.y);

			line.setAnchor(line.textAnchor.x, line.textAnchor.y + this.getHeight());
			line.setAnchor(line.textAnchor.x, line.textAnchor.y - this.displayTxtBounds.y);
			line.setAnchor(line.textAnchor.x, line.textAnchor.y + offset);
			line.setAnchor(line.textAnchor.x, line.textAnchor.y - this.lineBuffer);
			line.setAnchor(line.textAnchor.x, line.textAnchor.y + ((this.lineBuffer * (this.Lines.size() - 1)) / 2));

			break;
		}

	}

}
