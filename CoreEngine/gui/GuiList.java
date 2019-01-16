package gui;

import java.util.ArrayList;
import java.util.List;

import loader.Loader;

import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

import text.Text;
import texture.TextureMap;

public class GuiList extends GuiComponent {
	
	private static TextureMap textureGuiBackground;
	
	private Gui guiBackground;
	
	private GuiVerticalSlider guiVerticalSlider;
	
	private float size;
	
	public List<String> texts;
	public List<GuiLabel> guiLabels;

	public GuiList(float gridX, float gridY, float gridWidth, float gridHeight, float size) {
		super(gridX, gridY, gridWidth, gridHeight);
		
		this.guiVerticalSlider = new GuiVerticalSlider(gridWidth - 0.5f, 0, 0.5f, gridHeight);
		this.guiVerticalSlider.setScrolling(1);
		this.add(this.guiVerticalSlider);
		
		this.size = size;
		//this.scrolling = 0f;
		
		this.texts = new ArrayList<String>();
		this.guiLabels = new ArrayList<GuiLabel>();
		
		this.initialize();
	}
	
	public void add(String text) {
		this.add(text, new Vector3f(0.13f, 0.78f, 0f));
	}
	
	public void add(String text, Vector3f color) {
		
		int numberLines = 0;
		for(String string : this.texts) {
			numberLines++;
			for(char character : string.toCharArray()) {
				if(character == '\n') {
					numberLines++;
				}
			}
		}
		
		GuiLabel label = new GuiLabel(1, this.gridHeight - numberLines * this.size, this.gridWidth - 0.5f, 1, text, GuiLabel.TEXT_ALIGN_TOP, size, color);
		label.setGridX(0);
		label.setGridY(label.getGridY() - 1);
		label.initialize();
		label.setParent(this);
		label.reposition();
		
		this.guiLabels.add(label);
		this.texts.add(label.getString());
	}
	
	@Override
	public void initialize() {
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
		
		Vector2f guiBackgroundPosition = this.guiBackground.getPosition();
		guiBackgroundPosition.x = -1f + gridX * WIDTH;
		guiBackgroundPosition.y = -1f + gridY * HEIGHT;
		Vector2f guiBackgroundSize = this.guiBackground.getSize();
		guiBackgroundSize.x = this.gridWidth * WIDTH;
		guiBackgroundSize.y = this.gridHeight * HEIGHT;
		
		for(GuiLabel guiLabel : this.guiLabels) {
			
			guiLabel.reposition();
			
		}
		
	}
	
	@Override
	public void update() {
		super.update();
		
		float mouseDWheel = -Mouse.getDWheel() * 0.00025f;
	}
	
	@Override
	public void render() {
		
		if(!this.visible) {
			return;
		}
		
		super.render();
		
		float gridX = this.gridX;
		float gridY = this.gridY;
		
		if(this.parent != null) {
			
			gridX += this.parent.getGridX();
			gridY += this.parent.getGridY();
			
		}
		
		Vector4f canvas = new Vector4f(-1f + gridX * WIDTH, -1f + gridY * HEIGHT, this.gridWidth * WIDTH, this.gridHeight * HEIGHT);
		
		textRenderer.getTextShader().startProgram();
		textRenderer.getTextShader().loadCanvas(canvas);
		
		float scrolling = 0;
		
		float length = this.getListLength();
		
		if(length > this.gridHeight) {
			scrolling = (length - this.gridHeight) * (1 - this.guiVerticalSlider.getScrolling()) / ROWS * 2;
		}
		
		for(GuiLabel guiLabel : this.guiLabels) {
			Text text = guiLabel.getText();
			
			int numberOfLines = 1;
			
			for(char c : text.getString().toCharArray()) {
				if(c == '\n') {
					numberOfLines += 1;
				}
			}
			
			if(		guiLabel.gridY - numberOfLines * this.size + (scrolling / 2 * ROWS) > this.gridHeight - 1 ||
					guiLabel.gridY + (scrolling / 2 * ROWS) + 1 < 0) {
				
				continue;
				
			}
			
			Vector2f scrolledPosition = Vector2f.add(text.getPosition(), new Vector2f(0, scrolling), null);
			
			textRenderer.getTextShader().loadTranslation(scrolledPosition);
			textRenderer.getTextShader().loadSize(text.getSize());
			textRenderer.getTextShader().loadTextColor(text.getColor());
			textRenderer.render(text);
		}
		
		textRenderer.getTextShader().stopProgram();
		
		guiRenderer.getGuiShader().startProgram();
		guiRenderer.getGuiShader().loadCanvas(canvas);
		
		guiRenderer.getGuiShader().loadTranslation(this.guiBackground.getPosition());
		guiRenderer.getGuiShader().loadSize(this.guiBackground.getSize());
		guiRenderer.render(this.guiBackground);
		
		guiRenderer.getGuiShader().stopProgram();
	}
	
	private float getListLength() {
		float length = 0;
		
		for(String text : this.texts) {
			length++;
			for(char c : text.toCharArray()) {
				if(c == '\n') {
					length++;
				}
			}
		}
		
		length *= this.size;
		
		return length;
	}
	
	public static void loadTexture(Loader loader) {
		textureGuiBackground = loader.loadTexture("guiTextField");
	}

}
