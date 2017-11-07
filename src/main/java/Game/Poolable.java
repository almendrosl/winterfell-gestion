package Game;

/**
 * Nos sirve para llamar a todos los objetos que extendienden esta interfaz y no tener que
 * crearlos y destruirlos, simplemente reciclar el/los objetos que ya estan creados de este
 * tipo
 */
public interface Poolable{

	/**
	 * Nos marca el estado del objeto
	 * @return
	 * Estado de actividad.
	 */
	public boolean isActive();
	
	/**
	 * Lo llama el PoolGenerator cuando lo comienza a reciclar.
	 */
	public void Activar();
	
	/**
	 * Debe implementarlo el objeto para saber cuando ya no esta mas siendo usado asi el
	 * PoolGenerator lo puede utilizar.
	 */
	public void Desactivar();

	/**
	 * Copia este objeto con todos sus atributos
	 * @return
	 * Clon del objeto.
	 */
	public Poolable clone();
}
