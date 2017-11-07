package Demo;

import FrameWork.GameControl;
import FrameWork.GameObject;
import FrameWork.Sprite;

public class casa1 extends GameObject{
	
	public float speed = 400;
	
	public casa1() {
		super(480, 800, new Sprite("Assets/Images/Demo/casa1.png"));
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
