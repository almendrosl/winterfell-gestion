package Testing;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Test;

import FrameWork.Controller;
import FrameWork.GameControl;
import FrameWork.GameObject;
import FrameWork.GameObjectObserver;
import FrameWork.KeyBoardControl;
import FrameWork.Model;
import FrameWork.MouseControl;
import FrameWork.PlayState;
import FrameWork.View;

public class GameControlTest {

	public class ViewTest2 extends View{
		
		public ViewTest2(int width, int height) {
			super(width, height, "Game");
			// TODO Auto-generated constructor stub
		}

		public List<GameObjectObserver> getList()
		{
			return renderGO;
		}
	}
	
	public class GameObjectTest2 extends GameObject{

		public int seHizoStart = 0;
		
		public GameObjectTest2(int x, int y, int width, int height) {
			super(x, y, width, height);
			// TODO Auto-generated constructor stub
		}
		
		@Override
		public void Start()
		{
			seHizoStart++;
		}
	}
	
	public static class GameControlTest2 extends GameControl{
		
		private static GameControlTest2 gameControl2 = null;

		public static GameControlTest2 getGameControl2()
		{
		      if(gameControl2 == null) 
		    	  gameControl2 = new GameControlTest2();

		      return gameControl2;
		}
	}

	public static class GameControlTest3 extends GameControl{
		
		private static GameControlTest3 gameControl3 = null;

		public static GameControlTest3 getGameControl3()
		{
		      if(gameControl3 == null) 
		    	  gameControl3 = new GameControlTest3();

		      return gameControl3;
		}
	}

	public static class GameControlTest4 extends GameControl{
		
		private static GameControlTest4 gameControl4 = null;

		public static GameControlTest4 getGameControl4()
		{
		      if(gameControl4 == null) 
		    	  gameControl4 = new GameControlTest4();

		      return gameControl4;
		}
		
	}

	public static class GameControlTest5 extends GameControl{
		
		private static GameControlTest5 gameControl5 = null;

		public static GameControlTest5 getGameControl5()
		{
		      if(gameControl5 == null) 
		    	  gameControl5 = new GameControlTest5();

		      return gameControl5;
		}
	}

	public static class GameControlTest6 extends GameControl{
		
		private static GameControlTest6 gameControl6 = null;

		public static GameControlTest6 getGameControl6()
		{
		      if(gameControl6 == null) 
		    	  gameControl6 = new GameControlTest6();

		      return gameControl6;
		}
	}

	public static class GameControlTest7 extends GameControl{
		
		private static GameControlTest7 gameControl7 = null;

		public static GameControlTest7 getGameControl7()
		{
		      if(gameControl7 == null) 
		    	  gameControl7 = new GameControlTest7();

		      return gameControl7;
		}
	}
	/*
	 * Vamos a llamar 2 veces a getGameControl(), y lo vamos a guardar en lugares distintos
	 * luego vamos a ver si son el mismo objeto.
	 */
	@Test
	public void testGetGameControl() {
		GameControl gameControl1 = GameControl.getGameControl();
		GameControl gameControl2 = GameControl.getGameControl();
		assertEquals(gameControl1, gameControl2);
	}

	/*
	 * Creamos 2 PlayStates, luego cambiamos el PlayState y nos fijamos que el PlayState
	 * actual sea el primero no el segundo.
	 */
	@Test
	public void testChangePlayState() {
		GameControlTest6.getGameControl6().frameRate = 60;
		GameControlTest6.getGameControl6().views.add(new View(500,500, "Game"));
		
		GameControlTest6.getGameControl6().changePlayState(new PlayState("Nombre 1"));
		assertEquals(GameControlTest6.getGameControl6().actualPlayState.name, "Nombre 1");
		GameControlTest6.getGameControl6().changePlayState(new PlayState("Nombre 2"));
		assertEquals(GameControlTest6.getGameControl6().actualPlayState.name, "Nombre 2");
	}

	/**
	 * Seteamos el FrameRate del GameControl5 en 15 y luego nos fijamos si era lo que pedimos
	 */
	@Test
	public void testGetFrameRate() {
		GameControlTest5.getGameControl5().frameRate = 15;
		assertTrue(15f == GameControlTest5.getGameControl5().getFrameRate());
	}

