package Game;

import FrameWork.GameControl;
import FrameWork.GameObject;
import FrameWork.Sprite;

public class Character extends GameObject {
	
	float speedJump = 400;
	float gravity = 500;
	
	boolean jumping = false;
	
	float time = 0;
	float initY;

	public Character(int x, int y, Sprite sprite) {
		super(x, y, sprite);
		// TODO Auto-generated constructor stub
	}
	
	public void Update() {
		//Esta saltando
		if (jumping) {
			time += GameControl.getGameControl().getDeltaTime();
			y = initY - speedJump * time + gravity * time * time /2;
			//termino de saltar
			if (y > initY) {
				jumping = false;
				time = 0;
				y = initY;	
			}
		}
	}

	public void Jump(){
		if (jumping)
			return;
		//comienza el salto
		jumping = true;
		initY = y;
	}
	
	public void Destroy(){
		// Este metodo solo sirve para sobreescribir
	}
	
}
