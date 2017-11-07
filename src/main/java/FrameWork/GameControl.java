package FrameWork;
import java.util.ArrayList;
import java.util.List;



/**
 * Esta clase sirve para crear un objeto GameControl Singleton statico.
 * Este objeto tendra la capacidad de controlar las variables mas generales del Juego pudiendose
 * acceder desde cualquier PlayState o GameObject.
 */
public class GameControl {

	/**
	 * Nombre del Estadod e Juego actual
	 */
	public PlayState actualPlayState;
	
	/**
	 * FrameRate deseado
	 */
	public float frameRate;
	
	/**
	 * Referencia a la View que le pasa el Main
	 */
	public List<ViewSubject> views;
	
	/**
	 * Referencia al Controller que le pasa el Main
	 */
	public Controller controller;
	
	/**
	 * Objecto Singleton estatico que permite trabajar sobre GameControl en cualquier lugar
	 */
	private static GameControl gameControl = null;

	/**
	 * Constructor Singleton del objeto
	 * @param initPlayState
	 * Nombre del PlayState con el que se va a empezar el juego
	 * @param view
	 * View actual que se esta utilizando
	 */
	protected GameControl()
	{
		views = new ArrayList<ViewSubject>();
	}

	/**
	 * Este es el metodo que lo hace singleton
	 * Tiene que llamarse cada vez que se quiere obtener informacion hacerca del GameControl
	 * @return
	 */
	public static GameControl getGameControl()
	{
	      if(gameControl == null) 
	    	  gameControl = new GameControl();

	      return gameControl;
	}
	
	/**
	 * Hace que el PlayState actual termine de ejecutar su loop y muera luego asignamos
	 * a "actualPlayState" el que pasamos por referencia (Este debe ser un nuevo playState)
	 * usar: GameControl.getGameContol().changePlayState(new MainMenuState) por ejemplo.
	 * @param name
	 * PlayState al cual vamos a ir que recien se creo.
	 */
	public void changePlayState(PlayState playState)
	{
		//Limpiamos la View
		views.get(0).ClearDraw();

		//Limpiamos el Model
		for(int i = 0; i < 8; i++)
		{
			Model.getModel().setStateJustPressed(i,false);
			Model.getModel().setStateJustPressed(i,false);
			Model.getModel().setStateJustPressed(i,false);
		}
			
		//Nos vamos del PlayStateActual
		if(actualPlayState != null)
		{
			actualPlayState.PlayStateOut();

			//Termino la ejecucion de ese Hilo sin problemas
			actualPlayState.runningState = false;
		}
			
		actualPlayState = playState;
		actualPlayState.Start();
		actualPlayState.runningState = true;
		actualPlayState.RegistrarController(controller);
		
		actualPlayState.start();
	}

	/**
	 * Cambia si es necesario los inputs actuales por otros
	 * @param controller
	 * Inputs a los que vamos a ir cuando cambie.
	 */
	public void ChangeInputs(ControllerSubject input)
	{
		View aux;
		input.RegistrarController(controller);
		if(MouseControl.class.isAssignableFrom(input.getClass()))
		{
			aux = ((View)views.get(0));
			aux.ChangeMouseListener((MouseControl)input);
		}
		else if(KeyBoardControl.class.isAssignableFrom(input.getClass()))
		{
			aux = ((View)views.get(0));
			aux.ChangeKeyListener((KeyBoardControl)input);
		}
	}

	
	/**
	 * Sobrecarga que permite seleccionar la View.
	 * Cambia si es necesario los inputs actuales por otros
	 * @param controller
	 * Inputs a los que vamos a ir cuando cambie.
	 */
	public void ChangeInputs(ControllerSubject input, int index)
	{
		View aux;
		input.RegistrarController(controller);
		if(MouseControl.class.isAssignableFrom(input.getClass()))
		{
			aux = ((View)views.get(index));
			aux.ChangeMouseListener((MouseControl)input);
		}
		else if(KeyBoardControl.class.isAssignableFrom(input.getClass()))
		{
			aux = ((View)views.get(index));
			aux.ChangeKeyListener((KeyBoardControl)input);
		}
	}

