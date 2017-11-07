package Game;

import java.util.ArrayList;
import java.util.List;

import FrameWork.GameControl;
import FrameWork.GameObject;
import FrameWork.GameObjectProduct;

public class SpeedController extends GameObject implements GameObjectProduct{

	/**
	 * Lista donde agregamos a todos los objetos que tienen que moverse en el InGame
	 */
	public List<Cinematic> objects;
	
	/**
	 * Velocidad global con la que todos los objetos se van a mover
	 * (en realidad es la velocidad del personaje principal pero relativa)
	 */
	public float globalSpeed;

	/**
	 * Cambia en el udpate la "globalSpeed" segun los valores que se le pongan
	 */
	public float globalAceleration;
	
	public SpeedController() {
		super(0,0,0,0);
		// TODO Auto-generated constructor stub
		objects = new ArrayList<Cinematic>();
	}

	@Override
	public void Update()
	{
		globalSpeed += globalAceleration * GameControl.getGameControl().getDeltaTime();
		
		for(int i = 0; i < objects.size(); i++)
		{
			GameObject aux = (GameObject)(objects.get(i));
			aux.x += objects.get(i).getSpeedX() + globalSpeed + objects.get(i).getAcelerationX() * GameControl.getGameControl().getDeltaTime();
		}
	}
	
	/**
	 * Agrega a un objeto para que siga el movimiento relativo
	 * @param cinematic
	 * Objecto que va a seguir el movimiento.
	 */
	public void addCinematic(Cinematic cinematic)
	{
		objects.add(cinematic);
	}
	
	@Override
	public boolean getVisible() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getActive() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean getCollides() {
		// TODO Auto-generated method stub
		return false;
	}

}
