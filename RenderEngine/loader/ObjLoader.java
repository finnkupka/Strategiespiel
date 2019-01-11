package loader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

//import normalMapping.TangentGenerator;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import openglObjects.Vao;

public class ObjLoader {
	
	public static Vao loadObjModel(String fileName, Loader loader/*, boolean withTangents*/) {
		 
		List<Vector3f> positions = new ArrayList<Vector3f>();
		List<Vector2f> textureCoords = new ArrayList<Vector2f>();
		List<Vector3f> normals = new ArrayList<Vector3f>();
		List<Integer> indices = new ArrayList<Integer>();
		
		float[] positionsArray = null;
		float[] textureCoordsArray = null;
		float[] normalsArray = null;
		int[] indicesArray = null;
		
		try {
			
			FileReader fileReader = new FileReader(new File("Resources" + File.separator + fileName + ".obj"));
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			String line = null;
			
			while((line = bufferedReader.readLine()) != null) {
				
				if(line.startsWith("v ")) {
					String[] attributes = line.split(" ");
					Vector3f position = new Vector3f(Float.parseFloat(attributes[1]),
													 Float.parseFloat(attributes[2]),
													 Float.parseFloat(attributes[3]));
					positions.add(position);
				} 
				else if(line.startsWith("vt ")) {
					String[] attributes = line.split(" ");
					Vector2f textureCoord = new Vector2f(Float.parseFloat(attributes[1]),
														 1 - Float.parseFloat(attributes[2]));
					textureCoords.add(textureCoord);
				} 
				else if(line.startsWith("vn ")) {
					String[] attributes = line.split(" ");
					Vector3f normal = new Vector3f(Float.parseFloat(attributes[1]),
												   Float.parseFloat(attributes[2]),
												   Float.parseFloat(attributes[3]));
					normals.add(normal);
				}
				else if(line.startsWith("f ")) {
					break;
				}
				
			}
			
			positionsArray = new float[positions.size() * 3];
			textureCoordsArray = new float[positions.size() * 2];
			normalsArray = new float[positions.size() * 3];
					
			do {
				
				if(line.startsWith("f ")) {
					
					String[] face = line.split(" ");
					String[] vertex1 = face[1].split("/");
					String[] vertex2 = face[2].split("/");
					String[] vertex3 = face[3].split("/");
					
					processVertex(vertex1, positions, textureCoords, normals, indices, positionsArray, textureCoordsArray, normalsArray);
					processVertex(vertex2, positions, textureCoords, normals, indices, positionsArray, textureCoordsArray, normalsArray);
					processVertex(vertex3, positions, textureCoords, normals, indices, positionsArray, textureCoordsArray, normalsArray);
					
				}
				
			} while((line = bufferedReader.readLine()) != null);
			
			indicesArray = new int[indices.size()];
			
			for(int i = 0; i < indices.size(); i++) {
				indicesArray[i] = indices.get(i);
			}
			
			bufferedReader.close();
			fileReader.close();
			
		} catch(Exception e) {
			e.printStackTrace();
			System.err.println("Could not load obj-file " + fileName);
			System.exit(-1);
		}
		
		/*
		if(withTangents) {
			
			float[] tangentsArray = TangentGenerator.calculateTangents(indicesArray, positionsArray, textureCoordsArray);
			return loader.loadToVao(positionsArray, textureCoordsArray, normalsArray, tangentsArray, indicesArray);
			
		}
		*/
		
		return loader.loadToVao(positionsArray, textureCoordsArray, normalsArray, indicesArray);
		
	}
	
	private static void processVertex(String[] vertex, List<Vector3f> positions, List<Vector2f> textureCoords, List<Vector3f> normals, List<Integer> indices,
			float[] positionsArray, float[] textureCoordsArray, float[] normalsArray) {
		
		int positionIndex = Integer.parseInt(vertex[0]) - 1;
		int textureCoordIndex = Integer.parseInt(vertex[1]) - 1;
		int normalIndex = Integer.parseInt(vertex[2]) - 1;
		
		Vector3f position = positions.get(positionIndex);
		positionsArray[positionIndex * 3 + 0] = position.x;
		positionsArray[positionIndex * 3 + 1] = position.y;
		positionsArray[positionIndex * 3 + 2] = position.z;
		
		Vector2f textureCoord = textureCoords.get(textureCoordIndex);
		textureCoordsArray[positionIndex * 2 + 0] = textureCoord.x;
		textureCoordsArray[positionIndex * 2 + 1] = textureCoord.y;
		
		Vector3f normal = normals.get(normalIndex);
		normalsArray[positionIndex * 3 + 0] = normal.x;
		normalsArray[positionIndex * 3 + 1] = normal.y;
		normalsArray[positionIndex * 3 + 2] = normal.z;
		
		indices.add(positionIndex);
		
	}

}
