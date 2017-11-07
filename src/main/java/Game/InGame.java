package Game;

import FrameWork.PlayState;
import FrameWork.Sprite;
import FrameWork.UserActions;
import FrameWork.Util;

import java.util.ArrayList;
import java.util.List;

import Demo.Cactus;
import FrameWork.Animation;
import FrameWork.GameControl;
import FrameWork.GameObject;
import FrameWork.Model;

public class InGame extends PlayState {
	
    int depthCasa = 8;
    int depthPozo = 0;
    int depthCoin = 1;
    int depthMontana = 9;
    int depthPiso = 9;
	Character character;
	public SpeedController speedController;

	public InGame(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	
	PoolGenerator pozosPool;
	PoolGenerator coinPool;
	PoolGenerator casaPool;
	PoolGenerator montanaPool;
	PoolGenerator pisoPool;
	
	public void Start() {
		GameObject backGround = new GameObject(0,0, new Sprite("Assets/Images/Background.png"));
		backGround.depth = 100;
		GameControl.getGameControl().addToGame(false, true, false, backGround);
		
		
		List<Sprite> caballo = new ArrayList<Sprite>();
		for(int i = 1; i < 11; i++) {
			Sprite SpriteCaballo = new Sprite("Assets/Images/Character"+i+".png");
			caballo.add(SpriteCaballo);
		}
		
		Animation animation = new Animation(caballo,true);
		
		character = new Character (0,0, new Sprite("Assets/Images/Character1.png"));
		character.x = 200 - character.width/2;
		character.y= 480 - 70 - character.height;
		character.depth = 0;
		character.animation = animation;
		character.animation.duration = 0.4f;
		GameControl.getGameControl().addToGame(true, true, true, character);
	
		ButtonJump botonJump = new ButtonJump(0,0, new Sprite("Assets/Images/BotonJumpIdle.png"), new Sprite("Assets/Images/BotonJumpPressed.png"));
		botonJump.x = 740 - botonJump.width/2;
		botonJump.y = 420 - botonJump.height/2;
		botonJump.character = character;
		GameControl.getGameControl().addToGame(true, true, false, botonJump);
	
		speedController = new SpeedController();
		speedController.globalSpeed = -10;
		GameControl.getGameControl().addToGame(speedController);
		
		List<Poolable> montanas = new ArrayList<Poolable>();
		//Aca hay que crear montañas y agregarlas.
		Montana montana = new Montana(10000,10000,new Sprite("Assets/Images/montanas.png"));
		montanas.add(montana);
		speedController.addCinematic(montana);
		montana.speedController = speedController;
		montana.depth = depthMontana;
		GameControl.getGameControl().addToGame(true, true, false, montana);
		montana.Desactivar();
			
		montanaPool = new PoolGenerator(montanas);
		
		List<Poolable> pisos = new ArrayList<Poolable>();
		//Aca hay que crear montañas y agregarlas.
		Piso piso = new Piso(10000,10000,new Sprite("Assets/Images/piso.png"));
		pisos.add(piso);
		speedController.addCinematic(piso);
		piso.speedController = speedController;
		piso.depth = depthMontana;
		GameControl.getGameControl().addToGame(true, true, false, piso);
		piso.Desactivar();
			
		pisoPool = new PoolGenerator(pisos);
		
		List<Poolable> pozos = new ArrayList<Poolable>();
		//Aca hay que crear pozos y agergarselos.
		Pozo pozo = new Pozo(10000,10000,new Sprite("Assets/Images/Pozo.png"));
		pozos.add(pozo);
		speedController.addCinematic(pozo);
		pozo.speedController = speedController;
		pozo.depth = depthPozo;
		GameControl.getGameControl().addToGame(true, true, true, pozo);
		pozo.Desactivar();
			
		pozosPool = new PoolGenerator(pozos);
		
		List<Poolable> coins = new ArrayList<Poolable>();
		//Aca hay que crear monedas y agregarlas.
		Moneda coin = new Moneda(10000, 10000,new Sprite("Assets/Images/Sprite_Moneda_35x35.png"));
		coins.add(coin);
		speedController.addCinematic(coin);
		coin.speedController = speedController;
		coin.depth=depthCoin;
		GameControl.getGameControl().addToGame(true, true, true, coin);
		coin.Desactivar();

		coinPool = new PoolGenerator(coins);
				
		//Aca se crean las diferentes casas y se agregan.
		List<Poolable> casas = new ArrayList<Poolable>();

		Casa casa1 = new Casa(10000,10000, new Sprite("Assets/Images/Sprite_Casa1_210x326.png"));
		casas.add(casa1);
		speedController.addCinematic(casa1);
		casa1.speedController = speedController;
		casa1.depth=depthCasa;
		GameControl.getGameControl().addToGame(true, true, false, casa1);
		casa1.Desactivar();

		Casa casa2 = new Casa(10000,10000, new Sprite("Assets/Images/Sprite_Casa2_233x244.png"));
		casas.add(casa2);
		speedController.addCinematic(casa2);
		casa2.speedController = speedController;
		casa2.depth=depthCasa;
		GameControl.getGameControl().addToGame(true, true, false, casa2);
		casa2.Desactivar();
	
		Casa casa3 = new Casa(10000,10000, new Sprite("Assets/Images/Sprite_Casa3_272x298.png"));
		casas.add(casa3);
		speedController.addCinematic(casa3);
		casa3.speedController = speedController;
		casa3.depth=depthCasa;
		GameControl.getGameControl().addToGame(true, true, false, casa3);
		casa3.Desactivar();

		Casa casa4 = new Casa(10000,10000, new Sprite("Assets/Images/Sprite_Casa4_284x283.png"));
		casas.add(casa4);
		speedController.addCinematic(casa4);
		casa4.speedController = speedController;
		casa4.depth=depthCasa;
		GameControl.getGameControl().addToGame(true, true, false, casa4);
		casa4.Desactivar();

		Casa casa5 = new Casa(10000,10000, new Sprite("Assets/Images/Sprite_Casa5_334x292.png"));
		casas.add(casa5);
		speedController.addCinematic(casa5);
		casa5.speedController = speedController;
		casa5.depth=depthCasa;
		GameControl.getGameControl().addToGame(true, true, false, casa5);
		casa5.Desactivar();

		Casa casa6 = new Casa(10000,10000, new Sprite("Assets/Images/Sprite_Casa6_210x134.png"));
		casas.add(casa6);
		speedController.addCinematic(casa6);
		casa6.speedController = speedController;
		casa6.depth=depthCasa;
		GameControl.getGameControl().addToGame(true, true, false, casa6);
		casa6.Desactivar();

		Casa casa7 = new Casa(10000,10000, new Sprite("Assets/Images/Sprite_Casa7_232x198.png"));
		casas.add(casa7);
		speedController.addCinematic(casa7);
		casa7.speedController = speedController;
		casa7.depth=depthCasa;
		GameControl.getGameControl().addToGame(true, true, false, casa7);
		casa7.Desactivar();

		Casa casa8 = new Casa(10000,10000, new Sprite("Assets/Images/Sprite_Casa8_234x165.png"));
		casas.add(casa8);
		speedController.addCinematic(casa8);
		casa8.speedController = speedController;
		casa8.depth=depthCasa;
		GameControl.getGameControl().addToGame(true, true, false, casa8);
		casa8.Desactivar();

		casaPool = new PoolGenerator(casas);
				
	}	
	float minGenerateTimePiso = 1;
	float maxGenerateTimePiso = 1;
	
	float minGenerateTimeMontana = 2;
	float maxGenerateTimeMontana = 2;
	
	float minGenerateTimePozo = 3;
	float maxGenerateTimePozo = 5;
	
	float minGenerateTimeCasa = 0.4f;
	float maxGenerateTimeCasa = 1;
	
	float minGenerateTimeCoin = 1;
	float maxGenerateTimeCoin = 2;

	
	float timePozo = 0; 
	float timeCasa = 0;
	float timeCoin = 0; 
	float timeToWaitPozo = Util.RandomRange(minGenerateTimeMontana, maxGenerateTimeMontana);
	float timeToWaitPiso = Util.RandomRange(minGenerateTimePiso, maxGenerateTimePiso);
	float timeToWaitMontana = Util.RandomRange(minGenerateTimePozo, maxGenerateTimePozo);
	float timeToWaitCasa = Util.RandomRange(minGenerateTimeCasa, maxGenerateTimeCasa);
	float timeToWaitCoin = Util.RandomRange(minGenerateTimeCoin, maxGenerateTimeCoin);
	float timeMontana = timeToWaitMontana;
	float timePiso = timeToWaitPiso;
	
	int minCoin = 1;
	int maxCoin = 3;
	
	
	public void Update()
	{
		
		//Generador de Pozos
		if(timePozo > timeToWaitPozo)
		{
			pozosPool.getPoolObject();
			timePozo = 0;
			timeToWaitPozo = Util.RandomRange(minGenerateTimePozo, maxGenerateTimePozo);
		}
		else
		{
			timePozo += GameControl.getGameControl().getDeltaTime();
		}

		//Generador de monedas
		if(timeCoin > timeToWaitCoin)
		{
			Moneda coin = (Moneda) coinPool.getPoolObject();
			coin.x = 800;
			coin.y = 480/3;

			timeCoin = 0;
			timeToWaitCoin = Util.RandomRange(minGenerateTimeCoin, maxGenerateTimeCoin);
		}
		else
		{
			timeCoin += GameControl.getGameControl().getDeltaTime();
		}
		
		//Generador de Casas
		if(timeCasa > timeToWaitCasa)
		{
			//Implemente al Random directamente entre 0 y 9, porque es algo que no va a
			//cambiar.
			casaPool.getPoolObject();
			timeCasa = 0;
			timeToWaitCasa = Util.RandomRange(minGenerateTimeCasa, maxGenerateTimeCasa);
		}
		else
		{
			timeCasa += GameControl.getGameControl().getDeltaTime();
		}
		
		//Generador de Montañas
		if(timeMontana > timeToWaitMontana)
		{
			montanaPool.getPoolObject();
			timeMontana = 0;
			timeToWaitMontana = Util.RandomRange(minGenerateTimeMontana, maxGenerateTimeMontana);
		}
		else
		{
			timeMontana += GameControl.getGameControl().getDeltaTime();
		}
		
		//Generador de Pisos
		if(timePiso > timeToWaitPiso)
		{
			pisoPool.getPoolObject();
			timePiso = 0;
			timeToWaitPiso = Util.RandomRange(minGenerateTimePiso, maxGenerateTimePiso);
		}
		else
		{
			timePiso += GameControl.getGameControl().getDeltaTime();
		}
	}
	
}
