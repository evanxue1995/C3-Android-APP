/**
 * 
 */
package com.evanxue.space_invaders;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.util.Log;
import com.evanxue.framework.Screen;
import com.evanxue.framework.implementation.AndroidGame;

/**
 * @author evanxue
 *
 */
public class SampleGame extends AndroidGame
{
	public static String map;
	boolean firstTimeCreate = true;
	
	public Screen getInitScreen() 
	{
		if (firstTimeCreate) {
			Assets.load(this);
			firstTimeCreate = false;
		}

		
        return new LoadingScreen(this); 
    }
	private static String convertStreamToString(InputStream is) {

		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append((line + "\n"));
			}
		} catch (IOException e) {
			Log.w("LOG", e.getMessage());
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				Log.w("LOG", e.getMessage());
			}
		}
		return sb.toString();
	}
	
	public void onResume() {
		super.onResume();

		Assets.theme.play();

	}

	@Override
	public void onPause() {
		super.onPause();
		Assets.theme.pause();
	}
	@Override
	public void onBackPressed() 
	{
		getCurrentScreen().backButton();
	}
}
