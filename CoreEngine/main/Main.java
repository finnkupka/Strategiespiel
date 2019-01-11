package main;

import loader.Loader;
import loader.ObjLoader;
import openglObjects.Vao;

import java.util.ArrayList;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import camera.Camera;
import components.ModelComponent;
import components.RenderComponent;
import components.ShowcaseComponent;
import components.TransformationComponent;
import entities.Entity;
import entities.GenericEntity;
import renderer.Renderer;
import texture.TextureMap;

public class Main {

	public static void main(String[] args) {
		
		DisplayManager.createDisplay();
		
		Loader loader = new Loader();
		Renderer renderer = new Renderer();
		RenderComponent.setRenderer(renderer);
		
		Camera camera = new Camera(new Vector3f(0,0,0), 0, 0, 0);
		
		ArrayList<Entity> tempEntityList = new ArrayList<Entity>();
		
		//Sword Entity
		Vao sword_vao = ObjLoader.loadObjModel("sword", loader);
		TextureMap sword_texture = loader.loadTexture("sword");
		GenericEntity sword_entity = new GenericEntity(new Vector3f(0, 0, -2), sword_vao, sword_texture);
		tempEntityList.add(sword_entity);
		
		//Rock Entity
		Vao rock_vao = ObjLoader.loadObjModel("rock", loader);
		TextureMap rock_texture = loader.loadTexture("rock");
		GenericEntity rock_entity = new GenericEntity(new Vector3f(0, 0, -5), rock_vao, rock_texture);
		tempEntityList.add(rock_entity);
		
		while(!Display.isCloseRequested()) {
			
			renderer.prepare();
			camera.update();
			renderer.loadViewMatrix(camera);
			
			for (Entity e : tempEntityList) {
				e.update();
				e.render();
			}
			
			DisplayManager.updateDisplay();
			
		}
		
		loader.cleanUp();
		renderer.cleanUp();
		
		DisplayManager.closeDisplay();
		
		System.out.println("Program closed");
	}
}
