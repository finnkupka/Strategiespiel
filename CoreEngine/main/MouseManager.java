package main;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

public class MouseManager {
	
	private static int windowX, windowY, windowW, windowH;
	private static Robot bot;
	private static boolean active = true;
	
	static {
		windowX = Display.getX();
		windowY = Display.getY();
		windowW = Display.getWidth();
		windowH = Display.getHeight();
		try {
			bot = new Robot();
		} catch (AWTException e) {
			System.out.println("Could not create Robot");
			e.printStackTrace();
		}
	}
	
	public static void update() {
		if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) active = false;
		if (Display.isActive() && active) {
			windowX = Display.getX();
			windowY = Display.getY();
			windowW = Display.getWidth();
			windowH = Display.getHeight();
			constrainMouseToWindow();
		}
	}
	
	private static void constrainMouseToWindow() {
		MouseInfo.getPointerInfo();
		Point pt = MouseInfo.getPointerInfo().getLocation();
		int mouseX = pt.x;
		int mouseY = pt.y;
		int x = 0, y = 0;
		if (mouseX < windowX) 		  x = windowX;
		if (mouseX > windowX+windowW) x = windowX+windowW;
		if (mouseY < windowY) 		  y = windowY;
		if (mouseY > windowY+windowH) y = windowY+windowH;
		if (x != 0) bot.mouseMove(x, mouseY);
		if (y != 0) bot.mouseMove(mouseX, y);
	}
}
