package Profiler;

import FrameWork.GameObject;
import FrameWork.Sprite;

public class GraficPoint extends GameObject{

	/**
	 * Cuanto vale este punto "en x".
	 */
	public float value;
	
	public GraficPoint(int x, int y) {
		super(x, y, new Sprite("Assets/Images/Demo/ProfilerPoint.png"));
		// TODO Auto-generated constructor stub
	}

}
