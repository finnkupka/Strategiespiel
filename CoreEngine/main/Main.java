package main;

import loader.Loader;
import openglObjects.Vao;

import org.lwjgl.opengl.Display;

import renderer.Renderer;

public class Main {

	public static void main(String[] args) {
		
		DisplayManager.createDisplay();
		
		Loader loader = new Loader();
		Renderer renderer = new Renderer();
		
		float[] positions = {
			 0.5f,  0.5f, 0,
			 0.5f, -0.5f, 0,
			-0.5f,  0.5f, 0
		};
		
		float[] textureCoords = {
			0f, 1f,
			1f, 1f,
			1f, 0f
		};
		
		Vao vao = loader.loadToVao(positions, 3);
		
		while(!Display.isCloseRequested()) {
			
			renderer.prepare();
			
			renderer.render(vao);
			
			DisplayManager.updateDisplay();
			
		}
		
		DisplayManager.closeDisplay();

	}

}
