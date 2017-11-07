package FrameWork;

/**
 * Base de la Arquitectura MVC
 * Recibe si se apretan teclas que tengan que ver con las seleccionadas para los inputs y se
 * las manda al Model
 * Tambien recibe updates del GameLoop del PlayState actual y se lo manda al Model
 */
public class Controller implements ControllerObserver{

	/**
	 * Constructor de la Clase
	 */
	public Controller()
	{
		
	}
	
	@Override
	public void UpdateGameLoop() {
		Model.getModel().NotifyView();	
	}

	@Override
	public void UpdateInput(int userAction, boolean condition) {
		Model.getModel().setStateJustPressed(userAction,condition);
	}

	@Override
	public void UpdateInputPosition(int x, int y){
		Model.getModel().setActionPosition(x, y);
	}
}
