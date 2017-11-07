package Testing;
import static org.junit.Assert.*;

import org.junit.Test;

import FrameWork.Sprite;

public class SpriteTest {

	/*
	 * voy a hacer tal cosa
	 */
	@Test
	public void testSprite() {
		Sprite sprite = new Sprite("C:\\Boton_Play.png");
		assertEquals(256, sprite.width);
	}
	

}
