package gui;

import loader.Loader;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector2f;

import texture.TextureMap;

public class GuiHorizontalSlider extends GuiComponent {
	
	private static TextureMap textureGuiScrollBackground;
	
	private Gui guiScrollBackground;
	
	private GuiButton guiButton;
	
	private float scrolling;
	
	public GuiHorizontalSlider(float gridX, float gridY, float gridWidth, float gridHeight) {
		super(gridX, gridY, gridWidth, gridHeight);
		
		this.guiButton = new GuiButton(0, 0, gridHeight, gridHeight);
		this.guiButton.add(new GuiButtonListener() {

			@Override
			public void pressed() {
				
			}

			@Override
			public void entered() {
				
			}

			@Override
			public void held() {
				
				float newGridXPosition = ((float)Mouse.getX() / Display.getWidth() * COLUMNS) - guiButton.gridHeight * 0.5f - guiButton.parent.getGridX();
				
				newGridXPosition = Math.max(newGridXPosition, 0);
				newGridXPosition = Math.min(newGridXPosition, gridWidth - gridHeight);
				
				scrolling = newGridXPosition / (gridWidth - guiButton.gridHeight);
				
				guiButton.gridX = newGridXPosition;
				
				guiButton.reposition();
				
			}

			@Override
			public void hovered() {
				
			}

			@Override
			public void exited() {
				
			}

			@Override
			public void released() {
				
			}
			
		});
		this.add(this.guiButton);
		
		this.scrolling = 0f;
		
		this.initialize();
	}
	
	public float getScrolling() {
		return this.scrolling;
	}
	
	public void setScrolling(float scrolling)	{
		scrolling = Math.max(scrolling, 0);
		scrolling = Math.min(scrolling, 1);
		
		this.scrolling = scrolling;
		
		float newGridXPosition = scrolling * (this.gridWidth - this.guiButton.gridHeight);
		
		this.guiButton.gridX = newGridXPosition;
	}

	@Override
	public void initialize() {
		this.guiScrollBackground = new Gui(textureGuiScrollBackground, 	new Vector2f(),
																		new Vector2f());
		
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
		
		Vector2f guiScrollBackgroundPosition = this.guiScrollBackground.getPosition();
		guiScrollBackgroundPosition.x = -1f + gridX * WIDTH;
		guiScrollBackgroundPosition.y = -1f + gridY * HEIGHT;
		Vector2f guiScrollBackgroundSize = this.guiScrollBackground.getSize();
		guiScrollBackgroundSize.x = this.gridWidth * WIDTH;
		guiScrollBackgroundSize.y = this.gridHeight * HEIGHT;
	}
	
	@Override
	public void update() {
		super.update();
	}
	
	@Override
	public void render() {
		
		if(!this.isVisible()) {
			return;
		}
		
		super.render();
		
		guiRenderer.getGuiShader().startProgram();
		guiRenderer.getGuiShader().loadCanvas(fullCanvas);
		
		guiRenderer.getGuiShader().loadTranslation(this.guiScrollBackground.getPosition());
		guiRenderer.getGuiShader().loadSize(this.guiScrollBackground.getSize());
		guiRenderer.render(this.guiScrollBackground);
		
		guiRenderer.getGuiShader().stopProgram();
		
	}
	
	public static void loadTexture(Loader loader) {
		textureGuiScrollBackground = loader.loadTexture("guiHorizontalScrollBackground");
	}

}
