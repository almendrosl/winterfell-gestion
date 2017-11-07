package Demo;

import FrameWork.GameControl;
import FrameWork.GameObject;
import FrameWork.Sprite;

public class Cactus extends GameObject{

	public float speed = 400;
	
	public Cactus() {
		super(480, 800, new Sprite("Assets/Images/Demo/Cactus.png"));
		// TODO Auto-generated constructor stub
		x = 800;
		y = 480 - 81 - height;
	}
	
	public void Update()
	{
		x -= speed * GameControl.getGameControl().getDeltaTime();
		
		if(x < 0 - width -40)
			GameControl.getGameControl().removeFromGame(this);
	}

}
