package FrameWork;

/**
 * Esta interfaz sirve para definir a travez del metodo "Setup" como tiene que ser un GameObject
 * Si al metodo "AddToGame()" le pasamos un objeto que implementa esta interfaz, entonces es porque
 * ya tiene seteada su forma de ser.
 */
public interface GameObjectProduct {

	/**
	 * Para saber si el objeto era visible
	 */
	public boolean getVisible();

	/**
	 * Para saber si el objeto era activo
	 */
	public boolean getActive();

	/**
	 * Para saber si el objeto era colicionable
	 */
	public boolean getCollides();

}
