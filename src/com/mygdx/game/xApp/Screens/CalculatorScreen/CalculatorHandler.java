package com.mygdx.game.xApp.Screens.CalculatorScreen;

import static com.mygdx.game.uChumpClasses.uAppUtils.*;

import java.text.DecimalFormat;

import com.mygdx.game.uChumpClasses.Core.Event.iEventCaster;
import com.mygdx.game.uChumpClasses.Core.Event.iEventObserver;
import com.mygdx.game.uChumpClasses.Core.Event.uDirector;
import com.mygdx.game.uChumpClasses.Core.Event.uEvent;
import com.mygdx.game.uChumpClasses.Core.Utils.AppInjector;

public class CalculatorHandler implements iEventObserver, iEventCaster {

	public String display = "0";
	public String inputBuffer = "0"; // string built by un-interupted input
	public String backBuffer = "0"; // stored inputBuffer, whenever an OpBtn is pressed
	public boolean lastEquals = false;

	public eOpMode mode = eOpMode.INPUT;

	protected uDirector Director;

	public CalculatorHandler() {
		this.Director = AppInjector.getInjector().getInstance(uDirector.class);
		this.Director.registerEventHandler(this);
	}

	@Override
	public void castEvent(String event) {

	}

	@Override
	public boolean handleEvent(uEvent event) {

		boolean handled = false;

		switch (event.getEvent()) {
		case "CALC_CLEAR":
			println("CALCULATOR DATA CLEARED");
			this.display = "0";
			this.inputBuffer = "0";
			this.backBuffer = "0";
			this.mode = eOpMode.INPUT;
			this.lastEquals = false;
			break;

		case "INPUT_0":
			this.input('0');
			break;

		case "INPUT_1":
			this.input('1');
			break;

		case "INPUT_2":
			this.input('2');
			break;

		case "INPUT_3":
			this.input('3');
			break;

		case "INPUT_4":
			this.input('4');
			break;

		case "INPUT_5":
			this.input('5');
			break;

		case "INPUT_6":
			this.input('6');
			break;

		case "INPUT_7":
			this.input('7');
			break;

		case "INPUT_8":
			this.input('8');
			break;

		case "INPUT_9":
			this.input('9');
			break;

		case "INPUT_DECIMAL":
			this.input('.');
			break;

		case "INPUT_EQUALS":
			this.calculate(this.mode, true);// repeat previous operation
			break;

		case "MODE_ADD":
			if (this.lastEquals) {
				this.inputBuffer = "0";
			}

			if (this.mode != eOpMode.ADD) {
				this.calculate(this.mode, false);
				this.mode = eOpMode.ADD;
			} else
				this.calculate(eOpMode.ADD, false);

			break;

		case "MODE_SUB":

			if (this.lastEquals) {
				this.inputBuffer = "0";
			}

			if (this.mode != eOpMode.SUB) {
				this.calculate(this.mode, false);
				this.mode = eOpMode.SUB;
			} else
				this.calculate(eOpMode.SUB, false);

			break;

		case "MODE_MUL":
			if (this.lastEquals) {
				this.inputBuffer = "0";
			}

			if (this.mode != eOpMode.MUL) {
				this.calculate(this.mode, false);
				this.mode = eOpMode.MUL;
			} else
				this.calculate(eOpMode.MUL, false);
			break;

		case "MODE_DIV": // problemo
			if (this.lastEquals) {
				this.inputBuffer = "0";
			}

			if (this.mode != eOpMode.DIV) {
				this.calculate(this.mode, false);
				this.mode = eOpMode.DIV;
			} else
				this.calculate(eOpMode.DIV, false);
			break;

		default:
			break;
		}

		return handled;
	}

	public void input(char input) {

		if(input == '.' && this.inputBuffer.contains("."))
		{
			
		}
		else
		{
		this.inputBuffer = this.inputBuffer + input;
		}

		println("--INPUT--");
		println("INPUT: " + input);
		println("INPUT_BUFFER: " + this.inputBuffer);
		println("BACK_BUFFER: " + backBuffer);

		if (input != '.') {
			this.display = inputBuffer;
			this.display = trimDisplay();
		}

		if (this.display.equals(""))
			this.display = "0";
		println("DISPLAY: " + this.display);

	}

	public void calculate(eOpMode mode, boolean equals) {

		println("==CALCULATING==");
		println("PREV_MODE: " + this.mode);
		println("MODE: " + mode);
		println("INPUT_BUFFER: " + this.inputBuffer);
		println("BACK_BUFFER: " + backBuffer);
		println("LAST_EQUALS: " + this.lastEquals); // false
		println("EQUALS: " + equals); // true

		float inputVal = Float.valueOf(this.inputBuffer.trim()).floatValue();
		float backVal = Float.valueOf(this.backBuffer.trim()).floatValue();

		switch (mode) {

		case ADD:
			this.backBuffer = "" + (backVal + inputVal);
			// this.inputBuffer = "0";
			break;

		case SUB:
			this.backBuffer = "" + (backVal - inputVal);
			// this.inputBuffer = "0";
			break;

		case MUL:
			if (backVal == 0)
				backVal = 1;
			// if equals
			if (this.lastEquals == true && equals == false) {
				inputVal = 1;
			}
			this.backBuffer = "" + (backVal * inputVal); // problem clearing buffer here after equals

			break;

		case DIV:
			if (inputVal == 0) {
				inputVal = 1;
			}

			if (backVal != 0)//
				this.backBuffer = "" + (backVal / inputVal);
			else
				this.backBuffer = "" + inputVal;

			break;

		case INPUT:
			this.backBuffer = "" + inputVal;
			break;

		}

		this.lastEquals = true;

		if (equals == false) {
			this.inputBuffer = "0";
			this.lastEquals = false;
		}

		this.display = backBuffer;
		this.display = trimDisplay();//

		println("RESULT: " + backBuffer);
		println("DISPLAY: " + this.display);

	}

	public String trimDisplay() {
	
		String temp = this.display;
		temp = RemoveLeadingZeros(temp);
		println(temp + " <<<LEADING");
		temp = RemoveTrailingZeros(temp);
		println(temp + " <<<TRAILING");
			
		if(temp.endsWith("."))
		{
			StringBuilder sb = new StringBuilder(temp);
			sb.deleteCharAt(sb.length()-1);
			temp = sb.toString();
		}
	    println(temp + " <<<FINAL");
		
		
		String result = temp;
		return result;
	}

	public String getDisplay() {
		return this.display;
	}
	

	public String trimDisplayX() {
		// trim leading and trailing 0s

		// remove leading
		int i = 0;
		while (i < this.display.length() && this.display.charAt(i) == '0')
			i++;

		StringBuffer sb = new StringBuffer(this.display);
		sb.replace(0, i, "");
		float temp = 0;
		if (sb.toString().equals("") == false)
			temp = Float.valueOf(sb.toString());

		// remove trailing
		// reverse loop, remove 0s until hitting non-0
		// check if ending is decimal & remove

		// check length after decimal, if >8, clip	

		
		
		double val = temp;

		DecimalFormat formatter = new DecimalFormat("0");
		DecimalFormat decimalFormatter = new DecimalFormat("0.0");
		String s;
		if (val % 1L > 0L) // <<this. causes weird math errors
			s = decimalFormatter.format(val);
		else
			s = formatter.format(val);
		
		return s;
		
		
		//remove trailing
		
		
		
		
		
		//String result = "" + temp;
		//return result;

	}

}
