package components;

import java.util.List;
import java.util.ArrayList;

import org.lwjgl.util.vector.Vector3f;

import light.Light;

public class LightComponent extends Component {
	
	private static final int NUMBER_OF_LIGHTS = 5;
	private static List<Light> allLights = new ArrayList<Light>();
	
	private TransformComponent transformationComponent;
	
	private List<Light> lights;
	
	public LightComponent(Light... lights) {
		super();
		
		//allLights.add(light);
		
		this.lights = new ArrayList<Light>();
		
		for(Light light : lights) {
			allLights.add(light);
			this.lights.add(light);
		}
		
		//this.lights = new ArrayList<Light>();
		//this.lights.add(light);
		
	}
	
	@Override
	public int[] getRequiredComponents() {
		int[] requiredComponents = {
				Component.TRANSFORM_COMPONENT
		};
		return requiredComponents;
	}
	
	@Override
	public void setRequiredComponents(List<Component> components) {
		this.transformationComponent = (TransformComponent) components.get(0);
	}
	
	@Override
	public void initialize() {
		for(Light light : this.lights) {
			light.transform(this.transformationComponent.generateTransformationMatrix());
		}
	}
	
	@Override
	public void update() {
		
	}
	
	@Override
	public int getType() {
		return super.LIGHT_COMPONENT;
	}
	
	public Light getLight() {
		return this.lights.get(0);
	}
	
	public static Light[] getLights(Vector3f position) {
		
		float[] distances = new float[allLights.size()];
		
		for(int i = 0; i < allLights.size(); i++) {
			Vector3f lightPosition = allLights.get(i).getPosition();
			Vector3f distance = new Vector3f();
			Vector3f.sub(lightPosition, position, distance);
			distances[i] = distance.length();
		}
		
		int[] indices = new int[NUMBER_OF_LIGHTS];
		
		for(int i = 0; i < NUMBER_OF_LIGHTS; i++) {
			float smallestDistance = 1000;
			//int index = -1;
			
			for(int j = 0; j < allLights.size(); j++) {
				if(distances[j] < smallestDistance) {
					int tempIndex = j;
					for(int k = 0; k < i; k++) {
						if(indices[k] == j) {
							tempIndex = -1;
						}
					}
					if(tempIndex != -1) {
						indices[i] = tempIndex;
						smallestDistance = distances[j];
					}
				}
			}
		}
		
		Light[] nearLights = new Light[NUMBER_OF_LIGHTS];
		
		for(int i = 0; i < Math.min(NUMBER_OF_LIGHTS, allLights.size()); i++) {
			nearLights[i] = allLights.get(indices[i]);
		}
		
		for(int i = 0; i < NUMBER_OF_LIGHTS; i++) {
			if(nearLights[i] == null) {
				nearLights[i] = new Light(new Vector3f(), new Vector3f());
			}
		}
		
		return nearLights;
	}
	
	public static List<Light> getAllLights() {
		return allLights;
	}
	
	public static void setAllLights(List<Light> lights) {
		allLights = lights;
	}

}
