package components;

import java.util.List;

//import lighting.Light;
import renderer.Renderer;
//import setup.Configurations;

public class RenderComponent extends Component {
	
	private static Renderer renderer;
	
	private ModelComponent modelComponent;
	private TransformComponent transformationComponent;
	
	public RenderComponent() {
		super();
	}
	
	@Override
	public int[] getRequiredComponents() {
		int[] requiredComponents = {
				Component.MODEL_COMPONENT,
				Component.TRANSFORM_COMPONENT
		};
		return requiredComponents;
	}
	
	@Override
	public void setRequiredComponents(List<Component> components) {
		this.modelComponent = (ModelComponent) components.get(0);
		this.transformationComponent = (TransformComponent) components.get(1);
	}
	
	@Override
	public void initialize() {
		
	}
	
	@Override
	public void update() {
			
		renderer.getObjectRenderer().getObjectShader().startProgram();
		renderer.getObjectRenderer().getObjectShader().loadTransformationMatrix(this.transformationComponent.generateTransformationMatrix());
		//Light[] lights = LightComponent.getLights(this.transformationComponent.getPosition());
		//renderer.getObjectRenderer().getObjectShader().loadLights(lights);
		renderer.getObjectRenderer().render(this.modelComponent.getVao(), this.modelComponent.getTextureMap());
		renderer.getObjectRenderer().getObjectShader().stopProgram();
		
	}
	
	@Override
	public int getType() {
		return Component.RENDER_COMPONENT;
	}
	
	public static void setRenderer(Renderer r) {
		renderer = r;
	}

}
