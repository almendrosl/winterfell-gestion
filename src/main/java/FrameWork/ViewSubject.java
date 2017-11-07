package FrameWork;
/**
 * Esta interfaz se va a utilizar para comunicar la View con los GameObject
 * Se utilizan los 3 metodos de Registrar, Desregistrar y Notificar.
 */
public interface ViewSubject {

	/**
	 * Se utiliza para que ahora el GameObject se muestre
	 */
	public void RegistrarDraw(GameObjectObserver obs);
	
	/**
	 * Se utiliza para que a partir de ahora no se vea mas
	 */
	public void DesregistrarDraw(GameObjectObserver obs);
	
	/**
	 * Se utiliza para que los GameObject se dibujen
	 */
	public void NotifyDraw();

	/**
	 * Elimina a todos los GameObject de su lista.
	 */
	public void ClearDraw();
	
	/**
	 * Le sede todos los GameObject de la lista.
	 */
	public void setGameObjectsToView(ViewSubject view);
}
