package FrameWork;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class KeyBoardControl extends KeyAdapter implements ControllerSubject{

	/**
	 * Es la lista de keys que se va a utilizar para comandar el juego.
	 */
	public int[] keys;

	/* Lista de Controllers para notificarlos, suscribirlos, etc. */
	public List<ControllerObserver> controllers;
	
	/**
	 * Constructor de la Clase
	 */
	public KeyBoardControl()
	{
		controllers = new ArrayList<ControllerObserver>();
		keys = new int[]{0,0,0,0,0,0,0,0,0,0};
		
		keys[UserActions.Jump] = KeyEvent.VK_Z;
		keys[UserActions.Horse] = KeyEvent.VK_X;
		keys[UserActions.Shoot] = KeyEvent.VK_C;
		keys[UserActions.Escape] = KeyEvent.VK_F;
		keys[UserActions.Enter] = KeyEvent.VK_ENTER;
		keys[UserActions.ArrowRight] = KeyEvent.VK_RIGHT;
		keys[UserActions.ArrowUp] = KeyEvent.VK_UP;
		keys[UserActions.ArrowLeft] = KeyEvent.VK_LEFT;
		keys[UserActions.ArrowDown] = KeyEvent.VK_DOWN;
	}
	
	/*
	 * Si el KeyPressed es igual a algun elemento del arreglo entonces llamamos al Notify
	 * del Controller
	 */
	@Override
	public void keyPressed(KeyEvent e){
		for(int i = 0; i < keys.length; i++)
		{
			if(keys[i] == e.getKeyCode())
			{
				NotifyController(i,true);
				break;
			}
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e){
		for(int i = 0; i < keys.length; i++)
		{
			if(keys[i] == e.getKeyCode())
			{
				NotifyController(i,false);
				break;
			}
		}
	}
	
	@Override
	public void RegistrarController(ControllerObserver obs) {
		controllers.add(obs);
	}

	@Override
	public void DesregistrarController(ControllerObserver obs) {
		controllers.remove(obs);
	}

	@Override
	public void NotifyController(int ke, boolean condition) {
		for(int i = 0; i < controllers.size(); i++)
			controllers.get(i).UpdateInput(ke, condition);
	}

	@Override
	public void NotifyPosition(int x, int y){};
}
