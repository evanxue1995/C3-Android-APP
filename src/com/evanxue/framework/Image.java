/**
 * 
 */
package com.evanxue.framework;
import com.evanxue.framework.Graphics.ImageFormat;

/**
 * @author evanxue
 *
 */
public interface Image 
{
	public int getWidth();
    public int getHeight();
    public ImageFormat getFormat();
    public void dispose();
}
