package FrameWork;
/**
 * Esta interfaz se va a utilizar para comunicar los PlayState con los GameObject
 * Se utilizan los 3 metodos de Registrar, Desregistrar y Notificar.
 */
public interface CollideSubject {

	
	/**
	 * Se utiliza para que ahora checkee al que se registro si coliciono
	 */
	public void RegistrarCollition(GameObjectObserver obs);
	
	/**
	 * Se utiliza para que a partir de ahora deje de checkear al que se registro si coliciono
	 */
	public void DesregistrarCollition(GameObjectObserver obs);
	
	/**
	 * Se utiliza para ver el estado de colicion de todos los registrados
	 */
	public void NotifyCollition();

	/**
	 * Eliminamos a todos los objetos de la lista de Collides
	 */
	public void ClearCollition();
}
