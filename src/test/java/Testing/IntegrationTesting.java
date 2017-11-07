package Testing;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import Demo.DemoMainMenu;
import FrameWork.Animation;
import FrameWork.Controller;
import FrameWork.GameControl;
import FrameWork.GameObject;
import FrameWork.KeyBoardControl;
import FrameWork.Model;
import FrameWork.MouseControl;
import FrameWork.PlayState;
import FrameWork.Sprite;
import FrameWork.UserActions;
import FrameWork.View;
import Profiler.ProfilerObject;

public class IntegrationTesting {

	/**
	 * Constructor de la clase de Testing de Integracion
	 */
	public IntegrationTesting()
	{
		
	}
	
	/**
	 * Testeamos la Integracion de Los componentes: GameObject - View - Sprite
	 */
	public void testViewGameObject()
	{
		View view = new View(300,300, "Game");

		GameControl.getGameControl().frameRate = 60;
		GameObject gObj = new GameObject(0,0,new Sprite("C:\\Boton_Play.png"));
		gObj.visible = true;
		view.RegistrarDraw(gObj);
		view.Update();
	}

	public class PlayStateTestAnimation extends PlayState{

		public PlayStateTestAnimation(String name) {
			super(name);
			// TODO Auto-generated constructor stub
		}
		
		public void Start()
		{
			GameControl.getGameControl().addToGame(true, true, false, new GameObjectTestAnimation(0,0,100,100,false,1f));
			GameControl.getGameControl().addToGame(true, true, false, new GameObjectTestAnimation(100,0,100,100,true,1f));
			GameControl.getGameControl().addToGame(true, true, false, new GameObjectTestAnimation(0,100,100,100,false,5f));
			GameControl.getGameControl().addToGame(true, true, false, new GameObjectTestAnimation(100,100,100,100,true,5f));
		}
		
	}
	
	public class GameObjectTestAnimation extends GameObject{

		boolean loops;
		float duration;
		
		public GameObjectTestAnimation(int x, int y, int width, int height, boolean loops, float duration) {
			super(x, y, width, height);
			// TODO Auto-generated constructor stub
			
			this.loops = loops;
			this.duration = duration;
		}
		
		public void Start()
		{
			List<Sprite> sprites = new ArrayList<Sprite>();
			
			sprites.add(new Sprite("C:\\TestImage_0.png"));
			sprites.add(new Sprite("C:\\TestImage_1.png"));
			sprites.add(new Sprite("C:\\TestImage_2.png"));
	
			animation = new Animation(sprites, loops);
			sprite = animation.sprites.get(0);
			animation.duration = duration;
		}
		
		public void Update()
		{
			if(animation.finish)
			{
				System.out.println("Termino animacion con FrameRate: " + duration);
				active = false;
			}
		}
	}

	/**
	 * Testeamos la integracion de los componentes: Game - GameControl - Animation
	 */
	public void testAnimation()
	{
		int initWindowWidth = 480;
		int initWindowHeight = 800;
		
		View view = new View(initWindowWidth,initWindowHeight, "Game");

		Model.getModel().RegistrarView(view);
		Controller controller = new Controller();
		
		GameControl.getGameControl().controller = controller;
		GameControl.getGameControl().frameRate = 60;
		GameControl.getGameControl().views.add(view);

		PlayStateTestAnimation playState = new PlayStateTestAnimation("MainMenu");

		GameControl.getGameControl().changePlayState(playState);
	}
	
	public class ControllerInputsTest extends Controller{
		@Override
		public void UpdateInput(int userAction, boolean condition) {
			// TODO Auto-generated method stub
			super.UpdateInput(userAction, condition);
			System.out.println(userAction + " -  " + condition);
		}
	}
	/**
	 * Testeamos con un Controller que imprime en patalla cuales son las teclas que entraron
	 */
	public void testInputs()
	{
		int initWindowWidth = 480;
		int initWindowHeight = 800;
		
		View view = new View(initWindowWidth,initWindowHeight, "Game");

		Model.getModel().RegistrarView(view);
		ControllerInputsTest controller = new ControllerInputsTest();
		
		KeyBoardControl keyBoard = new KeyBoardControl();
		keyBoard.RegistrarController(controller);
		
		view.ChangeKeyListener(keyBoard);
		view.ChangeKeyListener(keyBoard);
		view.ChangeKeyListener(keyBoard);
		view.ChangeKeyListener(keyBoard);
	}

