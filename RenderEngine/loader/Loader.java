package loader;

import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import openglObjects.Vao;
import openglObjects.Vbo;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL14;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import de.matthiasmann.twl.utils.PNGDecoder;
import de.matthiasmann.twl.utils.PNGDecoder.Format;
import texture.TextureMap;

public class Loader {
	
	private List<Integer> vaos;
	private List<Integer> vbos;
	private List<Integer> textures;
	
	public Loader() {
		this.vaos = new ArrayList<Integer>();
		this.vbos = new ArrayList<Integer>();
		this.textures = new ArrayList<Integer>();
	}
	
	public Vao loadToVao(float[] positions, float[] textureCoords, float[] normals, int[] indices) {
		int vaoID = this.createVao();
		int vertexCount = indices.length;
		
		Vao vao = new Vao(vaoID, vertexCount);
		
		this.bindVao(vao);
		
		vao.addVbo(this.storeIndicesInVbo(indices));
		vao.addVbo(this.storeDataInVbo(positions, 3, 0));
		vao.addVbo(this.storeDataInVbo(textureCoords, 2, 1));
		vao.addVbo(this.storeDataInVbo(normals, 3, 2));
		
		this.unbindVao();
		
		return vao;
	}
	
	public Vao loadToVao(float[] positions, float[] textureCoords, float[] normals, float[] tangents, int[] indices) {
		int vaoID = this.createVao();
		int vertexCount = indices.length;
		
		Vao vao = new Vao(vaoID, vertexCount);
		
		this.bindVao(vao);
		
		vao.addVbo(this.storeIndicesInVbo(indices));
		vao.addVbo(this.storeDataInVbo(positions, 3, 0));
		vao.addVbo(this.storeDataInVbo(textureCoords, 2, 1));
		vao.addVbo(this.storeDataInVbo(normals, 3, 2));
		vao.addVbo(this.storeDataInVbo(tangents, 3, 3));
		
		this.unbindVao();
		
		return vao;
	}
	
	public Vao loadToVao(float[] positions, float[] textureCoords, int dimensions) {
		int vaoID = this.createVao();
		int vertexCount = positions.length / dimensions;
		
		Vao vao = new Vao(vaoID, vertexCount);
		
		this.bindVao(vao);
		
		vao.addVbo(this.storeDataInVbo(positions, 2, 0));
		vao.addVbo(this.storeDataInVbo(textureCoords, 2, 1));
		
		this.unbindVao();
		
		return vao;
	}
	
	public Vao loadToVao(float[] positions, int dimensions) {
		int vaoID = this.createVao();
		int vertexCount = positions.length / dimensions;
		
		Vao vao = new Vao(vaoID, vertexCount);
		
		this.bindVao(vao);
		
		vao.addVbo(this.storeDataInVbo(positions, dimensions, 0));
		
		this.unbindVao();
		
		return vao;
	}
	
	private int createVao() {
		int vaoID = GL30.glGenVertexArrays();
		this.vaos.add(vaoID);
		return vaoID;
	}
	
	private void bindVao(Vao vao) {
		GL30.glBindVertexArray(vao.getID());
	}
	
	private void unbindVao() {
		GL30.glBindVertexArray(0);
	}
	
	private Vbo storeDataInVbo(float[] data, int attributeSize, int attributeIndex) {
		int vboID = GL15.glGenBuffers();
		this.vbos.add(vboID);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);
		FloatBuffer buffer = this.storeDataInFloatBuffer(data);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
		GL20.glVertexAttribPointer(attributeIndex, attributeSize, GL11.GL_FLOAT, false, 0, 0);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
		return new Vbo(vboID);
	}
	
	public void updateDataInVbo(Vbo vbo, float[] data, int attributeSize, int attributeIndex) {
		this.deleteVbo(vbo.getID());
		vbo = this.storeDataInVbo(data, attributeSize, attributeIndex);
	}
	
	private Vbo storeIndicesInVbo(int[] indices) {
		int vboID = GL15.glGenBuffers();
		this.vbos.add(vboID);
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vboID);
		IntBuffer buffer = this.storeDataInIntBuffer(indices);
		GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
		return new Vbo(vboID);
	}
	
	private FloatBuffer storeDataInFloatBuffer(float[] data) {
		FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		return buffer;
	}
	
	private IntBuffer storeDataInIntBuffer(int[] data) {
		IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		return buffer;
	}
	
	public TextureMap loadTexture(String fileName) {
		Texture texture = null;
		
		try {
			
			texture = TextureLoader.getTexture("PNG", new FileInputStream(new File("Resources" + File.separator + fileName + ".png")));
			GL30.glGenerateMipmap(GL11.GL_TEXTURE_2D);
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR_MIPMAP_LINEAR);
			GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL14.GL_TEXTURE_LOD_BIAS, -0.05f);
			
		} catch (Exception e) {
			System.err.println("Missing Texture: \"" + fileName + "\"");
			e.printStackTrace();
			System.exit(-1);
		}
		
		
		
		return new TextureMap(texture.getTextureID());
		
	}
	
	public TextureMap loadCubeMapTexture(String[] fileNames) {
		int textureID = GL11.glGenTextures();
		
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL13.GL_TEXTURE_CUBE_MAP, textureID);
		
		for(int i = 0; i < fileNames.length; i++) {
			int width = 0;
			int height = 0;
			ByteBuffer buffer = null;
			
			try {
				
				FileInputStream fileInputStream = new FileInputStream("Resources" + File.separator + fileNames[i] + ".png");
				PNGDecoder pngDecoder = new PNGDecoder(fileInputStream);
				width = pngDecoder.getWidth();
				height = pngDecoder.getHeight();
				buffer = ByteBuffer.allocateDirect(width * height * 4);
				pngDecoder.decode(buffer, width * 4, Format.RGBA);
				buffer.flip();
				fileInputStream.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			GL11.glTexImage2D(GL13.GL_TEXTURE_CUBE_MAP_POSITIVE_X + i, 0, GL11.GL_RGBA, width, height, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, buffer);
			
		}
		
		GL11.glTexParameteri(GL13.GL_TEXTURE_CUBE_MAP, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
		GL11.glTexParameteri(GL13.GL_TEXTURE_CUBE_MAP, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
		
		this.textures.add(textureID);
		
		return new TextureMap(textureID);
	}
	
	public void cleanUp() {
		for(int vbo : this.vbos) {
			GL15.glDeleteBuffers(vbo);
		}
		for(int vao : this.vaos) {
			GL30.glDeleteVertexArrays(vao);
		}
		for(int texture : this.textures) {
			GL11.glDeleteTextures(texture);
		}
	}
	
	public void deleteVbo(int vboID) {
		for(int i = 0; i < this.vbos.size(); i++) {
			if(this.vbos.get(i) == vboID) {
				this.vbos.remove(i);
				GL15.glDeleteBuffers(vboID);
				return;
			}
		}
	}
}
