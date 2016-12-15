package objetos;

//Estudio de interfaz
/* 
 * Propiedades:
 * 	Básicas:	
 * 		String nombre c,m
 * 		int nivel c,m
 * 		int salud c,m
 * 		int saludmax c,m
 *		ItemImpl[] inventario c,m
 *		Arma arma c,m
 *	Derivadas:
 *		boolean muerto c
 *
 *	Restricciones:
 *		salud nunca será inferior a 0 ni mayor que saludmax
 *		nivel no puede ser negativo
 */

public interface Jugador {
	public String getNombre();
	public int getNivel();
	public int getSalud();
	public int getSaludMaxima();
	public boolean getMuerto();
	public ItemImpl[] getInventario();
	public ItemImpl getItemInventario(int i);
	public Arma getArma();
	
	public void setNombre(String nombre);
	public void setNivel(int nivel);
	public void setSalud(int salud);
	public void setSaludMaxima(int salud);
	public void setInventario(ItemImpl[] inv);
	public void setItemInventario(ItemImpl item, int i);
	public void setArma(Arma arma);
}