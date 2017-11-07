package FrameWork;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Esta clase es complementaria al GameObject, solo se encarga de agarrar un path y devolver
 * una imagen "BufferedImage" que luego el GameObject puede dibujar en su metodo Draw().
 */
public class Sprite {
	
	/**
	 * Imagen que tiene el Sprite ya cargada.
	 */
	public BufferedImage image;
	
	/**
	 * Tamaño en 'x' de la imagen
	 */
	public int width;
	
	/**
	 * Tamaño en 'y' de la imagen
	 */
	public int height;
	
	/**
	 * Constructor de la Clase
	 * @param path
	 * Recibe el path donde esta ese archivo
	 */
	public Sprite(String path)
	{
		try 
		{
			image = ImageIO.read(new File(path));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		width = image.getWidth();
		height = image.getHeight();
	}
}
