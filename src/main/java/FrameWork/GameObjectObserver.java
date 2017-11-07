package FrameWork;
import java.awt.Canvas;
import java.awt.Graphics2D;

/**
 * Esta interfaz se va a utilizar para comunicar los GameObjects con los PlayState y la View
 * Se utilizan los 3 metodos de colision, renderizado y actualizacion.
 */

public interface GameObjectObserver {

	/**
     * Se llama cada vez que el PlayState hace un update (Pasa un frame de Juego)
     * Llama al Update del GameObject y tambien maneja su animation si es que tiene una.
     */
	public void PreUpdate();
	
	/**
     * Se llama cada vez que el PlayState detecta una colision en este objeto
     */
	public void OnCollition();
	
	/**
     * Se llama por la View cada vez que el objeto se va renderizar
     * @param grafics
     * El Graphics2D que se obtiene del BufferStrategy
     */
	public void Draw(Graphics2D grafics, Canvas canvas);
}
