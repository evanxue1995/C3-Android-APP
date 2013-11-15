package com.evanxue.space_invaders;
/**
 * My game for space invaders
 */
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;

import com.evanxue.framework.Game;
import com.evanxue.framework.Graphics;
import com.evanxue.framework.Image;
import com.evanxue.framework.Screen;
import com.evanxue.framework.Input.TouchEvent;
import com.evanxue.framework.implementation.AndroidGame;

public class GameScreen extends Screen {

	enum GameState {
        Ready, Running, Paused, GameOver
    }

    GameState state = GameState.Ready;
    

    // Variable Setup
    //global constants
    public static int Awidth;
    public static int Aheight;
    public static final int MAX_PLAYER_BULLETS = 200;
	public static final int MAX_ENEMIES_AMT = 50;
	public static final int MAX_POWERUPS = 10;
	//Global variables
	private int num_Enemies = 0;
    public static int deathCounter = 0;
    public static int powerUpDropRate = 30;
	public static int rounds = 0;
	public static int score = 0;
	public static int num_Enemies_Killed = 0;
    //Images for various enemies
    private Image playerSprite, enemySprite1, enemySprite2, enemySprite3;
    // You would create game objects here.
    private static PlayerShip player;
    public static Bullet[] bulletList;
    
	public static final int ENEMY_BULLET_START = MAX_PLAYER_BULLETS + MAX_POWERUPS;

    public static Enemy[] enemyList;
    
    
    
    public static int livesLeft = 10;
    Paint paint, paint2;

    public GameScreen(Game game) 
    {
        super(game);
        deathCounter = 0;
    	rounds = 0;
    	score = 0;
    	num_Enemies_Killed = 0;
        livesLeft = 10;

        //Save settings for Screen
        	Display display = ((AndroidGame)game).getWindowManager().getDefaultDisplay();
        	Point size = new Point();
        	Awidth = display.getWidth();
        	Aheight = display.getHeight();
//        WindowManager wm = (WindowManager) ctx.getSystemService(Context.WINDOW_SERVICE);
//        Display display = wm.getDefaultDisplay();
        
        
        // Initialize game objects here
        //Creating player
        player = new PlayerShip();
        //Creating enemies
        enemyList= new Enemy[MAX_ENEMIES_AMT];
        bulletList = new Bullet[1000];
        loadMoreEnemies();
        
        playerSprite = Assets.playerImg;
        enemySprite1 = Assets.enemy1;
        enemySprite2 = Assets.enemy2;
        enemySprite3 = Assets.enemy3;
        // Defining a paint object
        paint = new Paint();
        paint.setTextSize(30);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);
        
