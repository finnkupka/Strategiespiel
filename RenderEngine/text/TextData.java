package text;

public class TextData {
	
	private float[] positions;
	private float[] textureCoords;
	
	public TextData(float[] positions, float[] textureCoords) {
		this.positions = positions;
		this.textureCoords = textureCoords;
	}
	
	public float[] getPositions() {
		return this.positions;
	}
	
	public float[] getTextureCoords() {
		return this.textureCoords;
	}
	
	public void append(TextData textData) {
		this.append(textData.getPositions(), textData.getTextureCoords());
	}
	
	public void append(float[] positions, float[] textureCoords) {
		float[] p = new float[this.positions.length + positions.length];
		float[] t = new float[this.textureCoords.length + textureCoords.length];
		
		System.arraycopy(this.positions, 0, p, 0, this.positions.length);
		System.arraycopy(positions, 0, p, this.positions.length, positions.length);
		
		System.arraycopy(this.textureCoords, 0, t, 0, this.textureCoords.length);
		System.arraycopy(textureCoords, 0, t, this.textureCoords.length, textureCoords.length);
		
		this.positions = p;
		this.textureCoords = t;
	}
	
	public void delete(int number) {
		int subLength = number * 6 * 2;
		
		if(this.positions.length < subLength) {
			return;
		}
		
		float[] p = new float[this.positions.length - subLength];
		float[] t = new float[this.textureCoords.length - subLength];
		
		System.arraycopy(this.positions, 0, p, 0, p.length);
		System.arraycopy(this.textureCoords, 0, t, 0, t.length);
		
		this.positions = p;
		this.textureCoords = t;
	}

}
