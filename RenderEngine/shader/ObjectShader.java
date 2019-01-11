package shader;

import org.lwjgl.util.vector.Matrix4f;

public class ObjectShader extends Shader {
	
	private int location_transformationMatrix;
	private int location_viewMatrix;
	private int location_projectionMatrix;
	
	public ObjectShader() {
		super("shader", "objectVertexShader", "objectFragmentShader");
	}

	@Override
	protected void getAllUniformLocation() {
		this.location_transformationMatrix = super.getUniformLocation("transformationMatrix");
		this.location_viewMatrix = super.getUniformLocation("viewMatrix");
		this.location_projectionMatrix = super.getUniformLocation("projectionMatrix");
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
