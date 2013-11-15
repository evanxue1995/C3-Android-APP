/**
 * 
 */
package com.evanxue.space_invaders;

/**
 * @author evanxue
 *
 */
public class PowerUp extends Bullet
{
	public static int PowerUpNum = 3;
	public static final int PowerUp_Size = 15;
	int code = 0;
	int timer = 100;
	public PowerUp(int x, int y, int vX, int vY) 
	{
		super(x, y, vX, vY);
		this.bulletRadius = PowerUp_Size;
		timer = 0;
		// TODO Auto-generated constructor stub
	}
	public PowerUp(int x, int y, int vX, int vY,int bulletR, int code) 
	{
		super(x, y, vX, vY, PowerUp_Size);
		this.code = code;
		switch(code)
		{
			case 0:
				timer = 0;
			case 1:
				timer = 1000;
				break;
			case 2: 
				timer = 1000;
				break;
			default:
				timer = 0;
		}
		// TODO Auto-generated constructor stub
	}
	public void powerUp(PlayerShip player) 
	{
		// TODO Auto-generated method stub
		player.add(this);
		
	}
	
}
