package objetos;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;


/* ANALISIS
 * 
 * Propiedades:
 * 	JugadorImpl jugador b, c, m
 * 	int fase b, c, m
 * 	Calendar fecha b, c, m
 * 
 * Restricciones:
 * 
 * Funciones:
 * 	
 * 
 */
 /**Esta clase contiene los datos suficientes para poder guardar y continuar una sesión de juego*/
public class PartidaImpl implements Serializable, Partida, Comparable<PartidaImpl> {
	private static final long serialVersionUID = 8167543308860334502L;
	
	//Atributos
	JugadorImpl jugador = null;
	int fase = 0;
	Calendar fecha;
	//Constructor
	public PartidaImpl(JugadorImpl jugador, int fase, Calendar fecha) {
		this.jugador = jugador;
		this.fase = fase;
		this.fecha = fecha;
	}
	//Getters
	public JugadorImpl getJugador() {
		return jugador;
	}
	public int getFase() {
		return fase;
	}
	public Calendar getFecha() {
		return fecha;
	}
	
	//Setters
	public void setJugador(JugadorImpl jugador) {
		this.jugador = jugador;
	}
	public void setFase(int fase) {
		this.fase = fase;
	}
	public void setFecha(Calendar fecha) {
		this.fecha = fecha;
	}
	
	
	//Metodos sobreescritos
	@Override
	public String toString() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		return (jugador.getNombre()+", Nivel: "+jugador.getNivel()+"  "+dateFormat.format(fecha.getTime())); //2014/08/06 16:00:22
	}
	
	//Se ordenan por fecha
	@Override
	public int compareTo(PartidaImpl o) {
		return o.getFecha().compareTo(this.fecha);
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + fase;
		result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
		result = prime * result + ((jugador == null) ? 0 : jugador.hashCode());
		return result;
	}
	
	//Son iguales si los atributos son iguales
	public boolean equals(Object obj) {
		boolean res = false;
		if(obj!=null){
			PartidaImpl p = (PartidaImpl) obj;
			res=(p.getFecha().equals(this.fecha)&&
					p.getJugador().equals(this.jugador)&&
					p.getFase()==this.fase);
		}
		return res;
	}
}