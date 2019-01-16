package gui;

import input.InputManager;
import input.MouseEvent;

import org.lwjgl.input.Controllers;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector4f;

import loader.Loader;
import texture.TextureMap;

public class GuiButton extends GuiComponent {
	
	private static TextureMap textureGuiButton;
	private static TextureMap textureGuiButtonPressed;
	
	private Gui guiButton;
	private Gui guiButtonPressed;
	
	private boolean hovered;
	private boolean pressed;
	
	private GuiButtonListener guiButtonListener;
	
	public GuiButton(float gridX, float gridY, float gridWidth, float gridHeight) {
		super(gridX, gridY, gridWidth, gridHeight);
		
		this.hovered = false;
		this.pressed = false;
		
		this.guiButtonListener = null;
		
		initialize();
	}
	
	@Override
	public void initialize() {
		this.guiButton 			= new Gui(textureGuiButton, 		new Vector2f(), new Vector2f());
		this.guiButtonPressed 	= new Gui(textureGuiButtonPressed, 	new Vector2f(), new Vector2f());
		
		this.reposition();
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
		
		Vector2f guiButtonPosition = this.guiButton.getPosition();
		guiButtonPosition.x = -1f + gridX * WIDTH;
		guiButtonPosition.y = -1f + gridY * HEIGHT;
		Vector2f guiButtonSize = this.guiButton.getSize();
		guiButtonSize.x = gridWidth * WIDTH;
		guiButtonSize.y = gridHeight * HEIGHT;
		
		Vector2f guiButtonPressedPosition = this.guiButtonPressed.getPosition();
		guiButtonPressedPosition.x = -1f + gridX * WIDTH;
		guiButtonPressedPosition.y = -1f + gridY * HEIGHT;
		Vector2f guiButtonPressedSize = this.guiButtonPressed.getSize();
		guiButtonPressedSize.x = gridWidth * WIDTH;
		guiButtonPressedSize.y = gridHeight * HEIGHT;
	}
	
	@Override
	public void update() {
		super.update();
		
		float gridX = this.gridX;
		float gridY = this.gridY;
		
		if(this.parent != null) {
			
			gridX += this.parent.getGridX();
			gridY += this.parent.getGridY();
			
		}
		
		for(MouseEvent mouseEvent : InputManager.mouseEvents) {
			
			float mouseX = mouseEvent.getPosition().x / Display.getWidth() * COLUMNS;
			float mouseY = mouseEvent.getPosition().y / Display.getHeight() * ROWS;;
			
			boolean collision = (	gridX <= mouseX && 
									gridX + this.gridWidth >= mouseX && 
									gridY <= mouseY && 
									gridY + this.gridHeight >= mouseY) ? true : false;
			
			if(this.guiButtonListener != null) {
				
				if(mouseEvent.isPressed() && collision) {
					this.guiButtonListener.pressed();
					//System.out.println("pressed");
					this.pressed = true;
				} else if(mouseEvent.isHeld() && collision) {
					this.guiButtonListener.held();
					//System.out.println("\theld");
				} else if(collision) {
					if(this.pressed) {
						this.guiButtonListener.released();
						//System.out.println("released");
						this.pressed = false;
					} else {
						if(!this.hovered) {
							this.guiButtonListener.entered();
							//System.out.println("entered");
							this.hovered = true;
						} else {
							this.guiButtonListener.hovered();
							//System.out.println("\thovered");
						}
					}
				} else {
					if(hovered) {
						this.guiButtonListener.exited();
						//System.out.println("exited");
						this.hovered = false;
					} else if(this.pressed && mouseEvent.isHeld()) {
						this.guiButtonListener.held();
						//System.out.println("\theld");
					} else if(this.pressed) {
						this.guiButtonListener.released();
						//System.out.println("released");
						this.pressed = false;
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
		
		if(pressed) {
			guiRenderer.getGuiShader().startProgram();
			guiRenderer.getGuiShader().loadTranslation(this.guiButtonPressed.getPosition());
			guiRenderer.getGuiShader().loadSize(this.guiButtonPressed.getSize());
			guiRenderer.getGuiShader().loadCanvas(fullCanvas);
			guiRenderer.render(this.guiButtonPressed);
			guiRenderer.getGuiShader().stopProgram();
		} else {
			guiRenderer.getGuiShader().startProgram();
			guiRenderer.getGuiShader().loadTranslation(this.guiButton.getPosition());
			guiRenderer.getGuiShader().loadSize(this.guiButton.getSize());
			guiRenderer.getGuiShader().loadCanvas(fullCanvas);
			guiRenderer.render(this.guiButton);
			guiRenderer.getGuiShader().stopProgram();
		}
	}
	
	public void add(GuiButtonListener guiButtonListener) {
		this.guiButtonListener = guiButtonListener;
	}
	
	public static void loadTextures(Loader loader) {
		textureGuiButton 		= loader.loadTexture("guiButton");
		textureGuiButtonPressed = loader.loadTexture("guiButtonPressed");
	}

}
