package text;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

import shader.Shader;

public class TextShader extends Shader {
	
	private int location_translation;
	private int location_size;
	private int location_textColor;
	private int location_canvas;
	
	public TextShader() {
		super("text", "textVertexShader", "textFragmentShader");
	}
	
	@Override
	protected void getAllUniformLocation() {
		this.location_translation = super.getUniformLocation("translation");
		this.location_size = super.getUniformLocation("size");
		this.location_textColor = super.getUniformLocation("textColor");
		this.location_canvas = super.getUniformLocation("canvas");
	}
	
	public void loadTranslation(Vector2f translation) {
		super.loadVector2f(location_translation, translation);
	}
	
	public void loadSize(float size) {
		super.loadFloat(location_size, size);
	}
	
	public void loadTextColor(Vector3f textColor) {
		super.loadVector3f(location_textColor, textColor);
	}
	
	public void loadCanvas(Vector4f canvas) {
		super.loadVector4f(location_canvas, canvas);
	}

}
