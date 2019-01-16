package gui;

import input.InputManager;
import input.KeyEvent;
import loader.Loader;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

import texture.TextureMap;

public class GuiTextField extends GuiLabel {
	
	private static TextureMap textureGuiBackground;
	
	private Gui guiBackground;
	
	public GuiTextField(float gridX, float gridY, float gridWidth, float gridHeight, int positioning, float size, Vector3f color) {
		super(gridX, gridY, gridWidth, gridHeight, "", positioning, size, color);
	}
	
	@Override
	public void initialize() {
		this.guiBackground = new Gui(textureGuiBackground, 	new Vector2f(-1f + this.gridX * WIDTH, -1f + this.gridY * HEIGHT), 
															new Vector2f(this.gridWidth * WIDTH, this.gridHeight * HEIGHT));
		
		super.initialize();
	}
	
	@Override
	public void reposition() {
		super.reposition();
		
		float gridX = this.gridX;
		float gridY = this.gridY;
		
		if(this.parent != null) {
			
			gridX += this.parent.getGridX();
			gridY += this.parent.getGridY();
			
		}
		
		Vector2f guiBackgroundPosition = this.guiBackground.getPosition();
		guiBackgroundPosition.x = -1f + gridX * WIDTH;
		guiBackgroundPosition.y = -1f + gridY * HEIGHT;
		Vector2f guiBackgroundSize = this.guiBackground.getSize();
		guiBackgroundSize.x = this.gridWidth * WIDTH;
		guiBackgroundSize.y = this.gridHeight * HEIGHT;
	}
	
	@Override
	public void update() {
		super.update();
		
		for(KeyEvent keyEvent : InputManager.keyEvents) {
			
			if(keyEvent.getKeyCode() == 28) {
				if(!keyEvent.isHeld() && keyEvent.isPressed()) {
					text.append("\n", loader);
					string.append("\n");
				}
			}
			else if(keyEvent.getKeyCode() == 14) {
				if(!keyEvent.isHeld() && keyEvent.isPressed()) {
					text.delete(1, loader);
					string.delete(Math.max(string.length() - 1, 0), string.length());
				}
			}
			else if(keyEvent.isPressed()) {
				if(!keyEvent.isHeld()) {
					if(keyEvent.getKeyChar() != '\u0000' && !Character.isISOControl(keyEvent.getKeyChar())) {
						char key = keyEvent.getKeyChar();
						text.append(String.valueOf(key), loader);
						string.append(key);
						//System.out.println("key: " + key + " " + (int)key + " " + Character.isISOControl(key));
					}
				}
			}
		}
	}
	
	@Override
	public void render() {
		
		if(!this.visible) {
			return;
		}
		
		super.render();
		
		guiRenderer.getGuiShader().startProgram();
		guiRenderer.getGuiShader().loadTranslation(this.guiBackground.getPosition());
		guiRenderer.getGuiShader().loadSize(this.guiBackground.getSize());
		guiRenderer.getGuiShader().loadCanvas(fullCanvas);
		guiRenderer.render(this.guiBackground);
		guiRenderer.getGuiShader().stopProgram();
	}
	
	public static void loadTexture(Loader loader) {
		textureGuiBackground = loader.loadTexture("guiTextField");
	}

}
