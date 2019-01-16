package main;

import java.awt.Toolkit;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Mouse;
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
	private static float frameRate = 0;
	private static long frameCount = 0;
	private static float showFramerate = 0;
	
	public static void createDisplay() {
		
		//createDisplayFullScreen();
		createDisplayWindowed();
		BufferManager.changeIcon();
		
	}
	
	private static void createDisplayWindowed() {
		try {
			Display.setDisplayModeAndFullscreen(new DisplayMode(WIDTH/*-200*/, HEIGHT/*-200*/));
			Display.create(new PixelFormat(), new ContextAttribs(3,2).withForwardCompatible(true).withProfileCore(true));
			
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
	}
	
	private static void createDisplayFullScreen() {
		try {
			
			DisplayMode[] displayModes = Display.getAvailableDisplayModes();
			DisplayMode displayMode = null;
			
			for(DisplayMode d : displayModes) {
				if(d.getWidth() == WIDTH && d.getHeight() == HEIGHT && d.isFullscreenCapable()) {
					displayMode = d;
					break;
				}
			}
			
			if(displayMode == null) {
				displayMode = new DisplayMode(WIDTH-150, HEIGHT-250);
			}
			
			Display.setDisplayModeAndFullscreen(displayMode);
			Display.create(new PixelFormat(), new ContextAttribs(3,2).withForwardCompatible(true).withProfileCore(true));
			
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
	}
	
	public static void updateDisplay() {
		
		if (frameCount % 15 == 0) showFramerate = ((int) frameRate*10.0f)/10.0f;
		
		Display.sync(FPS);
		Display.update();
		
		long currentFrameTime = getTime();
		DELTA = (currentFrameTime - lastFrameTime) / 1000f;
		lastFrameTime = currentFrameTime;
		frameRate = 1 / DELTA;
		
		Display.setTitle(TITLE + " | " + showFramerate + " fps" + " | " + frameCount + " frames");
		
		frameCount++;
	}
	
	public static void closeDisplay() {
		
		Display.destroy();
		Mouse.destroy();
		
	}
	
	public static long getTime() {
		return Sys.getTime() * 1000 / Sys.getTimerResolution();
	}
}
