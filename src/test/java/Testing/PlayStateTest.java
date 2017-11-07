package Testing;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import FrameWork.Controller;
import FrameWork.ControllerObserver;
import FrameWork.GameObject;
import FrameWork.GameObjectObserver;
import FrameWork.PlayState;

public class PlayStateTest {

	/*
	 * PlayState usado para extender el PlayState y testearlo
	 */
	public class PlayStateTest2 extends PlayState{

		public GameObjectTest2 gObj1;
		public GameObjectTest2 gObj2;
		public GameObjectTest2 gObj3;
		public int suma = 0;
		
		public int regitrarCollitionsCounter;
		
		public PlayStateTest2(String name) {
			super(name);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void Awake()
		{
			this.name = "nombre2";
			gObj1 = new GameObjectTest2(0,0,0,0,1);
			gObj2 = new GameObjectTest2(0,0,0,0,2);
			gObj3 = new GameObjectTest2(0,0,0,0,3);
		}
		
		public int TestRegistrarCollitions()
		{
			int aux = 0;
			
			for(int i = 0; i < collitionGoObservers.size(); i++)
			{
				GameObject auxGO = (GameObject)(collitionGoObservers.get(i));
				aux += auxGO.x;
			}
			
			return aux;
		}
		
		@Override
		public void Update()
		{
			suma += gObj1.contadorUpdate;
			suma += gObj2.contadorUpdate;
			suma += gObj3.contadorUpdate;
		}
		
		public void preUpdate2()
		{
			NotifyUpdate();
		}
		
		public List<GameObjectObserver> getUpdates()
		{
			return updateGoObservers;
		}
		
		public List<GameObjectObserver> getCollitions()
		{
			return collitionGoObservers;
		}

		public List<ControllerObserver> getControllers()
		{
			return controllersObservers;
		}
	}
	
	public class GameObjectTest2 extends GameObject{

		public boolean siColiciono = false;
		public int contadorUpdate = 0;
		public int velocidadContar;
		
		public GameObjectTest2(int x, int y, int width, int height) {
			super(x, y, width, height);
			// TODO Auto-generated constructor stub
		}
		
		public GameObjectTest2(int x, int y, int width, int height, int contador) {
			super(x, y, width, height);
			// TODO Auto-generated constructor stub
			velocidadContar = contador;
		}
		
		@Override
		public void OnCollition()
		{
			siColiciono = true;
		}
		
		@Override
		public void Update()
		{
			contadorUpdate += velocidadContar;
		}
	}

	public class ControllerTest2 extends Controller{
		
		public int controllerCounter = 0;
		
		@Override
		public void UpdateGameLoop()
		{
			controllerCounter++;
		}
	}
	
	@Test
	public void testRun() {
//		fail("Not yet implemented");
	}

	/*
	 * Deberia poder crear un PlayState y luego tomar su nombre y que sea el mismo
	 * Luego deberia crear un PlayState que en su Awake seetee su nombre en "nombre2".
	 */
	@Test
	public void testPlayState() {
		PlayState playState = new PlayState("nombre1");
		assertEquals(playState.name, "nombre1");
		
		PlayStateTest2 playState2 = new PlayStateTest2("nombre1");
		assertEquals(playState2.name, "nombre2");
	}

	/*
	 * Creo 3 objetos GameObjects y los registro en un PlayState que cree, y le pido
	 * al PlayState que me muestre la lista de Collisiones
	 */
	@Test
	public void testRegistrarCollition() {

		PlayStateTest2 playState = new PlayStateTest2("testRegistrarCollitions");
		
		GameObject gObj1 = new GameObject(1,0,0,0);
		GameObject gObj2 = new GameObject(2,0,0,0);
		GameObject gObj3 = new GameObject(4,0,0,0);
		
		playState.RegistrarCollition(gObj1);
		playState.RegistrarCollition(gObj2);
		playState.RegistrarCollition(gObj3);
		
		assertEquals(playState.TestRegistrarCollitions(),7);
	}

