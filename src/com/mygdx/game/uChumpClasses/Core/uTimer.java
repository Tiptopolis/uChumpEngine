package com.mygdx.game.uChumpClasses.Core;

public class uTimer {

	public boolean repeat = false;
	public float startTime = 0;
	public float ellapsed = 0;
	public float duration = 0;
	
	
	public uTimer(float duration, boolean repeat)
	{
		this.duration = duration;
		this.repeat = repeat;
	}
	
	public void start()
	{
		this.startTime = System.currentTimeMillis();
	}
	
	public void update()
	{
		
		
		
	}
	
}
