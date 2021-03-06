package audio;

import org.lwjgl.openal.AL10;
import org.lwjgl.util.vector.Vector3f;

public class AudioSource {
	
	private int sourceId;
	
	public AudioSource(Vector3f pos, float volume, float pitch) {
		sourceId = AL10.alGenSources();
		setVolume(volume);
		setPitch(pitch);
		setPosition(pos.x, pos.y, pos.z);
		
		AL10.alSourcef(sourceId, AL10.AL_ROLLOFF_FACTOR, AudioMaster.DEFAULT_ROLLOFF_FACTOR);
		AL10.alSourcef(sourceId, AL10.AL_REFERENCE_DISTANCE, AudioMaster.DEFAULT_REFERENCE_DISTANCE);
	}
	
	public void play(int buffer) {
		stop();
		AL10.alSourcei(sourceId, AL10.AL_BUFFER, buffer);
		continuePlaying();
	}
	
	public void setVolume(float volume) {
		AL10.alSourcef(sourceId, AL10.AL_GAIN, volume);
	}
	
	public void setPitch(float pitch) {
		AL10.alSourcef(sourceId, AL10.AL_PITCH, pitch);
	}
	
	public void setPosition(float x, float y, float z) {
		AL10.alSource3f(sourceId, AL10.AL_POSITION, x, y, z);
	}
	
	void setVelocity(float x, float y, float z) {
		AL10.alSource3f(sourceId, AL10.AL_VELOCITY, x, y, z);
	}
	
	void setLooping(boolean isLooping) {
		AL10.alSourcei(sourceId, AL10.AL_LOOPING, isLooping ? AL10.AL_TRUE : AL10.AL_FALSE);
	}
	
	public boolean isPlaying() {
		return AL10.alGetSourcei(sourceId, AL10.AL_SOURCE_STATE) == AL10.AL_PLAYING;
	}
	
	public void continuePlaying() {
		AL10.alSourcePlay(sourceId);
	}
	
	public void pause() {
		AL10.alSourcePause(sourceId);
	}
	
	public void stop() {
		AL10.alSourcePlay(sourceId);
	}
	
	public void delete() {
		stop();
		AL10.alDeleteSources(sourceId);
	}
	
}
