package com.mygdx.game.uChumpClasses.Core.Utils;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import com.google.inject.Singleton;

@Singleton
public class uFileExecutor {

	
	public uFileExecutor()
	{
		
	}
	
	
	public static void open(String path)
	{
		try {
			Desktop.getDesktop().open(new File(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
