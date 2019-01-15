package terrainTile;

//import lighting.Light;

import light.Light;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;



import components.LightComponent;

//import components.LightComponent;
//import setup.Configurations;
//import terrain.NormalMappedTerrainRenderer;
import terrain.Terrain;
import terrain.TerrainRenderer;
//import terrainCollision.TerrainCollider;
import texture.TextureMap;

public class TerrainTile {
	
	private static TerrainRenderer terrainRenderer;
	//private static NormalMappedTerrainRenderer normalMappedTerrainRenderer;
	
	private int tileX;
	private int tileY;
	
	private Terrain terrain;
	
	private TextureMap textureMap1;
	private TextureMap textureMap2;
	private TextureMap textureMap3;
	private TextureMap textureMap4;
	
	private TextureMap normalMap1;
	private TextureMap normalMap2;
	private TextureMap normalMap3;
	private TextureMap normalMap4;
	
	private TextureMap blendMap;
	
	//private static TerrainCollider terrainCollider = new TerrainCollider();
	
	public TerrainTile(int tileX, int tileY, Terrain terrain, 
					   TextureMap textureMap1, TextureMap textureMap2, TextureMap textureMap3, TextureMap textureMap4, 
					   TextureMap normalMap1, TextureMap normalMap2, TextureMap normalMap3, TextureMap normalMap4, 
					   TextureMap blendMap) {
		this.tileX = tileX;
		this.tileY = tileY;
		
		this.terrain = terrain;
		
		this.textureMap1 = textureMap1;
		this.textureMap2 = textureMap2;
		this.textureMap3 = textureMap3;
		this.textureMap4 = textureMap4;
		
		this.normalMap1 = normalMap1;
		this.normalMap2 = normalMap2;
		this.normalMap3 = normalMap3;
		this.normalMap4 = normalMap4;
		
		this.blendMap = blendMap;
	}
	
	public void update() {
		
	}
	
	public void render() {
		/*
		if(Configurations.WITH_NORMAL_MAPPING) {
			normalMappedTerrainRenderer.getNormalMappedTerrainShader().startProgram();
			normalMappedTerrainRenderer.getNormalMappedTerrainShader().loadTransformationMatrix(this.generateTransformationMatrix());
			float x = (this.tileX + 0.5f) * Terrain.SIZE;
			float z = (this.tileY + 0.5f) * Terrain.SIZE;
			Light[] lights = LightComponent.getLights(new Vector3f(x, this.getHeight(x, z), z));
			normalMappedTerrainRenderer.getNormalMappedTerrainShader().loadLights(lights);
			normalMappedTerrainRenderer.render(this.terrain, 	this.textureMap1, this.textureMap2, this.textureMap3, this.textureMap4, 
																this.normalMap1, this.normalMap2, this.normalMap3, this.normalMap4, 
																this.blendMap);
			normalMappedTerrainRenderer.getNormalMappedTerrainShader().stopProgram();
		} else {*/
			terrainRenderer.getTerrainShader().startProgram();
			terrainRenderer.getTerrainShader().loadTransformationMatrix(this.generateTransformationMatrix());
			float x = (this.tileX + 0.5f) * Terrain.SIZE;
			float z = (this.tileY + 0.5f) * Terrain.SIZE;
			Light[] lights = LightComponent.getLights(new Vector3f(x, this.getHeight(x, z), z));
			terrainRenderer.getTerrainShader().loadLights(lights);
			terrainRenderer.render(	this.terrain, this.textureMap1, this.textureMap2, this.textureMap3, this.textureMap4, 
									this.blendMap);
			terrainRenderer.getTerrainShader().stopProgram();
		//}
		
	}
	
	private Matrix4f generateTransformationMatrix() {
		Matrix4f transformationMatrix = new Matrix4f();
		transformationMatrix.setIdentity();
		Matrix4f.translate(new Vector3f(this.tileX * Terrain.SIZE - this.tileX * Terrain.DISTANCE, 0, this.tileY * Terrain.SIZE - this.tileY * Terrain.DISTANCE), transformationMatrix, transformationMatrix);
		return transformationMatrix;
	}
	
	public float getHeight(float x, float z) {
		return 0;//terrainCollider.getHeightAt(x, z, this);
	}
	
	public int getTileX() {
		return this.tileX;
	}
	
	public int getTileY() {
		return this.tileY;
	}
	
	public Terrain getTerrain() {
		return this.terrain;
	}
	
	public TextureMap getTexture1() {
		return this.textureMap1;
	}
	
	public TextureMap getTexture2() {
		return this.textureMap2;
	}
	
	public TextureMap getTexture3() {
		return this.textureMap3;
	}
	
	public TextureMap getTexture4() {
		return this.textureMap4;
	}
	
	public TextureMap getNormalMap1() {
		return this.normalMap1;
	}
	
	public TextureMap getNormalMap2() {
		return this.normalMap2;
	}
	
	public TextureMap getNormalMap3() {
		return this.normalMap3;
	}
	
	public TextureMap getNormalMap4() {
		return this.normalMap4;
	}
	
	public TextureMap getBlendMap() {
		return this.blendMap;
	}
	
	public static void setTerrainRenderer(TerrainRenderer t/*, NormalMappedTerrainRenderer n*/) {
		terrainRenderer = t;
		//normalMappedTerrainRenderer = n;
	}

}
