package text;

import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import loader.Loader;
import openglObjects.Vao;
import texture.TextureMap;

public class Text {
	
	public static TextGenerator textGenerator;
	
	public StringBuilder string;
	
	private Vao vao;
	
	private TextData textData;
	
	private Vector3f color;
	private Vector2f position;
	private float size;
	private float blockWidth;
	
	private Cursor cursor;
	
	private Font font;
	
	public Text(String text, Font font, Loader loader, Vector3f color, Vector2f position, float size, float blockWidth) {
		this.string = new StringBuilder();
		this.string.append(text);
		
		this.cursor = new Cursor(0, 0);
		
		this.color = color;
		this.position = position;
		this.size = size;
		this.blockWidth = blockWidth;
		
		this.font = font;
		
		this.size /= 80;
		
		this.textData = textGenerator.generateText(this.string, this.font, this.cursor, this.blockWidth * (1 / (this.size * 80 * 9)));
		this.vao = loader.loadToVao(this.textData.getPositions(), this.textData.getTextureCoords(), 2);
	}
	
	public void append(String string, Loader loader) {
		
		StringBuilder text = new StringBuilder(string);
		
		TextData newTextData = textGenerator.generateText(text, this.font, this.cursor, this.blockWidth * (1 / (this.size * 80 * 9)));
		this.textData.append(newTextData);
		
		this.string.append(text);
		
		GL30.glBindVertexArray(this.vao.getID());
		
		loader.updateDataInVbo(this.vao.getVbo(0), this.textData.getPositions(), 2, 0);
		loader.updateDataInVbo(this.vao.getVbo(1), this.textData.getTextureCoords(), 2, 1);
		
		GL30.glBindVertexArray(0);
		
		this.vao.changeVertexCount(newTextData.getPositions().length / 2);
	}
	
	public void delete(int number, Loader loader) {
		
		if(number > this.string.length()) {
			return;
		}
		
		char[] deletedCharacters = this.string.substring(this.string.length() - number).toCharArray();
		
		for(int i = 0; i < deletedCharacters.length; i++) {
			char c = deletedCharacters[i];
			
			if(c == '\n') {
				
				this.cursor.setPositionX(0);
				this.cursor.increasePositionY(80);
				
				this.string.delete(this.string.length() - number, this.string.length());
				
				int index = Math.max(this.string.lastIndexOf("\n"), 0);
				
				char[] characters = this.string.substring(index).toCharArray();
				
				for(char character : characters) {
					this.cursor.increasePositionX(this.font.getCharacter((int)character).getXAdvance() * (9f / 16f));
				}
				
			} else {
				
				Character character = this.font.getCharacter((int)c);
				this.cursor.increasePositionX(-character.getXAdvance() * (9f / 16f));
				
				this.string.delete(this.string.length() - number, this.string.length());
				this.textData.delete(number);
				
				GL30.glBindVertexArray(this.vao.getID());
				
				loader.updateDataInVbo(this.vao.getVbo(0), this.textData.getPositions(), 2, 0);
				loader.updateDataInVbo(this.vao.getVbo(1), this.textData.getTextureCoords(), 2, 1);
				
				GL30.glBindVertexArray(0);
				
				this.vao.changeVertexCount(-(number * 6));
				
			}
		}
	}
	
	public String getString() {
		return this.string.toString();
	}
	
	public Vao getVao() {
		return this.vao;
	}
	
	public Font getFont() {
		return this.font;
	}
	
	public Vector3f getColor() {
		return this.color;
	}
	
	public Vector2f getPosition() {
		return this.position;
	}
	
	public float getSize() {
		return this.size;
	}
	
	public void setSize(float size) {
		this.size = size / 80;
	}
	
	public void setColor(Vector3f color) {
		this.color = color;
	}

}
