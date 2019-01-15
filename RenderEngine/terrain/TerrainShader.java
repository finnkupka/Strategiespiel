package terrain;

//import lighting.Light;

import light.Light;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector4f;

import shader.Shader;

public class TerrainShader extends Shader {
	
	private final int NUMBER_OF_LIGHTS = 5;
	
	private int location_transformationMatrix;
	private int location_viewMatrix;
	private int location_projectionMatrix;
	
	private int[] location_lightPosition;
	private int[] location_lightColor;
	private int[] location_attenuation;
	
	private int location_plane;
	
	public TerrainShader() {
		super("terrain", "terrainVertexShader", "terrainFragmentShader");
	}
	
	@Override
	protected void getAllUniformLocation() {
		this.location_transformationMatrix = super.getUniformLocation("transformationMatrix");
		this.location_viewMatrix = super.getUniformLocation("viewMatrix");
		this.location_projectionMatrix = super.getUniformLocation("projectionMatrix");
		
		this.location_lightPosition = new int[this.NUMBER_OF_LIGHTS];
		this.location_lightColor = new int[this.NUMBER_OF_LIGHTS];
		this.location_attenuation = new int[this.NUMBER_OF_LIGHTS];
		
		for(int i = 0; i < this.NUMBER_OF_LIGHTS; i++) {
			this.location_lightPosition[i] = super.getUniformLocation("lightPosition[" + i + "]");
			this.location_lightColor[i] = super.getUniformLocation("lightColor[" + i + "]");
			this.location_attenuation[i] = super.getUniformLocation("attenuation[" + i + "]");
		}
		
		this.location_plane = super.getUniformLocation("plane");
		
	}
	
	public void loadTransformationMatrix(Matrix4f transformationMatrix) {
		super.loadMatrix4f(this.location_transformationMatrix, transformationMatrix);
	}
	
	public void loadViewMatrix(Matrix4f viewMatrix) {
		super.loadMatrix4f(location_viewMatrix, viewMatrix);
	}
	
	public void loadProjectionMatrix(Matrix4f projectionMatrix) {
		super.loadMatrix4f(this.location_projectionMatrix, projectionMatrix);
	}
	
	
	public void loadLight(Light light) {
		super.loadVector3f(this.location_lightPosition[0], light.getPosition());
		super.loadVector3f(this.location_lightColor[0], light.getColor());
		super.loadVector3f(this.location_attenuation[0], light.getAttenuation());
	}
	
	public void loadLights(Light[] lights) {
		for(int i = 0; i < this.NUMBER_OF_LIGHTS; i++) {
			Light light = lights[i];
			super.loadVector3f(this.location_lightPosition[i], light.getPosition());
			super.loadVector3f(this.location_lightColor[i], light.getColor());
			super.loadVector3f(this.location_attenuation[i], light.getAttenuation());
		}
	}
	
	public void loadPlane(Vector4f plane) {
		super.loadVector4f(this.location_plane, plane);
	}

}
