package FrameWork;
import java.util.ArrayList;
import java.util.List;

/**
 * Son Hilos que tienen estados de juego dentro, tienen todos los GameObject que se estan
 * ejecutando en ese instante en el juego.
 * Se encarga de hacer Update a los GameObjects y controlar sus Coliciones.
 * Sobreescribiendo la funcion "Update()" se puede hacer que ademas controlen variables de juego
 * o creen GameObjects, todo en frames por segundo.
 */

public class PlayState extends Thread implements UpdateSubject, CollideSubject, ControllerSubject{

	/**
	 * Nombre para Identificar en que estado de juego estamos y luego poder movernos de uno en otro
	 */
	public String name;
	
	/**
	 * Indica si el Hilo esta frenado o puede hacer algo
	 */
	public boolean runningState;
	
	/**
	 * Variacion de tiempo que hubo entre este frame y el anterior
	 */
	public float deltaTime;
	
	/* Lista con los GameObjects a los que se les tienen que hacer Update */
	protected List<GameObjectObserver> updateGoObservers;

	/* Lista con los GameObjects a los que se les tienen que revisar Collisiones y notificar si coliciono */
	protected List<GameObjectObserver> 	collitionGoObservers;

	/* Lista con los Controllers a los que se les tienen que hacer UpdateGameLoop */
	protected List<ControllerObserver> 	controllersObservers;

	/**
	 * Constructor de la Clase, requiere del nombre del PlayState
	 * @param name
	 * Nombre que se le va a dar al Estado de Juego
	 */
	public PlayState(String name)
	{
		this.name = name;
		runningState = true;
		updateGoObservers = new ArrayList<GameObjectObserver>();
		collitionGoObservers = new ArrayList<GameObjectObserver>();
		controllersObservers = new ArrayList<ControllerObserver>();
		Awake();
	}
	
	/**
	 * Este Metodo se llama cada vez que paso un frame y se puede sobreescribir para que
	 * realice acciones por frame.
	 * Se llama al finalizar el GameLoop.
	 */
	protected void Update()
	{
		//Este metodo es solo para que los Sobreescriban los Hijos
	}
	
	/**
	 * Este Metodo es el que debe generar el GameLoop.
	 */
	@Override
	public void run()
	{
		
		//Este es el ciclo que hace que los hilos hagan el GameLoop o no
		long beginLoopTime;
		long endLoopTime;
		long currentUpdateTime = System.nanoTime();
		long lastUpdateTime;
		long deltaLoop;

		while(runningState)
		{
			//Seteamos los Inputs para este frame
			Model.getModel().setInputs();
			
			//Medimos el tiempo en que se inicia el frame.
			beginLoopTime = System.nanoTime();
			
			//Seteamos el tiempo en el que se termino el frame anterior
	     	lastUpdateTime = currentUpdateTime;

	     	//Llamamos a las Colliciones, a los Updates y a la View
	     	NotifyCollition();
	     	NotifyUpdate();
	     	NotifyController(0,false);

	     	//Tomtamos el nuevo tiempo en el que se termino el grame rate.
	     	currentUpdateTime = System.nanoTime();
	     	
	     	//Guardamos el deltaTime: "Tiempo entre este frame y el frame anterior.
	     	deltaTime = (currentUpdateTime - lastUpdateTime)/(1000*1000);
	     
	     	//Tomamos el tiempo en que termino este frame.
	     	endLoopTime = System.nanoTime();
	     	
	     	//Guardamos el valor de duracion de todo el frame.
	     	deltaLoop = endLoopTime - beginLoopTime;

	     	//Este es el valor de tiempo ideal que estamos buscando que dure el frame.
	     	long desiredDeltaLoop = (1000*1000*1000)/((long)GameControl.getGameControl().getFrameRate());
		  
	     	//Si el frame tarndo mas de lo esperado seguimos con el siguiente
	     	if(deltaLoop > desiredDeltaLoop)
	     	{
	           //Do nothing. We are already late.
	     	}
	     	//Si el frame tardo menos de lo esperado entonces dormimos al PlayState por la
	     	//diferencia de tiempo restante que sobro.
	     	else
	     	{
	     		try
	     		{
	     			Thread.sleep((desiredDeltaLoop - deltaLoop)/(1000*1000));
	     		}
	     		catch(InterruptedException e)
	     		{
	     			e.printStackTrace();
	     		}
	     	}
		}
	}
	
	/**
	 * A este Metodo lo llama GameControl cada vez que el PlayState comienza a funcionar
	 */
	public void Start()
	{
		//Este metodo es solo para que los Sobreescriban los Hijos
	}

	/**
	 * A este Metodo lo llama GameControl apenas el objecto PlayState es creado.
	 */
	protected void Awake()
	{
		//Este metodo es solo para que los Sobreescriban los Hijos
	}

	/**
	 * A este Metodo lo llama GameControl cada vez que se sale de este estadod e juego y se va a otro.
	 */
	public void PlayStateOut()
	{
		//Este metodo es solo para que los Sobreescriban los Hijos
	}

	@Override
	public void RegistrarCollition(GameObjectObserver obs) {
		collitionGoObservers.add(obs);
	}

	@Override
	public void DesregistrarCollition(GameObjectObserver obs) {
		collitionGoObservers.remove(obs);
	}

	@Override
	public void NotifyCollition() {
		//Siguiendo el algoritmo de NotifyCollide():
		for(int i = 0; i < collitionGoObservers.size() - 1; i++)
		{
			for(int j = i + 1; j < collitionGoObservers.size(); j++)
			{
				GameObject aux = (GameObject)(collitionGoObservers.get(i));
				GameObject aux2 = (GameObject)(collitionGoObservers.get(j));
					//Si ambos colicionaron entonces llamo al OnCollition de ambos.
				if(aux.Collide(aux2))
				{
					aux.OnCollition();
					collitionGoObservers.get(j).OnCollition();
				}
			}
		}
	}

	@Override
	public void RegistrarUpdate(GameObjectObserver obs) {
		updateGoObservers.add(obs);
	}

	@Override
	public void DesregistrarUpdate(GameObjectObserver obs) {
		updateGoObservers.remove(obs);
	}

	@Override
	public void NotifyUpdate() {
		for(int i = 0; i < updateGoObservers.size(); i++)
		{
			updateGoObservers.get(i).PreUpdate();
		}
		
		Update();
	}

	@Override
	public void RegistrarController(ControllerObserver obs) {
		controllersObservers.add(obs);
	}

	@Override
	public void DesregistrarController(ControllerObserver obs) {
		controllersObservers.remove(obs);
	}

	@Override
	public void NotifyController(int ke, boolean condition) {
		for(int i = 0; i < controllersObservers.size(); i++)
			controllersObservers.get(i).UpdateGameLoop();
	}

	@Override
	public void ClearCollition() {
		collitionGoObservers.clear();
	}

	@Override
	public void ClearUpdate() {
		updateGoObservers.clear();
	}

	@Override
	public void NotifyPosition(int x, int y){};
}
