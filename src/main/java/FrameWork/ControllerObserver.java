package FrameWork;
/**
 * Esta interfaz se va a utilizar para comunicar los Controllers con los Inputs y los PlayStates
 * Se utiliza un metodo Update.
 */
public interface ControllerObserver {
	/**
     * Se llama cada vez que al los Input toman una nueva muestra
     */
	public void UpdateInput(int userAction, boolean condition);

	/**
     * Se llama cada vez que un PlayState termina un GameLoop.
     */
	public void UpdateGameLoop();
	
	/**
	 * Se llama cada vez que un Input con posicion realiza una accion de presionar, o moverse.
	 * @param x
	 * Posicion en X del input
	 * @param y
	 * Posicion en Y del input
	 */
	public void UpdateInputPosition(int x, int y);
}
