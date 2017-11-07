package Profiler;

import java.awt.Canvas;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import FrameWork.GameControl;
import FrameWork.GameObject;
import FrameWork.Model;
import FrameWork.Util;
import FrameWork.View;
import UserInterface.Text;

public class ProfilerObject extends GameObject{

	List<GraficPoint> points;
	
	float xScale;
	
	float time;
	
	GraficPoint maxPoint;
	GraficPoint minPoint;
	
	int profilerWidth;
	int profilerHeight;
	
	float fps;
	
	Text text;
	Text textMin;
	
	public ProfilerObject(int profilerWidth, int profilerHeight) {
		super(0,0,profilerWidth,profilerHeight);
		// TODO Auto-generated constructor stub
		
		time = 0;
		points = new ArrayList<GraficPoint>();
		xScale = 10;
		
		this.profilerWidth = profilerWidth;
		this.profilerHeight= profilerHeight;

		View profiler = new View(300,300, "Profiler");
		Model.getModel().RegistrarView(profiler);
		GameControl.getGameControl().views.add(profiler);
		
		GameControl.getGameControl().addToGame(false, true, false, this,"Profiler");
	}
	
	public void Start()
	{
		text = new Text(0,50 - 5,"0",20);
		GameControl.getGameControl().addToGame(false, true, false, text,"Profiler");

		textMin = new Text(0,profilerHeight - 5,"0",30);
		GameControl.getGameControl().addToGame(false, true, false, textMin,"Profiler");
	}
	
	@Override
	public void Draw(Graphics2D grafics, Canvas canvas)
	{
		time += GameControl.getGameControl().getDeltaTime();
		
		if(time > 1)
		{
			fps = GameControl.getGameControl().getFrameRate()/time;
			
			time = 0;
			for(int i = 0; i < points.size(); i++)
			{
				points.get(i).x -= profilerWidth/xScale;
				
				if(maxPoint.value > 0)
					points.get(i).y = profilerHeight - points.get(i).height - (profilerHeight - 50) * Util.Lerp(minPoint.value, maxPoint.value, points.get(i).value);
				else
					points.get(i).y = profilerHeight;
				
				if(points.get(i).x <= 0 - points.get(i).width)
				{
					GameControl.getGameControl().removeFromGame(points.get(i));
					points.remove(0);
				}
			}

			if(maxPoint == null)
			{
				maxPoint = new GraficPoint(0,0);
				maxPoint.value = fps;
				points.add(maxPoint);
				maxPoint.x = profilerWidth - maxPoint.width;
				maxPoint.y = profilerHeight - (profilerHeight - 50);
				minPoint = maxPoint;
				GameControl.getGameControl().addToGame(false, true, false, maxPoint,"Profiler");
			}
			else
			{
				GraficPoint point = new GraficPoint(0,0);
				point.value = fps;
	
				points.add(point);

				GraficPoint auxMax = points.get(0);
				GraficPoint auxMin = points.get(0);
				
				//Asignamos cual es el maxPoint.
				for(int i = 0; i < points.size() - 1; i++)
				{
					if(points.get(i + 1).value > auxMax.value)
						auxMax = points.get(i + 1);
					
					if(points.get(i + 1).value < auxMin.value)
						auxMin = points.get(i + 1);
				}
				
				maxPoint = auxMax;
				minPoint = auxMin;
				
				point.x = profilerWidth - maxPoint.width;;
				
				if(maxPoint.value > 0)
					point.y = profilerHeight - point.height - (profilerHeight - 50) * Util.Lerp(minPoint.value, maxPoint.value, point.value);
				else
					point.y = profilerHeight;
				GameControl.getGameControl().addToGame(false, true, false, point,"Profiler");
			}
			
			text.text = "MAX - FPS: " + maxPoint.value;
			textMin.text = "MIN - FPS" + minPoint.value;
		}
		
		//Dibujamos las lineas entre los puntos del grafico
		for(int i = 0; i < points.size() - 1; i++)
		{
			grafics.drawLine((int)points.get(i).x, (int)points.get(i).y, (int)points.get(i+1).x, (int)points.get(i+1).y);
		}
		
		//Dibujamos la linea encima de todo marcando el maximo.
		if(maxPoint != null)
		{
			grafics.drawLine(0, (int)maxPoint.y, (int)profilerWidth, (int)maxPoint.y);
			text.y = maxPoint.y;
		}

		if(minPoint != null)
		{
			grafics.drawLine(0, (int)minPoint.y, (int)profilerWidth, (int)minPoint.y);
			textMin.y = minPoint.y;
		}
	}
}
