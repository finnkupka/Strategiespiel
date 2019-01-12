package audio;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.LWJGLException;
import org.lwjgl.openal.AL;
import org.lwjgl.openal.AL10;
import org.lwjgl.openal.AL11;
import org.lwjgl.util.vector.Vector3f;
import org.newdawn.slick.openal.WaveData;

public class AudioMaster {
	
	public static final float DEFAULT_VOLUME = 0.06f;
	public static final float DEFAULT_PITCH = 1f;
	public static final float DEFAULT_ROLLOFF_FACTOR = 1;
	public static final float DEFAULT_REFERENCE_DISTANCE = 4;
	private static List<Integer> buffers = new ArrayList<Integer>();
	
	public static void init() {
		try {
			AL.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		AL10.alDistanceModel(AL11.AL_EXPONENT_DISTANCE_CLAMPED);
	}
	
	public static void setListenerData(Vector3f pos) {
		AL10.alListener3f(AL10.AL_POSITION, pos.x, pos.y, pos.z);
		AL10.alListener3f(AL10.AL_VELOCITY, 0, 0, 0);
	}
	
	public static int loadSound(String file) {
		int buffer = AL10.alGenBuffers();
		buffers.add(buffer);
		
		FileInputStream fin = null;
		BufferedInputStream bin = null;
		try {
			fin = new FileInputStream(file);
			bin = new BufferedInputStream(fin);
		}
		catch(FileNotFoundException e) {
			System.out.println("Missing File:" + file);
			e.printStackTrace();
		}
		WaveData waveFile = WaveData.create(bin);
		AL10.alBufferData(buffer, waveFile.format, waveFile.data, waveFile.samplerate);
		waveFile.dispose();
		return buffer;
	}
	
	public static void cleanUp() {
		for (int b : buffers) AL10.alDeleteBuffers(b);
		AL.destroy();
	}
	
}
