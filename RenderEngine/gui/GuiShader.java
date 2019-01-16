package gui;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector4f;

import shader.Shader;

public class GuiShader extends Shader {
	
	private int location_translation;
	private int location_size;
	private int location_canvas;
	
	public GuiShader() {
		super("gui", "guiVertexShader", "guiFragmentShader");
	}
	
	@Override
	protected void getAllUniformLocation() {
		this.location_translation = super.getUniformLocation("translation");
		this.location_size = super.getUniformLocation("size");
		this.location_canvas = super.getUniformLocation("canvas");
	}
	
	public void loadTranslation(Vector2f translation) {
		super.loadVector2f(location_translation, translation);
	}
	
	public void loadSize(Vector2f size) {
		super.loadVector2f(location_size, size);
	}
	
	public void loadCanvas(Vector4f canvas) {
		super.loadVector4f(location_canvas, canvas);
	}

}
