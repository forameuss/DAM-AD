package objetos;

import java.io.Serializable;
/**Esta clase representa un tipo de item que usa el jugador para infligir daño a los enemigos en un combate*/

public class Arma extends ItemImpl implements Serializable {
	private static final long serialVersionUID = -7281666834540775415L;
	
	
	//Constructor
	public Arma(String nombre, int danyo){
		super(nombre, danyo);
	}
	
	//Getters
	public int getDanyo(){
		return super.getPoder();
	}
	
	//Setters
	public void setDanyo(int danyo){
		super.setPoder(danyo);
	}

}
