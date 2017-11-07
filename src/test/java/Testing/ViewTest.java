package Testing;
import static org.junit.Assert.*;

import java.awt.Canvas;
import java.awt.Graphics2D;
import java.util.List;

import org.junit.Test;

import FrameWork.GameObject;
import FrameWork.GameObjectObserver;
import FrameWork.View;

public class ViewTest {

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

		public int wasDrawed = 0;
		
		public GameObjectTest2(int x, int y, int width, int height) {
			super(x, y, width, height);
			// TODO Auto-generated constructor stub
		}
		
		@Override
		public void Draw(Graphics2D grafics, Canvas canvas)
		{
			wasDrawed++;
		}
	}
	
	/*
	 * Debemos crear muchas View con muchos tamaños diferentes y ver que se creen correctamente.
	 */
	@Test
	public void testView() {
		View view = new View(100, 100, "Game");
		View view2 = new View(100, 200, "Game");
		View view3 = new View(100, 300, "Game");
		View view4 = new View(100, 400, "Game");
		View view5 = new View(200, 100, "Game");
		View view6 = new View(300, 100, "Game");
	}

	/*
	 * Probamos meter varios GameObjects dentro y luego nos fijamos que esten ahi con una
	 * clase que nos da acceso.
	 */
	@Test
	public void testRegistrarDraw() {
		ViewTest2 view = new ViewTest2(200,200);
		view.RegistrarDraw(new GameObject(0,0,0,0));
		view.RegistrarDraw(new GameObject(0,0,0,0));
		view.RegistrarDraw(new GameObject(0,0,0,0));
		view.RegistrarDraw(new GameObject(0,0,0,0));
		assertEquals(view.getList().size(),4);
	}
	
	/*
	 * Vamos a registrar 10 objetos en la lista Updates de un PlayState y luego vamos a 
	 * sacar 8, vamos a medir el tamaño de la lista y esperamos que el tamaño sea 2.
	 */
	@Test
	public void testDesregistrarDraw() {
		ViewTest2 view = new ViewTest2(100,100);

		GameObject[] gameObjects = new GameObject[]{
			new GameObject(0,0,0,0),
			new GameObject(1,0,0,0),
			new GameObject(2,0,0,0),
			new GameObject(3,0,0,0),
			new GameObject(4,0,0,0),
			new GameObject(5,0,0,0),
			new GameObject(6,0,0,0),
			new GameObject(7,0,0,0),
			new GameObject(8,0,0,0),
			new GameObject(9,0,0,0),
		};
		
		for(int i = 0; i < 10; i++)
			view.RegistrarDraw(gameObjects[i]);

		view.DesregistrarDraw(gameObjects[0]);
		view.DesregistrarDraw(gameObjects[7]);
		view.DesregistrarDraw(gameObjects[3]);
		view.DesregistrarDraw(gameObjects[3]);
		view.DesregistrarDraw(gameObjects[2]);
		view.DesregistrarDraw(gameObjects[1]);
		view.DesregistrarDraw(gameObjects[5]);
		view.DesregistrarDraw(gameObjects[6]);
		view.DesregistrarDraw(gameObjects[6]);
		view.DesregistrarDraw(gameObjects[9]);
		
		int suma = 0;

		assertEquals(view.getList().size(),2);

		for(int i = 0; i < view.getList().size(); i++)
		{
			suma += ((GameObject)view.getList().get(i)).x;
		}
		
		assertEquals(suma,12);
	}

	/*
	 * Vamos a crear una view, le vamos a registrar 5 GameObjects que en sus Draws aumentan
	 * una variable wasDrawed en 1, luego vamos a desregistrar a 3 de esos GameObjects y 
	 * vamos a hacer NotifyDraw 5 veces mas luego vamos a comprobar que estos 3 siguieron
	 *  en 5 y los otros 2 se fueron a 10
	 */
	
	@Test
	public void testNotifyDraw() {
		ViewTest2 view = new ViewTest2(50,50);
		
		GameObjectTest2[] gObjects = new GameObjectTest2[]
		{
			new GameObjectTest2(0,0,0,0),
			new GameObjectTest2(0,0,0,0),
			new GameObjectTest2(0,0,0,0),
			new GameObjectTest2(0,0,0,0),
			new GameObjectTest2(0,0,0,0),
		};

		for(int i = 0; i < 5; i++)
			view.RegistrarDraw(gObjects[i]);

		for(int i = 0; i < 5; i++)
			view.NotifyDraw();
		
		view.DesregistrarDraw(gObjects[0]);
		view.DesregistrarDraw(gObjects[3]);
		view.DesregistrarDraw(gObjects[2]);
		
		for(int i = 0; i < 5; i++)
			view.NotifyDraw();
		
		assertEquals(gObjects[0].wasDrawed, 5);
		assertEquals(gObjects[1].wasDrawed, 10);
		assertEquals(gObjects[2].wasDrawed, 5);
		assertEquals(gObjects[3].wasDrawed, 5);
		assertEquals(gObjects[4].wasDrawed, 10);
	}
	
	/*
	 * Registramos a una View, unos 5 objetos con depths (4,2,0,1,3) en ese orden, luego
	 * hacemos un NotifyDraw() y nos fijamos si el orden de los depth ahora es (4,3,2,1,0).
	 */
	@Test
	public void testUpdate(){
		ViewTest2 view = new ViewTest2(100,100);
		
		GameObject[] gameObjects = new GameObject[]{
				new GameObject(0,0,0,0),
				new GameObject(0,0,0,0),
				new GameObject(0,0,0,0),
				new GameObject(0,0,0,0),
				new GameObject(0,0,0,0),
		};
		
		gameObjects[0].depth = 4;
		gameObjects[1].depth = 2;
		gameObjects[2].depth = 0;
		gameObjects[3].depth = 1;
		gameObjects[4].depth = 3;
		
		for(int i = 0; i < 5; i++)
			view.RegistrarDraw(gameObjects[i]);
		
		view.Update();
		
		GameObject aux;

		aux = (GameObject)(view.getList().get(0));
		assertEquals(aux.depth, 4);	
		aux = (GameObject)(view.getList().get(1));
		assertEquals(aux.depth, 3);	
		aux = (GameObject)(view.getList().get(2));
		assertEquals(aux.depth, 2);	
		aux = (GameObject)(view.getList().get(3));
		assertEquals(aux.depth, 1);	
		aux = (GameObject)(view.getList().get(4));
		assertEquals(aux.depth, 0);	
	}
}
