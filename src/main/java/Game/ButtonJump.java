package Game;

import FrameWork.Sprite;
import UserInterface.Button;

public class ButtonJump extends Button {

	public Character character;
	
	public ButtonJump(int x, int y, Sprite spriteIdle, Sprite spritePressed) {
		super(x, y, spriteIdle, spritePressed);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void OnRelease(){
		character.Jump();
	}
}
