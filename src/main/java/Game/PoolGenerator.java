package Game;

import java.util.ArrayList;
import java.util.List;

import FrameWork.GameObject;
import FrameWork.Util;

/**
 * Esta clase sirve para controlar aquellos objetos que implementan la interfaz Poolable.
 */
public class PoolGenerator {

	/**
	 * Todos los objetos que forman parte actualmente de este Pool
	 */
	List<Poolable> objectsPool;
	
	PoolGenerator(List<Poolable> objectsPool)
	{
		this.objectsPool = objectsPool;
	}
	
	/**
	 * Se fija en la lista de Poolables, si hay alguno que no este activo lo saca, sino
	 * devuelve null.
	 */
	public Poolable getPoolObject()
	{			
		for(int i = 0; i < objectsPool.size(); i++)
			if(!objectsPool.get(i).isActive())
			{
				objectsPool.get(i).Activar();
				return objectsPool.get(i);
			}
		
		int random = (int)Util.RandomRange(0, objectsPool.size() - 1);

		//Si llego aca es que no hubo ninguno disponible, por eso clonamos a alguno random.
		Poolable aux = objectsPool.get(random).clone();
		objectsPool.add(aux);
		
		aux.Activar();
		return aux;
	}
	
	/**
	 * Se fija en la lista de Poolables, si hay alguno que no este activo lo saca de forma
	 * random, sino devuelve null.
	 */
	public Poolable getRandomPoolObject()
	{	
		List<Poolable> aux = new ArrayList<Poolable>();
		
		for(int i = 0; i < objectsPool.size(); i++)
			if(!objectsPool.get(i).isActive())
			{
				aux.add(objectsPool.get(i));
			}
		
		int random = (int)Util.RandomRange(0, objectsPool.size() - 1);
		
		if(aux.size() > 0)
		{
			aux.get(random).Activar();
			return aux.get(random);
		}		
		
		return null;
	}
}
