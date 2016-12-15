package objetos;

import java.io.Serializable;

/**Esta clase representa los enemigos con los que se enfrentará el jugador*/
/* ANALISIS
 *  
 * 	Restricciones:
 * 		saludmax no puede menor o igual que 0
 * 		salud nunca será inferior a 0 ni mayor que saludmax
 *		nivel no puede ser negativo*/

public class EnemigoImpl implements Enemigo, Serializable {
	private static final long serialVersionUID = 8818784954831292866L;
	
	//Atributos
	private String nombre;
	private int salud;	
	private int saludmax;
	private int ataque;
	private int nivel;
	
	//Constructor
	public EnemigoImpl(String nombre, int saludmax, int ataque, int nivel) {
		if(nivel<0)
			throw new IllegalArgumentException("Nivel no puede ser negativo.");
		else
			if(saludmax<=0)
				throw new IllegalArgumentException("Salud máxima no puede ser negativa o 0.");
		this.nombre = nombre;
		this.salud = saludmax;
		this.saludmax = saludmax;
		this.ataque = ataque;
		this.nivel=nivel;
	}
	
	//Getters
	public String getNombre() {
		return nombre;
	}
	public int getSalud() {
		return salud;
	}
	public int getSaludMaxima() {
		return saludmax;
	}
	public int getAtaque() {
		return ataque;
	}
	public int getNivel() {
		return nivel;
	}
	
	//Setters
	public void setNombre(String nombre) {
		this.nombre = nombre;
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
	public void setSaludMaxima(int saludmax) {
		if(saludmax<=0)
			throw new IllegalArgumentException("Salud máxima no puede ser negativa o 0.");	
		this.saludmax = salud;
	}
	public void setAtaque(int ataque) {
		this.ataque = ataque;
	}
	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public boolean getMuerto() {
		return salud<=0;
	}
}
