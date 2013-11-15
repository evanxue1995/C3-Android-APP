/**
 * 
 */
package com.evanxue.framework;

/**
 * @author evanxue
 * This is the Game interface. This is the class that links together the audio, display, output, computation, etc, etc
 */
public interface Game 
{
	public Audio getAudio();

    public Input getInput();

    public FileIO getFileIO();

    public Graphics getGraphics();

    public void setScreen(Screen screen);

    public Screen getCurrentScreen();

    public Screen getInitScreen();
}
