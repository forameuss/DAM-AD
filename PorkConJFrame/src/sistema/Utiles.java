package sistema;

import java.io.*;
import java.util.Calendar;
import objetos.*;

public class Utiles {

	// Esta clase contiene los métodos necesarios para el funcionamiento del
	// programa principal

	/**
	 * boolean menuJugador(JugadorImpl j, int fase)
	 * 
	 * Descripción: Muestra un menu, lee las entradas del usuario y realiza las
	 * operaciones pertinentes. 
	 * Entradas: int Fase 
	 * Salidas: boolean CONTINUAR
	 * Entradas/Salidas: Jugador
	 * Precondiciones: 
	 * Postcondiciones: Una vez el usuario halla acabado sus operaciones, el método devuelve true si el
	 * jugador puede continuar y false en caso contrario
	 */
	/**
	 * PSEUDOCODIGO 
	 * repetir 
	 * 		MostraMenuYValidarOpción
	 * 		segun(opcion) 
	 * 			case 1: continuar
	 * 			case 2: mostrarEstado 
	 * 			case 3: mostarInventario
	 * 			case 4: guardarPartida
	 * 			case 5: salir 
	 * mientras(opcion no sea salir del menu)
	 * 
	 * 
	 */
	public static boolean menuJugador(JugadorImpl j, int fase, MiJFrame mj) {	
		boolean res = true, repetir = true;
		int opcion = 0, sel = 0;
		do {
			// MostraMenuYValidarOpción
			do {
				mj.anadeLinea("\n1. Continuar");
				mj.anadeLinea("2. Estado");
				mj.anadeLinea("3. Inventario");
				mj.anadeLinea("4. Guardar partida");
				mj.anadeLinea("5. Salir");
				opcion = mj.sigInt();
				if (opcion < 1 || opcion > 5)
					mj.anadeLinea("¡Opción no válida!");
			} while (opcion < 1 || opcion > 5);

			switch (opcion) {
			case 1:
				// continuar
				repetir = false;
				break;
			case 2:
				// mostrarEstado
				mj.anadeLinea("Nombre : " + j.getNombre() + "\nNivel: " + j.getNivel() + "\nSalud: " + j.getSalud()
						+ "/" + j.getSaludMaxima());
				break;
			case 3:
				// mostarInventario
				menuInventario(j, mj);
				break;
			case 4:
				// guardarPartida
				do {
					mj.anadeLinea("Elije donde quieres guardar la partida.(9 para salir)");
					Utiles.mostrarPartidas("src/ficheros/Partidas.dat", mj);
					sel = mj.sigInt();
					if ((sel < 0 || sel > 2) && sel != 9)
						mj.anadeLinea("¡Opción no válida!");
				} while ((sel < 0 || sel > 2) && sel != 9);
				if (sel != 9) {
					guardarPartida("src/ficheros/Partidas.dat", "src/ficheros/PartidasAux.dat", j, fase,
							Calendar.getInstance(), sel);
					mj.anadeLinea("Partida guardada.");
				}
				break;
			case 5:
				// salir
				res = false;
				repetir = false;
				break;
			}
		} while (repetir);
		return res;
	}

	/**
	 * void menuInventario(JugadorImpl j)
	 * 
	 * Descripción: Muestra un menu, lee las entradas del usuario y realiza las
	 * operaciones pertinentes. Estas opciones son: 
	 * 		-Mostrar inventario 
	 * 		-Cambiar arma
	 * 		-Eliminar objeto
	 * 		-Usar objeto
	 * Entradas: 
	 * Salidas: 
	 * Entradas/Salidas:Jugador 
	 * Precondiciones: 
	 * Postcondiciones: el jugador será modificado en caso de que el usuario quiera
	 */

