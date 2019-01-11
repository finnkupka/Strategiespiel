package components;

import java.util.List;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

public class TransformationComponent extends Component {
	
	private Vector3f position;
	private float rotationX;
	private float rotationY;
	private float rotationZ;
	private float scale;
	
	public TransformationComponent(Vector3f position, float rotationX, float rotationY, float rotationZ, float scale) {
		super();
		this.position = position;
		this.rotationX = rotationX;
		this.rotationY = rotationY;
		this.rotationZ = rotationZ;
		this.scale = scale;
	}
	
	@Override
	public int[] getRequiredComponents() {
		int[] requiredComponents = {
				
		};
		return requiredComponents;
	}
	
	@Override
	public void setRequiredComponents(List<Component> components) {
		
	}
	
	@Override
	public void initialize() {
		
	}
	
	@Override
	public void update() {
		this.rotationY += 0.5f;
	}
	
	@Override
	public int getType() {
		return super.TRANSFORMATION_COMPONENT;
	}
	
	public Matrix4f generateTransformationMatrix() {
		Matrix4f transformationMatrix = new Matrix4f();
		transformationMatrix.setIdentity();
		Matrix4f.translate(this.position, transformationMatrix, transformationMatrix);
		Matrix4f.rotate((float)Math.toRadians(this.rotationX), new Vector3f(1,0,0), transformationMatrix, transformationMatrix);
		Matrix4f.rotate((float)Math.toRadians(this.rotationY), new Vector3f(0,1,0), transformationMatrix, transformationMatrix);
		Matrix4f.rotate((float)Math.toRadians(this.rotationZ), new Vector3f(0,0,1), transformationMatrix, transformationMatrix);
		Matrix4f.scale(new Vector3f(this.scale, this.scale, this.scale), transformationMatrix, transformationMatrix);
		return transformationMatrix;
	}
	
	public Vector3f getPosition() {
		return this.position;
	}
	
	public float getRotationX() {
		return this.rotationX;
	}
	
	public float getRotationY() {
		return this.rotationY;
	}
	
	public float getRotationZ() {
		return this.rotationZ;
	}
	
	public float getScale() {
		return this.scale;
	}

}
