package objetos;

import java.util.Calendar;

/**	ESTUDIO DE INTERFAZ
 * 
 * Propiedades
 * 	JugadorImpl jugador b, c, m
 * 	int fase b, c, m
 * 	Calendar fecha b, c, m
 *
 */

public interface Partida {
	public JugadorImpl getJugador();
	public int getFase();
	public Calendar getFecha();
	
	public void setJugador(JugadorImpl jugador); 
	public void setFase(int fase);
	public void setFecha(Calendar fecha);
}
