package entities;

import light.Light;

import org.lwjgl.util.vector.Vector3f;

import components.LightComponent;
import components.ModelComponent;
import components.RenderComponent;
import components.ShowcaseComponent;
import components.TransformComponent;
import openglObjects.Vao;
import texture.TextureMap;

public class GenericEntity extends Entity {
	
	public GenericEntity(Vector3f position, Vao vao, TextureMap texture) {
		super();
		this.addComponent(new RenderComponent());
		this.addComponent(new TransformComponent(position, new Vector3f(0, 0, 0), 1));
		this.addComponent(new ShowcaseComponent());
		this.addComponent(new ModelComponent(vao, texture));
		this.addComponent(new LightComponent(new Light(new Vector3f(2,10,5), new Vector3f(1,0.5f,1))));
	}
	
}
