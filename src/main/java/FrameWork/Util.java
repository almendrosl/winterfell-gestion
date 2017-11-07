package FrameWork;

public class Util {

	/**
	 * Retorna un numero random entre 2 numeros
	 * @param lower
	 * El numero menor al que puede tocar
	 * @param upper
	 * El numero mayor al que puede tocar
	 * @return
	 * El numero random entre los 2.
	 */
	public static float RandomRange(float lower, float upper)
	{
		return (float) (Math.random() * (upper - lower)) + lower;
	}
	
	/**
	 * Devuelve un valor entre 0 y 1, de la proporcion de un valor entre 2 valores
	 * @param down
	 * Valor menor
	 * @param up
	 * Valor mayor
	 * @param value
	 * Valor que se busca la proporcion
	 * @return
	 * Proporcion
	 */
	public static float Lerp(float down, float up, float value)
	{
		if(up - down != 0)
			return (value - down)/(up - down);
		else
			return 1;
	}
}
