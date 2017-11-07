package Game;

/**
 * Sirve para darle el comportamiento a los botones.
 *
 */

public interface OnClickButton {

	/**
	 * Cuando se detecta un click, este metodo se sobreescribe 
	 *con el comportamiento que tiene que hacer dicho boton. 
	 */	
	public OnClickButton OnClick();
}