	public class PlayStateTetsCPS1 extends PlayState {

		public PlayStateTetsCPS1(String name) {
			super(name);
			// TODO Auto-generated constructor stub
		}
		
		@Override
		public void Start()
		{
			GameObjectTestCPS1 gameObject = new GameObjectTestCPS1(50,50,new Sprite("C:\\Boton_Play.png"));
			GameControl.getGameControl().addToGame(true, true, false, gameObject);
		}
		
	}

	public class PlayStateTetsCPS2 extends PlayState {

		public PlayStateTetsCPS2(String name) {
			super(name);
			// TODO Auto-generated constructor stub
		}
		
		@Override
		public void Start()
		{
			GameObjectTestCPS2 gameObject = new GameObjectTestCPS2(50,50,new Sprite("C:\\Boton_Play.png"));
			GameControl.getGameControl().addToGame(true, true, false, gameObject);
		}
		
	}
	
	public class GameObjectTestCPS1 extends GameObject {

		public GameObjectTestCPS1(int x, int y, Sprite sprite) {
			super(x, y, sprite);
			// TODO Auto-generated constructor stub
		}
		
		@Override
		public void Update()
		{
			if(Model.getModel().getStatePressed(UserActions.Escape))
			{
				GameControl.getGameControl().changePlayState(new PlayStateTetsCPS2("Nombre2"));
			}

			x += GameControl.getGameControl().getDeltaTime();
		}
	}
	
	public class GameObjectTestCPS2 extends GameObject {

		public GameObjectTestCPS2(int x, int y, Sprite sprite) {
			super(x, y, sprite);
			// TODO Auto-generated constructor stub
		}
		
		@Override
		public void Update()
		{
			if(Model.getModel().getStatePressed(UserActions.Escape))
			{
				GameControl.getGameControl().changePlayState(new PlayStateTetsCPS1("Nombre1"));
			}
			
			y += GameControl.getGameControl().getDeltaTime();
		}
	}	
	/**
	 * Este test creara un PlayState con un objeto que se mueve hacia la derecha y luego de
	 * que se apreta un boton, se creara un PlayState con un objeto que se mueve hacia la 
	 * izquirda.
	 */
	public void testChangePlayState()
	{
		int initWindowWidth = 480;
		int initWindowHeight = 800;
		
		View view = new View(initWindowWidth,initWindowHeight, "Game");

		Model.getModel().RegistrarView(view);
		Controller controller = new Controller();
		
		KeyBoardControl keyBoard = new KeyBoardControl();
		keyBoard.RegistrarController(controller);
		
		view.ChangeKeyListener(keyBoard);
		
		GameControl.getGameControl().frameRate = 60;
		GameControl.getGameControl().views.add(view);
		GameControl.getGameControl().controller = controller;

		PlayStateTetsCPS1 playState = new PlayStateTetsCPS1("MainMenu");

		GameControl.getGameControl().changePlayState(playState);
	}
	
	public class PlayStateGOInputs extends PlayState{
	
		GameObject gObj;
		
		public PlayStateGOInputs(String name) {
				super(name);
				// TODO Auto-generated constructor stub
		}
		
		@Override
		public void Start()
		{
			gObj = new GameObject(50,50, new Sprite("C:\\Boton_Play.png"));
			GameControl.getGameControl().addToGame(false, true, false, gObj);
		}
		
		@Override
		public void Update()
		{
			if(Model.getModel().getStatePressed(UserActions.ArrowRight))
				gObj.x += GameControl.getGameControl().getDeltaTime();
			if(Model.getModel().getStatePressed(UserActions.ArrowLeft))
				gObj.x -= GameControl.getGameControl().getDeltaTime();
			if(Model.getModel().getStatePressed(UserActions.ArrowDown))
				gObj.y += GameControl.getGameControl().getDeltaTime();
			if(Model.getModel().getStatePressed(UserActions.ArrowUp))
				gObj.y -= GameControl.getGameControl().getDeltaTime();
			
		}
	}
	
