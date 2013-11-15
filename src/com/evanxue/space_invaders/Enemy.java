package com.evanxue.space_invaders;

import com.evanxue.framework.Image;
import android.graphics.Rect;

/**
 * 
 */

/**
 * @author evanxue
 *
 */
public class Enemy
{


	public boolean inBounds = true;
	public int velocityx = 1;
	int counter = 0;
	double velocityX = 0;
	double velocityY = 1;
	int speed = 1;
	public double x = 100;
	public double y = 500;
	int bullet_size = 5;
	public int base;
	public int height;
	Image img;
	private int fireCounter = 100;
	private int fireDelayTimer = 400;
	public int ID = 0;
	private int[] bulletPattern = {-1, 0, 1, 0, -1, 0, 1};

	/**
	 * 
	 */
	public Enemy(int x, int y, int code) 
	{
		// TODO Auto-generated constructor stub
		this.x = x;
		this.y = y;
		fireCounter += (int)(Math.random()*400);
		ID = code;
		switch(code)
		{
			case 1:
				base = Assets.enemy1.getWidth();
				height = Assets.enemy1.getHeight();
				img = Assets.enemy1;
				
				break;
			case 2:
				base = Assets.enemy2.getWidth();
				height = Assets.enemy2.getHeight();
				img = Assets.enemy2;

				break;
			case 3:
				base = Assets.enemy3.getWidth();
				height = Assets.enemy3.getHeight();
				img = Assets.enemy3;

				break;
			default:
				base = Assets.enemy1.getWidth();
				height = Assets.enemy1.getHeight();
				img = Assets.enemy1;

		}
		

	}

	public void checkInbounds(int width, int height) 
	{
		// TODO Auto-generated method stub
		if (x  < 0 )
        {
			inBounds = false;
        }
        if ( x >= width )
        {
        	inBounds = false;
        }
        if ( y  < 0 )
        {
        	inBounds = false;
        }
        if ( y >= height)
        {
        	inBounds = false;
        }
	}

	public void moveE() 
	{
		// TODO Auto-generated method stub
		if(counter <= 0)
		{
			velocityX = 0;
			velocityY = -1;
		}
		if(counter>= 30)
		{
			velocityX = 0;
			velocityY = 1;
		}
		
		if(y < 120)
		{
			if(counter <= 0)
			{
				counter = 1;
				velocityX = 1;
				velocityY = 0;
			}
			else
			{
				counter+=speed;
			}

			
		}
		
		if(y>( 120 + 9 * GameScreen.Aheight/ 12 ))
		{
			if(counter >= 30)
			{
				counter = 29;
				velocityX = 1;
				velocityY = 0;
			}
			else
			{
				counter -=speed;
				
			}
		}
		
		
	}

	public void update(int num_Enemies) 
	{
		checkInbounds(GameScreen.Awidth, GameScreen.Aheight);
		// TODO Auto-generated method stub
		speed = Math.max(10 - (num_Enemies)/(GameScreen.rounds+1), 1);
		moveE();
		x+=velocityX*speed;
		y += velocityY*speed;
		fireCounter-= 1 + GameScreen.rounds / 5 ;
		
	}

	public boolean intersects(Bullet bullet) 
	{
		
		Rect eRect = new Rect((int)x, (int)y, (int)(x + base), (int)(y + height));
		Rect bRect = new Rect((int)bullet.posX, (int)bullet.posY, (int)(bullet.posX + 
				bullet.bulletRadius), (int)(bullet.posY + bullet.bulletRadius));
		if(Rect.intersects(eRect, bRect))
		{
			if((int)(Math.random()*GameScreen.powerUpDropRate)==0)
			{
				for(int i  = GameScreen.MAX_PLAYER_BULLETS; i < GameScreen.MAX_PLAYER_BULLETS+GameScreen.MAX_POWERUPS; i ++)
					if(GameScreen.bulletList[i]==null)
					{
						GameScreen.bulletList[i] = new PowerUp((int)x , (int)y + this.bullet_size, +5, 0, 
								bullet_size,
								(int)(Math.random()*PowerUp.PowerUpNum));
//						2);
						break;
					}	
			}
			return true;
		}
		return false;
		// TODO Auto-generated method stub
	}

	public void fire() 
	{
		// TODO Auto-generated method stub
		if(fireCounter > 0)
		{
			return;
		}
		fireCounter += fireDelayTimer;
		switch(ID)
		{
			case 1:
				for(int i  = GameScreen.ENEMY_BULLET_START; i < GameScreen.bulletList.length; i ++)
					if(GameScreen.bulletList[i]==null)
					{
						GameScreen.bulletList[i] = new Bullet((int)x , (int)y + this.bullet_size, +5, 0, bullet_size);
						break;
					}	
				break;
			case 2:
				int acounter = 0;
				for(int i  = GameScreen.ENEMY_BULLET_START; i < GameScreen.bulletList.length && acounter < 5; i ++)
				{
					if(GameScreen.bulletList[i]==null)
					{
						GameScreen.bulletList[i] = new Bullet((int)x + acounter * bullet_size , 
								(int)y + this.bullet_size, +5, 0, bullet_size);
						acounter ++ ;
					}	
				}
				break;
			case 3:
				int bcounter = 0;
				for(int i  = GameScreen.ENEMY_BULLET_START; i < GameScreen.bulletList.length; i ++)
				{
					if(GameScreen.bulletList[i]==null && bcounter < bulletPattern.length)
					{
						GameScreen.bulletList[i] = new Bullet((int)x  + bcounter * bullet_size , 
								(int)y + bulletPattern[bcounter]*this.bullet_size, +5, 0, bullet_size);
						bcounter ++ ;
						if(bcounter >= bulletPattern.length)
							break;
					}	
				}
				break;
		}
		
	}

	public boolean intersects(PlayerShip player) 
	{
		// TODO Auto-generated method stub
		Rect eRect = new Rect((int)x, (int)y, (int)(x + base), (int)(y + height));
		Rect pRect = new Rect((int)player.x, (int)player.y, (int)(player.x + 
				player.base), (int)(player.y + player.height));
		return Rect.intersects(eRect, pRect);
	}

}
