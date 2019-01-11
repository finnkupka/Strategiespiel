package components;

import org.lwjgl.util.vector.Vector3f;

//Likely just a temporary component, can be used to show an entity
public class ShowcaseComponent extends TransformationComponent {
	
	public ShowcaseComponent(Vector3f position, Vector3f rotation, float scale) {
		super(position, rotation, scale);
	}
	
	@Override //Changes rotation over time to show the entity from multiple sides
	public void update() {
		this.getRotation().y += 0.5f;
	}
	
}
