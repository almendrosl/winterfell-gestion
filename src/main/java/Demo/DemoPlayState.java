package Demo;

import FrameWork.GameControl;
import FrameWork.GameObject;
import FrameWork.Model;
import FrameWork.PlayState;
import FrameWork.Sprite;
import FrameWork.UserActions;
import FrameWork.Util;
import UserInterface.Text;

public class DemoPlayState extends PlayState {

	private float minGenerateTime = 2;
	private float maxGenerateTime = 5;
	private float time = 0;
	private float timeToWait = Util.RandomRange(minGenerateTime, maxGenerateTime);
	int score = 0;
	
	Character character;
	Text Scoretext;
	
	public DemoPlayState(String name) {
		super(name);
		// TODO Auto-generated constructor stub
		
	}

	public void Update()
	{
		if(time > timeToWait)
		{
			GameControl.getGameControl().addToGame(true, true, true, new Cactus());
			time = 0;
			timeToWait = Util.RandomRange(minGenerateTime, maxGenerateTime);
			score++;
		}
		else
		{
			time += GameControl.getGameControl().getDeltaTime();
		}
		
		if(Model.getModel().getStateJustPressed(UserActions.Enter))
		{
			character.Jump();
		}
		
		Scoretext.text = "Score: " + score;
	}
	
	public void Start()
	{
		GameObject backGround = new GameObject(0,0,new Sprite("Assets/Images/Demo/Background.png"));
		backGround.depth = 10;
		character = new Character(0,0,new Sprite("Assets/Images/Demo/Character.png"));
		character.x = 800/2 - character.width/2;
		character.y = 480 - 81 - character.height;
		
		Scoretext = new Text(0,50, "Score: 0",30);
		
		GameControl.getGameControl().addToGame(false, true, false, backGround);
		GameControl.getGameControl().addToGame(true, true, true, character);
		GameControl.getGameControl().addToGame(true, true, true, Scoretext);
	}
	
	public void PlayStateOut()
	{
		if(score > Register.HighScore)
			Register.HighScore = score;
	}
}
