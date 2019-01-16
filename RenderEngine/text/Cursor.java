package text;

public class Cursor {
	
	private float positionX;
	private float positionY;
	
	public Cursor(float positionX, float positionY) {
		this.positionX = positionX;
		this.positionY = positionY;
	}
	
	public void reset() {
		this.positionX = 0;
		this.positionY = 0;
	}
	
	public float getPositionX() {
		return this.positionX;
	}
	
	public void setPositionX(float positionX) {
		this.positionX = positionX;
	}
	
	public float getPositionY() {
		return this.positionY;
	}
	
	public void setPositionY(float positionY) {
		this.positionY = positionY;
	}
	
	public void increasePositionX(float increaseX) {
		this.positionX += increaseX;
	}
	
	public void increasePositionY(float increaseY) {
		this.positionY += increaseY;
	}

}
