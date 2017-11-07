package FrameWork;
import java.util.ArrayList;
import java.util.List;

/**
 * Es un Singleton estatico que puede ser accedido desde cualquier clase por tener los inputs.
 * Base de la Arquitectura MVC
 * Recibe llamadas del Controller y notifica a las Views
 * Ademas guarda las variables booleanas de los imputs.
 */
public class Model implements ModelSubject{

	/* Guarda todas las Views que se registraron para poder notificarlas */
	protected static List<ViewObserver>  viewObservers;
	
	/**
	 * Arreglo que guarda los estados de las teclas, si estan apretadas (True)
	 * o levantadas (False).
	 */
	public boolean[] userActionsState;

	/**
	 * Arreglo que guarda todos los booleanos de los inputs seguin si se estan apretando
	 */
	public boolean[] userActionsPressed;

	/**
	 * Arreglo que guarda todos los booleanos de los inputs seguin si se acaban de apretar
	 */
	public boolean[] userActionsJustPressed;

	/**
	 * Arreglo que guarda todos los booleanos de los inputs seguin si se acaban de soltar
	 */
	public boolean[] userActionsJustReleased;
	
	/**
	 * Mouse Position x
	 */
	public int X;

	/**
	 * Mouse Position y
	 */
	public int Y;

	/**
	 * Objecto que crearemos para usar el Singleton
	 */
	private static Model modelControl = null;
	
	/**
	 * Constructor Singleton de la clase Model
	 */
	protected Model()
	{
		viewObservers = new ArrayList<ViewObserver>();
		userActionsState = new boolean[] {false, false, false, false, false, false, false, false, false, false, false, false};
		userActionsPressed = new boolean[] {false, false, false, false, false, false, false, false, false, false, false, false};
		userActionsJustPressed = new boolean[] {false, false, false, false, false, false, false, false, false, false, false, false};
		userActionsJustReleased = new boolean[] {false, false, false, false, false, false, false, false, false, false, false, false};
		}
	
	/**
	 * Esta funcion nos permite llamar al Model Singleton desde cualquier lugar
	 * para usar el objeto modelControl.
	 * @return
	 * Objecto ModelControl.
	 */
	public static Model getModel()
	{
	      if(modelControl == null) 
	    	  modelControl = new Model();

	      return modelControl;
	}
	
	@Override
	public void RegistrarView(ViewObserver obs) {
		viewObservers.add(obs);
	}

	@Override
	public void DesregistrarView(ViewObserver obs) {
		viewObservers.remove(obs);
	}

	@Override
	public void NotifyView() {
		for(int i = 0; i < viewObservers.size(); i++)
			viewObservers.get(i).Update();
	}

	/**
	 * Nos dice si una accion de usuario esta siendo apretada.
	 * @param action
	 * Que input se quiere verificar
	 * @return
	 * Condicion en la que esta ese input
	 */
	public boolean getStatePressed(int action)
	{
		return userActionsPressed[action];
	}

	/**
	 * Nos dice si una accion de usuario se acaba de apretar.
	 * @param action
	 * Que input se quiere verificar
	 * @return
	 * Condicion en la que esta ese input
	 */
	public boolean getStateJustPressed(int action)
	{
		return userActionsJustPressed[action];
	}

	/**
	 * Nos dice si una accion de usuario se acaba de soltar.
	 * @param action
	 * Que input se quiere verificar
	 * @return
	 * Condicion en la que esta ese input
	 */
	public boolean getStateJustReleased(int action)
	{
		return userActionsJustReleased[action];
	}
	
	/**
	 * Nos permite setear el estado de una accion si la acaban de apretar
	 * @param action
	 * Que input vamos a setear
	 * @param condition
	 * En que estado lo vamos a setear
	 */
	public void setStateJustPressed(int action, boolean condition)
	{
		userActionsState[action] = condition;
	}
	
	/**
	 * Si hay un mouse y este se mueve se encarga de setear MouseX y MouseY llamando este
	 * metodo.
	 */
	public void setActionPosition(int x, int y)
	{
		X = x;
		Y = y;
	}
	
	/**
	 * Es llamada al comienzo del GameLoop para saber cuales son las teclas que tendremos
	 * en ese momento y coordinar los "JustPreesed" asi como "JustReleased"
	 */
	public void setInputs()
	{
		for(int i = 0; i < userActionsPressed.length; i++)
		{
			//La tecla esta apretada y el estado de Pressed era falso
			if(userActionsState[i] && !userActionsPressed[i])
			{
				//Se acaba de Apretar una tecla.
				userActionsJustPressed[i] = true;
				userActionsPressed[i] = true;
			}
			//La tecla esta apretada y el estado de Pressed era verdadero
			else if(userActionsState[i] && userActionsPressed[i])
			{
				//Se mantuvo apretada la tecla
				userActionsJustPressed[i] = false;
			}
			//La tecla esta suelta, pero el estado de pressed era verdadero
			else if(!userActionsState[i] && userActionsPressed[i])
			{
				//Se acaba de Soltar la tecla
				userActionsPressed[i] = false;
				userActionsJustReleased[i] = true;
			}
			//La tecla esta suelta, pero el estado de pressed era falso
			else if(!userActionsState[i] && !userActionsPressed[i])
			{
				//Se mantuvo suelta la tecla
				userActionsJustReleased[i] = false;
			}
		}
	}
}
