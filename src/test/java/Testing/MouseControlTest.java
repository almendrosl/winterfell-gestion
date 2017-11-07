package Testing;
import static org.junit.Assert.*;

import java.awt.event.KeyEvent;
import java.util.List;

import org.junit.Test;

import FrameWork.Controller;
import FrameWork.ControllerObserver;
import FrameWork.MouseControl;

public class MouseControlTest {

	public class MouseControlTest2 extends MouseControl{
		
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
	 * Vamos a crear un objeto "MouseControlTest2" y nos vamos a asegurar de que en su
	 * controllerObserverList haya algo distinto de null.
	 */
	@Test
	public void testMouseControl() {
		MouseControlTest2 mouse = new MouseControlTest2();
		assertNotNull(mouse.getControllers());
	}

	/*
	 * Vamos a registrar 2 controllers, luego vamos a pedirle el tamaño y vamos a comprobar
	 * que sea de 2.
	 */
	@Test
	public void testRegistrarController() {
		MouseControlTest2 mouse = new MouseControlTest2();
		mouse.RegistrarController(new Controller());
		mouse.RegistrarController(new Controller());

		assertEquals(mouse.getControllers().size(),2);
	}

	/*
	 * Vamos a registrar 5 controllers, luego vamos a desregistrar 3, y vamos a comprobar
	 * que sea de 2 el tamaño de la lista de controllers.
	 */
	@Test
	public void testDesregistrarController() {
		MouseControlTest2 mouse = new MouseControlTest2();
		
		mouse.RegistrarController(new Controller());
		mouse.RegistrarController(new Controller());
		mouse.RegistrarController(new Controller());
		mouse.RegistrarController(new Controller());
		mouse.RegistrarController(new Controller());

		mouse.DesregistrarController(mouse.controllers.get(4));
		mouse.DesregistrarController(mouse.controllers.get(3));
		mouse.DesregistrarController(mouse.controllers.get(2));

		assertEquals(mouse.getControllers().size(),2);
	}
	/*
	 * Vamos a crear un mouse, le vamos a registrar 1 controller y le vamos a hacer 5
	 * notify con true y false, (los que son true suman un contador los otros no), vamos a
	 * comprobar que se sumo correctamente.
	 */
	@Test
	public void testNotifyController() {
		ControllerTest2 controller = new ControllerTest2();
		
		MouseControl mouse = new MouseControl();
		
		mouse.RegistrarController(controller);
		
		mouse.NotifyController(KeyEvent.VK_A, true);
		mouse.NotifyController(KeyEvent.VK_A, false);
		mouse.NotifyController(KeyEvent.VK_A, true);
		mouse.NotifyController(KeyEvent.VK_A, true);
		mouse.NotifyController(KeyEvent.VK_A, false);
		
		assertEquals(controller.userActions,3);
	}

}
