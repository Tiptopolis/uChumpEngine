package com.mygdx.game.uChumpClasses.UI.Widgets;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.uChumpClasses.Core.Utils.AppInjector;
import com.mygdx.game.uChumpClasses.Core.Utils.Fonts.uFontReference;
import com.mygdx.game.uChumpClasses.UI.iLayer;
import com.mygdx.game.uChumpClasses.UI.uWidget;
import com.mygdx.game.uChumpClasses.UI.Heirarchy.UiOverseer;
import com.mygdx.game.uChumpClasses.UI.Util.eTextH;
import com.mygdx.game.uChumpClasses.UI.Util.eTextV;

import static com.mygdx.game.uChumpClasses.uAppUtils.*;

public class uLabel extends uWidget implements iText {

	// static text

	public String text = "NULL";
	public Color textColor = Color.DARK_GRAY;
	public int textSize = 12;
	public BitmapFont font;
	public String defaultFont;
	public String fontName;
	public eTextH horizontal = eTextH.CENTER;
	public eTextV vertical = eTextV.CENTER;
	public Vector2 textAnchor = new Vector2(0,0); //origin of text
	
	public UiOverseer UI;


	public uLabel(iLayer layer, int x, int y, String text, boolean front) {
		super(layer, "Label{" + text + "}");
		this.text = text;
		this.setPosition(x, y);
		this.setSize(textSize, text.length() * textSize);
		this.setStatic(true); // standard labels are always static
		this.setStaticType(front); // always on top? if false, will always be on bottom
		
		this.defaultFont = "ARIAL.TTF";
		this.fontName = defaultFont;
		this.font = Sketcher.genFont(defaultFont, this.textSize);

		
		this.UI = AppInjector.getInjector().getInstance(UiOverseer.class);


		this.init();
	}

	@Override
	public void init() {
		super.init();
	}

	@Override
	public void drawT(float deltaTime) {
		this.Sketcher.fill(this.textColor);
		this.Sketcher.text(this.text, this.getX(), this.getY() + this.textSize);

	}

	@Override
	public void buildInputListener() {

	}

	@Override
	public void mouseExit() {

	}

	//
	// TEXT
	//

	public void setText(String text) {
		this.text = text;
		this.fullName = "Label{" + text + "}";
		// update Size/Position also
		this.formatText();
		this.updateFullName();
	}

	public String getText() {
		return this.text;
	}

	@Override
	public void formatText() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setAllignmentH(eTextH h) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setAllignment(eTextH h, eTextV v) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setAllignmentV(eTextV v) {
		// TODO Auto-generated method stub

	}

	@Override
	public eTextH getAllignmentH() {
		return this.horizontal;
	}

	@Override
	public eTextV getAllignmentV() {
		return this.vertical;
	}



	@Override
	public void setTextSize(int size) {
		this.textSize = size;
		this.font.dispose();
		BitmapFont newFont = Sketcher.genFont(fontName, this.textSize);
		this.font = newFont;		
	}

	@Override
	public void setMarginBuffer(int buffer) {
		// TODO Auto-generated method stub
		
	}
	
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

}
