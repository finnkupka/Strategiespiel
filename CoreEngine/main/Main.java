package main;

import loader.Loader;
import loader.ObjLoader;
import openglObjects.Vao;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;

import audio.AudioMaster;
import camera.Camera;
import components.RenderComponent;
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
		
		AudioMaster.init();
		
		
		ArrayList<Entity> tempEntityList = new ArrayList<Entity>();
		
		//Sword Entity
		Vao sword_vao = ObjLoader.loadObjModel("sword", loader);
		TextureMap sword_texture = loader.loadTexture("sword");
		GenericEntity sword_entity = new GenericEntity(new Vector3f(0, 0, -2), sword_vao, sword_texture, "audioTest2");
		tempEntityList.add(sword_entity);
		
		//Rock Entity
		Vao rock_vao = ObjLoader.loadObjModel("rock", loader);
		TextureMap rock_texture = loader.loadTexture("rock");
		GenericEntity rock_entity = new GenericEntity(new Vector3f(0, 0, -5), rock_vao, rock_texture, "audioTest2");
		tempEntityList.add(rock_entity);
		
		while(!Display.isCloseRequested()) {
			
			if(Keyboard.isKeyDown(Keyboard.KEY_ADD)) {
				GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_LINE);
			} else if(Keyboard.isKeyDown(Keyboard.KEY_SUBTRACT)) {
				GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_FILL);
			}
			
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
		AudioMaster.cleanUp();
		
		DisplayManager.closeDisplay();
		
		System.out.println("Program closed");
	}
}
