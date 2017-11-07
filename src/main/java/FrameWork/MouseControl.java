package FrameWork;
import java.awt.MouseInfo;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase es una de las que reciben inputs
 * Toma los inputs del Mouse y los convierte en variables de accion para el Controller.
 */
public class MouseControl extends MouseAdapter implements ControllerSubject
{	      
	/**
	 * Variables de estado del mouse de botones Izquierdo, Derecho, Medio.
	 */
	public int[] mousePressed;
	
	/**
	 * Variable de x del mouse en este momento.
	 */
	public int x;
	
	/**
	 * Variable de y del mouse en este momento.
	 */
	public int y;
	
	/* Lista de Controllers para notificarlos, suscribirlos, etc. */
	public List<ControllerObserver> controllers;
	
	/**
	 * Constructor de la Clase
	 */
	public MouseControl()
	{
		mousePressed = new int[]{0,0,0};

		mousePressed[UserActions.LeftClick - 9] = MouseEvent.BUTTON1;
		mousePressed[UserActions.RightClick - 9] = MouseEvent.BUTTON2;
		mousePressed[UserActions.MiddleClick - 9] = MouseEvent.BUTTON3;
		
		controllers = new ArrayList<ControllerObserver>();
	}
	
	@Override
	public void mouseDragged(MouseEvent e){}
	@Override
	public void mouseEntered(MouseEvent e){}
	@Override
	public void mouseExited(MouseEvent e){}
	@Override
	public void mouseMoved(MouseEvent e){
		NotifyPosition(e.getX(),e.getY());
	}
	@Override
	public void mouseClicked(MouseEvent e){}
	
	@Override
	public void mousePressed(MouseEvent e)
	{
		NotifyPosition(e.getX(),e.getY());
		for(int i = 0; i < mousePressed.length; i++)
		{
			if(mousePressed[i] == e.getButton())
			{
				NotifyController(i + 9,true);
				break;
			}
		}
	}
	
	@Override
	public void mouseReleased(MouseEvent e)
	{
		NotifyPosition(e.getX(),e.getY());
		for(int i = 0; i < mousePressed.length; i++)
		{
			if(mousePressed[i] == e.getButton())
			{
				NotifyController(i + 9,false);
				break;
			}
		}
	}
    
	@Override
	public void mouseWheelMoved(MouseWheelEvent e){}
	
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
	public void NotifyPosition(int x, int y){
		for(int i = 0; i < controllers.size(); i++)
			controllers.get(i).UpdateInputPosition(x, y);
	}
}
