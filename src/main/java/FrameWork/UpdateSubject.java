package FrameWork;
/**
 * Esta interfaz se va a utilizar para comunicar los PlayState con los GameObject
 * Se utilizan los 3 metodos de Registrar, Desregistrar y Notificar.
 */
public interface UpdateSubject {
	/**
	 * Se utiliza para que ahora que ese GameObject este activo
	 */
	public void RegistrarUpdate(GameObjectObserver obs);
	
	/**
	 * Se utiliza para que a partir de ahora un GameObject no este activo
	 */
	public void DesregistrarUpdate(GameObjectObserver obs);
	
	/**
	 * Se utiliza para que todos los registrados "activos" hagan su Update.
	 */
	public void NotifyUpdate();
	
	/**
	 * Eliminamos a todos los objetos de la lista de Updates
	 */
	public void ClearUpdate();
}
