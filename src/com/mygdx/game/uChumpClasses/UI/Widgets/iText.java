package com.mygdx.game.uChumpClasses.UI.Widgets;

import com.mygdx.game.uChumpClasses.UI.Util.eTextH;
import com.mygdx.game.uChumpClasses.UI.Util.eTextV;

public interface iText {

	public void setText(String text);
	
	public void setTextSize(int size);

	public String getText();

	// cull vowels
	// shorten

	public void formatText();

	public void setAllignmentH(eTextH h);

	public void setAllignmentV(eTextV v);
	
	public void setAllignment(eTextH h, eTextV v);

	public eTextH getAllignmentH();

	public eTextV getAllignmentV();
	
	public void setMarginBuffer(int buffer);

}
