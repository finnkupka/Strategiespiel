package main;

import loader.Loader;
import openglObjects.Vao;

import org.lwjgl.opengl.Display;

import renderer.Renderer;
import shader.ObjectShader;

public class Main {

	public static void main(String[] args) {
		
		DisplayManager.createDisplay();
		
		Loader loader = new Loader();
		Renderer renderer = new Renderer();
		
		ObjectShader objectShader = new ObjectShader();
		
		float[] positions = {
			 0.5f,  0.5f, 0,
			 0.5f, -0.5f, 0,
			-0.5f,  0.5f, 0
		};
		
		Vao vao = loader.loadToVao(positions, 3);
		
		while(!Display.isCloseRequested()) {
			
			renderer.prepare();
			
			objectShader.startProgram();
			
			renderer.render(vao);
			
			objectShader.stopProgram();
			
			DisplayManager.updateDisplay();
			
		}
		
		DisplayManager.closeDisplay();

	}

}
