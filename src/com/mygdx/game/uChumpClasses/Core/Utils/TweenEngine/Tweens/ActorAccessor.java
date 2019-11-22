package com.mygdx.game.uChumpClasses.Core.Utils.TweenEngine.Tweens;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.uChumpClasses.Core.Utils.TweenEngine.TweenAccessor;

/**
 * Lidgx Actor tween accessor.
 * 
 */
public class ActorAccessor implements TweenAccessor<Actor>
{
	public static final int POSITION_XY = 1;
	public static final int SCALE_XY = 2;
	
	@Override
	public int getValues(Actor target, int tweenType, float[] returnValues)
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
			default:
				assert false;
				return -1;
		}
	}
	
	@Override
	public void setValues(Actor target, int tweenType, float[] newValues)
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
			default:
				assert false;
		}
	}
}