	/*
	 * Vamos a registrar 10 objetos en la lista Collitions de un PlayState y luego vamos a 
	 * sacar 3, vamos a medir el tamaño de la lista y esperamos que el tamaño sea 7
	 */
	@Test
	public void testDesregistrarCollition() {
		PlayStateTest2 playState = new PlayStateTest2("lalalala");

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
			playState.RegistrarCollition(gameObjects[i]);

		playState.DesregistrarCollition(gameObjects[2]);
		playState.DesregistrarCollition(gameObjects[5]);
		playState.DesregistrarCollition(gameObjects[8]);
		
		int suma = 0;

		assertEquals(playState.getCollitions().size(),7);

		for(int i = 0; i < playState.getCollitions().size(); i++)
		{
			suma += ((GameObject)playState.getCollitions().get(i)).x;
		}
		
		assertEquals(suma,30);
	}

	/*
	 * Agrego 4 GameObjects, 3 que estan pegados y uno separado, de los 3 pegados 1 no es
	 * colicionable, y espero que me devuelva que hubo 2 coliciones.
	 */
	@Test
	public void testNotifyCollition() {
		//Este esta pegado al gObj2
		GameObjectTest2 gObj1 = new GameObjectTest2(0,0,100,100);
		gObj1.collides = true;

		//Este esta pegado al gObj1
		GameObjectTest2 gObj2 = new GameObjectTest2(50,50,100,100);
		gObj2.collides = true;
		
		//Esta esta lejos de los otros 2
		GameObjectTest2 gObj3 = new GameObjectTest2(200,0,100,100);
		gObj3.collides = true;

		//Este esta entre los 2 primeros, pero no coliciona
		GameObjectTest2 gObj4 = new GameObjectTest2(25,25,100,100);
		gObj4.collides = false;
		
		PlayStateTest2 playState = new PlayStateTest2("algo");
		
		playState.RegistrarCollition(gObj1);
		playState.RegistrarCollition(gObj2);
		playState.RegistrarCollition(gObj3);
		playState.RegistrarCollition(gObj4);
		
		playState.NotifyCollition();
		
		assertTrue(gObj1.siColiciono);
		assertTrue(gObj2.siColiciono);
		assertFalse(gObj3.siColiciono);
		assertFalse(gObj4.siColiciono);
	}

	/*
	 * Vamos a crear 2 GameObjects que tengan en su Update un sumar 1 y 2 respectivamente
	 * vamos a llamarles el Update 5 veces y vamos a fijarnos de que se hayan aumentado
	 */
	@Test
	public void testRegistrarUpdate() {
		
		PlayStateTest2 playState = new PlayStateTest2("prueba");
		playState.gObj1.active = true;
		playState.gObj2.active = true;
		
		playState.RegistrarUpdate(playState.gObj1);
		playState.RegistrarUpdate(playState.gObj2);
		
		for(int j = 0; j < 5; j++)
			for(int i = 0; i < playState.getUpdates().size(); i++)
				playState.getUpdates().get(i).PreUpdate();

		assertEquals(playState.gObj1.contadorUpdate,5);
		assertEquals(playState.gObj2.contadorUpdate,10);
	}

	/*
	 * Vamos a registrar 10 objetos en la lista Updates de un PlayState y luego vamos a 
	 * sacar 5, vamos a medir el tamaño de la lista y esperamos que el tamaño sea 5
	 */
	@Test
	public void testDesregistrarUpdate() {
		PlayStateTest2 playState = new PlayStateTest2("lalalala");

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
			playState.RegistrarUpdate(gameObjects[i]);

		playState.DesregistrarUpdate(gameObjects[0]);
		playState.DesregistrarUpdate(gameObjects[7]);
		playState.DesregistrarUpdate(gameObjects[3]);
		playState.DesregistrarUpdate(gameObjects[3]);
		playState.DesregistrarUpdate(gameObjects[2]);
		playState.DesregistrarUpdate(gameObjects[1]);
		
		int suma = 0;

		assertEquals(playState.getUpdates().size(),5);

		for(int i = 0; i < playState.getUpdates().size(); i++)
		{
			suma += ((GameObject)playState.getUpdates().get(i)).x;
		}
		
		assertEquals(suma,32);
	}