	/*
	 * Creamos un PlayState, se lo agregamos al GameControl, por lo que el va a tomarlo
	 * como actual, luego le seteamos a ese PlayState un deltaTime de 10.5 y le pedimos
	 * al GameControl que nos de el deltaTime.
	 */
	@Test
	public void testGetDeltaTime() {
		GameControl.getGameControl().actualPlayState.deltaTime = 10.5f;
		assertTrue(10.5f/1000 == GameControl.getGameControl().getDeltaTime());
	}
	
	/*
	 * Agregamos 10 PlayStates a un GameControlTest3 y luego le sacamos 7, luevo verificamos
	 * que quedan 3.
	 */
	@Test
	public void testremoveFromGame()
	{
		ViewTest2 view = new ViewTest2(300,300);

		Model.getModel().RegistrarView(view);
		GameControlTest4.getGameControl4().views.add(view);
		
		Controller controller = new Controller();

		GameControlTest4.getGameControl4().controller = controller;
		GameControlTest4.getGameControl4().ChangeInputs(new KeyBoardControl());
		GameControlTest4.getGameControl4().ChangeInputs(new MouseControl());
		GameControlTest4.getGameControl4().frameRate = 60;
		
		GameControlTest4.getGameControl4();
		GameControlTest4.getGameControl4().changePlayState(new PlayState("lala"));

		
		GameObjectTest2 gObj1 = new GameObjectTest2(0,0,0,0);
		GameObjectTest2 gObj2 = new GameObjectTest2(0,0,0,0);
		GameObjectTest2 gObj3 = new GameObjectTest2(0,0,0,0);
		GameControlTest4.getGameControl4().addToGame(true, false, false, (GameObject)gObj1);
		GameControlTest4.getGameControl4().addToGame(false, true, true, (GameObject)gObj2);
		GameControlTest4.getGameControl4().addToGame(false, true, false, (GameObject)gObj3);
		
		GameControlTest4.getGameControl4().removeFromGame(gObj1);
		GameControlTest4.getGameControl4().removeFromGame(gObj1);
		GameControlTest4.getGameControl4().removeFromGame(gObj1);
		GameControlTest4.getGameControl4().removeFromGame(gObj3);

		assertEquals(view.getList().size(),1);
		assertTrue(gObj1.seHizoStart == 1);
	}
	
	/*
	 * Vamos a crear 3 objetos, con diferentes boleanos en lo que son los registros
	 * luego vamos a ver si se registraron correctamente en donde tenian que ir.
	 * Para testear el remove, vamos a sacar GameObjects del GameControl y vamos a ver si la cuenta es menor.
	 */
	@Test
	public void testaddToGame() {
		ViewTest2 view = new ViewTest2(300,300);
		GameControlTest7.getGameControl7().views.add(view);
		GameControlTest7.getGameControl7().changePlayState(new PlayState("lala"));

		GameObjectTest2 gObj1 = new GameObjectTest2(0,0,0,0);
		GameObjectTest2 gObj2 = new GameObjectTest2(0,0,0,0);
		GameObjectTest2 gObj3 = new GameObjectTest2(0,0,0,0);
		GameControlTest7.getGameControl7().addToGame(true, false, false, (GameObject)gObj1);
		GameControlTest7.getGameControl7().addToGame(false, true, true, (GameObject)gObj2);
		GameControlTest7.getGameControl7().addToGame(false, true, false, (GameObject)gObj3);
		
		assertEquals(view.getList().size(),2);
		assertTrue(gObj1.seHizoStart == 1);
		
		GameControlTest7.getGameControl7().removeFromGame(gObj1);
		GameControlTest7.getGameControl7().removeFromGame(gObj1);
		GameControlTest7.getGameControl7().removeFromGame(gObj1);
		GameControlTest7.getGameControl7().removeFromGame(gObj2);
		GameControlTest7.getGameControl7().removeFromGame(gObj3);
		assertEquals(view.getList().size(),0);
	}
}
