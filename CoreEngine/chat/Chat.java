package chat;

import gui.Gui;
import gui.GuiActionListener;
import gui.GuiBox;
import gui.GuiButton;
import gui.GuiButtonListener;
import gui.GuiHorizontalSlider;
import gui.GuiLabel;
import gui.GuiList;
import gui.GuiRenderer;
import gui.GuiVerticalSlider;
import gui.GuiTextField;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import loader.Loader;
import main.Main;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

import text.Text;
import text.TextRenderer;

public class Chat {
	
	private GuiTextField guiChatTextField;
	private GuiList guiChatList;
	private GuiLabel guiChatTitle;
	
	private GuiBox guiChatBox1;
	private GuiBox guiChatBox2;
	
	private GuiButton guiChatButton;
	
	private GuiBox guiMenueBox1;
	private GuiLabel guiMenueText1;
	private GuiLabel guiMenueText2;
	private GuiLabel guiMenueText3;
	private GuiLabel guiMenueText4;
	private GuiLabel guiMenueText5;
	//private GuiButton guiMenueButton1;
	//private GuiButton guiMenueButton2;
	private GuiButton guiMenueButton3;
	private GuiButton guiMenueButton4;
	private GuiButton guiMenueButton5;
	
	private GuiHorizontalSlider sliderR;
	private GuiHorizontalSlider sliderG;
	private GuiHorizontalSlider sliderB;
	
	private GuiBox guiTopPanelBox;
	private GuiButton guiTopPanelButton1;
	private GuiButton guiTopPanelButton2;
	
	private GuiBox guiInfoBox;
	private GuiLabel guiInfoLabel;
	
	private Gui DEBUG;
	
	public void setSliders(float r, float g, float b) {
		this.sliderR.setScrolling(r);
		this.sliderG.setScrolling(g);
		this.sliderB.setScrolling(b);
		this.sliderR.reposition();
		this.sliderG.reposition();
		this.sliderB.reposition();
	}
	