	/**
	 * Vamos a crear un PlayState que tiene un objeto, y el PlayState en su update lo mueve
	 * segun la direccion de la tecla que se esta apretando.
	 */
	public void testGameObjectInputs()
	{
		int initWindowWidth = 1000;
		int initWindowHeight = 1000;
		
		View view = new View(initWindowWidth,initWindowHeight, "Game");

		Model.getModel().RegistrarView(view);
		Controller controller = new Controller();
		
		KeyBoardControl keyBoard = new KeyBoardControl();
		keyBoard.RegistrarController(controller);
		
		view.ChangeKeyListener(keyBoard);
		
		GameControl.getGameControl().frameRate = 60;
		GameControl.getGameControl().views.add(view);
		GameControl.getGameControl().controller = controller;

		PlayStateGOInputs playState = new PlayStateGOInputs("MainMenu");
		GameControl.getGameControl().changePlayState(playState);
	}
	
	public class PlayStateTCollitions extends PlayState{

		public PlayStateTCollitions(String name) {
			super(name);
			// TODO Auto-generated constructor stub
		}
		
		@Override
		public void Start()
		{
			GameObjectTCollitions gObj1 = new GameObjectTCollitions(150,100,new Sprite("C:\\TestImage_0.png"));
			GameObject gObj2 = new GameObject(0,100,new Sprite("C:\\TestImage_0.png"));
			GameObject gObj3 = new GameObject(400,100,new Sprite("C:\\TestImage_0.png"));
			
			GameControl.getGameControl().addToGame(true, true, true, gObj1);
			GameControl.getGameControl().addToGame(false, true, true, gObj2);
			GameControl.getGameControl().addToGame(false, true, true, gObj3);
		}
	}
	
	public class GameObjectTCollitions extends GameObject{

		public float speed = 1;
		
		public GameObjectTCollitions(int x, int y, Sprite sprite) {
			super(x, y, sprite);
			// TODO Auto-generated constructor stub
		}
		
		@Override
		public void Update()
		{
			x += speed * GameControl.getGameControl().getDeltaTime();
		}
		
		@Override
		public void OnCollition()
		{
			if(speed == 1)
			{
				speed = -1;
			}
			else
			{
				speed = 1;
			}
		}
	}
	
	/**
	 * Vamos a hacer un objeto que se mueve desde la derecha a la izquierda y cuando colliciona
	 * con uno que esta a la derecha comienza a moverse para el otro lado, luego hay un objeto
	 * a la izquierda que tambien le produce el mismo efecto.
	 */
	public void testCollitions(){
		int initWindowWidth = 1000;
		int initWindowHeight = 1000;
		
		View view = new View(initWindowWidth,initWindowHeight, "Game");

		Model.getModel().RegistrarView(view);
		Controller controller = new Controller();
				
		GameControl.getGameControl().frameRate = 60;
		GameControl.getGameControl().views.add(view);
		GameControl.getGameControl().controller = controller;

		PlayStateTCollitions playState = new PlayStateTCollitions("MainMenu");
		GameControl.getGameControl().changePlayState(playState);	
	}

	public class PlayStateAU extends PlayState{

		public PlayStateAU(String name) {
			super(name);
			// TODO Auto-generated constructor stub
		}
		
		GameObject gObj;
		boolean jumpWasPreesed = false;
		boolean shootWasPreesed = false;
		
		public void Start()
		{
			gObj = new GameObject(0,0,100,100);
			List<Sprite> sprites = new ArrayList<Sprite>();

			sprites.add(new Sprite("C:\\TestImage_0.png"));
			sprites.add(new Sprite("C:\\TestImage_1.png"));
			sprites.add(new Sprite("C:\\TestImage_2.png"));
			
			gObj.animation = new Animation(sprites,true);
			GameControl.getGameControl().addToGame(true, true, false, gObj);
		}
		
		public void Update()
		{
			if(Model.getModel().getStatePressed(UserActions.Jump) && !jumpWasPreesed)
			{
				gObj.active = !gObj.active;
				jumpWasPreesed = true;
			}
			
			if(Model.getModel().getStatePressed(UserActions.Shoot) && !shootWasPreesed)
			{
				gObj.animation.stop = !gObj.animation.stop;
				shootWasPreesed = true;
			}
			
			if(!Model.getModel().getStatePressed(UserActions.Jump))
				jumpWasPreesed = false;

			if(!Model.getModel().getStatePressed(UserActions.Shoot))
				shootWasPreesed = false;
		}
	}
	
