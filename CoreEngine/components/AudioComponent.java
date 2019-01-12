package components;

import java.io.File;
import java.util.List;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;

import audio.AudioMaster;
import audio.AudioSource;

public class AudioComponent extends Component {
	
	private int buffer;
	private AudioSource audioSource;
	private TransformComponent transformComponent;
	
	public AudioComponent(String fileName) {
		super();
		try {
			buffer = AudioMaster.loadSound("Resources" + File.separator + fileName + ".wav");
		} catch (Exception e) {
			System.out.println("Missing File: \"" + fileName + "\"");
		}
		audioSource = new AudioSource(new Vector3f(0, 0, 0), AudioMaster.DEFAULT_VOLUME, AudioMaster.DEFAULT_PITCH);
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
		this.transformComponent = (TransformComponent) components.get(0);
	}

	@Override
	public void update() {
		if (Keyboard.isKeyDown(Keyboard.KEY_P)) audioSource.play(buffer);
		Vector3f pos = transformComponent.getPosition();
		audioSource.setPosition(pos.x, pos.y, pos.z);
	}

	@Override
	public void initialize() {
		
	}

	@Override
	public int getType() {
		return AUDIO_COMPONENT;
	}
	
}
