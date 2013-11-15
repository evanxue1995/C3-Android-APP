package com.evanxue.space_invaders;


/**
 * 
 */
import android.graphics.Rect;

/**
 * @author evanxue
 *typical bullet fires forward
 */
public class Bullet
{
	boolean inBounds = true;
	int bulletRadius = 20;
	double posX = 0;
	double posY = 0;
	double velocityX = 0;
	double velocityY = 0;
	boolean pierce = false;
	/**
	 * 
	 */
	public Bullet(int x, int y, int vX, int vY) 
	{
		// TODO Auto-generated constructor stub
		posX = x;
		posY = y;
		velocityX = vX;
		velocityY = vY;
	}
	public Bullet(int x, int y, int vX, int vY, int bulletR) 
	{
		// TODO Auto-generated constructor stub
		posX = x;
		posY = y;
		velocityX = vX;
		velocityY = vY;
		bulletRadius = bulletR;
	}

	public void checkInbounds(int width, int height) 
	{
		// TODO Auto-generated method stub
		if (posX  < 0 )
        {
//			posX = 0;
			inBounds = false;
        }
        if ( posX + bulletRadius >= width )
        {
//        	posX = width - bulletRadius;
        	inBounds = false;
        }
        if ( posY  < 0)
        {
//        	posY = 0;
        	inBounds = false;
        }
        if ( posY + bulletRadius >= height )
        {
//        	posY = height - bulletRadius;
        	inBounds = false;
        }
	}

	public void update() 
	{
		checkInbounds(GameScreen.Awidth, GameScreen.Aheight);
		posX += velocityX;
		posY += velocityY;
		// TODO Auto-generated method stub
		
	}

}
