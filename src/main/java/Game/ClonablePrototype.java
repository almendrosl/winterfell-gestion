package Game;

/**
 * Esta interfaz sirve para que un objeto tenga la posiblidad de clonarse asi mismo
 * a travez del metodo Clone, devolviendo una copia de el mismo.
 */
public interface ClonablePrototype {

		/**
		 * Copia este objeto con todos sus atributos
		 * @return
		 * Clon del objeto.
		 */
		public ClonablePrototype clone();
	}
