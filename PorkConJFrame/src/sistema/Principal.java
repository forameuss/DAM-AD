package sistema;

import java.io.File;
import fases.*;
import objetos.*;

public class Principal {
	/**PORK** 
	 * 
	 * El siguiente programa es un juego de rol en el que manejamos un personaje mediante decisiones en 
	 * diferentes situaciones y combatimos enemigos.
	 *
	 *PSEUDOCODIGO GENERALIZADO
	 *	repetir
	 *		MostrarMenuPrincipal
	 *		segun(opcion)
	 *			caso 1: Nueva partida
	 *			caso 2: Cargar partida
	 *			caso 3: Salir
	 *		si(opcion != 3)
	 *			Jugar
	 *		finsi
	 *		preguntarYLeerReiniciarPrograma
	 *	mientras(usuario quiera repetir)
	 */
	public static void main(String[] args) {
		
		char c;
		int opcion = 0, siguienteFase = 0, sel = 0;
		JugadorImpl j = null;
		FaseImpl fase = null;
		
		GestionBaseDeDatos gbd = new GestionBaseDeDatos("localhost", "porkAdmin", "porkAdmin");
		
		boolean recienCargado=false;//Esta variable es true cuando la partida se ha cargado y no se ha creado.
		boolean continuar = true;		
		boolean reiniciar = false;
		
		File fichPartidas = new File("src/ficheros/Partidas.dat");
		if(!fichPartidas.exists())
			Utiles.creaFicheroPartidas("src/ficheros/Partidas.dat");
		
		MiJFrame mj = new MiJFrame();
			
		do{	
			mj.limpiaTexto();
			//MostrarMenuPrincipal
			pantallaBienvenida(mj);
			mj.actualizarImagen(0);
			do {
				mj.anadeLinea("1. Nueva partida");
				mj.anadeLinea("2. Cargar partida");
				mj.anadeLinea("3. Salir");
				opcion = mj.sigInt();
				if(opcion < 1 || opcion > 3)
					mj.anadeLinea("¡Opción no válida!");
			} while (opcion < 1 || opcion > 3);
			
			
			switch(opcion){
				case 1:
					//NuevaPartida
					mj.anadeTexto("Escribe el nombre de tu personaje: ");
					String nombre = mj.sigString();
					j = new JugadorImpl(nombre, 1, 100, new ItemImpl[9]);
					siguienteFase = 1;continuar=true;
					break;
				case 2:
					//Cargar partida
					PartidaImpl p = null;
					do {
						mj.anadeLinea("Elije la partida que quieras cargar(9 para salir).");
						Utiles.mostrarPartidas("src/ficheros/Partidas.dat", mj);
						sel = mj.sigInt();
						if((sel < 0 || sel > 2)&&sel!=9)
							mj.anadeLinea("¡Opción no válida!");
					} while ((sel < 0 || sel > 2)&&sel!=9);
					if(sel!=9){
						p=Utiles.cargaPartida("src/ficheros/Partidas.dat", sel);		
						if(p == null){
							mj.anadeLinea("ERROR: No se encuentra la partida seleccionada.");
							opcion=3;
						}
						else{
							mj.anadeLinea("Partida cargada.");
							j = p.getJugador();
							siguienteFase = p.getFase();
							recienCargado = true;
						}
					}
					else{
						opcion=3;
						continuar=true;
					}
					break;
				case 3:
					//Salir
					break;				
			}	
			if(opcion!=3){			
				//Jugar
				do{
					mj.limpiaTexto();
					mj.actualizarImagen(siguienteFase);
					//MenuTrasCargarPartida
					if(recienCargado){
						continuar = Utiles.menuJugador(j, siguienteFase, mj);
						recienCargado = false;
					}
					if(continuar){
						//EjecutarFase
						fase = gbd.getFaseBaseDeDatos(siguienteFase);
						
						if(fase != null){
							siguienteFase = fase.ejecutar(j, mj);
							//mj.actualizarImagen(siguienteFase);
						}
						else
							mj.anadeLinea("ERROR: no se encuentra la fase, puede que todavía no haya sido implementada.");
						
						//ComprobarSijugadorEstaMuerto
						if(siguienteFase !=-1)
							continuar = Utiles.menuJugador(j, siguienteFase, mj);
						else{
							mj.actualizarImagen(siguienteFase);
							continuar = false ;
							gameOver(mj);
						}
					}
				
				}while(continuar);
			}
			//Preguntar y leer reiniciar programa
			do{
				mj.anadeLinea("¿Volver a menu principal?(S/N)");
				c=Character.toUpperCase(mj.sigString().charAt(0));
				if(c!='S'&&c!='N')
					mj.anadeLinea("Carácter incorrecto");
				else
					if(c=='S'){
						reiniciar=true;
					}
					else
						reiniciar=false;
			}while(c!='S'&&c!='N');
		}while(reiniciar);
		
		gbd.desconectar();
		mj.setVisible(false);
		mj.dispose();
	}

	
	/**void pantallaBienvenida()
	 * 
	 * Descripción: muestra una pantalla de bienvenida
	 * Entradas: 
	 * Salidas: 
	 * Entradas/Salidas:
	 * Precondiciones: 
	 * Postcondiciones: muestra el mensaje por pantalla
	 */
	private static void pantallaBienvenida(MiJFrame mj) {
		mj.anadeLinea();
		mj.anadeLinea("░░░▄███████▄░░▄██████▄░░░░░▄████████░░░░▄█░░░▄█▄░░░░░░");
		mj.anadeLinea("░░███░░░░███░███░░░░███░░░███░░░░███░░░███░▄███▀░░░░░░"); 
		mj.anadeLinea("░░███░░░░███░███░░░░███░░░███░░░░███░░░███▐██▀░░░░░░░░");
		mj.anadeLinea("░░███░░░░███░███░░░░███░░▄███▄▄▄▄██▀░░▄█████▀░░░░░░░░░");
		mj.anadeLinea("▀█████████▀░░███░░░░███░▀▀███▀▀▀▀▀░░░▀▀█████▄░░░░░░░░░");
		mj.anadeLinea("░░███░░░░░░░░███░░░░███░▀███████████░░░███▐██▄░░░░░░░░");
		mj.anadeLinea("░░███░░░░░░░░███░░░░███░░░███░░░░███░░░███░▀███▄░░░░░░");
		mj.anadeLinea("░▄████▀░░░░░░░▀██████▀░░░░███░░░░███░░░███░░░▀█▀░░░░░░");
		mj.anadeLinea("░░░░░░░░░░░░░░░░░░░░░░░░░░███░░░░███░░░▀░░░░░░░░░░░░░░");
		mj.anadeLinea();
	}
	