	/**
	 * PSEUDOCODIGO GENERALIZADO
	 * 
	 * repetir 		
	 * 		mostrarMenuLeerYValidarOpcion 
	 * mientras(opcionInvalida)
	 * segun(opcion) 
	 * 		case 1:mostrarInventario 
	 * 		case 2:cambiarArma 
	 * 		case 3:eliminarObjeto 
	 * 		case 4:usarObjeto 
	 * 		case 5:volver 
	 * finsegun
	 */
	private static void menuInventario(JugadorImpl j, MiJFrame mj) {
		int opcion;
		int sel;
		char c;

		// mostrarMenuLeerYValidarEntrada
		do {
			mj.anadeLinea("\n1. Mostrar inventario");
			mj.anadeLinea("2. Cambiar arma");
			mj.anadeLinea("3. Eliminar objeto");
			mj.anadeLinea("4. Usar objeto");
			mj.anadeLinea("5. Volver\n");
			opcion = mj.sigInt();
			if (opcion < 1 || opcion > 5)
				mj.anadeLinea("¡Opción no válida!");
		} while (opcion < 1 || opcion > 5);

		switch (opcion) {
		case 1:
			// mostrarInventario
			muestraInventario(j, mj);
			break;

		case 2:
			// cambiarArma
			muestraArmas(j, mj);
			mj.anadeLinea("Elije el arma que deseas equipar (9 para salir.)");
			sel = mj.sigInt();
			if (sel >= 0 && sel <= 8)
				if (j.getItemInventario(sel) instanceof Arma) {
					j.setArma((Arma) j.getItemInventario(sel));
					mj.anadeLinea(j.getArma() + " equipada");
				} else if (sel != 9)
					mj.anadeLinea("¡Opción no válida!");
			break;

		case 3:
			// eliminarObjeto
			muestraInventario(j, mj);
			do {
				mj.anadeLinea("Elije el objeto que deseas eliminar (9 para salir).");
				sel = mj.sigInt();
				if (sel < 0 || sel > 8) {
					if (sel != 9)
						mj.anadeLinea("¡Opción no válida!");
				} else {
					if (j.getItemInventario(sel) == null)
						mj.anadeLinea("¡Opción no válida!");
					else {
						do {
							mj.anadeLinea("¿Eliminar " + j.getItemInventario(sel) + "?(S/N)");
							c = Character.toUpperCase(mj.sigString().charAt(0));
							if (c != 'S' && c != 'N')
								mj.anadeLinea("Carácter incorrecto");
							else if (c == 'S') {
								j.eliminarItem(sel);
								mj.anadeLinea("Objeto eliminado");
							}
						} while (c != 'S' && c != 'N');

					}
				}
			} while (sel < 0 || sel > 9);
			break;

		case 4:
			// usarObjeto
			usarObjetos(j, mj);
			break;

		case 5:
			// volver
			break;
		}
	}

	/**
	 * void muestraInventario(JugadorImpl j)
	 * 
	 * Descripción: Muestra el inventario de un jugador. 
	 * Entradas: Jugador
	 * Salidas: 
	 * Entradas/Salidas: 
	 * Precondiciones: 
	 * Postcondiciones: muestra por pantalla los elementos del inventario clasificados.
	 */
	private static void muestraInventario(JugadorImpl j, MiJFrame mj) {
		int i = 0;
		mj.anadeLinea("--------------------");
		mj.anadeLinea("Armas");
		mj.anadeLinea("--------------------");
		for (ItemImpl temp : j.getInventario()) {
			if (temp instanceof Arma) {
				mj.anadeLinea(i + " " + temp + "    daño: " + ((Arma) temp).getDanyo());
			}
			i++;
		}
		mj.anadeLinea("--------------------");
		mj.anadeLinea("Apoyo");
		mj.anadeLinea("--------------------");
		i = 0;
		for (ItemImpl temp : j.getInventario()) {
			if (temp instanceof Apoyo) {
				if (temp instanceof Pocion)
					mj.anadeLinea(i + " " + temp + "  curación: " + temp.getPoder());
				else
					mj.anadeLinea(i + " " + temp);
			}

			i++;
		}
		mj.anadeLinea("--------------------");
		mj.anadeLinea("Objetos clave");
		mj.anadeLinea("--------------------");
		i = 0;
		for (ItemImpl temp : j.getInventario()) {
			if (temp instanceof Clave) {
				mj.anadeLinea(i + " " + temp);
			}
			i++;
		}
		mj.anadeLinea("--------------------");
		mj.anadeLinea("Arma equipada: " + j.getArma());
		mj.anadeLinea("--------------------");
		mj.anadeLinea();
	}

	/**
	 * void muestraArmas(JugadorImpl j)
	 * 
	 * Descripción: Muestra las armas de un jugador. 
	 * Entradas: Jugador 
	 * Salidas:
	 * Entradas/Salidas: 
	 * Precondiciones: 
	 * Postcondiciones: muestra por pantalla los elementos del inventario del tipo Arma.
	 */
	private static void muestraArmas(JugadorImpl j, MiJFrame mj) {
		int i = 0;
		mj.anadeLinea("--------------------");
		mj.anadeLinea("Armas");
		mj.anadeLinea("--------------------");
		for (ItemImpl temp : j.getInventario()) {
			if (temp instanceof Arma) {
				mj.anadeLinea(i + " " + temp + " Daño: " + ((Arma) temp).getDanyo());
			}
			i++;
		}
		mj.anadeLinea();
	}

