package gui;

public interface GuiButtonListener {
	
	public abstract void pressed();
	
	public abstract void entered();
	
	public abstract void held();
	
	public abstract void hovered();
	
	public abstract void exited();
	
	public abstract void released();

}
