package components;

import java.util.List;

public abstract class Component {
	
	public static final int MODEL_COMPONENT = 0;
	public static final int TRANSFORM_COMPONENT = 1;
	public static final int RENDER_COMPONENT = 2;
	public static final int LIGHT_COMPONENT = 3;
	public static final int COLLISION_COMPONENT = 4;
	public static final int PICKING_COMPONENT = 5;
	public static final int AUDIO_COMPONENT = 6;
	public static final int SHOWCASE_COMPONENT = 7; //Temporary Component used during testing
	
	public Component() {
		
	}
	
	public abstract int[] getRequiredComponents();
	
	public abstract void setRequiredComponents(List<Component> components);
	
	public abstract void update();
	
	public abstract void initialize();
	
	public abstract int getType();

}
