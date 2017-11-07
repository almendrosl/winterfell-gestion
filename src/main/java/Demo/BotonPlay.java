package Demo;

import FrameWork.GameControl;
import FrameWork.Sprite;
import UserInterface.Button;

public class BotonPlay extends Button{

	public BotonPlay(int x, int y, Sprite spriteIdle, Sprite spritePressed) {
		super(x, y, spriteIdle, spritePressed);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void OnRelease()
	{
		GameControl.getGameControl().changePlayState(new DemoPlayState("PlayState"));
	}
}