	public Chat(Loader loader/*, Client client*/) {
		
		this.guiInfoBox = new GuiBox(0, 0, 3, 2, 0.3f);
		this.guiInfoLabel = new GuiLabel(0.5f, 0.5f, 3, 2, "Send", GuiLabel.TEXT_ALIGN_CENTER, 1.0f, new Vector3f(1f, 0.8f, 0.2f));
		this.guiInfoBox.add(this.guiInfoLabel);
		this.guiInfoBox.setVisible(false);
		
		this.guiChatBox1 = new GuiBox(0, 0, 17, 2, 0.5f);
		
		this.guiChatBox2 = new GuiBox(0, 1.5f, 7, 8.5f, 0.5f);
		this.guiChatTextField = new GuiTextField(0.5f, 0.5f, 15.5f, 1, GuiTextField.TEXT_ALIGN_TOP, 0.5f, new Vector3f(1f, 0.6f, 0f));
		this.guiChatList = new GuiList(0.5f, 0.5f, 6.0f, 7.5f, 0.4f);
		this.guiChatBox2.add(this.guiChatList);
		this.guiChatList.add("Wilkommen im Chat!\nBitte verhalte dich angemessen\n!!!", new Vector3f(0.255f, 0.412f, 0.882f));
		
		this.guiChatButton = new GuiButton(15.5f, 0.5f, 1, 1);
		this.guiChatButton.add(new GuiButtonListener() {
			@Override
			public void pressed() {
				Random r = new Random();
				String string = guiChatTextField.getString().replace("\n", "");
				guiChatList.add("Du: " + string, new Vector3f(r.nextFloat(), r.nextFloat(), r.nextFloat()));
				while(!guiChatTextField.getText().getString().equals("")) {
					guiChatTextField.getText().delete(1, loader);
				}
				
			}

			@Override
			public void hovered() {
				float gridX = (float)Mouse.getX() / Display.getWidth() * 32f;
				float gridY = (float)Mouse.getY() / Display.getHeight() * 18f;
				
				guiInfoBox.setGridX(gridX);
				guiInfoBox.setGridY(gridY);
				
				guiInfoBox.reposition();
			}

			@Override
			public void released() {
				
				guiInfoBox.setVisible(false);
				
			}

			@Override
			public void entered() {
				guiInfoBox.setVisible(true);
				float gridX = (float)Mouse.getX() / Display.getWidth() * 32f + 1;
				float gridY = (float)Mouse.getY() / Display.getHeight() * 18f + 1;
				
				guiInfoBox.setGridX(gridX);
				guiInfoBox.setGridY(gridY);
				
				guiInfoBox.reposition();
			}

			@Override
			public void held() {
				
			}

			@Override
			public void exited() {
				guiInfoBox.setVisible(false);
			}
		});
		
		this.guiChatTitle = new GuiLabel(0.5f, 8, 10, 1, "Chat", GuiLabel.TEXT_ALIGN_BOTTOM, 0.5f, new Vector3f(1f, 0.8f, 0.2f));
		
		this.guiChatBox1.add(this.guiChatBox2);
		
		this.guiChatBox1.add(this.guiChatButton);
		this.guiChatBox1.add(this.guiChatTextField);

		this.guiChatBox2.add(this.guiChatTitle);
		
		this.guiMenueBox1 = new GuiBox(23, 0, 9, 11, 0.5f);
		this.guiMenueText1 = new GuiLabel(1, 9, 5, 1, "Random Color", GuiLabel.TEXT_ALIGN_CENTER, 0.5f, new Vector3f(1f,1f,1f));
		this.guiMenueText2 = new GuiLabel(1, 8, 5, 1, "Random Color", GuiLabel.TEXT_ALIGN_CENTER, 0.5f, new Vector3f(1f,1f,1f));
		this.guiMenueText3 = new GuiLabel(1, 7, 5, 1, "Random Color", GuiLabel.TEXT_ALIGN_CENTER, 0.5f, new Vector3f(1f,1f,1f));
		this.guiMenueText4 = new GuiLabel(1, 6, 5, 1, "Random Color", GuiLabel.TEXT_ALIGN_CENTER, 0.5f, new Vector3f(1f,1f,1f));
		this.guiMenueText5 = new GuiLabel(1, 5, 5, 1, "Slider Color", GuiLabel.TEXT_ALIGN_CENTER, 0.5f, new Vector3f(1f,1f,1f));
		this.guiMenueText5.add(new GuiActionListener() {

			@Override
			public void actionPerformed() {
				guiMenueText5.getText().setColor(new Vector3f(sliderR.getScrolling(), sliderG.getScrolling(), sliderB.getScrolling()));
			}
			
		});
		
		//this.guiMenueButton1 = new GuiButton(6, 1, 2, 1);
		//this.guiMenueButton2 = new GuiButton(6, 3, 2, 1);
		this.sliderR = new GuiHorizontalSlider(1.5f, 3, 6, 0.5f);
		this.sliderG = new GuiHorizontalSlider(1.5f, 2, 6, 0.5f);
		this.sliderB = new GuiHorizontalSlider(1.5f, 1, 6, 0.5f);
		
		this.guiMenueBox1.add(this.sliderR);
		this.guiMenueBox1.add(this.sliderG);
		this.guiMenueBox1.add(this.sliderB);
		
		this.guiMenueButton3 = new GuiButton(7, 5, 1, 1);
		this.guiMenueButton3.add(new GuiButtonListener() {
			@Override
			public void pressed() {
				
			}
			
			@Override
			public void hovered() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void released() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void entered() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void held() {
				guiMenueText1.setGridY(guiMenueText1.getGridY()- 0.05f);
				guiMenueText1.reposition();
			}

			@Override
			public void exited() {
				// TODO Auto-generated method stub
				
			}
		});
		this.guiMenueButton4 = new GuiButton(7, 7, 1, 1);
		this.guiMenueButton4.add(new GuiButtonListener() {
			@Override
			public void pressed() {
				
			}
			
			@Override
			public void hovered() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void released() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void entered() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void held() {
				guiMenueText1.setGridY(guiMenueText1.getGridY() + 0.05f);
				guiMenueText1.reposition();
			}

			@Override
			public void exited() {
				// TODO Auto-generated method stub
				
			}
		});
		this.guiMenueButton5 = new GuiButton(7, 9, 1, 1);
		this.guiMenueButton5.add(new GuiButtonListener() {
			@Override
			public void pressed() {
				Random random = new Random();
				guiMenueText1.getText().setColor(new Vector3f(random.nextFloat(), random.nextFloat(), random.nextFloat()));
				guiMenueText2.getText().setColor(new Vector3f(random.nextFloat(), random.nextFloat(), random.nextFloat()));
				guiMenueText3.getText().setColor(new Vector3f(random.nextFloat(), random.nextFloat(), random.nextFloat()));
				guiMenueText4.getText().setColor(new Vector3f(random.nextFloat(), random.nextFloat(), random.nextFloat()));
				//guiMenueText5.getText().setColor(new Vector3f(random.nextFloat(), random.nextFloat(), random.nextFloat()));
			}
			
			@Override
			public void hovered() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void released() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void entered() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void held() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void exited() {
				// TODO Auto-generated method stub
				
			}
		});
		this.guiMenueBox1.add(this.guiMenueText1);
		this.guiMenueBox1.add(this.guiMenueText2);
		this.guiMenueBox1.add(this.guiMenueText3);
		this.guiMenueBox1.add(this.guiMenueText4);
		this.guiMenueBox1.add(this.guiMenueText5);
		//this.guiMenueBox1.add(this.guiMenueButton1);
		//this.guiMenueBox1.add(this.guiMenueButton2);
		this.guiMenueBox1.add(this.guiMenueButton3);
		this.guiMenueBox1.add(this.guiMenueButton4);
		this.guiMenueBox1.add(this.guiMenueButton5);
		
		this.guiTopPanelBox = new GuiBox(28, 15, 4, 3, 0.75f);
		this.guiTopPanelButton1 = new GuiButton(1f, 1f, 1, 1);
		this.guiTopPanelButton1.add(new GuiButtonListener() {
			@Override
			public void pressed() {
				try {
					if(Display.isFullscreen()) {
						Display.setFullscreen(false);
					} else {
						Display.setFullscreen(true);
					}
				} catch(LWJGLException e) {
					e.printStackTrace();
				}
			}
			
			@Override
			public void hovered() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void released() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void entered() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void held() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void exited() {
				// TODO Auto-generated method stub
				
			}
		});
		this.guiTopPanelButton2 = new GuiButton(2f, 1f, 1, 1);
		this.guiTopPanelButton2.add(new GuiButtonListener() {
			@Override
			public void pressed() {
				Main.running = false;
			}
			
			@Override
			public void hovered() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void released() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void entered() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void held() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void exited() {
				// TODO Auto-generated method stub
				
			}
		});
		this.guiTopPanelBox.add(this.guiTopPanelButton1);
		this.guiTopPanelBox.add(this.guiTopPanelButton2);
		
	}
	
	public void update() {
		
		this.guiChatBox1.update();
		
		this.guiMenueBox1.update();
		
		this.guiTopPanelBox.update();
		
		this.guiInfoBox.update();
	}
	
	public void render() {
		
		this.guiInfoBox.render();
		
		this.guiChatBox1.render();
		
		this.guiMenueBox1.render();
		
		this.guiTopPanelBox.render();
		
	}

}