	/**void gameOver()
	 * 
	 * Descripción: muestra una pantalla de juego acabado
	 * Entradas: 
	 * Salidas: 
	 * Entradas/Salidas:
	 * Precondiciones: 
	 * Postcondiciones: muestra el mensaje por pantalla
	 */
	private static void gameOver(MiJFrame mj) {
		mj.anadeLinea();
		mj.anadeLinea("░░▄████░░▄▄▄░░░░░░░███▄░▄███▓▓█████░░░░░▒█████░░░██▒░░░█▓▓█████░░██▀███░░");
		mj.anadeLinea("░██▒░▀█▒▒████▄░░░░▓██▒▀█▀░██▒▓█░░░▀░░░░▒██▒░░██▒▓██░░░░█▒▓█░░░▀░▓██░▒░██▒");
		mj.anadeLinea("▒██░▄▄▄░▒██░░▀█▄░░▓██░░░░▓██░▒███░░░░░░▒██░░░██▒░▓██░░█▒░▒███░░░▓██░░▄█░▒");
		mj.anadeLinea("░▓█░░██▓░██▄▄▄▄██░▒██░░░░▒██░▒▓█░░▄░░░░▒██░░░██░░░▒██░█░░▒▓█░░▄░▒██▀▀█▄░░");
		mj.anadeLinea("░▒▓███▀▒░▓█░░░▓██▒▒██▒░░░░██▒░▒████▒░░░░░████▓▒░░░░▒▀█░░░░▒████▒░██▓░▒██▒");
		mj.anadeLinea("░░▒░░░▒░░▒▒░░░▓▒█░░░▒░░░░░░░░░░░▒░░░░░░░░▒░▒░▒░░░░░░░▐░░░░░░▒░░░░░▒▓░░▒▓░");
		mj.anadeLinea("░░░░░░░░░░▒░░░▒▒░░░░░░░░░░░░░░░░░░░░░░░░░░░▒░▒░░░░░░░░░░░░░░░░░░░░░▒░░░▒░");
		mj.anadeLinea("░░░░░░░░░░░░░░▒░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▒░░░░░░░░░░░░░░░░░░░░░░░░░░░");
		mj.anadeLinea("░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░");
		mj.anadeLinea("░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░");
	}
	
}
