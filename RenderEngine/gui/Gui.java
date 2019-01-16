package gui;

import loader.Loader;
import openglObjects.Vao;

import org.lwjgl.util.vector.Vector2f;

import texture.TextureMap;

public class Gui {
	
	private static Vao rectangle;
	
	public TextureMap textureMap;
	
	private Vector2f position;
	private Vector2f size;
	
	public Gui(TextureMap textureMap, Vector2f position, Vector2f size) {
		this.textureMap = textureMap;
		this.position = position;
		this.size = size;
	}
	
	public Vector2f getPosition() {
		return this.position;
	}
	
	public Vector2f getSize() {
		return this.size;
	}
	
	public void setPosition(Vector2f position) {
		this.position = position;
	}
	
	public void setSize(Vector2f size) {
		this.size = size;
	}
	
	public void setPositionAndSize(Vector2f position, Vector2f size) {
		this.position = position;
		this.size = size;
	}
	
	public static Vao getRectangle() {
		return rectangle;
	}
	
	public TextureMap getTextureMap() {
		return this.textureMap;
	}
	
	public static void loadRectangle(Loader loader) {
		float[] positions = {
				0f,  0f,
				1f,  0f,
				0f,  1f,
				1f,  1f
		};
		rectangle = loader.loadToVao(positions, 2);
	}

}
