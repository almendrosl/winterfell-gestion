package UserInterface;

import java.awt.Canvas;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class Text extends UserInterface{

	public String text;
	public Font font;
	
	public Text(int x, int y, String text, int tamanio) {
		super(x, y, 0, 0);
		// TODO Auto-generated constructor stub
		this.text = text;
		font = new Font("Serif", Font.PLAIN, tamanio);
	}
	
	@Override
	public void Draw(Graphics2D grafics, Canvas canvas)
	{
		if(!visible)
			return;
		
		grafics.setFont(font);
		grafics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		grafics.drawString(text, x, y);
	}

}