	/**
	 * Este test nos permite ver los estados de Update de una animacion y de Stop del Update
	 * del objeto cuando este esta desactivado o activado.
	 */
	public void testAnimationUpdates()
	{
		int initWindowWidth = 1000;
		int initWindowHeight = 1000;
		
		View view = new View(initWindowWidth,initWindowHeight, "Game");

		Model.getModel().RegistrarView(view);
		Controller controller = new Controller();
				
		KeyBoardControl keyBoard = new KeyBoardControl();
		keyBoard.RegistrarController(controller);
		
		view.ChangeKeyListener(keyBoard);
		
		GameControl.getGameControl().frameRate = 60;
		GameControl.getGameControl().views.add(view);
		GameControl.getGameControl().controller = controller;

		PlayStateAU playState = new PlayStateAU("MainMenu");
		GameControl.getGameControl().changePlayState(playState);	
	}
	
	public class PlayStateButton extends PlayState{

		public PlayStateButton(String name) {
			super(name);
			// TODO Auto-generated constructor stub
		}
		
		public void Start()
		{
			GameControl.getGameControl().addToGame(true, true, false,
				new GameObjectButton(0,0,new Sprite("C:\\Boton_Play.png")));
		}
	}
	
	public class GameObjectButton extends GameObject{

		public boolean isClicked = false;
		
		public GameObjectButton(int x, int y, Sprite sprite) {
			super(x, y, sprite);
			// TODO Auto-generated constructor stub
		}
		
		public void Update()
		{
			if(!isClicked && Model.getModel().getStatePressed(UserActions.LeftClick)
				&& x < Model.getModel().X && Model.getModel().X < x + width 
				&& y < Model.getModel().Y && Model.getModel().Y < y + height)
			{
				isClicked = true;
				OnClick();
			}
			else if(!Model.getModel().getStatePressed(UserActions.LeftClick))
			{
				isClicked = false;
			}
				
		}
		
		public class GameObjectCreatedOnClick extends GameObject{

			public GameObjectCreatedOnClick(int x, int y, Sprite sprite) {
				super(x, y, sprite);
				// TODO Auto-generated constructor stub
			}
			
			public void Update()
			{
				x += GameControl.getGameControl().getDeltaTime();
			}
		}
		
		/**
		 * Este Metodo es para que reaccione al Pressed del Mouse.
		 */
		public void OnClick()
		{
			GameControl.getGameControl().addToGame(true, true, false,
				new GameObjectCreatedOnClick(100,100,new Sprite("C:\\Boton_Play.png")));
		}
	}
	
	/**
	 * Creamos un Boton que cuando lo apretamos genera un GameObject que se mueve hacia la derecha
	 */
	public void testButton()
	{
		int initWindowWidth = 1000;
		int initWindowHeight = 1000;
		
		View view = new View(initWindowWidth,initWindowHeight, "Game");

		Model.getModel().RegistrarView(view);
		Controller controller = new Controller();
				
		MouseControl mouse = new MouseControl();
		mouse.RegistrarController(controller);
		
		view.ChangeMouseListener(mouse);
		
		GameControl.getGameControl().frameRate = 60;
		GameControl.getGameControl().views.add(view);
		GameControl.getGameControl().controller = controller;

		PlayStateButton playState = new PlayStateButton("MainMenu");
		GameControl.getGameControl().changePlayState(playState);	
	}
	
	public class PlayStateRemove extends PlayState{

		public PlayStateRemove(String name) {
			super(name);
			// TODO Auto-generated constructor stub
		}
		
		public void Start()
		{
			GameControl.getGameControl().addToGame(false, false, true,
					new GameObject(500,0,50,500));

			GameControl.getGameControl().addToGame(true, true, true,
					new GameObjectDestroyOnCollition(0,50,new Sprite("C:\\Boton_Play.png")));
		}
		
		public class GameObjectDestroyOnCollition extends GameObject{

			public GameObjectDestroyOnCollition(int x, int y, Sprite sprite) {
				super(x, y, sprite);
				// TODO Auto-generated constructor stub
			}
			
			public void Update(){
				x += GameControl.getGameControl().getDeltaTime();
			}
			
			public void OnCollition(){
				GameControl.getGameControl().removeFromGame(this);
			}
		}
	}
	
	/**
	 * Creamos 2 objetos y cuando uno coliciona con el otro lo borra del juego.
	 */
	public void testDesaparecerGameObject()
	{
		int initWindowWidth = 1000;
		int initWindowHeight = 1000;
		
		View view = new View(initWindowWidth,initWindowHeight, "Game");

		Model.getModel().RegistrarView(view);
		Controller controller = new Controller();
				
		GameControl.getGameControl().frameRate = 60;
		GameControl.getGameControl().views.add(view);
		GameControl.getGameControl().controller = controller;

		PlayStateRemove playState = new PlayStateRemove("MainMenu");
		GameControl.getGameControl().changePlayState(playState);	
	}
	
