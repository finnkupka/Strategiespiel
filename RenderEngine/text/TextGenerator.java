package text;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector2f;

import loader.Loader;
import openglObjects.Vao;

public class TextGenerator {
	
	//private Font font;
	
	public TextGenerator() {
		//this.font = font;
	}
	
	public TextData generateText(StringBuilder text, Font font, Cursor cursor, float blockWidth) {
		
		float cursorPositionX = cursor.getPositionX();
		float cursorPositionY = cursor.getPositionY();
		
		float aspectRatio = (float) Display.getHeight() / Display.getWidth();
		
		float[] positionsArray = null;
		float[] textureCoordsArray = null;
		
		List<Vector2f> positions = new ArrayList<Vector2f>();
		List<Vector2f> textureCoords = new ArrayList<Vector2f>();
		
		char[] characters = text.toString().toCharArray();
		
		for(int i = 0; i < characters.length; i++) {
			char c = characters[i];
			
			Character character = font.getCharacter((int) c);
			
			if(character == null) {
				continue;
			}
			
			if(c == '\n') {
				cursorPositionX = 0;
				cursorPositionY -= 80;
				continue;
			}
			
			if(cursorPositionX + 10 > blockWidth * 80 * aspectRatio) {
				cursorPositionX = 0;
				cursorPositionY -= 80;
				int numberLines = 0;
				for(char currentCharacter : text.toString().toCharArray()) {
					if(currentCharacter == '\n') {
						numberLines++;
					}
				}
				text.insert(i + numberLines, "\n");
			}
			
			float width = character.getWidth();
			float height = character.getHeight();
			
			float xPosition = character.getXPosition();
			float yPosition = character.getYPosition();
			
			float xOffset = character.getXOffest();
			float yOffset = character.getYOffset();
			
			Vector2f position1 = new Vector2f(cursorPositionX + xOffset							, cursorPositionY - height - yOffset);
			Vector2f position2 = new Vector2f(cursorPositionX + xOffset + width * aspectRatio	, cursorPositionY - height - yOffset);
			Vector2f position3 = new Vector2f(cursorPositionX + xOffset + width * aspectRatio	, cursorPositionY - yOffset);
			Vector2f position4 = new Vector2f(cursorPositionX + xOffset							, cursorPositionY - yOffset);
			
			Vector2f textureCoord1 = new Vector2f(xPosition / 512f			, (yPosition + height) / 512f);
			Vector2f textureCoord2 = new Vector2f((xPosition + width) / 512f, (yPosition + height) / 512f);
			Vector2f textureCoord3 = new Vector2f((xPosition + width) / 512f, yPosition / 512f);
			Vector2f textureCoord4 = new Vector2f(xPosition / 512f			, yPosition / 512f);
			
			positions.add(position1);
			positions.add(position2);
			positions.add(position3);
			positions.add(position1);
			positions.add(position3);
			positions.add(position4);
			
			textureCoords.add(textureCoord1);
			textureCoords.add(textureCoord2);
			textureCoords.add(textureCoord3);
			textureCoords.add(textureCoord1);
			textureCoords.add(textureCoord3);
			textureCoords.add(textureCoord4);
			
			cursorPositionX += character.getXAdvance() * aspectRatio;
			
		}
		
		cursor.setPositionX(cursorPositionX);
		cursor.setPositionY(cursorPositionY);
		
		positionsArray = new float[positions.size() * 2];
		textureCoordsArray = new float[textureCoords.size() * 2];
		
		for(int j = 0; j < positions.size(); j++) {
			Vector2f position = positions.get(j);
			positionsArray[j * 2] = position.x;
			positionsArray[j * 2 + 1] = position.y;
		}
		
		for(int j = 0; j < textureCoords.size(); j++) {
			Vector2f textureCoord = textureCoords.get(j);
			textureCoordsArray[j * 2] = textureCoord.x;
			textureCoordsArray[j * 2 + 1] = textureCoord.y;
		}
		
		return new TextData(positionsArray, textureCoordsArray);
	}

}