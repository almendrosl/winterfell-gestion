package Testing;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;

import FrameWork.Model;
import FrameWork.UserActions;
import FrameWork.View;
import FrameWork.ViewObserver;

public class ModelTest {
	
	ViewTest2[] views = new ViewTest2[]
	{
		new ViewTest2(5,5),
		new ViewTest2(5,5),
		new ViewTest2(5,5),
		new ViewTest2(5,5),
		new ViewTest2(5,5),
	};
	
	public class ViewTest2 extends View
	{
		public int contadorNotify = 0;
				
		public ViewTest2(int width, int height) {
			super(width, height, "Game");
			// TODO Auto-generated constructor stub
		}
		
		@Override
		public void Update()
		{
			contadorNotify++;
		}
	}
	
	public static class ModelTest2 extends Model{
		
		private static ModelTest2 modelControl2 = null;

		public static ModelTest2 getModel2()
		{
		      if(modelControl2 == null) 
		    	  modelControl2 = new ModelTest2();

		      return modelControl2;
		}
		
		public static List<ViewObserver> getView()
		{
			return viewObservers;
		}
	}

	public static class ModelTest3 extends Model{

		private static ModelTest3 modelControl3 = null;

		public static ModelTest3 getModel3()
		{
		      if(modelControl3 == null) 
		    	  modelControl3 = new ModelTest3();

		      return modelControl3;
		}

		public static List<ViewObserver> getView()
		{
			return viewObservers;
		}
	}

	public static class ModelTest4 extends Model{

		private static ModelTest4 modelControl4 = null;

		public static ModelTest4 getModel4()
		{
		      if(modelControl4 == null) 
		    	  modelControl4 = new ModelTest4();

		      return modelControl4;
		}

		public List<ViewObserver> getView()
		{
			return viewObservers;
		}
	}
	
	public static class ModelTest5 extends Model{

		private static ModelTest5 modelControl5 = null;

		public static ModelTest5 getModel5()
		{
		      if(modelControl5 == null) 
		    	  modelControl5 = new ModelTest5();

		      return modelControl5;
		}

		public List<ViewObserver> getView()
		{
			return viewObservers;
		}
	}
	
	/*
	 * Vamos a llamar 2 veces a getModel(), y lo vamos a guardar en lugares distintos
	 * luego vamos a ver si son el mismo objeto.
	 */
	@Test
	public void getModel()	{
		Model modelControl1 = Model.getModel();
		Model modelControl2 = Model.getModel();
		assertEquals(modelControl1, modelControl2);
	}
	
	/*
	 * Vamos a agregar 2 view nuevas y luego vamos a comprobar que el tamaño de Views aumento a 2.
	 */
	@Test
	public void testRegistrarView() {
		ModelTest2.getModel2().RegistrarView(new View(5,5, "Game"));
		ModelTest2.getModel2().RegistrarView(new View(5,5, "Game"));
		assertEquals(ModelTest2.getView().size(),2);
	}

	/*
	 * Vamos a registrar 5 views y luego vamos a desregistrar 4 y una la vamos a desregistrar
	 * 3 veces, lo esperado es tener 1 view. 
	 */
	@Test
	public void testDesregistrarView()
	{
		for(int i = 0; i < 5; i++)
			ModelTest3.getModel3().RegistrarView(views[i]);
	
		ModelTest3.getModel3().DesregistrarView(views[0]);
		ModelTest3.getModel3().DesregistrarView(views[2]);
		ModelTest3.getModel3().DesregistrarView(views[3]);
		ModelTest3.getModel3().DesregistrarView(views[4]);
		ModelTest3.getModel3().DesregistrarView(views[2]);
		ModelTest3.getModel3().DesregistrarView(views[2]);
		assertEquals(ModelTest3.getView().size(),1);
	}
	
