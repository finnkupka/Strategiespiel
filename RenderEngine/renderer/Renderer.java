package renderer;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Matrix4f;

import camera.Camera;

public class Renderer {
	
	private ObjectRenderer objectRenderer;
	
	public Renderer() {
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glCullFace(GL11.GL_BACK);
		
		this.objectRenderer = new ObjectRenderer();
		
		this.loadProjectionMatrix();
		//this.loadViewMatrix();
	}
	
	public void prepare() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glClearColor(0.5f, 0.5f, 0.5f, 1);
	}
	
	public void loadViewMatrix(Camera camera) {
		Matrix4f viewMatrix = camera.generateViewMatrix();
		
		this.objectRenderer.getObjectShader().startProgram();
		this.objectRenderer.getObjectShader().loadViewMatrix(viewMatrix);
		this.objectRenderer.getObjectShader().stopProgram();
	}
	
	public void loadProjectionMatrix() {
		Matrix4f projectionMatrix = this.generateProjectionMatrix();
		
		this.objectRenderer.getObjectShader().startProgram();
		this.objectRenderer.getObjectShader().loadProjectionMatrix(projectionMatrix);
		this.objectRenderer.getObjectShader().stopProgram();
	}
	
	public ObjectRenderer getObjectRenderer() {
		return this.objectRenderer;
	}
	
	public void cleanUp() {
		this.objectRenderer.cleanUp();
	}
	
	public Matrix4f generateProjectionMatrix() {
		Matrix4f projectionMatrix;
		
		float FOV = 120;
		float NEAR_PLANE = 0.1f;
		float FAR_PLANE = 100 * (float)Math.sqrt(3);
		
		float aspectRatio = (float) Display.getWidth() / (float) Display.getHeight();
		float y_scale = (float) ((1f / Math.tan(Math.toRadians(FOV / 2f))) * aspectRatio);
		float x_scale = y_scale / aspectRatio;
		float frustum_length = FAR_PLANE - NEAR_PLANE;
		
		projectionMatrix = new Matrix4f();
		projectionMatrix.m00 = x_scale;
		projectionMatrix.m11 = y_scale;
		projectionMatrix.m22 = -((FAR_PLANE + NEAR_PLANE) / frustum_length);
		projectionMatrix.m23 = -1;
		projectionMatrix.m32 = -((2 * FAR_PLANE * NEAR_PLANE) / frustum_length);
		projectionMatrix.m33 = 0;
		
		return projectionMatrix;
	}

}
