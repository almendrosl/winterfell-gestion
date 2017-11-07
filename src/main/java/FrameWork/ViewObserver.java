package FrameWork;
/**
 * Esta interfaz se va a utilizar para comunicar las View con el Model
 * Se utiliza un metodo Update.
 */

public interface ViewObserver {
	/**
     * Se llama cada vez que al Model le hacen un update y la view tiene que desplegarse
     */
	public void Update();
}
