package shader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.util.vector.Matrix3f;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

public abstract class Shader {

	private int vertexShaderID;
	private int fragmentShaderID;
	private int programID;
	
	public Shader(String directory, String vsFile, String fsFile) {
		this.vertexShaderID = this.loadShader(directory + File.separator + vsFile, GL20.GL_VERTEX_SHADER);
		this.fragmentShaderID = this.loadShader(directory + File.separator + fsFile, GL20.GL_FRAGMENT_SHADER);
		
		this.programID = GL20.glCreateProgram();
		
		GL20.glAttachShader(programID, vertexShaderID);
		GL20.glAttachShader(programID, fragmentShaderID);
		
		GL20.glLinkProgram(programID);
		GL20.glValidateProgram(programID);
		
		this.getAllUniformLocation();
	}
	
	private int loadShader(String fileName, int type) {
		
		StringBuilder shaderSource = new StringBuilder();
		
		try {
			
			FileReader fileReader = new FileReader(new File("RenderEngine" + File.separator + fileName + ".glsl"));
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			String line = null;
			
			while((line = bufferedReader.readLine()) != null) {
				shaderSource.append(line).append("\n");
			}
			
			bufferedReader.close();
			fileReader.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		int shaderID = GL20.glCreateShader(type);
		
		GL20.glShaderSource(shaderID, shaderSource);
		GL20.glCompileShader(shaderID);
		
		if(GL20.glGetShaderi(shaderID, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE) {
			System.err.println(GL20.glGetShaderInfoLog(shaderID, 500));
			System.err.println("Could not load shader " + fileName);
			System.exit(-1);
		}
		
		return shaderID;
		
	}
	
	protected void loadVector2f(int location, Vector2f vector) {
		GL20.glUniform2f(location, vector.x, vector.y);
	}
	
	protected void loadVector3f(int location, Vector3f vector) {
		GL20.glUniform3f(location, vector.x, vector.y, vector.z);
	}
	
	protected void loadVector4f(int location, Vector4f vector) {
		GL20.glUniform4f(location, vector.x, vector.y, vector.z, vector.w);
	}
	
	protected void loadFloat(int location, float value) {
		GL20.glUniform1f(location, value);
	}
	
	protected void loadInteger(int location, int value) {
		GL20.glUniform1i(location, value);
	}
	
	protected void loadMatrix4f(int location, Matrix4f matrix) {
		FloatBuffer buffer = BufferUtils.createFloatBuffer(16);
		matrix.store(buffer);
		buffer.flip();
		GL20.glUniformMatrix4(location, false, buffer);
	}
	
	protected void loadMatrix3f(int location, Matrix3f matrix) {
		FloatBuffer buffer = BufferUtils.createFloatBuffer(9);
		matrix.store(buffer);
		buffer.flip();
		GL20.glUniformMatrix3(location, false, buffer);
	}
	
	protected void loadBoolean(int location, boolean value) {
		if(value) {
			GL20.glUniform1i(location, 1);
		}
		GL20.glUniform1i(location, 0);
	}
	
	protected int getUniformLocation(String uniformName) {
		return GL20.glGetUniformLocation(this.programID, uniformName);
	}
	
	protected abstract void getAllUniformLocation();
	
	public void startProgram() {
		GL20.glUseProgram(programID);
	}
	
	public void stopProgram() {
		GL20.glUseProgram(0);
	}
	
	public void cleanUp() {
		GL20.glDetachShader(programID, vertexShaderID);
		GL20.glDetachShader(programID, fragmentShaderID);
		GL20.glDeleteShader(vertexShaderID);
		GL20.glDeleteShader(fragmentShaderID);
		GL20.glDeleteProgram(programID);
	}
	
}
