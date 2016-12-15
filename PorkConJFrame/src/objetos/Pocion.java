package objetos;

/**Esta función representa los objetos de curación del jugador*/
public class Pocion extends Apoyo {
	private static final long serialVersionUID = -8018430565052112716L;
		
	//Cosntructores
	public Pocion(String nombre, int curacion) {
		super(nombre, curacion);
	}

	//Getters
	public int getCuracion() {
		return super.getPoder();
	}

	//Setters
	public void setCuracion(int curacion) {
		super.setPoder(curacion);
	}
	
	//Métodos sobreescritos
	/**void usar(JugadorImpl j)
	 * 
	 * Descripción: cura salud al jugador
	 * Entradas: 
	 * Salidas: 
	 * Entradas/Salidas: JugadorImpl
	 * Precondiciones: 
	 * Postcondiciones: el jugador aumentará su atributo salud un cantidad igual al atributo
	 * 					poder de la clase base 
	 */
	@Override
	public void usar(JugadorImpl j){
		j.setSalud(j.getSalud()+super.getPoder());
	}
}
