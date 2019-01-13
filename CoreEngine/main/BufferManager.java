package main;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.ImageIOImageData;

public class BufferManager {
	
	public static void changeIcon() {
		try {
			Display.setIcon(new ByteBuffer[] {
					new ImageIOImageData().imageToByteBuffer(ImageIO.read(new File("Resources/icon.png")), false, false, null)});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
