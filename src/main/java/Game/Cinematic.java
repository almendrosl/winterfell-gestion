package Game;

/**
 * Esta interfaz sirve para saber cuanto se tienen que mover los objetos de todo el juego
 * y que el escenario los pueda controlar a todos juntos.
 */
public interface Cinematic {

	/**
	 * @return
	 * Velocidad en X
	 */
	public float getSpeedX();

	/**
	 * @return
	 * Velocidad en Y
	 */
	public float getSpeedY();

	/**
	 * @return
	 * Aceleracion en X
	 */
	public float getAcelerationX();
	
	/**
	 * @return
	 * Aceleracion en Y
	 */
	public float getAcelerationY();
}
