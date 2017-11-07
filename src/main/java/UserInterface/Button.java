package UserInterface;

import FrameWork.Model;
import FrameWork.Sprite;
import FrameWork.UserActions;

public class Button extends UserInterface{

	protected Sprite spriteIdle;
	protected Sprite spritePressed;
	
	public Button(int x, int y, Sprite spriteIdle, Sprite spritePressed) {
		super(x, y, spriteIdle);
		// TODO Auto-generated constructor stub
		this.spriteIdle = spriteIdle;
		this.spritePressed = spritePressed;
	}
	
	public void Update()
	{
		if(x < Model.getModel().X && Model.getModel().X < x + width 
			&& y < Model.getModel().Y && Model.getModel().Y < y + height &&
			Model.getModel().getStatePressed(UserActions.LeftClick))
		{
			sprite = spritePressed;
			OnPress();
		}
		else
			sprite = spriteIdle;
		
		if(Model.getModel().getStateJustReleased(UserActions.LeftClick))
		{
			if(x < Model.getModel().X && Model.getModel().X < x + width 
					&& y < Model.getModel().Y && Model.getModel().Y < y + height)
			{
				//De esta manera obligamos al Model a que no nos permita hacer click en 2
				//botones al mismo tiempo por mas que esten solapados.
				Model.getModel().userActionsJustReleased[UserActions.LeftClick] = false;
				OnRelease();
			}
			else
				OnOut();
		}
		
	}
	
	/**
	 * Cuando apretamos el boton
	 */
	public void OnPress()
	{
		/*La usamos solo para sobreescribirla*/
	}
	
	/**
	 * Cuando soltamos el boton apretado
	 */
	public void OnRelease()
	{
		/*La usamos solo para sobreescribirla*/
	}

	/**
	 * Cuando nos fuimos del boton y soltamos
	 */
	public void OnOut()
	{
		/*La usamos solo para sobreescribirla*/
	}
}
