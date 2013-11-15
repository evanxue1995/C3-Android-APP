package com.evanxue.space_invaders;

import android.graphics.Color;
import android.graphics.Point;
import android.os.Build.VERSION;
import android.view.Display;

import com.evanxue.framework.Screen;
import java.util.List;

import com.evanxue.framework.Game;
import com.evanxue.framework.Graphics;
import com.evanxue.framework.Input.TouchEvent;
import com.evanxue.framework.implementation.AndroidGame;
import com.evanxue.framework.implementation.AndroidGraphics;


public class MainMenuScreen extends Screen {

	public static int Awidth;
    public static int Aheight;
    
	public MainMenuScreen(Game game) 
	{
        super(game);
        Display display = ((AndroidGame)game).getWindowManager().getDefaultDisplay();
    	Point size = new Point();
    	Awidth = display.getWidth();
    	Aheight = display.getHeight();
    }


    @Override
    public void update(float deltaTime) 
    {
        Graphics g = game.getGraphics();
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();


        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_UP) {
/**
 * int x = 0;
        g.drawRect(Awidth/7 + x, Aheight/5, 10, 10, Color.RED);
        g.drawRect(11*Awidth/41 + x, Aheight/5, 10, 10, Color.RED);
        g.drawRect(Awidth/7 + x, 7* Aheight/9, 10, 10, Color.RED);
        g.drawRect(11*Awidth/41 + x, 7* Aheight/9, 10, 10, Color.RED);
        
        x = Awidth/6;
        g.drawRect(Awidth/7 + x, Aheight/5, 10, 10, Color.RED);
        g.drawRect(11*Awidth/41 + x, Aheight/5, 10, 10, Color.RED);
        g.drawRect(Awidth/7 + x, 7* Aheight/9, 10, 10, Color.RED);
        g.drawRect(11*Awidth/41 + x, 7* Aheight/9, 10, 10, Color.RED);

        x = 2 *Awidth/6;
        g.drawRect(Awidth/7 + x, Aheight/5, 10, 10, Color.RED);
        g.drawRect(11*Awidth/41 + x, Aheight/5, 10, 10, Color.RED);
        g.drawRect(Awidth/7 + x, 7* Aheight/9, 10, 10, Color.RED);
        g.drawRect(11*Awidth/41 + x, 7* Aheight/9, 10, 10, Color.RED);

        x = 3 *Awidth/6;
        g.drawRect(Awidth/7 + x, Aheight/5, 10, 10, Color.RED);
        g.drawRect(11*Awidth/41 + x, Aheight/5, 10, 10, Color.RED);
        g.drawRect(Awidth/7 + x, 7* Aheight/9, 10, 10, Color.RED);
        g.drawRect(11*Awidth/41 + x, 7* Aheight/9, 10, 10, Color.RED);
        
                g.drawRect(Awidth/7 + x, Aheight/5, Awidth/8, 3*Aheight/5, Color.RED);

 */
            	
                if (inBounds(event, Awidth/7 , Aheight/5, Awidth/8, 3*Aheight/5)) 
                {
                    //START GAME
                   game.setScreen(new GameScreen(game));               
                }


            }
        }
    }


    private boolean inBounds(TouchEvent event, int x, int y, int width,
            int height) {
        if (event.x > x && event.x < x + width - 1 && event.y > y
                && event.y < y + height - 1)
            return true;
        else
            return false;
    }


    @Override
    public void paint(float deltaTime) {
        Graphics g = game.getGraphics();
//        g.drawImage(Assets.menu, 0, 0);
		g.drawRect(0, 0, Awidth, Aheight, Color.BLACK);
        ((AndroidGraphics)g).drawScaledImage(Assets.menu, 
        		0, 0, Awidth, Aheight, 0, 0, Assets.menu.getWidth(), Assets.menu.getHeight());
        
//        g.drawRect(Awidth/10, Aheight/10, 10, 10, Color.RED);
//        g.drawRect(Awidth/9, Aheight/9, 10, 10, Color.RED);
//        g.drawRect(Awidth/8, Aheight/8, 10, 10, Color.RED);
        
        
//        g.drawRect(Awidth/6, Aheight/6, 10, 10, Color.RED);
//        g.drawRect(Awidth/5, Aheight/5, 10, 10, Color.RED);


        
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
        //Display "Exit Game?" Box
        android.os.Process.killProcess(android.os.Process.myPid());


    }
}
