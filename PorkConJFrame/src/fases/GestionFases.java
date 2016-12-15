package fases;

import java.io.*;

import sistema.MiObjectOutputStream;

public class GestionFases {
	
	/** FaseImpl buscaFase(int id, String fichero)
	 * 
	 * Descripción: busca una fase en un fichero
	 * Entradas: int id, String fichero
	 * Salidas: FaseImpl
	 * Entradas/Salidas: 
	 * Precondiciones: 
	 * Postcondiciones: devuelve la primera fase que contenga el id pasado en el fichero
	 */
	public static FaseImpl buscaFase(int id, String fichero) {
		boolean encontrado = false;
		FaseImpl fase, faseRes = null;

		File f = new File(fichero);
		FileInputStream fis = null;
		ObjectInputStream ois = null;

		if (f.exists()) {
			try {
				fis = new FileInputStream(f);
				ois = new ObjectInputStream(fis);

				fase = (FaseImpl) ois.readObject();

				while (fase != null && encontrado == false) {
					if (fase.getId() == id) {
						encontrado = true;
						faseRes = fase;
					} else
						fase = (FaseImpl) ois.readObject();
				}
			} catch (EOFException e) {

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
		return faseRes;
	}
	
	
	
	/** void escribeFases(FaseImpl[] fases, String fichero)
	 * 
	 * Descripción: escribe el contenido de en array de fases en un fichero
	 * Entradas: array de fases, String(nombre del fichero)
	 * Salidas: 
	 * Entradas/Salidas: 
	 * Precondiciones: 
	 * Postcondiciones: El contenido del array se descargará en el fichero
	 */
	public static void escribeFases(FaseImpl[] fases, String fichero) {
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		File f = new File(fichero);

		if (f.exists()) {
			try {
				fos = new FileOutputStream(fichero);
				oos = new MiObjectOutputStream(fos);

				for (FaseImpl temp : fases) {
					oos.writeObject(temp);
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
				if(fos != null){
					try {
						fos.close();
					} catch (IOException e){ 
						e.printStackTrace();
					}
				}
			}
		}
		else{
			try {
				fos = new FileOutputStream(fichero);
				oos = new ObjectOutputStream(fos);

				for (FaseImpl temp : fases) {
					oos.writeObject(temp);
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
				if(fos != null){
					try {
						fos.close();
					} catch (IOException e){ 
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	
	
	/** void escribeFases(FaseImpl[] fases, String fichero)
	 * 
	 * Descripción: escribe el contenido de en array de fases en un fichero
	 * Entradas: array de fases, String(nombre del fichero)
	 * Salidas: 
	 * Entradas/Salidas: 
	 * Precondiciones: 
	 * Postcondiciones: El contenido del array se descargará en el fichero
	 */
	public static void escribeFase(FaseImpl fase, String fichero) {
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		File f = new File(fichero);

		if (f.exists()) {
			try {
				fos = new FileOutputStream(fichero);
				oos = new MiObjectOutputStream(fos);

				oos.writeObject(fase);
				
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
				if(fos != null){
					try {
						fos.close();
					} catch (IOException e){ 
						e.printStackTrace();
					}
				}

			}
		}
		else{
			try {
				fos = new FileOutputStream(fichero);
				oos = new ObjectOutputStream(fos);

				oos.writeObject(fase);
				
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
				if(fos != null){
					try {
						fos.close();
					} catch (IOException e){ 
						e.printStackTrace();
					}
				}

			}
		}
	}


}
