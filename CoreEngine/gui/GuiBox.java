package gui;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector4f;

import texture.TextureMap;
import loader.Loader;

public class GuiBox extends GuiComponent {
	
	private float size;
	
	private static TextureMap textureGuiCorner;
	private static TextureMap textureGuiLineHorizontal;
	private static TextureMap textureGuiLineVertical;
	private static TextureMap textureGuiBackground;
	
	private Gui guiCornerTopLeft;
	private Gui guiCornerTopRight;
	private Gui guiCornerBottomLeft;
	private Gui guiCornerBottomRight;
	
	private Gui guiLineTop;
	private Gui guiLineBottom;
	private Gui guiLineLeft;
	private Gui guiLineRight;
	
	private Gui guiBackground;
	
	public GuiBox(float gridX, float gridY, float gridWidth, float gridHeight, float size) {
		super(gridX, gridY, gridWidth, gridHeight);
		
		this.size = size;
		
		this.initialize();
	}
	
	@Override
	public void initialize() {
		this.guiCornerTopLeft 		= new Gui(textureGuiCorner, new Vector2f(), 
																new Vector2f());
		this.guiCornerTopRight 		= new Gui(textureGuiCorner, new Vector2f(), 
																new Vector2f());
		this.guiCornerBottomLeft 	= new Gui(textureGuiCorner, new Vector2f(), 
																new Vector2f());
		this.guiCornerBottomRight 	= new Gui(textureGuiCorner, new Vector2f(), 
																new Vector2f());

		this.guiLineTop 	= new Gui(textureGuiLineHorizontal	, 	new Vector2f(), 
																	new Vector2f());
		this.guiLineBottom 	= new Gui(textureGuiLineHorizontal	, 	new Vector2f(), 
																	new Vector2f());
		this.guiLineLeft 	= new Gui(textureGuiLineVertical	, 	new Vector2f(), 
																	new Vector2f());
		this.guiLineRight 	= new Gui(textureGuiLineVertical	, 	new Vector2f(), 
																	new Vector2f());

		this.guiBackground = new Gui(textureGuiBackground, 	new Vector2f(), 
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
		
		Vector2f guiCornerTopLeftPosition = this.guiCornerTopLeft.getPosition();
		guiCornerTopLeftPosition.x = -1f + gridX * WIDTH;
		guiCornerTopLeftPosition.y = -1f +gridY * HEIGHT + this.gridHeight * HEIGHT - HEIGHT * this.size;
		Vector2f guiCornerTopLeftSize = this.guiCornerTopLeft.getSize();
		guiCornerTopLeftSize.x = WIDTH * this.size;
		guiCornerTopLeftSize.y = HEIGHT * this.size;
		
		Vector2f guiCornerTopRightPosition = this.guiCornerTopRight.getPosition();
		guiCornerTopRightPosition.x = -1f + gridX * WIDTH + this.gridWidth * WIDTH - WIDTH * this.size;
		guiCornerTopRightPosition.y = -1f + gridY * HEIGHT + this.gridHeight * HEIGHT - HEIGHT * this.size;
		Vector2f guiCornerTopRightSize = this.guiCornerTopRight.getSize();
		guiCornerTopRightSize.x = WIDTH * this.size;
		guiCornerTopRightSize.y = HEIGHT * this.size;
		
		Vector2f guiCornerBottomLeftPosition = this.guiCornerBottomLeft.getPosition();
		guiCornerBottomLeftPosition.x = -1f + gridX * WIDTH;
		guiCornerBottomLeftPosition.y = -1f + gridY * HEIGHT;
		Vector2f guiCornerBottomLeftSize = this.guiCornerBottomLeft.getSize();
		guiCornerBottomLeftSize.x = WIDTH * this.size;
		guiCornerBottomLeftSize.y = HEIGHT * this.size;
		
		Vector2f guiCornerBottomRightPosition = this.guiCornerBottomRight.getPosition();
		guiCornerBottomRightPosition.x = -1f + gridX * WIDTH + this.gridWidth * WIDTH - WIDTH * this.size;
		guiCornerBottomRightPosition.y = -1f + gridY * HEIGHT;
		Vector2f guiCornerBottomRightSize = this.guiCornerBottomRight.getSize();
		guiCornerBottomRightSize.x = WIDTH * this.size;
		guiCornerBottomRightSize.y = HEIGHT * this.size;
		
		
		Vector2f guiLineTopPosition = this.guiLineTop.getPosition();
		guiLineTopPosition.x = -1f + gridX * WIDTH + WIDTH * this.size;
		guiLineTopPosition.y = -1f + gridY * HEIGHT + this.gridHeight * HEIGHT - HEIGHT * this.size;
		Vector2f guiLineTopSize = this.guiLineTop.getSize();
		guiLineTopSize.x = this.gridWidth * WIDTH - WIDTH * this.size * 2;
		guiLineTopSize.y = HEIGHT * this.size;
		
		Vector2f guiLineBottomPosition = this.guiLineBottom.getPosition();
		guiLineBottomPosition.x = -1f + (gridX + 1) * WIDTH - (1 - this.size) * WIDTH;
		guiLineBottomPosition.y = -1f + gridY * HEIGHT;
		Vector2f guiLineBottomSize = this.guiLineBottom.getSize();
		guiLineBottomSize.x = this.gridWidth * WIDTH - WIDTH * this.size * 2;
		guiLineBottomSize.y = HEIGHT * this.size;
		
		Vector2f guiLineLeftPosition = this.guiLineLeft.getPosition();
		guiLineLeftPosition.x = -1f + gridX * WIDTH;
		guiLineLeftPosition.y = -1f + gridY * HEIGHT + HEIGHT * this.size;
		Vector2f guiLineLeftSize = this.guiLineLeft.getSize();
		guiLineLeftSize.x = WIDTH * this.size;
		guiLineLeftSize.y = this.gridHeight * HEIGHT - HEIGHT * this.size * 2;
		
		Vector2f guiLineRightPosition = this.guiLineRight.getPosition();
		guiLineRightPosition.x = -1f + gridX * WIDTH + this.gridWidth * WIDTH - WIDTH * this.size;
		guiLineRightPosition.y = -1f + gridY * HEIGHT + HEIGHT * this.size;
		Vector2f guiLineRightSize = this.guiLineRight.getSize();
		guiLineRightSize.x = WIDTH * this.size;
		guiLineRightSize.y = this.gridHeight * HEIGHT - HEIGHT * this.size * 2;
		
		
		Vector2f guiBackgroundPosition = this.guiBackground.getPosition();
		guiBackgroundPosition.x = -1f + (gridX + 1) * WIDTH - (1 - this.size) * WIDTH;
		guiBackgroundPosition.y = -1f + gridY * HEIGHT + HEIGHT * this.size;
		Vector2f guiBackgroundSize = this.guiBackground.getSize();
		guiBackgroundSize.x = this.gridWidth * WIDTH - WIDTH * this.size * 2;
		guiBackgroundSize.y = this.gridHeight * HEIGHT - HEIGHT * this.size * 2;
		
	}
	
	@Override
	public void update() {
		super.update();
	}
	
	@Override
	public void render() {
		
		if(!this.visible) {
			return;
		}
		
		super.render();
		
		guiRenderer.getGuiShader().startProgram();
		guiRenderer.getGuiShader().loadCanvas(fullCanvas);
		
		guiRenderer.getGuiShader().loadTranslation(this.guiCornerTopLeft.getPosition());
		guiRenderer.getGuiShader().loadSize(this.guiCornerTopLeft.getSize());
		guiRenderer.render(this.guiCornerTopLeft);
		
		guiRenderer.getGuiShader().loadTranslation(this.guiCornerTopRight.getPosition());
		guiRenderer.getGuiShader().loadSize(this.guiCornerTopRight.getSize());
		guiRenderer.render(this.guiCornerTopRight);
		
		guiRenderer.getGuiShader().loadTranslation(this.guiCornerBottomLeft.getPosition());
		guiRenderer.getGuiShader().loadSize(this.guiCornerBottomLeft.getSize());
		guiRenderer.render(this.guiCornerBottomLeft);
		
		guiRenderer.getGuiShader().loadTranslation(this.guiCornerBottomRight.getPosition());
		guiRenderer.getGuiShader().loadSize(this.guiCornerBottomRight.getSize());
		guiRenderer.render(this.guiCornerBottomRight);
		
		
		
		guiRenderer.getGuiShader().loadTranslation(this.guiLineTop.getPosition());
		guiRenderer.getGuiShader().loadSize(this.guiLineTop.getSize());
		guiRenderer.render(this.guiLineTop);
		
		guiRenderer.getGuiShader().loadTranslation(this.guiLineBottom.getPosition());
		guiRenderer.getGuiShader().loadSize(this.guiLineBottom.getSize());
		guiRenderer.render(this.guiLineBottom);
		
		guiRenderer.getGuiShader().loadTranslation(this.guiLineLeft.getPosition());
		guiRenderer.getGuiShader().loadSize(this.guiLineLeft.getSize());
		guiRenderer.render(this.guiLineLeft);
		
		guiRenderer.getGuiShader().loadTranslation(this.guiLineRight.getPosition());
		guiRenderer.getGuiShader().loadSize(this.guiLineRight.getSize());
		guiRenderer.render(this.guiLineRight);
		
		
		
		guiRenderer.getGuiShader().loadTranslation(this.guiBackground.getPosition());
		guiRenderer.getGuiShader().loadSize(this.guiBackground.getSize());
		guiRenderer.render(this.guiBackground);
		
		guiRenderer.getGuiShader().stopProgram();
	}
	
	public static void loadTextures(Loader loader) {
		textureGuiCorner 			= loader.loadTexture("guiCorner");
		textureGuiLineHorizontal	= loader.loadTexture("guiLineHorizontal");
		textureGuiLineVertical 		= loader.loadTexture("guiLineVertical");
		textureGuiBackground 		= loader.loadTexture("guiBackground");
	}

}
