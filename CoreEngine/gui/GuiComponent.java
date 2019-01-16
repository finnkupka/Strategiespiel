package gui;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Vector4f;

import loader.Loader;
import text.TextRenderer;

public abstract class GuiComponent {
	
	protected static GuiRenderer guiRenderer;
	protected static TextRenderer textRenderer;
	
	protected static Loader loader;
	
	protected static final int COLUMNS = 32;
	protected static final int ROWS = 18;
	
	protected static final float WIDTH = 2f / COLUMNS;
	protected static final float HEIGHT = 2f / ROWS;
	
	protected static Vector4f fullCanvas = new Vector4f(-1f, -1f, 2, 2);
	
	protected float gridX;
	protected float gridY;
	protected float gridWidth;
	protected float gridHeight;
	
	protected boolean visible;
	
	protected GuiComponent parent;
	protected List<GuiComponent> children;
	
	protected GuiActionListener guiActionListener;
	
	public GuiComponent(float gridX, float gridY, float gridWidth, float gridHeight) {
		this.parent = null;
		this.children = new ArrayList<GuiComponent>();
		
		this.guiActionListener = null;
		
		this.gridX = gridX;
		this.gridY = gridY;
		this.gridWidth = gridWidth;
		this.gridHeight = gridHeight;
		
		this.visible = true;
		
	}
	
	public abstract void initialize();
	
	public void reposition() {
		for(GuiComponent guiComponent : this.children) {
			guiComponent.reposition();
		}
	}
	
	public  void update() {
		if(this.guiActionListener != null) {
			this.guiActionListener.actionPerformed();
		}
		for(GuiComponent guiComponent : this.children) {
			guiComponent.update();
		}
	}
	
	public void add(GuiComponent guiComponent) {
		this.children.add(guiComponent);
		guiComponent.setParent(this);
		guiComponent.reposition();
	}
	
	public void add(GuiActionListener guiActionListener) {
		this.guiActionListener = guiActionListener;
	}
	
	public void render() {
		if(!visible) {
			return;
		}
		for(GuiComponent guiComponent : this.children) {
			guiComponent.render();
		}
	}
	
	public float getGridX() {
		if(this.parent != null) {
			return this.gridX + this.parent.getGridX();
		} else {
			return this.gridX;
		}
	}
	
	public float getGridY() {
		if(this.parent != null) {
			return this.gridY + this.parent.getGridY();
		} else {
			return this.gridY;
		}
	}
	
	public float getGridWidth() {
		return this.gridWidth;
	}
	
	public float getGridHeight() {
		return this.gridHeight;
	}
	
	public boolean isVisible() {
		return this.visible;
	}
	
	public void setGridX(float gridX) {
		if(this.parent != null) {
			this.gridX = gridX + this.parent.getGridX();
		} else {
			this.gridX = gridX;
		}
	}
	
	public void setGridY(float gridY) {
		if(this.parent != null) {
			this.gridY = gridY + this.parent.getGridY();
		} else {
			this.gridY = gridY;
		}
	}
	
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	public GuiComponent getParent() {
		return this.parent;
	}
	
	public void setParent(GuiComponent parent) {
		this.parent = parent;
	}
	
	public static void setRenderers(GuiRenderer g, TextRenderer t) {
		guiRenderer = g;
		textRenderer = t;
	}
	
	public static void setLoader(Loader l) {
		loader = l;
	}

}
