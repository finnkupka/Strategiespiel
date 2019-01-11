package terrain;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import org.lwjgl.util.vector.Vector3f;

import loader.Loader;
import openglObjects.Vao;
import texture.TextureMap;

public class Terrain {
	
	public static int SIZE;
	public static int VERTEX_COUNT;
	public static float DISTANCE;
	
	private Vao vao;
	
	private float[][] heights;
	
	public Terrain(float[][] precalculatedHeights, Loader loader, float tileNumber, float textureOffsetX, float textureOffsetY) {
		
		this.heights = precalculatedHeights;
		
		float[] positions = new float[VERTEX_COUNT * VERTEX_COUNT * 3];
		float[] textureCoords = new float[VERTEX_COUNT * VERTEX_COUNT * 2];
		float[] normals = new float[VERTEX_COUNT * VERTEX_COUNT * 3];
		int[] indices = new int[(VERTEX_COUNT - 1) * (VERTEX_COUNT - 1) * 6];
		
		for(int i = 0; i < VERTEX_COUNT; i++) {
			for(int j = 0; j < VERTEX_COUNT; j++) {
				float height = precalculatedHeights[j][i];
				positions[3 * (i * VERTEX_COUNT + j) + 0] = j * DISTANCE;
				positions[3 * (i * VERTEX_COUNT + j) + 1] = height;
				positions[3 * (i * VERTEX_COUNT + j) + 2] = i * DISTANCE;
			}
		}
		
		for(int i = 0; i < VERTEX_COUNT; i++) {
			for(int j = 0; j < VERTEX_COUNT; j++) {
				textureCoords[2 * (i * VERTEX_COUNT + j) + 0] = ((1f / VERTEX_COUNT) * j) / tileNumber + (textureOffsetX/tileNumber);
				textureCoords[2 * (i * VERTEX_COUNT + j) + 1] = ((1f / VERTEX_COUNT) * i) / tileNumber + (textureOffsetY/tileNumber);
			}
		}
		
		for(int i = 0; i < VERTEX_COUNT; i++) {
			for(int j = 0; j < VERTEX_COUNT; j++) {
				Vector3f normal = calculateNormal(j, i);
				normals[3 * (i * VERTEX_COUNT + j) + 0] = normal.x;
				normals[3 * (i * VERTEX_COUNT + j) + 1] = normal.y;
				normals[3 * (i * VERTEX_COUNT + j) + 2] = normal.z;
			}
		}
		
		int currentIndice = 0;
		for(int i = 0; i < VERTEX_COUNT-1; i++) {
			for(int j = 0; j < VERTEX_COUNT-1; j++) {
				int topLeft = i * VERTEX_COUNT + j;
				int topRight = topLeft + 1;
				int bottomLeft = (i+1) * VERTEX_COUNT + j;
				int bottomRigth = bottomLeft +1;
				indices[currentIndice++] = topLeft;
				indices[currentIndice++] = bottomLeft;
				indices[currentIndice++] = topRight;
				indices[currentIndice++] = topRight;
				indices[currentIndice++] = bottomLeft;
				indices[currentIndice++] = bottomRigth;
			}
		}
		
		this.vao = loader.loadToVao(positions, textureCoords, normals, indices);
	}
	
	private Vector3f calculateNormal(int x, int z) {
		float heightUp = this.getHeight(x, z+1);
		float heightDown = this.getHeight(x, z-1);
		float heightRight = this.getHeight(x+1, z);
		float heightLeft = this.getHeight(x-1, z);
		
		Vector3f normal = new Vector3f(heightLeft-heightRight, DISTANCE * 2, heightDown-heightUp);
		normal.normalise();
		return normal;
	}
	
	private float getHeight(int x, int z) {
		int indexX = x;
		int indexZ = z;
		
		int correctionIndexX = x;
		int correctionIndexZ = z;
		
		boolean error = false;
		
		if(indexX < 0) {
			indexX++;
			correctionIndexX += 2;
			error = true;
		}
		if(indexX > VERTEX_COUNT - 1) {
			indexX--;
			correctionIndexX -= 2;
			error = true;
		}
		if(indexZ < 0) {
			indexZ++;
			correctionIndexZ += 2;
			error = true;
		}
		if(indexZ > VERTEX_COUNT -1 ) {
			indexZ--;
			correctionIndexZ -= 2;
			error = true;
		}
		
		if(error) {
			float neighbourHeight = this.heights[indexX][indexZ];
			float previousHeight = this.heights[correctionIndexX][correctionIndexZ];
			float predictedHeight = 2 * neighbourHeight - previousHeight;
			
			return predictedHeight;
		}
		
		return this.heights[indexX][indexZ];
	}
	
	public Vao getVao() {
		return this.vao;
	}
	
	public float[][] getHeights() {
		return this.heights;
	}

}