        paint2 = new Paint();
		paint2.setTextSize(100);
		paint2.setTextAlign(Paint.Align.CENTER);
		paint2.setAntiAlias(true);
		paint2.setColor(Color.WHITE);

    }

    @Override
    public void update(float deltaTime) {
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();

        // We have four separate update methods in this example.
        // Depending on the state of the game, we call different update methods.
        // Refer to Unit 3's code. We did a similar thing without separating the
        // update methods.

        if (state == GameState.Ready)
            updateReady(touchEvents);
        if (state == GameState.Running)
            updateRunning(touchEvents, deltaTime);
        if (state == GameState.Paused)
            updatePaused(touchEvents);
        if (state == GameState.GameOver)
            updateGameOver(touchEvents);
    }

    private void updateReady(List<TouchEvent> touchEvents) {
        
        // This example starts with a "Ready" screen.
        // When the user touches the screen, the game begins. 
        // state now becomes GameState.Running.
        // Now the updateRunning() method will be called!
        
        if (touchEvents.size() > 0)
            state = GameState.Running;
    }

    private void updateRunning(List<TouchEvent> touchEvents, float deltaTime) {
        
        //This is identical to the update() method from our Unit 2/3 game.
        
        
        // 1. All touch input is handled here:
        int len = touchEvents.size();
        for (int i = 0; i < len; i++) 
        {
            TouchEvent event = touchEvents.get(i);

            if (event.type == TouchEvent.TOUCH_DOWN || event.type == TouchEvent.TOUCH_DRAGGED) 
            {
            	player.move(event.x, event.y);
            	player.fire();
            }

            if (event.type == TouchEvent.TOUCH_UP) {
            	player.stop();
            }

            
        }
        
        // 2. Check miscellaneous events like death:
        
//        if (livesLeft == 0) {
//            state = GameState.GameOver;
//        }
        
        
        // 3. Call individual update() methods here.
        player.update(); //player movement
        for(int i  = 0; i < bulletList.length; i++)
        {
        	if(bulletList[i]!=null)
        	{
        		if(bulletList[i].inBounds)
        		{
        			bulletList[i].update(); // bullet movement
        		}
        		else
        		{
        			bulletList[i] = null;
        		}
        	}
        }
        for(int i  = 0; i < enemyList.length; i++)
        {
        	if(enemyList[i]!=null)
        	{
        		if(enemyList[i].inBounds)
        		{
        			enemyList[i].update(num_Enemies); // enemy movement
        			enemyList[i].fire();
        		}
        		else
        		{
        			enemyList[i] = null;
					num_Enemies--;
        		}
        	}
        }
        
        for(int i  = 0; i < enemyList.length; i++)//change later
        {//enemy - bullet collision
    		for(int j = 0; enemyList[i]!=null && j < MAX_PLAYER_BULLETS; j ++)
    			if(bulletList[j]!=null)
    				if(enemyList[i].intersects(bulletList[j]))
    				{
    					score+=enemyList[i].ID * 100 * rounds;
    						enemyList[i] = null;
    						if(!bulletList[j].pierce)
    							bulletList[j] = null;
    						num_Enemies--;
    						num_Enemies_Killed++;
    						if(rounds >=20 && num_Enemies_Killed % 1000 ==0 )
    							rounds++;

    						
    				}
    		//enemy player collision
    		if(enemyList[i]!=null)
    		{
    			if(enemyList[i].intersects(player))
    			{
    				player.HP -=20;
    				enemyList[i] = null;
					num_Enemies--;
					num_Enemies_Killed++;
					if(rounds >=20 && num_Enemies_Killed % 1000 ==0 )
						rounds++;
    			}
    			
    		}
        }
        //player - powerup collision
        for(int i  = GameScreen.MAX_PLAYER_BULLETS; i < GameScreen.ENEMY_BULLET_START; i ++)
			if(bulletList[i]!=null)
			{
				if(player.intersects(bulletList[i]) && bulletList[i] instanceof PowerUp)
				{
					((PowerUp)bulletList[i]).powerUp(player);
					bulletList[i] = null;
				}
			}
        //player - bullet colliion
        for(int i  = GameScreen.ENEMY_BULLET_START; i < bulletList.length; i ++)
			if(bulletList[i]!=null)
			{
				if(player.intersects(bulletList[i]))
				{
					player.HP-=3;
					bulletList[i] = null;
				}
			}	

        if(num_Enemies == 0 && rounds < 20)
        {
        	loadMoreEnemies();
        }
        else
        {
        	if(rounds >= 20 && num_Enemies <= MAX_ENEMIES_AMT - 40 / rounds )
        		loadMoreEnemies();
        }
        // This is where all the game updates happen.
        // For example, robot.update();
    }

    private void loadMoreEnemies() 
    {
		// TODO Auto-generated method stub
    	if(rounds < 20)
    		rounds++;
    		

    	int incre = Math.min((int) (.5 * rounds), 3);
    	for(int i = 0; i < MAX_ENEMIES_AMT - 40 / rounds; i ++)
        {
    		if(enemyList[i]==null)
    		{
		    	enemyList[i] = new Enemy( 130 + (i / 10) * 30, 120 + (i%10) * (Aheight/12), (int)(Math.random()*incre)+1);
		    	enemyList[i].counter = (i/10)%2 * 30;
		    	num_Enemies++;
    		}
        }
    	

	}

	private void updatePaused(List<TouchEvent> touchEvents) 
    {
        int len = touchEvents.size();
        for (int i = 0; i < len; i++) 
        {
            TouchEvent event = touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_UP) 
            {
            	if (inBounds(event, 0, 0, Awidth, Aheight/2)) {
						resume();
				}
            	if (inBounds(event, 0, Aheight/2, Awidth, Aheight/2)) {
            		state = GameState.GameOver;
            	}
					
				
            }
        }
    }

    private void updateGameOver(List<TouchEvent> touchEvents) 
    {
        int len = touchEvents.size();
        for (int i = 0; i < len; i++) 
        {
            TouchEvent event = touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_UP) 
            {
                if (event.x > 300 && event.x < 980 && event.y > 100 && event.y < 500) 
                {
                    nullify();
                    game.setScreen(new MainMenuScreen(game));
                    return;
                }
            }
        }

    }
    
    private void goToMenu() 
    {
		// TODO Auto-generated method stub
		game.setScreen(new MainMenuScreen(game));

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

        // First draw the game elements.
		g.drawRect(0, 0, Awidth, Aheight, Color.BLACK);
		g.drawImage(playerSprite, (int)player.x, (int)player.y);
		
		for(int i  = 0; i < bulletList.length; i++)
        {
        	if(bulletList[i]!=null)
        	{
        		if(i >= GameScreen.MAX_PLAYER_BULLETS &&  i < GameScreen.MAX_PLAYER_BULLETS + GameScreen.MAX_POWERUPS )
        			g.drawRect((int)bulletList[i].posX, (int)bulletList[i].posY,bulletList[i].bulletRadius, bulletList[i].bulletRadius, Color.YELLOW);
        		else
        			g.drawRect((int)bulletList[i].posX, (int)bulletList[i].posY,bulletList[i].bulletRadius, bulletList[i].bulletRadius, Color.WHITE);
        	}
        }

        for(int i =0; i <enemyList.length; i ++)
        {
        	if(enemyList[i]!=null)
        	{
//        		System.out.println("lol");
                g.drawImage( enemyList[i].img, (int)enemyList[i].x, (int)enemyList[i].y );
//        		gc.fillRect(enemyList[i].x, enemyList[i].y, enemyList[i].width, enemyList[i].height);
        	}
        }
        // Example:
        // g.drawImage(Assets.background, 0, 0);
        // g.drawImage(Assets.character, characterX, characterY);

        // Secondly, draw the UI above the game elements.
        if (state == GameState.Ready)
            drawReadyUI();
        if (state == GameState.Running)
            drawRunningUI();
        if (state == GameState.Paused)
            drawPausedUI();
        if (state == GameState.GameOver)
            drawGameOverUI();

    }

    private void nullify() {

        // Set all variables to null. You will be recreating them in the
        // constructor.
        paint = null;

        // Call garbage collector to clean up memory.
        System.gc();
    }

    private void drawReadyUI() {
        Graphics g = game.getGraphics();

        g.drawARGB(155, 0, 0, 0);
        g.drawString("Tap each side of the screen to move in that direction.",
                640, 300, paint);

    }

    private void drawRunningUI() {
        Graphics g = game.getGraphics();
        g.drawString("Lives" + this.livesLeft, 60, 30, paint);
        g.drawString("Health" + player.HP, 60, 60, paint);
        g.drawString("Rounds" + rounds, 60, 90, paint);
        g.drawString("Score" + score, 60, 120, paint);

    }

    private void drawPausedUI() {
        Graphics g = game.getGraphics();
        // Darken the entire screen so you can display the Paused screen.
        g.drawARGB(155, 0, 0, 0);

		g.drawString("Resume", 400, 165, paint2);
		g.drawString("Menu", 400, Aheight/2 + 360, paint2);

    }

    private void drawGameOverUI() {
        Graphics g = game.getGraphics();
        g.drawRect(0, 0, 1281, 801, Color.BLACK);
        g.drawString("GAME OVER.", 640, 300, paint);
        g.drawString("Score" + score, 400, 120, paint2);
        g.drawString("Lives" + livesLeft, 400, 240, paint2);
        g.drawString("EnemiesKilled" + num_Enemies_Killed, 400, 360, paint2);



    }

    @Override
    public void pause() {
        if (state == GameState.Running)
            state = GameState.Paused;

    }

    @Override
    public void resume() {
    	if (state == GameState.Paused)
			state = GameState.Running;
    }

    @Override
    public void dispose() {

    }

    @Override
    public void backButton() 
    {
    	if(state == GameState.Paused)
    	{
    		nullify();
			goToMenu();
    	}
        pause();
    }

}
