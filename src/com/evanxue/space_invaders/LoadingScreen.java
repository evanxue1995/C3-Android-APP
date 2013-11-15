package com.evanxue.space_invaders;

import com.evanxue.framework.Screen;

import com.evanxue.framework.Game;
import com.evanxue.framework.Graphics;
import com.evanxue.framework.Graphics.ImageFormat;

public class LoadingScreen extends Screen 
{
	public LoadingScreen(Game game) {
        super(game);
    }
	
	@Override
    public void update(float deltaTime) {
        Graphics g = game.getGraphics();
        Assets.menu = g.newImage("menu2.png", ImageFormat.RGB565);
        Assets.enemy1 = g.newImage("Enemy1.png", ImageFormat.RGB565);
        Assets.enemy2 = g.newImage("Enemy2.png", ImageFormat.RGB565);
        Assets.enemy3 = g.newImage("Enemy3.png", ImageFormat.RGB565);
        Assets.playerImg = g.newImage("Ship.png", ImageFormat.RGB565);


//        Assets.click = game.getAudio().createSound("explode.ogg");


        
        game.setScreen(new MainMenuScreen(game));


    }


    @Override
    public void paint(float deltaTime) {


    }


    @Override
    public void pause() {


    }


    @Override
    public void resume() {


    }


    @Override
    public void dispose() {


    }


    @Override
    public void backButton() {


    }

}
