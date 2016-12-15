package sistema;

import objetos.*;

public class Combate {
	
	/** boolean iniciar(JugadorImpl j, EnemigoImpl e)
	 * 
	 * Descripción: Muestra la interfaz de combate y ejecuta las ordenes del usuario
	 * Entradas: Jugador y Enemigo
	 * Salidas: boolean
	 * Entradas/Salidas:
	 * Precondiciones: 
	 * Postcondiciones: devuelve true si el jugador ha acabado el combate con vida
	 */
	public static boolean iniciar(JugadorImpl j, EnemigoImpl e, ItemImpl rec, MiJFrame mj){
		boolean vivo = !j.getMuerto();
		mj.anadeLinea("¡Batalla contra "+e.getNombre()+"!\n");
		
		while(!e.getMuerto() && vivo){
			mj.anadeLinea();
			mj.anadeTexto(e.getNombre()+":\n");
			mostrarSalud(e, mj);
			mj.anadeLinea(" vs");
			mj.anadeTexto(j.getNombre()+":\n");
			mostrarSalud(j, mj);
			mj.anadeLinea();
			menu(j,e, mj);
			if(!e.getMuerto())
				accionEnemigo(j,e, mj);
			vivo = !j.getMuerto();
		}
		if(vivo){
			mj.anadeLinea("Has derrotado a "+e.getNombre());
			j.setNivel(j.getNivel()+1);
			mj.anadeLinea("¡Has subido de nivel!");
			if(rec!=null){
				mj.anadeLinea(e.getNombre()+" dropeó "+rec);
				Utiles.anadirItem(j, rec, mj);
			}
		}
		else
			mj.anadeLinea(e.getNombre()+" te ha matado.");
		
		return vivo;
	}
	
	/**void mostrarSalud(Jugador j)
	 * 
	 * Descripción: muestra la salud de un jugador
	 * Entradas: jugador
	 * Salidas: 
	 * Entradas/Salidas:
	 * Precondiciones: 
	 * Postcondiciones: 
	 */
	static void mostrarSalud(Jugador j, MiJFrame mj){
		int max = 20;
		int lleno=(int) (j.getSalud()*max/j.getSaludMaxima());
		int vacio= max-lleno;
		
		for(int i=0; i<lleno;i++){
			mj.anadeTexto("█");
		}
		for(int i=0; i<vacio;i++){
			mj.anadeTexto("_");
		}
		mj.anadeLinea("| "+j.getSalud()+"/"+j.getSaludMaxima());
	}
	
	/**void mostrarSalud(EnemigoImpl e)
	 * 
	 * Descripción: muestra la salud de un enemigo
	 * Entradas: Enemigo
	 * Salidas: 
	 * Entradas/Salidas:
	 * Precondiciones: 
	 * Postcondiciones: 
	 */
	static void mostrarSalud(EnemigoImpl e, MiJFrame mj){
		int max = 20;
		int lleno=(int) (e.getSalud()*max/e.getSaludMaxima());
		int vacio= max-lleno;
		
		for(int i=0; i<lleno;i++){
			mj.anadeTexto("█");
		}
		for(int i=0; i<vacio;i++){
			mj.anadeTexto("_");
		}
		mj.anadeLinea("| "+e.getSalud()+"/"+e.getSaludMaxima());
	}
	
	/**menu(JugadorImpl j, EnemigoImpl e)
	 * 
	 * Descripción: muestra y ejecuta las acciones de un jugador segun quiera el usuario
	 * Entradas: EnemigoImpl e
	 * Salidas: 
	 * Entradas/Salidas: JugadorImpl j
	 * Precondiciones: 
	 * Postcondiciones: el jugador puede ser modificado segun la accion del usuario
	 */
	static void menu(JugadorImpl j, EnemigoImpl e, MiJFrame mj){
		int opcion = 0; boolean seguir = true;
		do{
			do {
				mj.anadeLinea("1. Atacar");
				mj.anadeLinea("2. Usar objeto");
				mj.anadeLinea("3. Huir");
				opcion = mj.sigInt();
				if(opcion < 1 || opcion > 3)
					mj.anadeLinea("¡Opcion no válida!");
			} while (opcion < 1 || opcion > 3);
					
			switch(opcion){
				case 1:
					if(j.getArma()==null)
						mj.anadeLinea("No puedes atacar, no tienes arma!");
					else{
						mj.limpiaTexto();
						int dano = (int) (j.getArma().getDanyo()+0.1*j.getNivel() - e.getNivel()*Math.random()*2);
						e.setSalud(e.getSalud()-dano);
						mj.anadeLinea("Le has hecho "+dano+" de daño a "+e.getNombre());
					}
					seguir=false;
					break;
				case 2:
					seguir=(!Utiles.usarObjetos(j, mj));
					break;
				case 3:
					mj.anadeLinea("No puedes huir. "+e.getNombre()+" te corta el paso.");
					break;				
			}
			
		}while(seguir);
	}
	
	/** void accionEnemigo(JugadorImpl j, EnemigoImpl e)
	 * 
	 * Descripción: quita salud al jugador en base a la fuerza de un enemigo
	 * Entradas: Enemigo
	 * Salidas: 
	 * Entradas/Salidas: Jugador
	 * Precondiciones: 
	 * Postcondiciones: se le restará salud al jugador
	 */
	private static void accionEnemigo(JugadorImpl j, EnemigoImpl e, MiJFrame mj) {
		int dano = (int) (e.getAtaque()+e.getNivel()*Math.random()-j.getNivel()*Math.random()*2);
		j.setSalud(j.getSalud()-dano);
		mj.anadeLinea(e.getNombre()+" te ha infligido "+dano+" de daño.");		
	}

	
}
