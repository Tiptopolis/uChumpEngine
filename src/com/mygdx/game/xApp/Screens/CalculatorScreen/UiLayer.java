package com.mygdx.game.xApp.Screens.CalculatorScreen;

import com.mygdx.game.uChumpClasses.UI.iSpace;
import com.mygdx.game.uChumpClasses.UI.uLayer;
import com.mygdx.game.uChumpClasses.UI.Util.eTextH;
import com.mygdx.game.uChumpClasses.UI.Util.eTextV;
import com.mygdx.game.uChumpClasses.UI.Widgets.uLabel;
import com.mygdx.game.uChumpClasses.UI.Widgets.uPanel;
import com.mygdx.game.uChumpClasses.UI.Widgets.uTextButton;

import static com.mygdx.game.uChumpClasses.uAppUtils.*;

public class UiLayer extends uLayer {

	public CalculatorHandler system;

	public uPanel CalculatorPanel;
	//public uLabel Display;
	public uTextButton Display;

	public uTextButton BtnClear;
	public uTextButton BtnDec;
	public uTextButton Btn0;
	public uTextButton Btn1;
	public uTextButton Btn2;
	public uTextButton Btn3;
	public uTextButton Btn4;
	public uTextButton Btn5;
	public uTextButton Btn6;
	public uTextButton Btn7;
	public uTextButton Btn8;
	public uTextButton Btn9;

	public uTextButton BtnEquals;
	public uTextButton BtnAdd;
	public uTextButton BtnSub;
	public uTextButton BtnMul;
	public uTextButton BtnDiv;

	public UiLayer(iSpace owner) {
		super(owner);
	}

