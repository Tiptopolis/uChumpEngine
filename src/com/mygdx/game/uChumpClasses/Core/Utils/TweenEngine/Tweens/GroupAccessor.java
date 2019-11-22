package com.mygdx.game.uChumpClasses.Core.Utils.TweenEngine.Tweens;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.mygdx.game.uChumpClasses.Core.Utils.TweenEngine.TweenAccessor;

/**
 * Lidgx Group tween accessor.
 * 
 */
public class GroupAccessor implements TweenAccessor<Group>
{
	public static final int POSITION_XY = 1;
	public static final int SCALE_XY = 2;
	public static final int ROTATION = 3;
	
	@Override
	public int getValues(Group target, int tweenType, float[] returnValues)
	{
		switch (tweenType)
		{
			case POSITION_XY:
				returnValues[0] = target.getX();
				returnValues[1] = target.getY();
				return 2;
				
			case SCALE_XY:
				returnValues[0] = target.getX();
				returnValues[1] = target.getY();
				return 2;
				
			case ROTATION:
				returnValues[0] = target.getRotation();
				return 1;
			default:
				assert false;
				return -1;
		}
	}
	
	@Override
	public void setValues(Group target, int tweenType, float[] newValues)
	{
		switch (tweenType)
		{
			case POSITION_XY:
				target.setX(newValues[0]);
				target.setY(newValues[1]);
				break;
			case SCALE_XY:
				target.setScaleX(newValues[0]);
				target.setScaleY(newValues[1]);
				break;
			case ROTATION:
				target.setRotation(newValues[0]);
				break;
			default:
				assert false;
		}
	}
}