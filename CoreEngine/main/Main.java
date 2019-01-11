package main;

import loader.Loader;
import loader.ObjLoader;
import openglObjects.Vao;

import org.lwjgl.opengl.Display;

import renderer.Renderer;
import shader.ObjectShader;
import texture.TextureMap;

public class Main {

	public static void main(String[] args) {
		
		DisplayManager.createDisplay();
		
		Loader loader = new Loader();
		Renderer renderer = new Renderer();
		
		//ObjectShader objectShader = new ObjectShader();
		
		float[] positions = {
			 0.5f,  0.5f, 0,
			 0.5f, -0.5f, 0,
			-0.5f,  0.5f, 0
		};
		
		float[] textureCoords = {
				 0.5f,  0.5f,
				 0.5f, -0.5f,
				-0.5f,  0.5f
		};
		
		float[] normals = {
				 0.5f,  0.5f, 0,
				 0.5f, -0.5f, 0,
				-0.5f,  0.5f, 0
			};
		
		int[] indices = {
				0,1,2
		};
		
		//Vao vao = loader.loadToVao(positions, textureCoords, normals, indices);
		Vao vao = ObjLoader.loadObjModel("sword", loader);
		TextureMap texture = loader.loadTexture("qdqwd");
		
		while(!Display.isCloseRequested()) {
			
			renderer.prepare();
			
			//objectShader.startProgram();
			
			//renderer.render(vao);
			renderer.getObjectRenderer().getObjectShader().startProgram();
			renderer.getObjectRenderer().render(vao, texture);
			renderer.getObjectRenderer().getObjectShader().stopProgram();
			
			//objectShader.stopProgram();
			
			DisplayManager.updateDisplay();
			
		}
		
		loader.cleanUp();
		renderer.cleanUp();
		
		DisplayManager.closeDisplay();
		
		System.out.println("Ende");

	}

}
