/**
 * 
 */
package com.evanxue.framework;
import android.graphics.Paint;
/**
 * @author evanxue
 * The Graphics interface contains many methods that we will use to draw images to the screen.
 * 
 */
public interface Graphics 
{
	public static enum ImageFormat{
		ARGB8888, ARGB4444, RGB565
	}
	
	public Image newImage(String fileName, ImageFormat format);
	
	public void clearScreen( int color);
	
	public void drawLine(int x, int y, int x2, int y2, int color);
	
	public void drawRect(int x, int y, int width, int height, int color);
	
	public void drawImage(Image image, int x, int y, int srcX, int srcY, int srcWidth, int srcHeight);
	
	public void drawImage(Image image, int x, int y);
	
	void drawString(String text, int x, int y, Paint paint);
	
	public int getWidth();
	
	public int getHeight();
	/**
	 * ARGB in the last method stands for Alpha, Red, Green, and Blue.
	 * @param i
	 * @param j
	 * @param k
	 * @param l
	 */
	public void drawARGB(int i, int j,  int k, int l);
}
