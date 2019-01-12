package components;

import java.util.List;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

public class TransformComponent extends Component {
	
	private Vector3f position;
	private Vector3f rotation;
	private float scale;
	
	public TransformComponent(Vector3f position, Vector3f rotation, float scale) {
		super();
		this.position = position;
		this.rotation = rotation;
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
		
	}
	
	@Override
	public int getType() {
		return TRANSFORM_COMPONENT;
	}
	
	public Matrix4f generateTransformationMatrix() {
		Matrix4f transformationMatrix = new Matrix4f();
		transformationMatrix.setIdentity();
		Matrix4f.translate(this.position, transformationMatrix, transformationMatrix);
		Matrix4f.rotate((float)Math.toRadians(this.rotation.x), new Vector3f(1,0,0), transformationMatrix, transformationMatrix);
		Matrix4f.rotate((float)Math.toRadians(this.rotation.y), new Vector3f(0,1,0), transformationMatrix, transformationMatrix);
		Matrix4f.rotate((float)Math.toRadians(this.rotation.z), new Vector3f(0,0,1), transformationMatrix, transformationMatrix);
		Matrix4f.scale(new Vector3f(this.scale, this.scale, this.scale), transformationMatrix, transformationMatrix);
		return transformationMatrix;
	}
	
	public Vector3f getPosition() {
		return this.position;
	}
	
	public Vector3f getRotation() {
		return this.rotation;
	}
	
	public float getScale() {
		return this.scale;
	}

}
