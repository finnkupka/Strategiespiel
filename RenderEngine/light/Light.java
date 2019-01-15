package light;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

public class Light {
	
	private Vector3f position;
	private Vector3f color;
	private Vector3f attenuation;
	
	public Light(Vector3f position, Vector3f color, Vector3f attenuation) {
		this.position = position;
		this.color = color;
		this.attenuation = attenuation;
	}
	
	public Light(Vector3f position, Vector3f color) {
		this(position, color, new Vector3f(1,0,0));
	}
	
	public void transform(Matrix4f matrix) {
		Vector4f p = new Vector4f(this.position.x, this.position.y, this.position.z, 1);
		Matrix4f.transform(matrix, p, p);
		this.position.x = p.x / p.w;
		this.position.y = p.y / p.w;
		this.position.z = p.z / p.w;
	}
	
	public Vector3f getPosition() {
		return this.position;
	}
	
	public Vector3f getColor() {
		return this.color;
	}
	
	public Vector3f getAttenuation() {
		return this.attenuation;
	}

}
