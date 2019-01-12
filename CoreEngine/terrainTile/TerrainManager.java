package terrainTile;

import java.util.List;

import terrain.Terrain;

public class TerrainManager {
	
	private TerrainTile[][] terrainTiles;
	
	public TerrainManager() {
		this.terrainTiles = null;
	}
	
	public TerrainManager(TerrainTile[][] terrainTiles) {
		this.terrainTiles = terrainTiles;
	}
	
	public TerrainManager(List<TerrainTile> terrainTiles) {
		int maxTileX = 0;
		int maxTileY = 0;
		
		for(TerrainTile terrainTile : terrainTiles) {
			if(terrainTile.getTileX() > maxTileX) {
				maxTileX = terrainTile.getTileX();
			}
			if(terrainTile.getTileY() > maxTileY) {
				maxTileY = terrainTile.getTileY();
			}
		}
		
		this.terrainTiles = new TerrainTile[maxTileX+1][maxTileY+1];
		
		for(TerrainTile terrainTile : terrainTiles) {
			this.terrainTiles[terrainTile.getTileX()][terrainTile.getTileY()] = terrainTile;
		}
	}
	
	public float getHeight(float x, float z) {
		int terrainGridX = (int) Math.floor(x / ((Terrain.VERTEX_COUNT - 1) * Terrain.DISTANCE));
		int terrainGridZ = (int) Math.floor(z / ((Terrain.VERTEX_COUNT - 1) * Terrain.DISTANCE));
		
		if(terrainGridX < 0 || terrainGridZ < 0 || terrainGridX >= this.terrainTiles.length || terrainGridZ >=	 this.terrainTiles.length) {
			return 0;
		}
			
		return terrainTiles[terrainGridX][terrainGridZ].getHeight(x, z);
	}
	
	public TerrainTile[][] getTerrainTiles() {
		return this.terrainTiles;
	}
	
	public TerrainTile getTerrainTile(int indexX, int indexZ) {
		return this.terrainTiles[indexX][indexZ];
	}

}
