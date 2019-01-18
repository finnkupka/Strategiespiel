package main;

import loader.Loader;
import loader.ObjLoader;
import openglObjects.Vao;
import input.InputManager;

import java.util.ArrayList;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;

import chat.Chat;
import audio.AudioMaster;
import camera.Camera;
import components.AudioComponent;
import components.RenderComponent;
import entities.Entity;
import entities.GenericEntity;
import gui.Gui;
import gui.GuiBox;
import gui.GuiButton;
import gui.GuiComponent;
import gui.GuiHorizontalSlider;
import gui.GuiList;
import gui.GuiTextField;
import gui.GuiVerticalSlider;
import renderer.Renderer;
import terrainTile.TerrainManager;
import terrainTile.TerrainTextureLibrary;
import terrainTile.TerrainTile;
import terrainTile.TerrainTiler;
import text.Text;
import text.TextGenerator;
import texture.TextureMap;

public class Main {
	
	public static boolean running = true;

	public static void main(String[] args) {
		
		DisplayManager.createDisplay();
		
		Loader loader = new Loader();
		Renderer renderer = new Renderer();
		RenderComponent.setRenderer(renderer);
		Camera camera = new Camera(new Vector3f(0,0,0));
		TerrainTile.setTerrainRenderer(renderer.getTerrainRenderer());
		
		TextureMap blendMap = loader.loadTexture("blendMap");
		TerrainTextureLibrary terrainTextureLibrary = new TerrainTextureLibrary(loader);
		
		TerrainTile[][] terrainTiles = TerrainTiler.generateTerrainTiles(32, "heightMap", terrainTextureLibrary, blendMap, loader);
		TerrainManager terrainManager = new TerrainManager(terrainTiles);
		
		Text.textGenerator = new TextGenerator();
		Gui.loadRectangle(loader);
		GuiBox.loadTextures(loader);
		GuiButton.loadTextures(loader);
		GuiTextField.loadTexture(loader);
		GuiList.loadTexture(loader);
		GuiVerticalSlider.loadTexture(loader);
		GuiHorizontalSlider.loadTexture(loader);
		GuiComponent.setRenderers(renderer.getGuiRenderer(), renderer.getTextRenderer());
		GuiComponent.setLoader(loader);
		
		Chat chat = new Chat(loader);
		
		ArrayList<Entity> tempEntityList = new ArrayList<Entity>();
		
		//Sword Entity
		Vao sword_vao = ObjLoader.loadObjModel("sword", loader);
		TextureMap sword_texture = loader.loadTexture("sword");
		GenericEntity sword_entity = new GenericEntity(new Vector3f(0, 0, -2), sword_vao, sword_texture);
		sword_entity.addComponent(new AudioComponent("audioTest2"));
		sword_entity.linkComponents();
		tempEntityList.add(sword_entity);
		
		//Rock Entity
		Vao rock_vao = ObjLoader.loadObjModel("rock", loader);
		TextureMap rock_texture = loader.loadTexture("rock");
		GenericEntity rock_entity = new GenericEntity(new Vector3f(0, 0, -5), rock_vao, rock_texture);
		rock_entity.linkComponents();
		tempEntityList.add(rock_entity);
		
		//Building Entity
		Vao building_vao = ObjLoader.loadObjModel("building", loader);
		TextureMap building_texture = loader.loadTexture("building1colored");
		GenericEntity building_entity = new GenericEntity(new Vector3f(-10, 0, -15), building_vao, building_texture);
		building_entity.linkComponents();
		tempEntityList.add(building_entity);
		
		while(!Display.isCloseRequested() && running) {
			
			InputManager.update();
			
			if(Keyboard.isKeyDown(Keyboard.KEY_ADD)) {
				GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_LINE);
			} else if(Keyboard.isKeyDown(Keyboard.KEY_SUBTRACT)) {
				GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_FILL);
			}
			
			if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
				try {
					Display.setFullscreen(false);
				} catch (LWJGLException e1) {
					e1.printStackTrace();
				}
			}
			
			renderer.prepare();
			camera.update();
			
			renderer.loadViewMatrix(camera.generateViewMatrix());
			
			for(TerrainTile[] t1 : terrainTiles) {
				for(TerrainTile t2 : t1) {
					t2.update();
					t2.render();
				}
			}
			
			for (Entity e : tempEntityList) {
				e.update();
				e.render();
			}
			
			chat.update();
			chat.render();
			
			DisplayManager.updateDisplay();
			
			InputManager.clear();
		}
		
		loader.cleanUp();
		renderer.cleanUp();
		AudioMaster.cleanUp();
		
		DisplayManager.closeDisplay();
		
		System.out.println("Program closed");
	}
}
