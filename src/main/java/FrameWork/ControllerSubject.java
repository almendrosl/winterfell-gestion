package FrameWork;
import java.awt.event.KeyEvent;

/**
 * Esta interfaz se va a utilizar para comunicar tanto los Input con el Controller
 * como tambien los PlayState con el Controller.
 * Se utilizan los 3 metodos de Registrar, Desregistrar y Notificar.
 */
public interface ControllerSubject {
	/**
	 * Se utiliza para que ahora el Controller que se registre sea avisado de cambios
	 */
	public void RegistrarController(ControllerObserver obs);
	
	/**
	 * Se utiliza para que a partir de ahora no se le avise mas de cambios al Controller
	 */
	public void DesregistrarController(ControllerObserver obs);
	
	/**
	 * Se utiliza para avisarle a los Controller de los cambios producidos
	 */
	public void NotifyController(int ke, boolean condition);
	
	/**
	 * Se utiliza para registrar al Controller cambios en la posicion del Mouse
	 */
	public void NotifyPosition(int x, int y);
}
