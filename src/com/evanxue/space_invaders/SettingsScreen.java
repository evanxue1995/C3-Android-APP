/**
 * 
 */
package com.evanxue.space_invaders;

import android.graphics.Color;

import com.evanxue.framework.Game;
import com.evanxue.framework.Graphics;
import com.evanxue.framework.Screen;

/**
 * @author evanxue
 *
 */
public class SettingsScreen extends Screen
{

	public SettingsScreen(Game game) 
	{
		super(game);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(float deltaTime) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void paint(float deltaTime) 
	{
		// TODO Auto-generated method stub
        Graphics g = game.getGraphics();
		g.drawRect(0, 0, 100, 100, Color.YELLOW);

		
	}

	@Override
	public void pause() 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void backButton() 
	{
		// TODO Auto-generated method stub
		
	}

}
