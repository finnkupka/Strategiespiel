package main;

import java.awt.Toolkit;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.PixelFormat;

public class DisplayManager {
	
	private static final int FPS = 60;
	
	private static final int WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
	private static final int HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
	
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
		
	}
	
	public static void closeDisplay() {
		
		Display.destroy();
		
	}

}
