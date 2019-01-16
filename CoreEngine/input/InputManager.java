package input;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector2f;

public class InputManager {
	
	public static List<KeyEvent> keyEvents = new ArrayList<KeyEvent>();
	public static List<MouseEvent> mouseEvents = new ArrayList<MouseEvent>();
	
	public static void update() {
		while(Keyboard.next()) {
			int keyCode 	= Keyboard.getEventKey();
			char keyChar 	= Keyboard.getEventCharacter();
			boolean pressed = Keyboard.getEventKeyState();
			boolean held 	= Keyboard.isRepeatEvent();
			long timeHeld 	= Keyboard.getEventNanoseconds();
			keyEvents.add(new KeyEvent(keyCode, keyChar, pressed, held, timeHeld));
		}
		
		while(Mouse.next()) {
			Vector2f position 	= new Vector2f(Mouse.getEventX(), Mouse.getEventY());
			int keyCode 		= Mouse.getEventButton();
			if(keyCode == -1) {
				//continue;
			}
			
			boolean pressed 	= Mouse.getEventButtonState();
			boolean held		= false;
			
			if(!pressed) {
				for(int i = 0; i < Mouse.getButtonCount(); i++) {
					if(Mouse.isButtonDown(i)) {
						held = true;
						break;
					}
				}
			}
			
			long timeHeld 		= Mouse.getEventNanoseconds();
			mouseEvents.add(new MouseEvent(position, keyCode, pressed, held, timeHeld));
			//System.out.println("position: " + position + "\tkeyCode: " + keyCode + "\tpressed: " + pressed + "\theld: " + held);
		}
		
		if(Mouse.getDX() == 0 && Mouse.getDY() == 0) {
			boolean buttonDown = false;
			for(int i = 0; i < Mouse.getButtonCount(); i++) {
				if(Mouse.isButtonDown(i)) {
					mouseEvents.add(new MouseEvent(new Vector2f(Mouse.getX(), Mouse.getY()), i, false, true, -1));
					//System.out.println("position: " + new Vector2f(Mouse.getX(), Mouse.getY()) + "\tkeyCode: " + i + "\tpressed: " + false + "\theld: " + true);
					buttonDown = true;
					break;
				}
			}
			
			if(!buttonDown) {
				mouseEvents.add(new MouseEvent(new Vector2f(Mouse.getX(), Mouse.getY()), -1, false, false, -1));
				//System.out.println("position: " + new Vector2f(Mouse.getX(), Mouse.getY()) + "\tkeyCode: " + -1 + "\tpressed: " + false + "\theld: " + false);
			}
		}
	}
	
	public static void clear() {
		keyEvents.clear();
		mouseEvents.clear();
	}

}
