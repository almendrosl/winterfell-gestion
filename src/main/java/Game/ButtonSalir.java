package Game;

import FrameWork.GameObjectProduct;
import FrameWork.Sprite;
import UserInterface.Button;
import UserInterface.Ventana;

public class ButtonSalir extends Button implements GameObjectProduct{

	public ButtonSalir(int x, int y, Sprite spriteIdle, Sprite spritePressed) {
		super(x, y, spriteIdle, spritePressed);
		// TODO Auto-generated constructor stub
	}

	public Ventana ventana;
	
	@Override
	public void OnRelease()
	{
		ventana.PopDown();
	}

	@Override
	public boolean getVisible() {
		return true;
	}

	@Override
	public boolean getActive() {
		return true;
	}

	@Override
	public boolean getCollides() {
		return false;
	}
}
