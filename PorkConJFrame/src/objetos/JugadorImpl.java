package objetos;

import java.io.Serializable;
import java.util.Arrays;

/* ANALISIS
 *  
 * 	Restricciones:
 * 		saludmax no puede menor o igual que 0
 * 		salud nunca será inferior a 0 ni mayor que saludmax
 *		nivel no puede ser negativo
 *
 * 	Funciones:
 *		Añadidas:
 *			void eliminarItem(int i)	
 *			void armaPorDefecto()
 *
 */ 
/**Esta clase representa al jugador, contiene las características necesarias para poder desarrollar lapartida*/
public class JugadorImpl implements Jugador, Serializable, Comparable<JugadorImpl> {
	private static final long serialVersionUID = 2437551627881433270L;
	
	//Atributos
	private String nombre;
	private int nivel;
	private int salud;	
	private int saludmax;
	private ItemImpl[] inventario = new ItemImpl[8];
	private Arma arma = null;
	
	//Constructores
	public JugadorImpl(String nombre, int nivel, int saludmax, ItemImpl[] inventario) throws IllegalArgumentException {
		if(nivel<0)
			throw new IllegalArgumentException("Nivel no puede ser negativo.");
		else
			if(saludmax<=0)
				throw new IllegalArgumentException("Salud máxima no puede ser negativa o 0.");
		this.nombre = nombre;
		this.nivel = nivel;
		this.salud = saludmax;
		this.saludmax = saludmax;
		this.inventario=inventario;
		armaPorDefecto();
	}	

	public JugadorImpl() {
		this.nombre = "Jugador";
		this.nivel = 1;
		this.saludmax = 100;
		this.salud = saludmax;
	}
	
	//Getters
	public String getNombre() {
		return nombre;
	}
	public int getNivel() {
		return nivel;
	}
	public int getSalud() {
		return salud;
	}
	public int getSaludMaxima() {
		return saludmax;
	}
	public boolean getMuerto(){
		return salud<=0;
	}
	public ItemImpl[] getInventario(){
		return inventario;
	}
	public ItemImpl getItemInventario(int i){
		return inventario[i];
	}	
	public Arma getArma(){
		return arma;
	}
	
	//Setters
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setNivel(int nivel) throws IllegalArgumentException {
		if(nivel<0)
			throw new IllegalArgumentException("Nivel no puede ser negativo.");
		this.nivel = nivel;
	}
	public void setSalud(int salud) {		
		if(salud<0)
			this.salud=0;
		else
			if(salud>getSaludMaxima())
				this.salud=getSaludMaxima();
			else
				this.salud=salud;
	}
	public void setSaludMaxima(int salud) {
		if(saludmax<=0)
			throw new IllegalArgumentException("Salud máxima no puede ser negativa o 0.");	
		this.saludmax = salud;
	}
	public void setInventario(ItemImpl[] inv){
		inventario=inv;
	}
	public void setItemInventario(ItemImpl item, int i){
		inventario[i] = item;
	}
	public void setArma(Arma arma){
		this.arma=arma;
	}
	
	//Métodos añadidos
	/**void armaPorDefecto()
	 * 
	 * Descripción: asigna un arma al Jugador
	 * Entradas: 
	 * Salidas: se modifica el atributo arma del objeto
	 * Entradas/Salidas:
	 * Precondiciones: 
	 * Postcondiciones: el atributo Arma se sustituirá por la primera arma que encuentre en el inventario
	 */
	public void armaPorDefecto() {
		boolean cont=true;
		for(int i=0; i<inventario.length && cont; i++){
			if(inventario[i] instanceof Arma){
				arma = (Arma) inventario[i];
				cont=false;
			}
		}
	}
	
	/**void eliminarItem()
	 * 
	 * Descripción: elimina un item del inventario
	 * Entradas: int indice
	 * Salidas: 
	 * Entradas/Salidas: array inventario
	 * Precondiciones: 
	 * Postcondiciones: el item que se encuentre en la posicion dada se sustituirá por null
	 */
	public void eliminarItem(int i){
		inventario[i] = null;
	}
	
	//Métodos sobreescritos
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((arma == null) ? 0 : arma.hashCode());
		result = prime * result + Arrays.hashCode(inventario);
		result = prime * result + nivel;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + salud;
		result = prime * result + saludmax;
		return result;
	}
	
	//Dos jugadores son iguales si sus nombres, niveles y saludes maximas son iguales
	@Override
	public boolean equals(Object obj) {
		boolean res = false;
		if(obj!=null /*COSA*/){
			JugadorImpl j = (JugadorImpl) obj;
			res=(j.getNombre().equals(this.nombre)&&
					j.getNivel()==this.nivel&&
					j.getSaludMaxima()==this.saludmax);
		}
		return res;
	}
	
	//Se ordenan por nombre y nivel
	/* Devuelve:
	 * 	-1: si es menor
	 * 	 0: si son iguales
	 * 	 1: si es mayor 
	 **/	
	@Override
	public int compareTo(JugadorImpl j) {
		int res = (j.getNombre().compareTo(this.nombre));
		if(res==0){
			if(j.getNivel()<this.nivel)
				res=1;
			if(j.getNivel()>this.nivel)
				res=-1;
		}
		return res;
	}
}
