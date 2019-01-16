package text;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Vector4f;

import openglObjects.Vao;
import texture.TextureMap;

public class TextRenderer {
	
	private TextShader textShader;
	
	public TextRenderer() {
		this.textShader = new TextShader();
	}
	
	public void render(Text text) {
		
		Vao vao = text.getVao();
		TextureMap textureMap = text.getFont().getTextureAtlas();
		
		GL30.glBindVertexArray(vao.getID());
		
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureMap.getID());
		
		GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, vao.getVertexCount());
		
		GL20.glDisableVertexAttribArray(0);
		GL20.glDisableVertexAttribArray(1);
		
		GL30.glBindVertexArray(0);
		
	}
	
	public TextShader getTextShader() {
		return this.textShader;
	}
	
	public void cleanUp() {
		this.textShader.cleanUp();
	}

}
