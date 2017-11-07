package Testing;
import static org.junit.Assert.*;

import org.junit.Test;

import FrameWork.GameObject;

public class GameObjectTest {

	GameObject GameObjectTest;
	
	//Esta clase es para testear una extension de GameObject que hace cosas en su Update.
	public class GameObjectTest2 extends GameObject
	{
		public int i = 0;
		
		public GameObjectTest2(int x, int y, int width, int height) 
		{
			super(x, y, width, height);
			// TODO Auto-generated constructor stub
		}
		
		@Override
		public void Update()
		{
			i++;
		}
	}
	
	//Creamos un objeto GameObject y nos fijamos si seteo bien sus variables
	@Test
	public void testGameObject() {
		GameObjectTest = new GameObject(0,0,10,10);
		assertTrue(GameObjectTest.width == 10);
	}

	/*
	 * Creamos un objeto GameObjectTest2 (que en su update suma 1 a i), como se inicia con 
	 * activate en false le hacemos update y comprobamos que no paso nada, luego seteamos
	 * el activate en true y comprobamos que el update si se ejecuto.
	*/
	@Test
	public void testPreUpdate() {
		GameObjectTest2 Got2 = new GameObjectTest2(0,0,0,0);
		Got2.PreUpdate();
		Got2.active = true;
		assertTrue(Got2.i == 0);
		Got2.PreUpdate();
		assertTrue(Got2.i == 1);
	}

	/*
	 * Primero voy a probar hacer 2 GameObjects que esten solapados y voy a comprobar que 
	 * que la funcion Collide me devuelve false. Luego le voy a poner la condicion de collides en true
	 * y me va a dar true.
	 * Segundo voy a probar hacer 2 GameObjects que no esten solapados con collides en true
	 * y voy a comprobar que la funcion Collide me devuelve false.
	 */
	@Test
	public void testCollide() {
		GameObject gobj1 = new GameObject(0,0,100,100);
		GameObject gobj2 = new GameObject(50,50,100,100);
		GameObject gobj3 = new GameObject(150,150,100,100);
		
		assertFalse(gobj1.Collide(gobj2));
		
		gobj1.collides = true;
		gobj2.collides = true;
		gobj3.collides = true;
		
		assertTrue(gobj1.Collide(gobj2));
		assertFalse(gobj1.Collide(gobj3));
	}

}