	/*
	 * Vamos a Registrar 4 Views que en su Udpate aumenta un contador notifyCounter
	 * a un Model 4, les vamos a hacer 5 NotifyDraw() luego vamos
	 * a Desregistrar 2 View y vamos a hacer 5 NotifyDraw() mas. Esperamos encontrar
	 * en total 30 de contador en "notifyCounter" de todos juntos. 
	 */
	@Test
	public void testNotifyView()
	{
		ViewTest2[] views2 = new ViewTest2[]
		{
			new ViewTest2(5,5),
			new ViewTest2(5,5),
			new ViewTest2(5,5),
			new ViewTest2(5,5),
		};

		for(int i = 0; i < 4; i++)
			ModelTest4.getModel4().RegistrarView(views2[i]);
		
		for(int i = 0; i < 5; i++)
			ModelTest4.getModel4().NotifyView();
		
		ModelTest4.getModel4().DesregistrarView(views2[0]);
		ModelTest4.getModel4().DesregistrarView(views2[1]);
		
		for(int i = 0; i < 5; i++)
			ModelTest4.getModel4().NotifyView();
		
		int sum = 0;
		
		for(int i = 0; i < 4; i++)
			sum += views2[i].contadorNotify;
		
		assertEquals(sum, 30);
	}
	
	/*
	 * Voy a setear los valores de userActions a mano y luego los voy a pedir por medio
	 * de un GetState();
	 */
	@Test
	public void testGetState() {

		Model.getModel().setStateJustPressed(UserActions.Jump, false);
		Model.getModel().setStateJustPressed(UserActions.Horse, true);
		Model.getModel().setStateJustPressed(UserActions.Shoot, true);
		Model.getModel().setStateJustPressed(UserActions.Escape,false);

		Model.getModel().setInputs();
		
		assertFalse(Model.getModel().getStatePressed(UserActions.Jump));
		assertTrue(Model.getModel().getStatePressed(UserActions.Horse));
		assertTrue(Model.getModel().getStatePressed(UserActions.Shoot));
		assertFalse(Model.getModel().getStatePressed(UserActions.Escape));
	}

	/*
	 * Seteo el valor de muchas variables en true y false y luego corroboro que se 
	 * hayan seteado correctamente.
	 */
	@Test
	public void testSetState() {
		Model.getModel().setStateJustPressed(UserActions.Jump, true);
		Model.getModel().setStateJustPressed(UserActions.Horse, false);
		Model.getModel().setStateJustPressed(UserActions.Shoot, false);
		Model.getModel().setStateJustPressed(UserActions.Escape, true);

		Model.getModel().setInputs();
		
		assertTrue(Model.getModel().getStatePressed(UserActions.Jump));
		assertFalse(Model.getModel().getStatePressed(UserActions.Horse));
		assertFalse(Model.getModel().getStatePressed(UserActions.Shoot));
		assertTrue(Model.getModel().getStatePressed(UserActions.Escape));		
	}

	/*
	 * Hacemos un Pressed(true), nos fijamos que JustPressed y Pressed sean true y que JustReleased
	 * sea false, luego hacemos otro Pressed(true), nos fijamos que JustPressed sea false Pressed
	 * sea true y JustReleased sea false, luego hacemos un Pressed(false) y nos fijamos que 
	 * Pressed y JustPressed sean false y JustReleased sea false, por ultimo hacemos un Pressed(false)
	 * y corroboramos que ahora todos estan en false.
	 */
	@Test
	public void testSetInputs(){
		ModelTest5.getModel5().setStateJustPressed(0, true);
		ModelTest5.getModel5().setInputs();
		assertTrue(ModelTest5.getModel5().getStateJustPressed(0));
		assertTrue(ModelTest5.getModel5().getStatePressed(0));
		assertFalse(ModelTest5.getModel5().getStateJustReleased(0));
		ModelTest5.getModel5().setStateJustPressed(0, true);
		ModelTest5.getModel5().setInputs();
		assertFalse(ModelTest5.getModel5().getStateJustPressed(0));
		assertTrue(ModelTest5.getModel5().getStatePressed(0));
		assertFalse(ModelTest5.getModel5().getStateJustReleased(0));
		ModelTest5.getModel5().setStateJustPressed(0, false);
		ModelTest5.getModel5().setInputs();
		assertFalse(ModelTest5.getModel5().getStateJustPressed(0));
		assertFalse(ModelTest5.getModel5().getStatePressed(0));
		assertTrue(ModelTest5.getModel5().getStateJustReleased(0));
		ModelTest5.getModel5().setStateJustPressed(0, false);
		ModelTest5.getModel5().setInputs();
		assertFalse(ModelTest5.getModel5().getStateJustPressed(0));
		assertFalse(ModelTest5.getModel5().getStatePressed(0));
		assertFalse(ModelTest5.getModel5().getStateJustReleased(0));
	}
}