	@Override
	public void init() {
		this.system = new CalculatorHandler();

		this.CalculatorPanel = new uPanel(this, 96, 96, 288, 360);
		this.CalculatorPanel.selfDrag = true;

		this.BtnClear = new uTextButton(this.CalculatorPanel.DefaultLayer, 16, 32, 48, 32) {
			@Override
			public void onClick() {
				super.onClick();
				this.castEvent("CALC_CLEAR");
			}
		};
		this.BtnClear.setAllignment(eTextH.LEFT, eTextV.TOP);
		this.BtnClear.setText("C");

		this.Btn0 = new uTextButton(this.CalculatorPanel.DefaultLayer, 72, 32, 48, 32) {
			@Override
			public void onClick() {
				super.onClick();
				this.castEvent("INPUT_0");
			}
		};
		this.Btn0.setAllignment(eTextH.LEFT, eTextV.TOP);
		this.Btn0.setText("0");

		this.Btn1 = new uTextButton(this.CalculatorPanel.DefaultLayer, 16, 72, 48, 32) {
			@Override
			public void onClick() {
				super.onClick();
				this.castEvent("INPUT_1");
			}
		};
		this.Btn1.setAllignment(eTextH.LEFT, eTextV.TOP);
		this.Btn1.setText("1");

		this.Btn2 = new uTextButton(this.CalculatorPanel.DefaultLayer, 72, 72, 48, 32) {
			@Override
			public void onClick() {
				super.onClick();
				this.castEvent("INPUT_2");
			}
		};
		this.Btn2.setAllignment(eTextH.LEFT, eTextV.TOP);
		this.Btn2.setText("2");

		this.Btn3 = new uTextButton(this.CalculatorPanel.DefaultLayer, 128, 72, 48, 32) {
			@Override
			public void onClick() {
				super.onClick();
				this.castEvent("INPUT_3");
			}
		};

		this.Btn3.setAllignment(eTextH.LEFT, eTextV.TOP);
		this.Btn3.setText("3");

		this.Btn4 = new uTextButton(this.CalculatorPanel.DefaultLayer, 16, 112, 48, 32) {
			@Override
			public void onClick() {
				super.onClick();
				this.castEvent("INPUT_4");
			}
		};

		this.Btn4.setAllignment(eTextH.LEFT, eTextV.TOP);
		this.Btn4.setText("4");

		this.Btn5 = new uTextButton(this.CalculatorPanel.DefaultLayer, 72, 112, 48, 32) {
			@Override
			public void onClick() {
				super.onClick();
				this.castEvent("INPUT_5");
			}
		};
		this.Btn5.setAllignment(eTextH.LEFT, eTextV.TOP);
		this.Btn5.setText("5");

		this.Btn6 = new uTextButton(this.CalculatorPanel.DefaultLayer, 128, 112, 48, 32) {
			@Override
			public void onClick() {
				super.onClick();
				this.castEvent("INPUT_6");
			}
		};
		this.Btn6.setAllignment(eTextH.LEFT, eTextV.TOP);
		this.Btn6.setText("6");

		this.Btn7 = new uTextButton(this.CalculatorPanel.DefaultLayer, 16, 152, 48, 32) {
			@Override
			public void onClick() {
				super.onClick();
				this.castEvent("INPUT_7");
			}
		};
		this.Btn7.setAllignment(eTextH.LEFT, eTextV.TOP);
		this.Btn7.setText("7");

		this.Btn8 = new uTextButton(this.CalculatorPanel.DefaultLayer, 72, 152, 48, 32) {
			@Override
			public void onClick() {
				super.onClick();
				this.castEvent("INPUT_8");
			}
		};
		this.Btn8.setAllignment(eTextH.LEFT, eTextV.TOP);
		this.Btn8.setText("8");

		this.Btn9 = new uTextButton(this.CalculatorPanel.DefaultLayer, 128, 152, 48, 32) {
			@Override
			public void onClick() {
				super.onClick();
				this.castEvent("INPUT_9");
			}
		};
		this.Btn9.setAllignment(eTextH.LEFT, eTextV.TOP);
		this.Btn9.setText("9");

		//
		//
		//

		this.BtnDec = new uTextButton(this.CalculatorPanel.DefaultLayer, 128, 32, 48, 32) {
			@Override
			public void onClick() {
				super.onClick();
				this.castEvent("INPUT_DECIMAL");
			}
		};
		this.BtnDec.setAllignment(eTextH.LEFT, eTextV.TOP);
		this.BtnDec.setText(".");

		this.BtnEquals = new uTextButton(this.CalculatorPanel.DefaultLayer, 216, 32, 48, 72) {
			@Override
			public void onClick() {
				super.onClick();
				this.castEvent("INPUT_EQUALS");
			}
		};
		this.BtnEquals.setAllignment(eTextH.LEFT, eTextV.TOP);
		this.BtnEquals.setText("=");

		this.BtnAdd = new uTextButton(this.CalculatorPanel.DefaultLayer, 216, 112, 48, 32) {
			@Override
			public void onClick() {
				super.onClick();
				this.castEvent("MODE_ADD");
			}
		};
		this.BtnAdd.setAllignment(eTextH.LEFT, eTextV.TOP);
		this.BtnAdd.setText("+");

		this.BtnSub = new uTextButton(this.CalculatorPanel.DefaultLayer, 216, 152, 48, 32) {
			@Override
			public void onClick() {
				super.onClick();
				this.castEvent("MODE_SUB");
			}
		};
		this.BtnSub.setAllignment(eTextH.LEFT, eTextV.TOP);
		this.BtnSub.setText("-");

		this.BtnMul = new uTextButton(this.CalculatorPanel.DefaultLayer, 216, 192, 48, 32) {
			@Override
			public void onClick() {
				super.onClick();
				this.castEvent("MODE_MUL");
			}
		};
		this.BtnMul.setAllignment(eTextH.LEFT, eTextV.TOP);
		this.BtnMul.setText("x");

		this.BtnDiv = new uTextButton(this.CalculatorPanel.DefaultLayer, 216, 232, 48, 32) {
			@Override
			public void onClick() {
				super.onClick();
				this.castEvent("MODE_DIV");
			}
		};
		this.BtnDiv.setAllignment(eTextH.LEFT, eTextV.TOP);
		this.BtnDiv.setText("/");

		//this.Display = new uLabel(this.CalculatorPanel.DefaultLayer, 16, (int) (this.CalculatorPanel.getHeight() - 32),
				//"0", true);
		this.Display = new uTextButton(this.CalculatorPanel.DefaultLayer, 16, (int) (this.CalculatorPanel.getHeight() - 64), 
				(int) (this.CalculatorPanel.getWidth() - 32), 32);

		this.Display.setTextSize(32);
		this.Display.setAllignment(eTextH.RIGHT, eTextV.CENTER);
		this.Display.setFont("digital-7");

	}

	@Override
	public void draw(float deltaTime) {
		super.draw(deltaTime);

		this.Display.setText(this.system.getDisplay());

	}

}
