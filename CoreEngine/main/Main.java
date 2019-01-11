package main;

import loader.Loader;
import loader.ObjLoader;
import openglObjects.Vao;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import camera.Camera;
import components.ModelComponent;
import components.RenderComponent;
import components.TransformationComponent;
import entities.Entity;
import renderer.Renderer;
import texture.TextureMap;

public class Main {

	public static void main(String[] args) {
		
		DisplayManager.createDisplay();
		
		Loader loader = new Loader();
		Renderer renderer = new Renderer();
		RenderComponent.setRenderer(renderer);
		
		Camera camera = new Camera(new Vector3f(0,0,0), 0, 0, 0);
		
		//ObjectShader objectShader = new ObjectShader();
		
		Vao vao = ObjLoader.loadObjModel("sword", loader);
		TextureMap texture = loader.loadTexture("testTex");
		
		Entity entity = new Entity();
		entity.addComponent(new RenderComponent());
		entity.addComponent(new TransformationComponent(new Vector3f(0, 0, -2), 0, 0, 0, 1));
		entity.addComponent(new ModelComponent(vao, texture));
		entity.linkComponents();
		
		while(!Display.isCloseRequested()) {
			
			renderer.prepare();
			
			camera.update();
			renderer.loadViewMatrix(camera);
			
			//objectShader.startProgram();
			
			//renderer.render(vao);
			//renderer.getObjectRenderer().getObjectShader().startProgram();
			//renderer.getObjectRenderer().render(vao, texture);
			//renderer.getObjectRenderer().getObjectShader().stopProgram();
			
			entity.update();
			entity.render();
			
			//objectShader.stopProgram();
			
			DisplayManager.updateDisplay();
			
		}
		
		loader.cleanUp();
		renderer.cleanUp();
		
		DisplayManager.closeDisplay();
		
		System.out.println("Ende");

	}

}
