package Game;

import FrameWork.GameControl;
import FrameWork.GameObject;
import FrameWork.PlayState;
import FrameWork.Sprite;
import UserInterface.Ventana;
import UserInterface.Text;

public class MainMenu extends PlayState {

	Text MonedaText; 
	
	
	public MainMenu(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	public void Start()
	{
		GameObject backGroundMenu = new GameObject(0,0, new Sprite("Assets/Images/BackgroundMenu.png"));
		backGroundMenu.depth = 100;
		GameControl.getGameControl().addToGame(false, true, false, backGroundMenu);
		
		ButtonOpcion botonOpcion = new ButtonOpcion(0,0, new Sprite("Assets/Images/BotonOptionsIdle1.png"), new Sprite("Assets/Images/BotonOptionsPressed1.png"));
		botonOpcion.x = 582;
		botonOpcion.y = 137 -botonOpcion.height;
		GameControl.getGameControl().addToGame(true, true, false, botonOpcion);
		
		ButtonPlay botonPlay = new ButtonPlay(0,0, new Sprite("Assets/Images/BotonPlayIdle1.png"), new Sprite("Assets/Images/BotonPlayPressed1.png"));
		botonPlay.x = 292;
		botonPlay.y = 152 ;
		GameControl.getGameControl().addToGame(true, true, false, botonPlay);

		Ventana ventana = new Ventana(100,100, new Sprite("Assets/Images/Ventana.png"));
		ventana.x = 800/2 - ventana.width/2;
		ventana.y = 480/2 - ventana.height/2;
		
		ButtonSalir botonSalir = new ButtonSalir(0,0, new Sprite("Assets/Images/BotonSalirIdle.png"), new Sprite("Assets/Images/BotonSalirPressed.png"));
		botonSalir.x = ventana.x + ventana.width - botonSalir.width - 10;
		botonSalir.y = ventana.y + 10;
		GameControl.getGameControl().addToGame(botonSalir);
		
		ventana.addButtonDesactivar(botonOpcion);
		ventana.addButtonDesactivar(botonPlay);
		ventana.addButtonActivar(botonSalir);

		botonOpcion.ventana = ventana;
		botonSalir.ventana = ventana;
		
		GameControl.getGameControl().addToGame(ventana);
		
		MonedaText = new Text(20,40,"Monedas: 0",20);   
		MonedaText.text = "Monedas: "+ Game.Register.Monedas;
		GameControl.getGameControl().addToGame(true, true, false, MonedaText);
	}
}
