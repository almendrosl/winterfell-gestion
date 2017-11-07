package UserInterface;

import FrameWork.GameObject;
import FrameWork.Sprite;

public class UserInterface extends GameObject{

	public UserInterface(int x, int y, Sprite sprite) {
		super(x, y, sprite);
		// TODO Auto-generated constructor stub
		depth = -1000;
	}
	
	public UserInterface(int x, int y, int width, int height)
	{
		super(x,y,width,height);
	}
}
