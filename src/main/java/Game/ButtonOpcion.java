package Game;

import FrameWork.GameControl;
import FrameWork.Sprite;
import UserInterface.Button;
import UserInterface.Ventana;

public class ButtonOpcion extends Button{

	public Ventana ventana;
	
	public ButtonOpcion(int x, int y, Sprite spriteIdle, Sprite spritePressed) {
		super(x, y, spriteIdle, spritePressed);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void OnRelease()
	{
		ventana.PopUp();
	}
}
