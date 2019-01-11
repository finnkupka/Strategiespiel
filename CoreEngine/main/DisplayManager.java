package main;

import java.awt.Toolkit;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.PixelFormat;

public class DisplayManager {
	
	private static final int FPS = 60;
	
	private static final String TITLE = "Strategiespiel";
	
	private static final int WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
	private static final int HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
	
	public static float DELTA = 0;
	private static long lastFrameTime = getTime();
	private static float framerate = 0;
	
	public static void createDisplay() {
		
		try {
			
			Display.setDisplayModeAndFullscreen(new DisplayMode(WIDTH, HEIGHT));
			Display.create(new PixelFormat(), new ContextAttribs(3,2).withForwardCompatible(true).withProfileCore(true));
			
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void updateDisplay() {
		
		Display.sync(FPS);
		Display.update();
		
		long currentFrameTime = getTime();
		DELTA = (currentFrameTime - lastFrameTime) / 1000f;
		lastFrameTime = currentFrameTime;
		framerate = 1 / DELTA;
		
		Display.setTitle(TITLE + "  FPS: " + framerate);
		
	}
	
	public static void closeDisplay() {
		
		Display.destroy();
		
	}
	
	public static long getTime() {
		return Sys.getTime() * 1000 / Sys.getTimerResolution();
	}

}
