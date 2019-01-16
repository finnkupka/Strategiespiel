package text;

public class Character {
	
	private int id;
	private int xPosition;
	private int yPosition;
	private int width;
	private int height;
	private int xOffset;
	private int yOffset;
	private int xAdvance;
	
	public Character(int id, int xPosition, int yPosition, int width, int height, int xOffset, int yOffset, int xAdvance) {
		this.id = id;
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		this.width = width;
		this.height = height;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		this.xAdvance = xAdvance;
	}
	
	public int getID() {
		return this.id;
	}

	public int getXPosition() {
		return this.xPosition;
	}
	
	public int getYPosition() {
		return this.yPosition;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public int getXOffest() {
		return this.xOffset;
	}
	
	public int getYOffset() {
		return this.yOffset;
	}
	
	public int getXAdvance() {
		return this.xAdvance;
	}

}
