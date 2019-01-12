package terrainTile;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import loader.Loader;
import terrain.Terrain;
import texture.TextureMap;

public class TerrainTiler {
	
	public static TerrainTile[][] generateTerrainTiles(int tileNumber, String heightMapFile, TerrainTextureLibrary terrainTextureLibrary, TextureMap blendMap, Loader loader) {
		
		TerrainTile[][] terrainTiles = new TerrainTile[tileNumber][tileNumber];
		
		float heights[][] = null;
		
		BufferedImage heightMap = null;
		
		try {
			
			heightMap = ImageIO.read(new File("Resources" + File.separator + heightMapFile + ".png"));
			heights = new float[heightMap.getWidth()][heightMap.getHeight()];
			
		} catch (IOException e) {
			System.err.println("Ressources" + File.separator + heightMapFile + ".png");
			e.printStackTrace();
		}
		
		for(int i = 0; i < heightMap.getWidth(); i++) {
			for(int j = 0; j < heightMap.getHeight(); j++) {
				float height = 0f;
				int radius = 3;
				float h = 0.5f;
				for(int m = -radius; m <= radius; m++) {
					for(int n = -radius; n <= radius; n++) {
						height += (new Color(heightMap.getRGB(Math.max(Math.min(heightMap.getWidth()-1, j+m), 0), Math.max(Math.min(heightMap.getHeight()-1, i+n), 0)))).getRed() * h;
					}
				}
				height /= Math.pow(radius * 2 + 1, 2);
				heights[j][i] = height;
			}
		}
		
		Terrain.SIZE = 512 / tileNumber;
		Terrain.VERTEX_COUNT = heightMap.getWidth() / tileNumber;
		Terrain.DISTANCE = (float)Terrain.SIZE / (float)Terrain.VERTEX_COUNT;
		
		for(int i = 0; i < tileNumber; i++) {
			for(int j = 0; j < tileNumber; j++) {
				
				float[][] tileHeights = new float[Terrain.VERTEX_COUNT][Terrain.VERTEX_COUNT];
				
				for(int m = 0; m < Terrain.VERTEX_COUNT; m++) {
					for(int n = 0; n < Terrain.VERTEX_COUNT; n++) {
						tileHeights[m][n] = heights[j*(Terrain.VERTEX_COUNT-1)+n][i*(Terrain.VERTEX_COUNT-1)+m];
					}
				}
				
				terrainTiles[i][j] = new TerrainTile(i,j,new Terrain(tileHeights, loader, tileNumber, i, j), terrainTextureLibrary.getTexture("terrainGrassTexture"),
																											 terrainTextureLibrary.getTexture("terrainDirtTexture"),
																											 terrainTextureLibrary.getTexture("terrainSandTexture"),
																											 terrainTextureLibrary.getTexture("terrainStoneTexture"),
																											 terrainTextureLibrary.getTexture("terrainGrassNormalMap"),
																											 terrainTextureLibrary.getTexture("terrainDirtNormalMap"),
																											 terrainTextureLibrary.getTexture("terrainSandNormalMap"),
																											 terrainTextureLibrary.getTexture("terrainStoneNormalMap"),blendMap);
				
			}
		}
		
		return terrainTiles;
	}

}
