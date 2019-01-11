package entities;

import java.util.ArrayList;
import java.util.List;

import components.Component;
import components.RenderComponent;

public class Entity {
	
	private List<Component> components;
	
	public Entity() {
		this.components = new ArrayList<Component>();
	}
	
	public void update() {
		for(Component component : this.components) {
			if(!(component instanceof RenderComponent)) {
				component.update();
			}
		}
	}
	
	public void render() {
		for(Component component : this.components) {
			if(component instanceof RenderComponent) {
				component.update();
				break;
			}
		}
	}
	
	public void addComponent(Component component) {
		this.components.add(component);
	}
	
	public void linkComponents() {
		for(Component component : this.components) {
			int[] requiredComponentTypes = component.getRequiredComponents();
			List<Component> requiredComponents = new ArrayList<Component>();
			for(int i : requiredComponentTypes) {
				for(Component c : this.components) {
					if(c.getType() == i) {
						requiredComponents.add(c);
					}
				}
			}
			component.setRequiredComponents(requiredComponents);
			component.initialize();
		}
	}
	
	public Component getComponent(int componentType) {
		for(Component component : this.components) {
			if(component.getType() == componentType) {
				return component;
			}
		}
		return null;
	}

}