	public class PlayStateChangeInputs extends PlayState{

		public PlayStateChangeInputs(String name) {
			super(name);
			// TODO Auto-generated constructor stub
		}
		
		public class GameObjectMovable extends GameObject{

			public GameObjectMovable(int x, int y, Sprite sprite) {
				super(x, y, sprite);
				// TODO Auto-generated constructor stub
			}
			
			@Override
			public void Update()
			{
				if(Model.getModel().getStatePressed(UserActions.ArrowRight))
					x += GameControl.getGameControl().getDeltaTime();
				if(Model.getModel().getStatePressed(UserActions.ArrowLeft))
					x -= GameControl.getGameControl().getDeltaTime();
				if(Model.getModel().getStatePressed(UserActions.ArrowDown))
					y += GameControl.getGameControl().getDeltaTime();
				if(Model.getModel().getStatePressed(UserActions.ArrowUp))
					y -= GameControl.getGameControl().getDeltaTime();
			}
		}
		
		public void Start()
		{
			GameControl.getGameControl().addToGame(true, true, false, new GameObjectMovable(0,0,
					new Sprite("C:\\Boton_Play.png")));
		}
		
		boolean input1 = true;
		boolean isClicked = false;

		public class KeyBoardControl2 extends KeyBoardControl{

			public KeyBoardControl2()
			{
				super();
				keys[UserActions.ArrowRight] = KeyEvent.VK_D;
				keys[UserActions.ArrowUp] = KeyEvent.VK_W;
				keys[UserActions.ArrowLeft] = KeyEvent.VK_A;
				keys[UserActions.ArrowDown] = KeyEvent.VK_S;
			}
		}

		public void Update()
		{
			if(Model.getModel().getStatePressed(UserActions.Escape) && !isClicked)
			{
				if(input1)
				{
					GameControl.getGameControl().ChangeInputs(new KeyBoardControl2());
				}
				else
				{
					GameControl.getGameControl().ChangeInputs(new KeyBoardControl());
				}

				input1 = !input1;
				isClicked = true;
			}
			else if(!Model.getModel().getStatePressed(UserActions.Escape))
			{
				isClicked = false;
			}
		}
	}
	
	
	/*
	 * Creamos un objeto que se mueve con las teclas del teclado, y un KeyBoardControl, luego
	 * cambiamos entre 2 KeyBoardControllers con diferentes teclas de Arrows.
	 */
	public void testChangeInputs()
	{
		int initWindowWidth = 1000;
		int initWindowHeight = 1000;
		
		View view = new View(initWindowWidth,initWindowHeight, "Game");

		Model.getModel().RegistrarView(view);
		Controller controller = new Controller();

		GameControl.getGameControl().views.add(view);
		GameControl.getGameControl().controller = controller;
		GameControl.getGameControl().ChangeInputs(new KeyBoardControl());
		GameControl.getGameControl().frameRate = 60;

		PlayStateChangeInputs playState = new PlayStateChangeInputs("MainMenu");
		GameControl.getGameControl().changePlayState(playState);
	}

	public class PlayStateDepth extends PlayState{
		public PlayStateDepth(String name) {
				super(name);
				// TODO Auto-generated constructor stub
				
		}
		
		public void Start()
		{
			GameControl.getGameControl().addToGame(true, true, false,
					new GameObject(0,0,new Sprite("C:\\Boton_Play.png")));

			GameControl.getGameControl().addToGame(true, true, false,
					new GameObjectDepth(50,50,new Sprite("C:\\Boton_Play.png")));
		}
		
		public class GameObjectDepth extends GameObject{

			public GameObjectDepth(int x, int y, Sprite sprite) {
				super(x, y, sprite);
				// TODO Auto-generated constructor stub
				depth = 10;
			}
			
			boolean isClicked = false;
			
			public void Update()
			{
				if(!isClicked && Model.getModel().getStatePressed(UserActions.Enter))
				{
					isClicked = true;
					
					if(depth == -10)
						depth = 10;
					else if(depth == 10)
						depth = -10;

				}
				else if(!Model.getModel().getStatePressed(UserActions.Enter))
				{
					isClicked = false;
				}
			}
		}
	}
	
