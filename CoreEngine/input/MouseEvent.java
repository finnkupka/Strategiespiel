package input;

import org.lwjgl.util.vector.Vector2f;

public class MouseEvent {
	
	private Vector2f position;
	private int keyCode;
	private boolean pressed;
	private boolean held;
	private long timeHeld;
	
	public MouseEvent(Vector2f position, int keyCode, boolean pressed, boolean held, long timeHeld) {
		this.position = position;
		this.keyCode = keyCode;
		this.pressed = pressed;
		this.held = held;
		this.timeHeld = timeHeld;
	}
	
	public Vector2f getPosition() {
		return this.position;
	}
	
	public int getKeyCode() {
		return this.keyCode;
	}
	
	public boolean isPressed() {
		return this.pressed;
	}
	
	public boolean isHeld() {
		return this.held;
	}
	
	public long getTimeHeld() {
		return this.timeHeld;
	}

}
