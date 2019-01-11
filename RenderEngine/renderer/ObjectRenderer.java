package renderer;

import openglObjects.Vao;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import shader.ObjectShader;
import texture.TextureMap;

public class ObjectRenderer {
	
	private ObjectShader objectShader;
	
	public ObjectRenderer() {
		this.objectShader = new ObjectShader();
	}
	
	public void render(Vao vao, TextureMap textureMap) {
		
		GL30.glBindVertexArray(vao.getID());
		
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		GL20.glEnableVertexAttribArray(2);
		
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureMap.getID());
		
		GL11.glDrawElements(GL11.GL_TRIANGLES, vao.getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
		
		GL20.glDisableVertexAttribArray(0);
		GL20.glDisableVertexAttribArray(1);
		GL20.glDisableVertexAttribArray(2);
		
		GL30.glBindVertexArray(0);
		
	}
	
	public ObjectShader getObjectShader() {
		return this.objectShader;
	}
	
	public void cleanUp() {
		this.objectShader.cleanUp();
	}

}
