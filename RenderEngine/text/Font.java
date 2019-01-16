package text;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import loader.Loader;
import texture.TextureMap;

public class Font {
	
	private Map<Integer, Character> characters;
	
	private TextureMap textureAtlas;
	
	public Font(String fontFile, String textureAtlasFile, Loader loader) {
		
		this.textureAtlas = loader.loadTexture(textureAtlasFile);
		
		this.characters = new HashMap<Integer, Character>();
		
		try {
			
			FileReader fileReader = new FileReader(new File("Resources" + File.separator + fontFile + ".fnt"));
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			String line = null;
			
			while((line = bufferedReader.readLine()) != null) {
				
				if(line.startsWith("char ")) {
					
					int id;
					int xPosition;
					int yPosition;
					int width;
					int height;
					int xOffset;
					int yOffset;
					int xAdvance;
					
					String[] lines = line.split(" ");
					List<String> lineList = new ArrayList<String>();
					
					for(String string : lines) {
						lineList.add(string);
					}
					
					for(int i = 0; i < lineList.size(); i++) {
						if(lineList.get(i).equals("")) {
							lineList.remove(i);
							i--;
						}
					}
					
					id 			= Integer.parseInt(lineList.get(1).substring(3));
					xPosition 	= Integer.parseInt(lineList.get(2).substring(2));
					yPosition 	= Integer.parseInt(lineList.get(3).substring(2));
					width 		= Integer.parseInt(lineList.get(4).substring(6));
					height 		= Integer.parseInt(lineList.get(5).substring(7));
					xOffset 	= Integer.parseInt(lineList.get(6).substring(8));
					yOffset 	= Integer.parseInt(lineList.get(7).substring(8));
					xAdvance 	= Integer.parseInt(lineList.get(8).substring(9));
					
					Character c = new Character(id, xPosition, yPosition, width, height, xOffset, yOffset, xAdvance);
					this.characters.put(id, c);
					
				}
				
			}
			
			bufferedReader.close();
			fileReader.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public Character getCharacter(int id) {
		return this.characters.get(id);
	}
	
	public TextureMap getTextureAtlas() {
		return this.textureAtlas;
	}

}
