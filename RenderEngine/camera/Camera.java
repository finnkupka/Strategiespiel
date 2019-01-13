package camera;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import audio.AudioMaster;
import main.DisplayManager;

public class Camera {
	
	private Vector3f anchor = new Vector3f(0, 0, 0);
	private Vector3f position = new Vector3f(0, 0, 0);
	private float speed = 16;
	
	private float pitch = 20;
	private float yaw = 0;
	private float roll = 0;
	
	private float distance = 40;
	private float angle = 0;
	
	
	public Camera(Vector3f anchor) {
		this.anchor = anchor;
	}
	
	public void move() {
		if(Keyboard.isKeyDown(Keyboard.KEY_W)) {
			anchor.x += Math.sin(Math.toRadians(yaw)) * speed * DisplayManager.DELTA;
			anchor.z -= Math.cos(Math.toRadians(yaw)) * speed * DisplayManager.DELTA;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_S)) {
			anchor.x -= Math.sin(Math.toRadians(yaw)) * speed * DisplayManager.DELTA;
			anchor.z += Math.cos(Math.toRadians(yaw)) * speed * DisplayManager.DELTA;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_A)) {
			anchor.x += Math.sin(Math.toRadians(yaw - 90)) * speed * DisplayManager.DELTA;
			anchor.z -= Math.cos(Math.toRadians(yaw - 90)) * speed * DisplayManager.DELTA;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_D)) {
			anchor.x += Math.sin(Math.toRadians(yaw + 90)) * speed * DisplayManager.DELTA;
			anchor.z -= Math.cos(Math.toRadians(yaw + 90)) * speed * DisplayManager.DELTA;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)  || Keyboard.isKeyDown(Keyboard.KEY_E)) {
			anchor.y += speed * DisplayManager.DELTA;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_Q)) {
			anchor.y -= speed * DisplayManager.DELTA;
		}
	}
	
	public void update() {
		move();
		calcZoom();
		calcPitchAndAngle();
		constrainValues();
		calcCameraPosition(calcHorizontalDistance(), calcVerticalDistance());
		yaw = 180 - angle;
		
		AudioMaster.setListenerData(position);
	}
	
	private void constrainValues() {
		if (pitch < 0) pitch = 0;
		if (pitch > 90) pitch = 90;
		if (distance < 5) distance = 5;
		if (distance > 75) distance = 75;
	}
	
	private void calcCameraPosition(float horizontalDistance, float verticalDistance) {
		float offsetX = (float) (horizontalDistance * Math.sin(Math.toRadians(angle)));
		float offsetZ = (float) (horizontalDistance * Math.cos(Math.toRadians(angle)));
		position.x = anchor.x - offsetX;
		position.z = anchor.z - offsetZ;
		position.y = anchor.y + verticalDistance;
	}
	
	private float calcHorizontalDistance() {
		return (float) (distance * Math.cos(Math.toRadians(pitch)));
	}
	
	private float calcVerticalDistance() {
		return (float) (distance * Math.sin(Math.toRadians(pitch)));
	}
	
	private void calcZoom() {
		float zoom = Mouse.getDWheel() * 0.1f;
		distance -= zoom;
	}
	
	private void calcPitchAndAngle() {
		while (Mouse.next()){
		    if (Mouse.getEventButtonState()) {
		        if (Mouse.getEventButton() == 0) Mouse.setGrabbed(true);
		    } else if (Mouse.getEventButton() == 0) Mouse.setGrabbed(false);
		}
		if (Mouse.isGrabbed()) {
			float angleDelta = Mouse.getDX() * 0.1f;
			angle -= angleDelta;
			float pitchDelta = Mouse.getDY() * 0.1f;
			pitch -= pitchDelta;
		}
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
	
}