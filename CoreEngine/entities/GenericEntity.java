package entities;

import org.lwjgl.util.vector.Vector3f;

import components.ModelComponent;
import components.RenderComponent;
import components.ShowcaseComponent;
import openglObjects.Vao;
import texture.TextureMap;

public class GenericEntity extends Entity {
	
	public GenericEntity(Vector3f position, Vao vao, TextureMap texture) {
		super();
		this.addComponent(new RenderComponent());
		this.addComponent(new ShowcaseComponent(position, new Vector3f(0, 0, 0), 1));
		this.addComponent(new ModelComponent(vao, texture));
		this.linkComponents();
	}
	
}
