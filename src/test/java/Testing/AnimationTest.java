package Testing;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Test;

import FrameWork.Animation;
import FrameWork.GameControl;
import FrameWork.PlayState;
import FrameWork.Sprite;
import FrameWork.View;

public class AnimationTest {

	/* Construimos una animacion con 2 Sprites y 
	*  luego preguntamos si estos se guardaron correctamente en la lista de sprites 
	*/
	@Test
	public void testAnimation() {
		List<Sprite> lista = new ArrayList<Sprite>();
		
		lista.add(new Sprite("C:\\Boton_Play.png"));
		lista.add(new Sprite("C:\\Boton_Play.png"));

		Animation animation = new Animation(lista, false);
		assertTrue(animation.sprites.size() == 2);
	}

	/*
	 * Vamos a hacer una animacoin con un 3 Sprites, que no loopee, le vamos a hacer 3 Updates
	 * y luego vamos a comprobar que el estado de finish se alcanzo
	 * Vamos a hacer una animacion con 2 Sprites, que loopeen, le vamos a hacer 3 Updates y
	 * luego vamos a comprobar que el Sprite donde llego era el mismo que antes.
	 * Ponemos un frameRate gigante para que si o si pase.
	 */
	@After
	public void testUpdate() {
		
		//Primera prueba "Finish"
		List<Sprite> lista = new ArrayList<Sprite>();
		
		lista.add(new Sprite("C:\\Boton_Play.png"));
		lista.add(new Sprite("C:\\Boton_Play.png"));
		lista.add(new Sprite("C:\\Boton_Play.png"));
		
		Animation animation1 = new Animation(lista,false);
		animation1.duration = 0;

		GameControl.getGameControl().frameRate = 60;

		if(GameControl.getGameControl().views.size() == 0)
			GameControl.getGameControl().views.add(new View(50,50, "Game"));
		
		if(GameControl.getGameControl().actualPlayState == null)
			GameControl.getGameControl().changePlayState(new PlayState("lalal2"));
			
		GameControl.getGameControl().actualPlayState.deltaTime = 1;
		
		for(int i = 0; i < 3; i++)
			animation1.Update();
		
		assertTrue(animation1.finish);
		
		//Segunda prueba "Loop"
		List<Sprite> lista2 = new ArrayList<Sprite>();
		
		lista2.add(new Sprite("C:\\Boton_Play.png"));
		lista2.add(new Sprite("C:\\Boton_Play.png"));
		
		Animation animation2 = new Animation(lista2,true);
		animation2.duration = 0;
		
		for(int i = 0; i < 3; i++)
			animation2.Update();
		
		assertTrue(animation2.sprites.get(animation2.frame) == animation2.sprites.get(1));
	}
}
