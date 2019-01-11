package components;

import java.util.List;

import openglObjects.Vao;
import texture.TextureMap;

public class ModelComponent extends Component {
	
	private Vao vao;
	private TextureMap textureMap;
	//private TextureMap normalMap;
	
	public ModelComponent(Vao vao, TextureMap textureMap) {
		super();
		this.vao = vao;
		this.textureMap = textureMap;
		//this.normalMap = null;
	}
	
	public ModelComponent(Vao vao, TextureMap textureMap, TextureMap normalMap) {
		super();
		this.vao = vao;
		this.textureMap = textureMap;
		//this.normalMap = normalMap;
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
		return super.MODEL_COMPONENT;
	}
	
	public Vao getVao() {
		return this.vao;
	}
	
	public TextureMap getTextureMap() {
		return this.textureMap;
	}
	
	/*
	public TextureMap getNormalMap() {
		return this.normalMap;
	}
	
	public boolean hasNormalMap() {
		return this.normalMap != null;
	}
	*/

}
