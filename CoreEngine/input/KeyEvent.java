package input;

public class KeyEvent {
	
	private int keyCode;
	private char keyChar;
	private boolean pressed;
	private boolean held;
	private long timeHeld;
	
	public KeyEvent(int keyCode, char keyChar, boolean pressed, boolean held, long timeHeld) {
		this.keyCode = keyCode;
		this.keyChar = keyChar;
		this.pressed = pressed;
		this.held = held;
		this.timeHeld = timeHeld;
	}
	
	public int getKeyCode() {
		return this.keyCode;
	}
	
	public char getKeyChar() {
		return this.keyChar;
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
