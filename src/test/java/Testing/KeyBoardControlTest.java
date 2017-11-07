package Testing;
import static org.junit.Assert.*;

import java.awt.event.KeyEvent;
import java.util.List;

import org.junit.Test;

import FrameWork.Controller;
import FrameWork.ControllerObserver;
import FrameWork.KeyBoardControl;

public class KeyBoardControlTest {

	public class KeyBoardControlTest2 extends KeyBoardControl{
		
		public List<ControllerObserver> getControllers()
		{
			return controllers;
		}
	}
	
	public class ControllerTest2 extends Controller{

		int userActions;
		
		@Override
		public void UpdateInput(int userAction, boolean condition) {
			if(condition)
				userActions++;
		}
	}
	
	/*
	 * Vamos a crear un objeto "KeyBoardControlTest2" y nos vamos a asegurar de que en su
	 * controllerObserverList haya algo distinto de null.
	 */
	@Test
	public void testKeyBoardControl() {
		
		KeyBoardControlTest2 keyController = new KeyBoardControlTest2();
		assertNotNull(keyController.getControllers());
	}

	/*
	 * Vamos a registrar 2 controllers, luego vamos a pedirle el tamaño y vamos a comprobar
	 * que sea de 2.
	 */
	@Test
	public void testRegistrarController() {
		KeyBoardControlTest2 keyBoard = new KeyBoardControlTest2();
		keyBoard.RegistrarController(new Controller());
		keyBoard.RegistrarController(new Controller());

		assertEquals(keyBoard.getControllers().size(),2);
	}

	/*
	 * Vamos a registrar 5 controllers, luego vamos a desregistrar 3, y vamos a comprobar
	 * que sea de 2 el tamaño de la lista de controllers.
	 */
	@Test
	public void testDesregistrarController() {
		KeyBoardControlTest2 keyBoard = new KeyBoardControlTest2();
		
		keyBoard.RegistrarController(new Controller());
		keyBoard.RegistrarController(new Controller());
		keyBoard.RegistrarController(new Controller());
		keyBoard.RegistrarController(new Controller());
		keyBoard.RegistrarController(new Controller());

		keyBoard.DesregistrarController(keyBoard.controllers.get(4));
		keyBoard.DesregistrarController(keyBoard.controllers.get(3));
		keyBoard.DesregistrarController(keyBoard.controllers.get(2));

		assertEquals(keyBoard.getControllers().size(),2);
	}

	/*
	 * Vamos a crear un keyBoard, le vamos a registrar 1 controller y le vamos a hacer 5
	 * notify con true y false, (los que son true suman un contador los otros no), vamos a
	 * comprobar que se sumo correctamente.
	 */
	@Test
	public void testNotifyController() {
		ControllerTest2 controller = new ControllerTest2();
		
		KeyBoardControl keyBoard = new KeyBoardControl();
		
		keyBoard.RegistrarController(controller);
		
		keyBoard.NotifyController(KeyEvent.VK_A, true);
		keyBoard.NotifyController(KeyEvent.VK_A, false);
		keyBoard.NotifyController(KeyEvent.VK_A, true);
		keyBoard.NotifyController(KeyEvent.VK_A, true);
		keyBoard.NotifyController(KeyEvent.VK_A, false);
		
		assertEquals(controller.userActions,3);
	}

}
