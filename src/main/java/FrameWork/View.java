package FrameWork;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Base de la Arquitectura MVC
 * Recibe desde el Model si tiene que redibujar a todos los GameObjects
 * Decide a que GameObject dibujar encima segun el "depth" de cada uno.
 * Tambien recibe updates del GameLoop del PlayState actual y se lo manda al Model
 */
public class View implements ViewObserver, ViewSubject{

	/**
	 * Tamaño de Resolucion en 'x' de la pantalla
	 */
	public int gameWidth;
	
	/**
	 * Nombre de la view.
	 */
	public String name;
	
	/**
	 * Tamaño de Resolucion en 'y' de la pantalla
	 */
	public int gameHeight;
	
	/* El frame que se va a dibujar */
	private JFrame frame;
	
	/* En que marco se va a dibujar */
	private JPanel panel;
	
	/* En que lugr se va a dibujar */
	public Canvas canvas;
	
	/* En donde va a estar todo lo que tengo que dibujar*/
	public BufferStrategy bufferStrategy;

	/* En donde se ponen todos los GameObject que se quieren renderizar */
	public List<GameObjectObserver> renderGO;
	
	/**
	 * Constructor de la Clase, deberia setear todas las variables necesarias para construir
	 * la ventana.
	 */
	public View(int width, int height, String name)
	{
		gameWidth = width;
		gameHeight = height;
		this.name = name;
		
		frame = new JFrame(name);
		  
		JPanel panel = (JPanel) frame.getContentPane();
		panel.setPreferredSize(new Dimension(gameWidth, gameHeight));
		panel.setLayout(null);
		      
		canvas = new Canvas();
		canvas.setBounds(0, 0, gameWidth, gameHeight);
		canvas.setIgnoreRepaint(true);
		     
		panel.add(canvas);
		    
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setResizable(false);
		frame.setVisible(true);
		
		canvas.createBufferStrategy(2);
		bufferStrategy = canvas.getBufferStrategy();
		      
		canvas.requestFocus();
		
		renderGO = new ArrayList<GameObjectObserver>();
	}
	
	/**
	 * Este metodo agrega un KeyListener al canvas y si ya habia uno lo cambia
	 * @param keyBoard
	 * El KeyListener que vamos a agregar.
	 */
	public void ChangeKeyListener(KeyListener keyBoard)
	{
		if(canvas.getKeyListeners().length > 0)
			canvas.removeKeyListener(canvas.getKeyListeners()[0]);
		
		canvas.addKeyListener(keyBoard);
	}

	/**
	 * Este metodo agrega un MouseListener al canvas y si ya habia uno lo cambia
	 * @param mouse
	 * El MouseListener que vamos a agregar.
	 */
	public void ChangeMouseListener(MouseListener mouse)
	{
		if(canvas.getMouseListeners().length > 0)
			canvas.removeMouseListener(canvas.getMouseListeners()[0]);
		
		canvas.addMouseListener(mouse);
	}

	@Override
	public void RegistrarDraw(GameObjectObserver obs) {
		renderGO.add(obs);
	}

	@Override
	public void DesregistrarDraw(GameObjectObserver obs) {
		renderGO.remove(obs);
	}

	@Override
	public void ClearDraw()
	{
		renderGO.clear();
	}
	
	@Override
	public void NotifyDraw() {
		for(int i = 0; i < renderGO.size(); i++)
		{
			renderGO.get(i).Draw((Graphics2D)bufferStrategy.getDrawGraphics(), canvas);
		}
	}

	@Override
	public void Update() {
		//Borramos lo que habia dibujado
	    bufferStrategy.getDrawGraphics().clearRect(0, 0, gameWidth, gameHeight);
	      
		//Antes de dibujarlos a todos los ordenamos
		renderGO.sort(new Comparator<GameObjectObserver>() {
			@Override
			public int compare(GameObjectObserver o1, GameObjectObserver o2) {
				// TODO Auto-generated method stub
				return ((GameObject)o2).depth - ((GameObject)o1).depth;
			}
		});

		NotifyDraw();
		bufferStrategy.show();
	}
	
	/**
	 * Le pasamos una view y le da todos los GameObjects que esta view tiene en su lista
	 */
	@Override
	public void setGameObjectsToView(ViewSubject view)
	{
		for(int i = 0; i < renderGO.size(); i++)	
		{
			view.RegistrarDraw(renderGO.get(i));
		}
	}
}
