package FrameWork;
import java.awt.Canvas;
import java.awt.Graphics2D;

import Game.Moneda;

/**
 * Unidad Basica de creacion del Juego
 * Todo lo que se quiere mostrar en pantalla debe ser un GameObject
 * Todo lo que debe interactuar con otras cosas tambien debe ser un GameObject.
 * Se encuentran dispersos en listas que les van llamando metodos, haciendo que aparezcan, reaccionen,
 * checkeen coliciones, etc.
 */
public class GameObject implements GameObjectObserver{

	/**
	 * Posicion en 'x' del GameObject
	 */
	public float x;
	
	/**
	 * Posicion en 'y' del GameObject
	 */
	public float y;
	
	/**
	 * Tamaño en 'x' del GameObject
	 */
	public float width;

	/**
	 * Tamaño en 'y' del GameObject
	 */
	public float height;

	/**
	 * La animacion que va a estar guiando al GameObject, si no tiene una su Sprite por default
	 * se va a quedar en el mismo estado siempre, a menos que se cambie a mano.
	 * Una vez que la animacion se cambio, esta va a setear el sprite en el GameObject automaticamente.
	 */
	public Animation animation;
	
	/**
	 * Esta variable puede ser seteada en cualquier momento, no es recomendable que un objeto
	 * cambie esta propiedad ya que va a seguir en la lista de la View. Pero no la vamos a mostrar.
	 */
	public boolean visible;
	
	/**
	 * Esta variable puede ser seteada en cualquier momento, no es recomendable que un objeto
	 * cambie esta propiedad ya que va a seguir en la lista de updates del PlayState
	 * Pero no la vamos a mostrar.
	 */
	public boolean active;
	
	/**
	 * Esta variable puede ser seteada en cualquier momento, no es recomendable que un objeto
	 * cambie esta propiedad ya que va a seguir en la lista de coliciones del PlayState
	 * Pero no la vamos a mostrar.
	 */
	public boolean collides;
	
	/**
	 * Indica la profundidad en donde el objeto se va a dibujar
	 * Mientras el depth sea menor, se va a dibujar mas atras.
	 * Por defecto es 0.
	 */
	public int depth;
	
	/**
	 * Indica si el objeto tiene algun tipo de prioridad en cuanto a la ejecucion de su update
	 * respecto del resto de los GameObjects en ese PlayState. Por defecto es 0.
	 */
	public int updatePriority;
	
	/**
	 * Imagen que va a mostrar el GameObject en la canvas del juego.
	 * Si el GameObject tiene una Animacion, este valor va a cambiarse automaticamente.
	 */
	public Sprite sprite;
	
	/**
	 * Constructor de la clase
	 * @param x
	 * Posicion en 'x' desde la izquierda
	 * @param y
	 * Posicion en 'y' desde arriba hacia abajo
	 * @param width
	 * Tamaño del Objeto en 'x'
	 * @param height
	 * Tamaño del Objeto en 'y'
	 */
	public GameObject(int x, int y, int width, int height)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		depth = 0;
		updatePriority = 0;
		animation = null;
		sprite = null;
		Start();
	}
	
	/**
	 * Sobrecarga del Constructor de la clase si tiene un Sprite por defecto toma el width y el height
	 * del Sprite.
	 * @param x
	 * Posicion en 'x' desde la izquierda
	 * @param y
	 * Posicion en 'y' desde arriba hacia abajo
	 * @param sprite
	 * Sprite que lleva el Objeto.
	 */
	public GameObject(int x, int y, Sprite sprite)
	{
		this.x = x;
		this.y = y;
		width = sprite.width;
		height = sprite.height;
		depth = 0;
		updatePriority = 0;
		animation = null;
		this.sprite = sprite;
	}
		
	/**
	 * Se llama por cada Update del PlayState, mientras el objeto este activo
	 */
	public void Update()
	{
		//Este metodo es solo para que lo Sobreescriban hijos.
	}
	
	@Override
	public void PreUpdate() {

		//Si no esta activo el objeto no debemos hacer nada.
		if(!active)
			return;
		
		//Primero el objeto hace lo que se sobreescribio en el Update
		Update();
		
		//Luego si tiene animacion la actualiza
		if(animation != null)
			sprite = animation.Update();
	}

	@Override
	public void OnCollition() {
		//Este metodo es solo para que lo Sobreescriban hijos.
	}

	/*
	 * Debe hacer g.drawImage(Sprite.image).
	 */
	@Override
	public void Draw(Graphics2D grafics, Canvas canvas){

		//Si el objeto es invisible, entonces nunca lo renderizamos.
		if(!visible)
			return;
		
		//Mostramos el objeto en el canvas
		grafics.drawImage(sprite.image, (int)x, (int)y, canvas);
	}
	
	/**
	 * Hace la prueba segun x, y, ancho y alto si este GameObject con el parametro se estan solapando
	 * @param gObj
	 * Objecto con el que se quiere comprobar la colicion
	 * @return
	 * La condicion de si estaban en solapamiento o no.
	 */
	public boolean Collide(GameObject gObj)	{
		//Siguiendo el algoritmo de Collide:
		return (((gObj.x <= x && x <= (gObj.x + gObj.width)) || (x <= gObj.x && gObj.x <= (x + width))) &&
				((gObj.y <= y && y <= (gObj.y + gObj.height)) || (y <= gObj.y && gObj.y <= (y + height)))) 
				&& gObj.collides && collides;
	}
	
	/**
	 * Este Metodo se llama cuando comienza el PlayState y se crea el objeto en particular
	 * puede sobreescribirse para que pase algo cuando el objeto se cree.
	 */
	public void Start()	{
		//Este metodo es solo para que lo Sobreescriban hijos.
	}
	
}
