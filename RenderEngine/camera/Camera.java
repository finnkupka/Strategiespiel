package camera;

import main.DisplayManager;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

public class Camera {
	
	private Vector3f position;
	private float pitch;		//x-axis
	private float yaw;			//y-axis
	private float roll;			//z-axis
	private float speed;
	private float sensitivity;
	
	public Camera(Vector3f position, float pitch, float yaw, float roll) {
		this.position = position;
		this.pitch = pitch;
		this.yaw = yaw;
		this.roll = roll;
		this.speed = 5;          //Constructor worthy?
		this.sensitivity = 0.1f; //Constructor worthy?
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
		//Keyboard Controls:
		if(Keyboard.isKeyDown(Keyboard.KEY_W)) {
			this.position.x += Math.sin(Math.toRadians(this.yaw)) * speed * DisplayManager.DELTA;
			this.position.z -= Math.cos(Math.toRadians(this.yaw)) * speed * DisplayManager.DELTA;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_S)) {
			this.position.x -= Math.sin(Math.toRadians(this.yaw)) * speed * DisplayManager.DELTA;
			this.position.z += Math.cos(Math.toRadians(this.yaw)) * speed * DisplayManager.DELTA;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_A)) {
			this.position.x += Math.sin(Math.toRadians(this.yaw - 90)) * speed * DisplayManager.DELTA;
			this.position.z -= Math.cos(Math.toRadians(this.yaw - 90)) * speed * DisplayManager.DELTA;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_D)) {
			this.position.x += Math.sin(Math.toRadians(this.yaw + 90)) * speed * DisplayManager.DELTA;
			this.position.z -= Math.cos(Math.toRadians(this.yaw + 90)) * speed * DisplayManager.DELTA;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
			this.position.y += speed * DisplayManager.DELTA;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
			this.position.y -= speed * DisplayManager.DELTA;
		}
		
		//Simple Mouse Controls:
		while (Mouse.next()){
		    if (Mouse.getEventButtonState()) {
		        if (Mouse.getEventButton() == 0) Mouse.setGrabbed(true);
		    } else if (Mouse.getEventButton() == 0) Mouse.setGrabbed(false);
		}
		if(Mouse.isGrabbed()) {
			this.yaw += Mouse.getDX() * sensitivity;
			this.pitch -= Mouse.getDY() * sensitivity;
		}
		//Failsafe:
		if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) Mouse.setGrabbed(false);
		
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
