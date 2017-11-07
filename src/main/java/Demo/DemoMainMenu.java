package Demo;

import FrameWork.GameControl;
import FrameWork.PlayState;
import FrameWork.Sprite;
import UserInterface.Text;

public class DemoMainMenu extends PlayState {

	public DemoMainMenu(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	Text Scoretext;
	
	public void Start()
	{
		BotonPlay boton = new BotonPlay(0,0, new Sprite("Assets/Images/Demo/BotonPlayIdle.png"), new Sprite("Assets/Images/Demo/BotonPlayPressed.png"));
		boton.x = 800/2 - boton.width/2;
		boton.y = 480/2 - boton.height/2;
		
		Scoretext = new Text(800/2, 100,"HighScore: " + Register.HighScore,30);
		Scoretext.x -= 100;
		GameControl.getGameControl().addToGame(true, true, false, Scoretext);
		GameControl.getGameControl().addToGame(true, true, false, boton);
	}
}