	/**
	 * Este metodo devuelve la cantidad de tiempo entre este frame y el anterior.
	 * @return
	 * deltaTime
	 */
	public float getDeltaTime()
	{
		return actualPlayState.deltaTime/1000;
	}

	/**
	 * Este metodo devuelve el frameRate en el que se esta corriendo el juego.
	 * @return
	 * frameRate
	 */
	public float getFrameRate()
	{
		return frameRate;
	}
	
	/**
	 * NO LLAMAR EN UN AWAKE DEL PLAYSTATE
	 * Toma un objeto que herede de GameObject o un GameObject y lo agrega
	 * a las listas del juego. Por defecto lo agrega a la view 0.
	 * @param active
	 * Este objeto hace algo en el juego?
	 * @param visible
	 * Este objeto es visible?
	 * @param collition
	 * Este objeto coliciona?
	 * @param gameObject
	 * El GameObject que queremos agregar.
	 */
	public void addToGame(boolean active, boolean visible, boolean collition, GameObject gameObject)
	{
		gameObject.visible = visible;
		gameObject.collides = collition;
		gameObject.active = active;
		
		gameObject.Start();

		if(active)
			GameControl.getGameControl().actualPlayState.RegistrarUpdate(gameObject);
		if(collition)
			GameControl.getGameControl().actualPlayState.RegistrarCollition(gameObject);
		if(visible)
			views.get(0).RegistrarDraw(gameObject);
	}

	/**
	 * NO LLAMAR EN UN AWAKE DEL PLAYSTATE
	 * Toma un objeto que herede de GameObject o un GameObject y lo agrega
	 * a las listas del juego. Llama a la view que se pasa.
	 * @param active
	 * Este objeto hace algo en el juego?
	 * @param visible
	 * Este objeto es visible?
	 * @param collition
	 * Este objeto coliciona?
	 * @param gameObject
	 * El GameObject que queremos agregar.
	 * @param index
	 * Que view vamos a cambiar.
	 */
	public void addToGame(boolean active, boolean visible, boolean collition, GameObject gameObject, String name)
	{
		gameObject.visible = visible;
		gameObject.collides = collition;
		gameObject.active = active;
		
		gameObject.Start();
		
		if(active)
			GameControl.getGameControl().actualPlayState.RegistrarUpdate(gameObject);
		if(collition)
			GameControl.getGameControl().actualPlayState.RegistrarCollition(gameObject);
		if(visible)
		{
			for(int i = 0; i < views.size(); i++)
			{
				View aux = (View)views.get(i);
				if(aux.name == name)
				{
					aux.RegistrarDraw(gameObject);
				}
			}
		}
	}

	/**
	 * NO LLAMAR EN UN AWAKE DEL PLAYSTATE (Solo si implmementa un GameObjectProduct)
	 * Toma un objeto que herede de GameObject o un GameObject y lo agrega
	 * a las listas del juego.
	 * @param gameObject
	 * El GameObject que queremos agregar (solo si implementa un tipo GameObjectProduct)
	 */
	public void addToGame(GameObjectProduct gameObject)
	{
		GameObject aux = (GameObject)gameObject;
		
		aux.visible = gameObject.getVisible();
		aux.collides = gameObject.getCollides();
		aux.active = gameObject.getActive();
		
		aux.Start();

		if(gameObject.getActive())
			GameControl.getGameControl().actualPlayState.RegistrarUpdate(aux);
		if(gameObject.getCollides())
			GameControl.getGameControl().actualPlayState.RegistrarCollition(aux);
		if(gameObject.getVisible())
			views.get(0).RegistrarDraw(aux);
	}
	
	/**
	 * Elimina a este objeto de todas las listas donde puede llegar a estar.
	 * primero lo busca en las listas y luego lo saca si lo encuentra.
	 * @param gameObject
	 * Objecto que se quiere eliminar
	 */
	public void removeFromGame(GameObject gameObject)
	{
		GameControl.getGameControl().actualPlayState.DesregistrarUpdate(gameObject);
		GameControl.getGameControl().actualPlayState.DesregistrarCollition(gameObject);
		
		for(int i = 0; i < views.size(); i++)
			views.get(i).DesregistrarDraw(gameObject);
	}
}
