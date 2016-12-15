package objetos;

//Estudio de interfaz
/* 
* Propiedades:
* 	Básicas:	
* 		String nombre c, m
* 		int poder c, m	
*/	
public interface Item {
	public String getNombre();
	public int getPoder();
	public void setNombre(String nombre);
	public void setPoder(int poder);

}
