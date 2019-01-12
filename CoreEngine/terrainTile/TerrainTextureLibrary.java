package terrainTile;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import loader.Loader;
import texture.TextureMap;

public class TerrainTextureLibrary {
	
	private Map<String, TextureMap> terrainTextures;
	
	public TerrainTextureLibrary(Loader loader) {
		
		this.terrainTextures = new HashMap<String, TextureMap>();
		this.loadAllTextures(loader);
		
	}
	
	private void loadAllTextures(Loader loader) {
		
		File resources = new File("Resources" + File.separator + "terrainTextures");
		
		String[] files = resources.list();
		
		for(String file : files) {
			
			String fileName = file.substring(0, file.indexOf("."));
			
			this.terrainTextures.put(fileName, loader.loadTexture("terrainTextures" + File.separator + fileName));
			
		}
		
	}
	
	public TextureMap getTexture(String name) {
		return this.terrainTextures.get(name);
	}

}
