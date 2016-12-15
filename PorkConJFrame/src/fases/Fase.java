package fases;

import objetos.*;
import sistema.MiJFrame;

/**DESCRIPCIÓN: Este tipo representa una fase o pantalla del juego, en el cual el jugador podrá
	 * 			ejecutar una acción, desembocando en otra fase. Dependiendo del valor del atriubto tipo, el método
	 * 			"ejecutar" actuará de forma diferente.
 * */


/*Propiedades
 * 	Básicas: 	
 * 		int id;	
 * 		TipoFase tipo
 * 		String contexto
 * 		String[] opciones
 * 		int[] salidas
 * 		ItemImpl item
 * 		int opcReq 	
 * 		EnemigoImpl enemigo
 *  		
 * 	Derivadas:
 * 		 
 */
public interface Fase {
	
	public int ejecutar(JugadorImpl j, MiJFrame mj);
	
	
	public int getId();
	public TipoFase getTipo();
	public String getContexto(); 
	public String[] getOpciones();
	public int[] getSalidas();
	public ItemImpl getItem();
	public int getOpcReq();
	public EnemigoImpl getEnemigo();
	

	public void setId(int id);
	public void setTipo(TipoFase tipo);
	public void setContexto(String contexto);
	public void setOpciones(String[] opciones);
	public void setSalidas(int[] salidas);
	public void setItem(ItemImpl recompensa);
	public void setOpcReq(int opcReq);
	public void setEnemigo(EnemigoImpl enemigo);
}
