package terrain;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import openglObjects.Vao;
import texture.TextureMap;

public class TerrainRenderer {
	
	private TerrainShader terrainShader;
	
	public TerrainRenderer() {
		this.terrainShader = new TerrainShader();
	}
	
	public void render(Terrain terrain, TextureMap texture1, TextureMap texture2, TextureMap texture3, TextureMap texture4, TextureMap blendMap) {
		Vao vao = terrain.getVao();
		
		GL30.glBindVertexArray(vao.getID());
		
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		GL20.glEnableVertexAttribArray(2);
		
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture1.getID());
		GL13.glActiveTexture(GL13.GL_TEXTURE1);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture2.getID());
		GL13.glActiveTexture(GL13.GL_TEXTURE2);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture3.getID());
		GL13.glActiveTexture(GL13.GL_TEXTURE3);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture4.getID());
		GL13.glActiveTexture(GL13.GL_TEXTURE4);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, blendMap.getID());
		
		GL11.glDrawElements(GL11.GL_TRIANGLES, vao.getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
		
		GL20.glDisableVertexAttribArray(0);
		GL20.glDisableVertexAttribArray(1);
		GL20.glDisableVertexAttribArray(2);
		
		GL30.glBindVertexArray(0);
	}
	
	public TerrainShader getTerrainShader() {
		return this.terrainShader;
	}
	
	public void cleanUp() {
		this.terrainShader.cleanUp();
	}

}
