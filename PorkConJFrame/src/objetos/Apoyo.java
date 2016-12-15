package objetos;

import java.io.Serializable;

/**Esta clase representa los items que son exclusivamente para modificar el jugador*/

public abstract class Apoyo extends ItemImpl implements Serializable {
	private static final long serialVersionUID = 4839828582667485664L;

	public Apoyo(String nombre, int poder) {
		super(nombre, poder);
	}
	
	//Métodos añadidos
	/**abstract void usar(JugadorImpl j)
	 * 
	 * NOTA: FUNCIÓN ABSTRACTA
	 * Descripción: activa la función del objeto sobre el jugador
	 * Entradas: 
	 * Salidas: 
	 * Entradas/Salidas: JugadorImpl
	 * Precondiciones: 
	 * Postcondiciones: el jugador se verá modificado dependiendo del efecto del item 
	 */
	public abstract void usar(JugadorImpl j);
}