	/**
	 * void muestraApoyo(JugadorImpl j)
	 * 
	 * Descripción: Muestra el inventario de un jugador. 
	 * Entradas: Jugador
	 * Salidas: 
	 * Entradas/Salidas: 
	 * Precondiciones: 
	 * Postcondiciones: muestra por pantalla los elementos del inventario del tipo Apoyo.
	 */
	private static void muestraApoyo(JugadorImpl j, MiJFrame mj) {
		int i = 0;
		mj.anadeLinea("--------------------");
		mj.anadeLinea("Apoyo");
		mj.anadeLinea("--------------------");
		for (ItemImpl temp : j.getInventario()) {
			if (temp instanceof Apoyo) {
				mj.anadeLinea(i + " " + temp);
			}
			i++;
		}
		mj.anadeLinea();
	}

	/**
	 * boolean usarObjetos(JugadorImpl j)
	 * 
	 * Descripción: Muestra el menu de un jugador para poder usar un objeto y lo
	 * 				usa en caso de que el usuario quiera 
	 * Entradas: 
	 * Salidas: boolean
	 * Entradas/Salidas: Jugador
	 * Precondiciones: 
	 * Postcondiciones: devuelve true si se ha usado el objeto y false en caso contrario.
	 */
	public static boolean usarObjetos(JugadorImpl j, MiJFrame mj) {
		boolean res = false;
		muestraApoyo(j, mj);
		int sel;
		mj.anadeLinea("Elije el objeto que quieres usar (9 para salir).");
		sel = mj.sigInt();
		if (sel < 0 || sel > 8) {
			if (sel != 9)
				mj.anadeLinea("¡Opción no válida!");
		} else if (j.getItemInventario(sel) instanceof Apoyo) {
			Apoyo a = (Apoyo) j.getItemInventario(sel);
			a.usar(j);
			mj.anadeLinea("Has usado " + a.getNombre());
			j.eliminarItem(sel);
			res = true;
		} else
			mj.anadeLinea("¡Opción no válida!");
		return res;
	}

