package FrameWork;

import Demo.DemoMainMenu;
import Game.MainMenu;
import Profiler.ProfilerObject;


/**
 * Esta es la clase principal donde se creara el juego, los atributos que tiene esta clase
 * seran los escenciales para que el juego se cree en si mismo.
 */
public class Main
{
	public static void main(String[] args) 
	{
		//runIntegrationTest(11);
		
	  	int initWindowWidth = 800;
	 
		int initWindowHeight = 480;
		
		View view = new View(initWindowWidth,initWindowHeight, "Game");
		
		Model.getModel().RegistrarView(view);
		GameControl.getGameControl().views.add(view);
		
		Controller controller = new Controller();

		GameControl.getGameControl().controller = controller;
		GameControl.getGameControl().ChangeInputs(new KeyBoardControl());
		GameControl.getGameControl().ChangeInputs(new MouseControl());
		GameControl.getGameControl().frameRate = 60;
		
		new ProfilerObject(300,300);
		
		GameControl.getGameControl().changePlayState(new MainMenu("MainMenu"));
	}
	


}