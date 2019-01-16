package gui;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Vector4f;

import openglObjects.Vao;
import texture.TextureMap;

public class GuiRenderer {
	
	private GuiShader guiShader;
	
	public GuiRenderer() {
		this.guiShader = new GuiShader();
	}
	
	public void render(Gui gui) {
		
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		
		Vao vao = Gui.getRectangle();
		TextureMap textureMap = gui.getTextureMap();
		
		GL30.glBindVertexArray(vao.getID());
		
		GL20.glEnableVertexAttribArray(0);
		
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureMap.getID());
		
		GL11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 0, vao.getVertexCount());
		
		GL20.glDisableVertexAttribArray(0);
		
		GL30.glBindVertexArray(0);
		
		GL11.glDisable(GL11.GL_BLEND);
		
	}
	
	public GuiShader getGuiShader() {
		return this.guiShader;
	}
	
	public void cleanUp() {
		this.guiShader.cleanUp();
	}

}
