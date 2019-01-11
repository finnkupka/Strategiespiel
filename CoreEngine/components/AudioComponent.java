package components;

import java.io.File;
import java.util.List;

import org.lwjgl.input.Keyboard;

import audio.AudioMaster;
import audio.AudioSource;

public class AudioComponent extends Component {
	
	int buffer;
	AudioSource audioSource;
	
	public AudioComponent(String fileName) {
		super();
		try {
			buffer = AudioMaster.loadSound("Resources" + File.separator + fileName + ".wav");
		} catch (Exception e) {
			System.out.println("Missing File: \"" + fileName + "\"");
		}
		audioSource = new AudioSource();
	}

	@Override
	public int[] getRequiredComponents() {
		int[] requiredComponents = {
				
		};
		return requiredComponents;
	}

	@Override
	public void setRequiredComponents(List<Component> components) {
		// No components required (yet)
	}

	@Override
	public void update() {
		if (Keyboard.isKeyDown(Keyboard.KEY_P)) audioSource.play(buffer);
	}

	@Override
	public void initialize() {
		// Not necessary
	}

	@Override
	public int getType() {
		return AUDIO_COMPONENT;
	}
	
}
