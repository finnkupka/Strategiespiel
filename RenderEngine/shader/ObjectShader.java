package shader;

import light.Light;

import org.lwjgl.util.vector.Matrix4f;

public class ObjectShader extends Shader {
	
	private static final int MAX_LIGHTS = 5;
	
	private int location_transformationMatrix;
	private int location_viewMatrix;
	private int location_projectionMatrix;
	
	private int[] location_lightPosition;
	private int[] location_lightColor;
	private int[] location_lightAttenuation;
	
	public ObjectShader() {
		super("shader", "objectVertexShader", "objectFragmentShader");
	}

	@Override
	protected void getAllUniformLocation() {
		this.location_transformationMatrix = super.getUniformLocation("transformationMatrix");
		this.location_viewMatrix = super.getUniformLocation("viewMatrix");
		this.location_projectionMatrix = super.getUniformLocation("projectionMatrix");
		
		this.location_lightPosition = new int[MAX_LIGHTS];
		this.location_lightColor = new int[MAX_LIGHTS];
		this.location_lightAttenuation = new int[MAX_LIGHTS];
		
		for(int i = 0; i < MAX_LIGHTS; i++) {
			this.location_lightPosition[i] = super.getUniformLocation("lightPosition[" + i + "]");
			this.location_lightColor[i] = super.getUniformLocation("lightColor[" + i + "]");
			this.location_lightAttenuation[i] = super.getUniformLocation("lightAttenuation[" + i + "]");
		}
	}
	
	public void loadLight(Light light) {
		super.loadVector3f(this.location_lightPosition[0], light.getPosition());
		super.loadVector3f(this.location_lightColor[0], light.getColor());
		super.loadVector3f(this.location_lightAttenuation[0], light.getAttenuation());
	}
	
	public void loadLights(Light[] lights) {
		for(int i = 0; i < MAX_LIGHTS; i++) {
			Light light = lights[i];
			super.loadVector3f(this.location_lightPosition[i], light.getPosition());
			super.loadVector3f(this.location_lightColor[i], light.getColor());
			super.loadVector3f(this.location_lightAttenuation[i], light.getAttenuation());
		}
	}
	
	public void loadTransformationMatrix(Matrix4f transformationMatrix) {
		super.loadMatrix4f(this.location_transformationMatrix, transformationMatrix);
	}
	
	public void loadViewMatrix(Matrix4f viewMatrix) {
		super.loadMatrix4f(this.location_viewMatrix, viewMatrix);
	}
	
	public void loadProjectionMatrix(Matrix4f projectionMatrix) {
		super.loadMatrix4f(this.location_projectionMatrix, projectionMatrix);
	}

}