	/**
	 * void mostrarPartidas(String fich)
	 * 
	 * Descripción: muestra partidas guardadas en un fichero
	 * Entradas: String (nombre del fichero) 
	 * Salidas: 
	 * Entradas/Salidas: 
	 * Precondiciones: 
	 * Postcondiciones: muestra por pantella las partidas almacenadas en el fichero
	 */
	public static void mostrarPartidas(String fich, MiJFrame mj) {
		PartidaImpl res = null;

		File fichero = new File(fich);
		ObjectInputStream ois = null;
		FileInputStream fis = null;

		if (fichero.isFile()) {
			try {
				fis = new FileInputStream(fichero);
				ois = new ObjectInputStream(fis);
				for (int i = 0; i < 3; i++) {
					res = (PartidaImpl) ois.readObject();

					mj.anadeTexto(i + ". ");
					if (res != null)
						mj.anadeLinea(res.toString());
					else
						mj.anadeLinea("<Ranura Vacía>");
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} finally {
				if (ois != null) {
					try {
						ois.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if (fis != null) {
					try {
						fis.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	/**
	 * PartidaImpl cargaPartida(String fich)
	 * 
	 * Descripción: Carga la partida almacenada en un fichero.
	 * Entradas: String(nombre del fichero) 
	 * Salidas: PartidaImpl
	 * Entradas/Salidas:
	 * Precondiciones: 
	 * Postcondiciones: devuelve la partida almacenada en el fichero o null si no existe esa partida.
	 */
	public static PartidaImpl cargaPartida(String fich, int posicion) {
		PartidaImpl res = null;

		File fichero = new File(fich);
		ObjectInputStream ois = null;

		if (fichero.isFile()) {// fis
			try {
				ois = new ObjectInputStream(new FileInputStream(fichero));
				for (int i = 0; i <= posicion; i++) {
					res = (PartidaImpl) ois.readObject();
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} finally {
				try {
					if (ois != null) {
						ois.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return res;
	}

	/**
	 * void guardarPartida(String fich, JugadorImpl j, int fase, Calendar fecha)
	 * 
	 * Descripción: Guarda una partida en un fichero. 
	 * Entradas: Fecha(Calendar), JugadorImpl, Fase 
	 * Salidas: 
	 * Entradas/Salidas: String (nombre del fichero)
	 * Precondiciones: 
	 * Postcondiciones: Crea una partida a partir de los parámetros y lo guarda en un fichero.
	 */
	public static void guardarPartida(String fich, JugadorImpl j, int fase, Calendar fecha) {
		PartidaImpl partida = null;
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		File fichero = new File(fich);

		try {
			fos = new FileOutputStream(fichero);
			oos = new ObjectOutputStream(fos);

			partida = new PartidaImpl(j, fase, fecha);
			oos.writeObject(partida);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (oos != null) {
					oos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * static void anadirItem(JugadorImpl j, ItemImpl item)
	 * 
	 * Descripción: añade un item a un jugador, validando la entrada. 
	 * Entradas: Item 
	 * Salidas: 
	 * Entradas/Salidas: Jugador 
	 * Precondiciones: 
	 * Postcondiciones: añade el item al inventario del jugador.
	 */
	public static void anadirItem(JugadorImpl j, ItemImpl item, MiJFrame mj) {
		char c;
		int sel;
		ItemImpl temp;
		boolean seguir = true;
		for (int i = 0; i < 8 && seguir; i++) {
			temp = j.getItemInventario(i);
			if (temp == null) {
				j.setItemInventario(item, i);
				seguir = false;
				mj.anadeLinea("Has obtenido " + item);
			}
		}
		if (seguir) {
			do {
				mj.anadeLinea("Tu inventario está lleno ¿Quieres eliminar un item para poder añadir otro?(S/N)");
				c = Character.toUpperCase(mj.sigString().charAt(0));
				if (c != 'S' && c != 'N')
					mj.anadeLinea("Carácter incorrecto");
				else {
					if (c == 'S') {
						muestraInventario(j, mj);
						do {
							mj.anadeLinea("Elije el objeto que deseas eliminar.");
							sel = mj.sigInt();
							if (sel < 0 || sel > 9)
								mj.anadeLinea("¡Opción no válida!");
							else {
								do {
									mj.anadeLinea("¿Eliminar " + j.getItemInventario(sel) + "?(S/N)");
									c = Character.toUpperCase(mj.sigString().charAt(0));
									if (c != 'S' && c != 'N')
										mj.anadeLinea("Carácter incorrecto");
									else if (c == 'S') {
										j.eliminarItem(sel);
										j.setItemInventario(item, sel);
										mj.anadeLinea("Has obtenido " + item);
									}
								} while (c != 'S' && c != 'N');
							}
						} while (sel < 0 || sel > 9);
					}
				}
			} while (c != 'S' && c != 'N');
		}

	}
	
	/** 
	 * void guardarPartida(String fich, String fichAux, JugadorImpl j, int fase, Calendar fecha, int pos)
	 * 
	 * Descripción: Guarda una partida en un fichero. 
	 * Entradas: String (nombre del fichero), Fecha(Calendar), posicion int, Jugador j 
	 * Salidas:
	 * Entradas/Salidas:
	 * Precondiciones: 
	 * Postcondiciones: Crea una partida a partir de los parámetros y lo guarda en un fichero en la posicion pasada.
	 */
	public static void guardarPartida(String fich, String fichAux, JugadorImpl j, int fase, Calendar fecha, int pos) {
		PartidaImpl partidaAGuardar = new PartidaImpl(j, fase, fecha);
		PartidaImpl partidaExistente = null;

		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;

		File fichero = new File(fich);
		File auxiliar = new File(fichAux);

		// Escribir a fichero auxiliar
		try {
			ois = new ObjectInputStream(new FileInputStream(fichero));
			oos = new ObjectOutputStream(new FileOutputStream(auxiliar));

			for (int i = 0; i < 3; i++) {
				if (pos == i){
					oos.writeObject(partidaAGuardar);
					partidaExistente = (PartidaImpl) ois.readObject();
				}
				else {
					partidaExistente = (PartidaImpl) ois.readObject();
					oos.writeObject(partidaExistente);
				}

			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				oos.close();
				ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// Eliminar fichero original y renombrar fichero auxiliar
		fichero.delete();
		auxiliar.renameTo(fichero);
	}

	/**
	 * void creaFicheroPartidas (String fich)
	 * 
	 * Descripción: crea un fichero para almacenar partidas 
	 * Entradas: String(nombre del fichero) 
	 * Salidas: 
	 * Entradas/Salidas: 
	 * Precondiciones:
	 * Postcondiciones: crea un fichero con tres partidas null
	 */
	public static void creaFicheroPartidas(String fich) {

		PartidaImpl partida = null;
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		File fichero = new File(fich);

		try {
			fos = new FileOutputStream(fichero);
			oos = new ObjectOutputStream(fos);
			for (int i = 0; i < 3; i++) {
				oos.writeObject(partida);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (oos != null) {
				try {
					oos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (oos != null) {
				try {
					oos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}
}
