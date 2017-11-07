package FrameWork;
import java.util.List;

/**
 * Son objetos complementarios a los GameObjects, que estan encargados exclusivamente
 * de controlar y tener en cuenta cual es la imagen que estos estan mostrando.
 * Una vez que se setearon que Sprites van en la Animacion, no se puede cambiar.
 */
public class Animation {

	/**
	 * Sprites que conforman la animacion
	 */
	public List<Sprite> sprites;
	
	/**
	 * Velocidad con la que corre la Animacion, sirve para calcular el tiempo maximo de espera
	 * para pasar al Sprite siguiente.
	 */
	public float duration;
	
	/* Variable de control del tiempo dentro del objeto*/
	private float time;
	
	/* Variable que controla en que frame estamos*/
	public int frame;
	
	/**
	 * La Animacion Loopea?
	 */
	public boolean loop;
	
	/**
	 * Variable para frenar la Animacion
	 */
	public boolean stop;
	
	/**
	 * La animacion termino? (Solo sirve si la animacion No loopea)
	 */
	public boolean finish;
	
	/**
	 * Toma en cuenta el deltaTime y aumenta el contador, segun cuanto tiempo paso decide que hacer.
	 */
	public Sprite Update()
	{
		//Si la animacion esta frenada no hacemos nada.
		if(stop)
			return sprites.get(frame);
		
		//Toma el tiempo desde el ultimo frame.
		time += GameControl.getGameControl().getDeltaTime();

		//Se fija si debemos pasar al proximo frame
		if(time > duration/sprites.size() && (!finish || loop))
		{
			time = 0;
			//Estabamos ya en el ultimo frame?
			if(frame == sprites.size() - 1)
			{
				//Loopea? entonces debemos volver al primer frame
				if(loop)
				{
					frame = 0;
				}
				//No Loopea? entonces debemos terminar todo ahi y poner finish = true.
				else
				{
					finish = true;
				}
			}
			//Si no era el ultimo frame, seguimos con el siguiente
			else
			{
				frame++;
			}
		}
		
		return sprites.get(frame);
	}
	
	/**
	 * Constructor de la Clase
	 * @param sprites
	 * Debe convertir la lista sprites en un arreglo y guardarlo.
	 */
	public Animation(List<Sprite> sprites, boolean loop)
	{
		//Asignamos todos los valores por default.
		this.sprites = sprites;
		time = 0;
		frame = 0;
		duration = 1;
		this.loop = loop;
		finish = false;
		stop = false;
	}
}
