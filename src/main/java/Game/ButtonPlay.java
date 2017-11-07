package Game;

import FrameWork.GameControl;
import FrameWork.Sprite;
import UserInterface.Button;

public class ButtonPlay extends Button{

	public ButtonPlay(int x, int y, Sprite spriteIdle, Sprite spritePressed) {
		super(x, y, spriteIdle, spritePressed);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void OnRelease()
	{
		GameControl.getGameControl().changePlayState(new InGame("InGame"));
	}
}
