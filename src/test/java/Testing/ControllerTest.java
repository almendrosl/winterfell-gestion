package Testing;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import FrameWork.Controller;
import FrameWork.Model;
import FrameWork.UserActions;

public class ControllerTest {

	/*
	 * Vamos a hacer 5 NotifyView() en el ModelTestController y luego vamos a comprobar que
	 * el counter del mismo sea 5.
	 */
	@Test
	public void testUpdateGameLoop() {
//		Model.
	}

	/*
	 * Vamos a llamar a Update input para 3 UserActions distintas con distintas condiciones
	 * luego las vamos a cambiar y vamos a ir haciendo asserts() en el Model a medida que avanzamos
	 */
	@Test
	public void testUpdateInput() {
		Controller controller  = new Controller();
		
		controller.UpdateInput(UserActions.Jump, false);
		controller.UpdateInput(UserActions.Horse, true);
		controller.UpdateInput(UserActions.Shoot, true);

		Model.getModel().setInputs();
		
		assertFalse(Model.getModel().getStatePressed(UserActions.Jump));
		assertTrue(Model.getModel().getStatePressed(UserActions.Horse));
		assertTrue(Model.getModel().getStatePressed(UserActions.Shoot));
	}

}
