package Game;

import FrameWork.GameControl;
import FrameWork.GameObject;
import FrameWork.Sprite;

public class Moneda extends GameObject implements Poolable, Cinematic {

	public SpeedController speedController;
	
	public Moneda(int x, int y, Sprite sprite) {
		super(x, y, sprite);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void Update()
	{
		//Cuando llegamos a la punta, nos desactivamos
		if(x < 0 - width -40)
		{
			Desactivar();
		}
	}
	
	@Override
	public float getSpeedX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getSpeedY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getAcelerationX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getAcelerationY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isActive() {
		// TODO Auto-generated method stub
		return active;
	}

	@Override
	public void Activar() {
		// TODO Auto-generated method stub
		x = 800;
		active = true;
		visible = true;
		collides = true;
	}

	@Override
	public void Desactivar() {
		// TODO Auto-generated method stub
		active = false;
		visible = false;
		collides = false;
	}
	
	@Override
	public void OnCollition() {
		Game.Register.Monedas++;
		Desactivar();
	}

	@Override
	public Poolable clone()
	{
		Moneda prototype = new Moneda((int)x,(int)y,sprite);
		GameControl.getGameControl().addToGame(active, visible, collides, prototype);
		speedController.addCinematic(prototype);
		prototype.speedController = speedController;
		prototype.animation = animation;
		prototype.depth = depth;
		
		return prototype;
	}
}
