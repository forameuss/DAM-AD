package objetos;

import java.io.Serializable;

/**Esta clase representa los objetos que pueden ser recojidos por el jugador*/
public class ItemImpl implements Item, Serializable, Comparable<ItemImpl> {
	private static final long serialVersionUID = 1404447092669550887L;
	
	//Atributos
	private String nombre;
	private int poder;
	
	//Constructor
	public ItemImpl(String nombre, int poder){
		this.nombre=nombre;
		this.poder=poder;
	}
	
	//Getters
	public String getNombre() {
		return nombre;
	}
	
	public int getPoder(){
		return poder;
	}
	
	//Setters
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setPoder(int poder){
		this.poder=poder;
	}
	
	//Métodos sobreescritos
	@Override
	public String toString(){
		return nombre;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}
	
	
	//Dos items son iguales si se llaman igual
	@Override
	public boolean equals(Object obj) {
		boolean res = false;
		if(obj!=null){
			ItemImpl i = (ItemImpl) obj;
			res=(i.getNombre().equals(this.nombre));
		}
		return res;
	}

	//Se ordenan por el nombre
	@Override
	public int compareTo(ItemImpl i) {
		return this.nombre.compareTo(i.getNombre());
	}
	
}
