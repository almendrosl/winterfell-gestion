package FrameWork;
/**
 * Esta interfaz se va a utilizar para comunicar el Model con la View
 * Se utilizan los 3 metodos de Registrar, Desregistrar y Notificar.
 */
public interface ModelSubject {
	/**
	 * Se utiliza para que ahora la View que se suscriba renderize
	 */
	public void RegistrarView(ViewObserver obs);
	
	/**
	 * Se utiliza para que a partir de ahora no se renderize mas esa View
	 */
	public void DesregistrarView(ViewObserver obs);
	
	/**
	 * Se utiliza para avisarle a las View Subscritas que tienen que renderizar.
	 */
	public void NotifyView();
}
