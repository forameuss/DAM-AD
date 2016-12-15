package objetos;

/*Propiedades
 * 	Básicas: 	
 * 		String nombre
 *		int salud
 *	 	int saludmax
 *	 	int ataque
 * 	 	int nivel
 * 	Derivadas:
 * 		boolean muerto
 * 
 */

public interface Enemigo {
	public String getNombre();
	public int getSalud();
	public int getSaludMaxima();
	public int getAtaque();
	public int getNivel();
	public boolean getMuerto();
	
	public void setNombre(String nombre);
	public void setSalud(int salud);
	public void setSaludMaxima(int saludmax);
	public void setAtaque(int ataque);
	public void setNivel(int nivel);

	
}
