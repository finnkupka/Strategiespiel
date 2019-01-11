package camera;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

public class Camera {
	
	private Vector3f position;
	private float pitch;		//x-axis
	private float yaw;			//y-axis
	private float roll;			//z-axis
	
	public Camera(Vector3f position, float pitch, float yaw, float roll) {
		this.position = position;
		this.pitch = pitch;
		this.yaw = yaw;
		this.roll = roll;
	}
	
	public Matrix4f generateViewMatrix() {
		Matrix4f viewMatrix = new Matrix4f();
		viewMatrix.setIdentity();
		Matrix4f.rotate((float)Math.toRadians(this.pitch), new Vector3f(1,0,0), viewMatrix, viewMatrix);
		Matrix4f.rotate((float)Math.toRadians(this.yaw), new Vector3f(0,1,0), viewMatrix, viewMatrix);
		Matrix4f.rotate((float)Math.toRadians(this.roll), new Vector3f(0,0,1), viewMatrix, viewMatrix);
		Matrix4f.translate(this.position.negate(null), viewMatrix, viewMatrix);
		return viewMatrix;
	}
	
	public void update() {
		
		float SPEED = 0.1f;
		
		if(Keyboard.isKeyDown(Keyboard.KEY_W)) {
			this.position.x += Math.sin(Math.toRadians(this.yaw)) * SPEED;
			this.position.z -= Math.cos(Math.toRadians(this.yaw)) * SPEED;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_S)) {
			this.position.x -= Math.sin(Math.toRadians(this.yaw)) * SPEED;
			this.position.z += Math.cos(Math.toRadians(this.yaw)) * SPEED;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_A)) {
			this.position.x += Math.sin(Math.toRadians(this.yaw - 90)) * SPEED;
			this.position.z -= Math.cos(Math.toRadians(this.yaw - 90)) * SPEED;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_D)) {
			this.position.x += Math.sin(Math.toRadians(this.yaw + 90)) * SPEED;
			this.position.z -= Math.cos(Math.toRadians(this.yaw + 90)) * SPEED;
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
			this.yaw -= 1.5f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
			this.yaw += 1.5f;
		}
	}
	
	public Vector3f getPosition() {
		return this.position;
	}
	
	public float getPitch() {
		return this.pitch;
	}
	
	public float getYaw() {
		return this.yaw;
	}
	
	public float getRoll() {
		return this.roll;
	}
	
	public void setPosition(Vector3f position) {
		this.position = position;
	}
	
	public void setPitch(float pitch) {
		this.pitch = pitch;
	}
	
	public void setYaw(float yaw) {
		this.yaw = yaw;
	}
	
	public void setRoll(float roll) {
		this.roll = roll;
	}
	
	public void invertPitch() {
		this.pitch *= -1f;
	}
	
	public void invertYaw() {
		this.yaw *= -1f;
	}
	
	public void invertRoll() {
		this.roll *= -1f;
	}
	
}
