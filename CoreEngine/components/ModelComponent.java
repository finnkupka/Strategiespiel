package components;

import java.util.List;

import openglObjects.Vao;
import texture.TextureMap;

public class ModelComponent extends Component {
	
	private Vao vao;
	private TextureMap textureMap;
	
	public ModelComponent(Vao vao, TextureMap textureMap) {
		super();
		this.vao = vao;
		this.textureMap = textureMap;
	}
	
	public ModelComponent(Vao vao, TextureMap textureMap, TextureMap normalMap) {
		super();
		this.vao = vao;
		this.textureMap = textureMap;
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
		return MODEL_COMPONENT;
	}
	
	public Vao getVao() {
		return this.vao;
	}
	
	public TextureMap getTextureMap() {
		return this.textureMap;
	}

}
