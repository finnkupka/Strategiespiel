package gui;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

import text.Font;
import text.Text;

public class GuiLabel extends GuiComponent {
	
	public static final int TEXT_ALIGN_TOP 		= 1;
	public static final int TEXT_ALIGN_CENTER 	= 2;
	public static final int TEXT_ALIGN_BOTTOM 	= 3;
	
	protected Text text;
	
	protected StringBuilder string;
	protected int positioning;
	protected float size;
	protected Vector3f color;
	
	public GuiLabel(float gridX, float gridY, float gridWidth, float gridHeight, String text, int positioning, float size, Vector3f color) {
		super(gridX, gridY, gridWidth, gridHeight);
		
		this.string = new StringBuilder(text);
		this.positioning = positioning;
		this.size = size;
		
		this.color = color;
		
		this.initialize();
	}
	
	@Override
	public void initialize() {
		if(this.positioning == GuiLabel.TEXT_ALIGN_TOP) {
			this.text = new Text(this.string.toString(), new Font("centaur", "centaur", loader), loader, this.color, new Vector2f(), this.size / 9f, this.gridWidth);
		} else if(this.positioning == GuiLabel.TEXT_ALIGN_CENTER) {
			this.text = new Text(this.string.toString(), new Font("centaur", "centaur", loader), loader, this.color, new Vector2f(), this.size / 9f, this.gridWidth);
		} else if(this.positioning == GuiLabel.TEXT_ALIGN_BOTTOM) {
			this.text = new Text(this.string.toString(), new Font("centaur", "centaur", loader), loader, this.color, new Vector2f(), this.size / 9f, this.gridWidth);
		}
		
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
		
		Vector2f textPosition = this.text.getPosition();
		
		if(this.positioning == GuiLabel.TEXT_ALIGN_TOP) {
			textPosition.x = -1f + gridX * WIDTH;
			textPosition.y = -1f + (gridY + 1) * HEIGHT;
		} else if(this.positioning == GuiLabel.TEXT_ALIGN_CENTER) {
			textPosition.x = -1f + gridX * WIDTH;
			textPosition.y = -1f + (gridY + ((this.size - 1) * 0.5f) + 1) * HEIGHT;
		} else if(this.positioning == GuiLabel.TEXT_ALIGN_BOTTOM) {
			textPosition.x = -1f + gridX * WIDTH;
			textPosition.y = -1f + (gridY + (this.size)) * HEIGHT;
		}
		
		this.text.setSize(this.size / 9f);
		
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
		
		textRenderer.getTextShader().startProgram();
		textRenderer.getTextShader().loadTranslation(this.text.getPosition());
		textRenderer.getTextShader().loadSize(this.text.getSize());
		textRenderer.getTextShader().loadTextColor(this.text.getColor());
		textRenderer.getTextShader().loadCanvas(fullCanvas);
		textRenderer.render(this.text);
		textRenderer.getTextShader().stopProgram();
	}
	
	public Text getText() {
		return this.text;
	}
	
	public String getString() {
		return this.text.getString();
	}

}
