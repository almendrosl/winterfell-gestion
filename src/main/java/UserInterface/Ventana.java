package UserInterface;

import java.util.ArrayList;
import java.util.List;

import FrameWork.GameObjectProduct;
import FrameWork.Sprite;

/**
 * Esta clase toma una lista de botones y los hace aparecer y/o desaparecer a todos juntos
 * y toma otra lista de botones, que a partir de ese momento ya no aceptan inputs hasta que
 * la ventana se cierre
 */
public class Ventana extends UserInterface implements GameObjectProduct{

	private List<Button> buttonsActivar;
	private List<Button> buttonsDesactivar;
	
	/**
	 * Para crear un objeto ventana, tienen que agregarse todos los botones y luego agregarse
	 * al juego. No en el orden opuesto.
	 */
	public Ventana(int x, int y, Sprite sprite) {
		super(x, y, sprite);
		// TODO Auto-generated constructor stub
		buttonsActivar = new ArrayList<Button>();
		buttonsDesactivar = new ArrayList<Button>();
	}

	@Override
	public void Start()
	{
		visible = false;
		for(int i = 0; i < buttonsActivar.size(); i++)
		{
			buttonsActivar.get(i).active = false;
			buttonsActivar.get(i).visible = false;
			buttonsActivar.get(i).depth -= 5;
		}
	}
	
	/**
	 * Hace aparecer la ventana con todos los botones activos y desactiva el resto de los botones
	 */
	public void PopUp()
	{
		visible = true;
		for(int i = 0; i < buttonsActivar.size(); i++)
		{
			buttonsActivar.get(i).active = true;
			buttonsActivar.get(i).visible = true;
		}
		for(int i = 0; i < buttonsDesactivar.size(); i++)
		{
			buttonsDesactivar.get(i).active = false;
		}
	}
	
	/**
	 * Hace desaparecer la ventana con todos los botones activos y desactiva el resto de los botones
	 */
	public void PopDown()
	{
		visible = false;
		for(int i = 0; i < buttonsActivar.size(); i++)
		{
			buttonsActivar.get(i).active = false;
			buttonsActivar.get(i).visible = false;
		}
		for(int i = 0; i < buttonsDesactivar.size(); i++)
		{
			buttonsDesactivar.get(i).active = true;
		}
	}
	
	/**
	 * Agrega un boton a los que pertenecen a la ventana.
	 * @param button
	 * El boton que vamos a agregar.
	 */
	public void addButtonActivar(Button button)
	{
		buttonsActivar.add(button);
	}

	/**
	 * Agrega un boton a los que NO pertenecen a la ventana.
	 * @param button
	 * El boton que vamos a agregar.
	 */
	public void addButtonDesactivar(Button button)
	{
		buttonsDesactivar.add(button);
	}

	@Override
	public boolean getVisible() {
		return true;
	}

	@Override
	public boolean getActive() {
		return false;
	}

	@Override
	public boolean getCollides() {
		return false;
	}
}
