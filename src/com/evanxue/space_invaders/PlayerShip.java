package com.evanxue.space_invaders;



/**
 * 
 */
import android.graphics.Rect;

/**
 * @author evanxue
 *
 */
public class PlayerShip
{
	public static Rect rect = new Rect(0, 0, 0, 0);
	boolean bullet_pierce = false;
	int base;
	int height;
	public int HP = 10;
	private int speed = 5;
	double velocityX = 0;
	double velocityY = 0;
	int bullet_size = 10;
	public double x = 100;
	public double y = 500;
	public static int defx = 100;
	public static int defy = 100;
	public static int[] powerUpList = new int[GameScreen.MAX_POWERUPS];
	private int fireCounter = -100;
	private int fireDelayTimer = 20;


	public PlayerShip()
	{
		
		base = Assets.playerImg.getWidth();
		height = Assets.playerImg.getHeight();
		x = 7 * GameScreen.Awidth /8;
		y = GameScreen.Aheight /2;
		defx = (int) x;
		defy = (int) y;
	}
	
	public void fire() 
	{
		// TODO Auto-generated method stub
		if(fireCounter > 0)
			return;
		fireCounter=fireDelayTimer;
		for(int i  = 0; i < GameScreen.MAX_PLAYER_BULLETS; i ++)
			if(GameScreen.bulletList[i]==null)
			{
				GameScreen.bulletList[i] = new Bullet((int)x -bullet_size , (int)y - bullet_size/2, -5, 0, bullet_size);
				if(this.bullet_pierce)
					GameScreen.bulletList[i].pierce = true;
				break;
			}
	}

	 void move(int eventX2, int eventY2) 
	{
		// set velocityX and velocityY to target position
		double deltaX =  eventX2 - x;
		double deltaY =  (eventY2 - y);
		double ratio = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
		if(ratio == 0)
		{
			velocityX = 0;
			velocityY = 0;
		}
		else
		{
			velocityX = speed / ratio * deltaX;
			velocityY = speed / ratio * deltaY;
		}
	}

	public void isInbounds(int width2, int height2) {
		// TODO Auto-generated method stub

		if (x  < 0 )
        {
			x=0;

        }
        if ( x + base >= width2 )
        {
        	x = width2 - base;
        }
        if ( y  < 0)
        {
        	y = 0;

        }
        if ( y + height >= height2 )
        {
        	y = height2 - height;
        }
	}

	public void update() 
	{
		if(HP<= 0)
		{
			gotoStartingPos();
		}
		fireCounter--;
		x += velocityX;
		y += velocityY;
		isInbounds (GameScreen.Awidth, GameScreen.Aheight );
		for(int i  = 0; i < GameScreen.MAX_POWERUPS ; i ++){
			if(powerUpList[i] > 0 )
				powerUpList[i]--;
			if(powerUpList[i]==1)
					remove(i);
			
		}
		// TODO Auto-generated method stub
		
	}

	

	

	public void gotoStartingPos()
	{
		x = PlayerShip.defx;
		y = PlayerShip.defy;
		HP = 10;
		GameScreen.deathCounter ++ ;
		GameScreen.livesLeft --;
	}
	public void stop() 
	{
		// TODO Auto-generated method stub
		velocityX = 0;
		velocityY = 0;
	}

	public boolean intersects(Bullet bullet) 
	{
		// TODO Auto-generated method stub
		
		Rect pRect = new Rect((int)x, (int)y, (int)(x + base), (int)(y + height));
		Rect bRect = new Rect((int)bullet.posX, (int)bullet.posY, (int)(bullet.posX + 
				bullet.bulletRadius), (int)(bullet.posY + bullet.bulletRadius));
		return Rect.intersects(bRect, pRect);	
	}
	public int getFireCounter()
	{
		return this.fireCounter;
	}
	public void setFireCounter( int counter)
	{
		fireCounter = counter;
	}
	public int getFireDelay()
	{
		return fireDelayTimer;
	}
	public void setFireDelay(int newDelay)
	{
		fireDelayTimer = newDelay;
	}
	public int getSpeed()
	{
		return speed;
	}
	public void setSpeed(int newSpeed)
	{
		speed = newSpeed;
	}

	public void add(PowerUp powerUp) 
	{
		if(powerUp.code < 0 && powerUp.code >=GameScreen.MAX_POWERUPS)
			return;
		if(powerUpList[powerUp.code]==0)
		{
			switch(powerUp.code)
			{
			case 0:
				HP += 10;
				break;
			case 1:
				fireDelayTimer /= 10;
				break;
			case 2:
				bullet_size *= 10;
				fireDelayTimer *= 10;
				bullet_pierce = true;
			}
		}
		
		// TODO Auto-generated method stub
			powerUpList[powerUp.code] += powerUp.timer;
//		int val = b? 1 : 0;
	}
	private void remove(int i) 
	{
		// TODO Auto-generated method stub
		switch(i)
		{
		case 0:
			break;
		case 1:
			fireDelayTimer *= 10;
			break;
		case 2:
			bullet_size /= 10;
			fireDelayTimer /= 10;
			bullet_pierce = false;
		}
	}

}
