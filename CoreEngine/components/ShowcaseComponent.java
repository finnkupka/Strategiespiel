package components;

import java.util.List;

import org.lwjgl.util.vector.Vector3f;

//Likely just a temporary component, can be used to show an entity
public class ShowcaseComponent extends Component {
	
	private TransformComponent transformComponent;
	
	@Override
	public int[] getRequiredComponents() {
		int[] requiredComponents = {
				Component.TRANSFORM_COMPONENT
		};
		return requiredComponents;
	}

	@Override
	public void setRequiredComponents(List<Component> components) {
		this.transformComponent = (TransformComponent) components.get(0);
	}

	@Override
	public void update() {
		this.transformComponent.getRotation().y += 0.3f;
	}

	@Override
	public void initialize() {
		
	}

	@Override
	public int getType() {
		return SHOWCASE_COMPONENT;
	}
	
	
	
}