	/**
	 * Este test va a crear 2 objetos uno con depth de 0 y otro que cuando se apreta una tecla
	 * varia entre un depth de 10 y uno de -10.
	 */
	public void testGameObjectDepth()
	{
		int initWindowWidth = 1000;
		int initWindowHeight = 1000;
		
		View view = new View(initWindowWidth,initWindowHeight, "Game");
	
		Model.getModel().RegistrarView(view);
		Controller controller = new Controller();
	
		GameControl.getGameControl().views.add(view);
		GameControl.getGameControl().controller = controller;
		GameControl.getGameControl().ChangeInputs(new KeyBoardControl());
		GameControl.getGameControl().frameRate = 60;
	
		GameControl.getGameControl().changePlayState(new PlayStateDepth("MainMenu"));
	}
	
	public class PlayStateJustPressed extends PlayState{

		public PlayStateJustPressed(String name) {
			super(name);
			// TODO Auto-generated constructor stub
		}
		
		public void Start()
		{
			GameControl.getGameControl().addToGame(true, true, false, 
					new GameObjectJustPressed(50,50,new Sprite("C:\\Boton_Play.png")));
		}
		
		public class GameObjectJustPressed extends GameObject{

			public GameObjectJustPressed(int x, int y, Sprite sprite) {
				super(x, y, sprite);
				// TODO Auto-generated constructor stub
			}
			
			int angle = 0;
			
			public void Update()
			{
				switch(angle)
				{
				case 0:
					x += GameControl.getGameControl().getDeltaTime();
					break;
				case 1:
					y -= GameControl.getGameControl().getDeltaTime();
					break;
				case 2:
					x -= GameControl.getGameControl().getDeltaTime();
					break;
				case 3:
					y += GameControl.getGameControl().getDeltaTime();
					break;
				}
				
				if(Model.getModel().getStateJustPressed(UserActions.Enter))
				{
					angle++;
					if(angle > 3)
						angle = 0;
				}
			}
		}
	}
	
	/**
	 * La idea de este test es comprobar que utilizando las teclas como "JustReleased" en los
	 * inputs, no necesito verificar mas el estado de apretado o no de una tecla.
	 */
	public void testJustPressedKey()
	{
		int initWindowWidth = 1000;
		int initWindowHeight = 1000;
		
		View view = new View(initWindowWidth,initWindowHeight, "Game");
	
		Model.getModel().RegistrarView(view);
		Controller controller = new Controller();
	
		GameControl.getGameControl().views.add(view);
		GameControl.getGameControl().controller = controller;
		GameControl.getGameControl().ChangeInputs(new KeyBoardControl());
		GameControl.getGameControl().frameRate = 60;
	
		GameControl.getGameControl().changePlayState(new PlayStateJustPressed("MainMenu"));
	}
	
	public class ProfilerTesting extends PlayState{

		public ProfilerTesting(String name) {
			super(name);
			// TODO Auto-generated constructor stub
		}
		
		public class GameObjectProfiler extends GameObject{

			public GameObjectProfiler(int x, int y, int width, int height) {
				super(x, y, width, height);
				// TODO Auto-generated constructor stub
			}
			
			public void Update()
			{
				for(int i = 0; i < 100; i++)
					System.out.println("LALA");
			}
		}
		
		public void Update()
		{
			if(Model.getModel().getStateJustPressed(UserActions.Enter))
			{
				GameObjectProfiler profiler = new GameObjectProfiler(0,0,0,0);
				GameControl.getGameControl().addToGame(true, false, false, profiler);
			}
		}
	}
	
	public void testProfiler()
	{
		int initWindowWidth = 800;
		int initWindowHeight = 480;
		
		View view = new View(initWindowWidth,initWindowHeight, "Game");
		
		Model.getModel().RegistrarView(view);
		GameControl.getGameControl().views.add(view);
		
		Controller controller = new Controller();

		GameControl.getGameControl().controller = controller;
		GameControl.getGameControl().ChangeInputs(new KeyBoardControl());
		GameControl.getGameControl().ChangeInputs(new MouseControl());
		GameControl.getGameControl().frameRate = 60;
		
		new ProfilerObject(300,300);
		
		GameControl.getGameControl().changePlayState(new ProfilerTesting("MainMenu"));
	}
}
