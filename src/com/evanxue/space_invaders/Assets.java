package com.evanxue.space_invaders;
import com.evanxue.framework.Image;
import com.evanxue.framework.Sound;
import com.evanxue.framework.Music;

public class Assets {
    
    public static Image menu;
    public static Image enemy1;
    public static Image enemy2;
    public static Image enemy3;
    public static Image playerImg;
	public static Music theme;
	public static void load(SampleGame sampleGame) {
		// TODO Auto-generated method stub
		theme = sampleGame.getAudio().createMusic("01 ParagonX9 - Chaoz Fantasy (ID_ 85046).mp3");
		theme.setLooping(true);
		theme.setVolume(0.85f);
		theme.play();
	}
//    public static Sound click;
}