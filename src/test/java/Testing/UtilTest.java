package Testing;

import static org.junit.Assert.*;

import org.junit.Test;

import FrameWork.Util;

public class UtilTest {

	/*
	 * Voy a crear un numero random entre 0 y 100 y me voy a fijar que este entre 0 y 100
	 */
	@Test
	public void testRandomRange() {
		float random = Util.RandomRange(0, 100);
		assertTrue(0 < random && random < 100);
	}

	/*
	 * Voy a ver si el 20 da 0 para [20,80]
	 * Voy a ver si el 80 da 1 para [20,80]
	 * Voy a ver si el 50 da 0.5 para [20,80]
	 */
	@Test
	public void testLerp() {
		assertTrue(0 == Util.Lerp(20,80,20));
		assertTrue(1 == Util.Lerp(20,80,80));
		assertTrue(0.5 == Util.Lerp(20,80,50));
	}

}
