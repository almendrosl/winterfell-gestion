package FrameWork;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game implements Runnable{
   
   final int WIDTH = 1000;
   final int HEIGHT = 700;
   
   View view;
   public Game(){
     view = new View(WIDTH, HEIGHT, "Game");
   }
   
   long desiredFPS = 60;
   long desiredDeltaLoop = (1000*1000*1000)/desiredFPS;
    
   boolean running = true;

   GameObject go;
   
   public void run()
   {
	   go = new GameObject(100,100,new Sprite("C:\\Boton_Play.png"));

	  long beginLoopTime;
	  long endLoopTime;
	  long currentUpdateTime = System.nanoTime();
	  long lastUpdateTime;
	  long deltaLoop;
	  
	  while(running)
	  {
		  beginLoopTime = System.nanoTime();
	     
		  render();
		  lastUpdateTime = currentUpdateTime;
		  currentUpdateTime = System.nanoTime();
		  update((int) ((currentUpdateTime - lastUpdateTime)/(1000*1000)));
	     
		  endLoopTime = System.nanoTime();
		  deltaLoop = endLoopTime - beginLoopTime;
	       
		  if(deltaLoop > desiredDeltaLoop)
		  {
	           //Do nothing. We are already late.
		  }
		  else
		  {
			  try
			  {
				  Thread.sleep((desiredDeltaLoop - deltaLoop)/(1000*1000));
			  }
			  catch(InterruptedException e)
			  {
	           //Do nothing
			  }
		  }
	  }
   }
   
   private void render() {
      Graphics2D g = (Graphics2D) view.bufferStrategy.getDrawGraphics();
      g.clearRect(0, 0, WIDTH, HEIGHT);
      render(g);
      g.dispose();
      view.bufferStrategy.show();
   }
   
   //TESTING
   private double x = 0;
   
   /**
    * Rewrite this method for your game
    */
   protected void update(int deltaTime){
      x += deltaTime * 0.2;
      while(x > 500){
         x -= 500;
      }
   }
   
	/**
    * Rewrite this method for your game
    */
	protected void render(Graphics2D g)
	{
	   g.fillRect((int)x, 0, 200, 200);
      

	   //g.drawImage(go.sprite.image, 0, 0, canvas);
	   go.visible = true;
	   go.Draw(g,view.canvas);
	}

	public Canvas getCanvas()
	{
		return view.canvas;
	}
	
	public Graphics2D getGraphics2D()
	{
		return (Graphics2D)view.bufferStrategy.getDrawGraphics();
	}
}