	/*
	 * Vamos a crear un PlayState con 3 GameObjects, cada uno va a tener un Update donde aumentan
	 * un contador, el primero de a 1, el segundo de a 2 y el tercero de a 3
	 * Vamos a llamar 5 veces a cada Update, luego vamos a desactivar uno y vamos a llamar
	 * otras 5 veces al Pre Update
	 * A todo esto, el Update del PlayState va a estar sumando todos los numeros que den
	 * los contadores de los Objetos que tiene dentro
	 */
	@Test
	public void testNotifyUpdate() {
		PlayStateTest2 playState = new PlayStateTest2("prueba");
		playState.gObj1.active = true;
		playState.gObj2.active = true;
		playState.gObj3.active = true;
		
		playState.RegistrarUpdate(playState.gObj1);
		playState.RegistrarUpdate(playState.gObj2);
		playState.RegistrarUpdate(playState.gObj3);
		
		for(int i = 0; i < 5; i++)
			playState.preUpdate2();
		
		playState.gObj3.active = false;
		
		for(int i = 0; i < 5; i++)
			playState.preUpdate2();
		
		assertEquals(playState.gObj1.contadorUpdate,10);
		assertEquals(playState.gObj2.contadorUpdate,20);
		assertEquals(playState.gObj3.contadorUpdate,15);
		assertEquals(playState.suma,6 + 12 + 18 + 24 + 30 + 33 + 36 + 39 + 42 + 45);
	}

	/*
	 * Vamos a crear un PlayStateTest2, luego le vamos a registrar 5 Controllers, vamos a
	 * comprobar que la lista de Controllers tiene 5.
	 */
	@Test
	public void testRegistrarController() {
		PlayStateTest2 playState = new PlayStateTest2("cara de pan");
		playState.RegistrarController(new Controller());
		playState.RegistrarController(new Controller());
		playState.RegistrarController(new Controller());
		playState.RegistrarController(new Controller());
		playState.RegistrarController(new Controller());
		
		assertEquals(playState.getControllers().size(),5);
	}

	/*
	 * Vamos a crear un PlayStateTest2, luego le vamos a registrar 7 Controllers desde un
	 * arreglo de Controllers, vamos a desregistrar 3 y luego vamos a comprobar que la
	 * lista tiene 4 Controllers.
	 */
	@Test
	public void testDesregistrarController() {
		Controller[] controller = new Controller[]{
				new Controller(),
				new Controller(),
				new Controller(),
				new Controller(),
				new Controller(),
				new Controller(),
				new Controller(),
		};
		
		PlayStateTest2 playState = new PlayStateTest2("Cara de pan");
		
		for(int i = 0; i < controller.length; i++)
			playState.RegistrarController(controller[i]);

		playState.DesregistrarController(controller[0]);
		playState.DesregistrarController(controller[0]);
		playState.DesregistrarController(controller[4]);
		playState.DesregistrarController(controller[0]);
		playState.DesregistrarController(controller[3]);
		playState.DesregistrarController(controller[3]);
		playState.DesregistrarController(controller[0]);
		
		assertEquals(playState.getControllers().size(), 4);
	}

	/*
	 * Creamos 3 ControllersTest2 y los registramos en un playState, dentro del
	 * UpdateGameLoop() de este sumamos un contador "controllerContador"
	 * hacemos 4 NotifyController, finalmente
	 */
	@Test
	public void testNotifyController() {
		ControllerTest2[] controllers = new ControllerTest2[]{
				new ControllerTest2(),
				new ControllerTest2(),
				new ControllerTest2(),
		};
		
		PlayState playState = new PlayState("Nombre de PlayState");
		
		for(int i = 0; i < 3; i++)
			playState.RegistrarController(controllers[i]);
		
		for(int i = 0; i < 4; i++)
			playState.NotifyController(0,false);
		
		int suma = 0;
		for(int i = 0; i < 3; i++)
			suma += controllers[i].controllerCounter;
		
		assertEquals(suma,12);
	}

}
