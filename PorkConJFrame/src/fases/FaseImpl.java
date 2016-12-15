package fases;

import java.io.Serializable;
import objetos.*;
import sistema.*;

public class FaseImpl implements Fase, Serializable {
	/**DESCRIPCIÓN: Este tipo representa una fase o pantalla del juego, en el cual el jugador podrá
	 * 				ejecutar una acción, desembocando en otra fase. Dependiendo del valor del atriubto tipo, el método
	 * 				"ejecutar" actuará de forma diferente.
	 * */	
	private static final long serialVersionUID = 6558287603571707181L;
		
	
	//Atributos
	int id;
	TipoFase tipo;
	String contexto;
	String[] opciones;
	int[] salidas;
	ItemImpl item;
	int opcReq;
	EnemigoImpl enemigo;
	
	//CONTRUCTORES
	
	public FaseImpl(int id, TipoFase tipo, String contexto, String[] opciones, int[] salidas,  EnemigoImpl enemigo, ItemImpl item, int opcReq) throws IllegalArgumentException{
		if(tipo==TipoFase.COMBATE && enemigo==null)
			throw new IllegalArgumentException("La fase de tipo combate necesita un enemigo");
		if((tipo==TipoFase.OPCIONES_RECOMPENSA || tipo==TipoFase.OPCIONES_REQ) && item==null)
			throw new IllegalArgumentException("Este tipo de fase necesita un item");
		this.id=id;
		this.contexto=contexto;
		this.opciones=opciones;
		this.salidas=salidas;
		this.tipo=tipo;
		this.enemigo=enemigo;
		this.item=item;
		this.opcReq=opcReq;
	}
	
	
	
	
	//Getters
	
	public int getId(){
		return id;
	}
	
	public TipoFase getTipo() {
		return tipo;
	}

	public String getContexto() {
		return contexto;
	}
	
	public String[] getOpciones(){
		return opciones;
	}
	
	public int[] getSalidas(){
		return salidas;
	}
	
	public ItemImpl getItem() {
		return item;
	}

	public int getOpcReq() {
		return opcReq;
	}

	public EnemigoImpl getEnemigo() {
		return enemigo;
	}
	
	//Setters

	public void setId(int id) {
		this.id = id;
	}

	public void setTipo(TipoFase tipo) {
		this.tipo = tipo;
	}

	public void setContexto(String contexto) {
		this.contexto = contexto;
	}
	
	public void setOpciones(String[] opciones){
		this.opciones=opciones;
	}
	
	public void setSalidas(int[] salidas){
		this.salidas=salidas;
	}
	
	public void setItem(ItemImpl item) {
		this.item = item;
	}

	public void setOpcReq(int opcReq) {
		this.opcReq = opcReq;
	}

	public void setEnemigo(EnemigoImpl enemigo) {
		this.enemigo = enemigo;
	}

	
	
	
	//Métodos sobreescitos
	@Override
	public String toString(){
		return "Fase "+id;
	}
	
	
	
	/**Dos fases son iguales si tienen el mismo id*/
	@Override
	public boolean equals(Object obj) {
		boolean res =false;
		if(obj != null &&  obj instanceof FaseImpl){
			FaseImpl f = (FaseImpl) obj;
			res = (f.getId()==id);
		}			
		return res;
	}

	/**int ejecutar(JugadorImpl j)
	 * 
	 * Descripción: muestra por pantalla un contexto y da al usuario varias opciones para elegir, o inicia un
	 * 				combate, dependiendo del tipo de la Fase
	 * Entradas: 
	 * Salidas: int fase
	 * Entradas/Salidas: JugadorImpl
	 * Precondiciones: 
	 * Postcondiciones: devuelve el índice de la siguiente fase, el Jugador puede sufrir cambios
	 */
	public int ejecutar(JugadorImpl j, MiJFrame mj) {
		//Variables
		int res = 0, sel, tam = 0;
		boolean permitir = false;
		
		switch (tipo){
		case GAME_OVER:
			mj.anadeLinea(contexto);
			res = -1;
			break;
		
		case COMBATE:
			boolean vivo = Combate.iniciar(j, enemigo, item, mj);
			if(vivo)
				res=salidas[0];
			else
				res=-1;
			break;
		
		case OPCIONES_NORMALES:
			mj.anadeLinea("\n"+contexto);		
			mj.anadeLinea();
			tam = 0;
			do{			
				for(int i = 0; i<opciones.length; i++){
					if(opciones[i]!=null){
						mj.anadeLinea((i+1)+". "+opciones[i]);
						tam=i;
					}
				}	
				sel = mj.sigInt();
				if(sel < 1 || sel > tam+1)
					mj.anadeLinea("¡Opción no válida!");
			} while (sel < 1 || sel > tam+1);
			res= salidas[sel-1];
			break;
		
			
		case OPCIONES_RECOMPENSA:
			mj.anadeLinea("\n"+contexto);		
			Utiles.anadirItem(j, item, mj);		
			mj.anadeLinea();
			tam=0;
			do{			
				for(int i = 0; i<opciones.length; i++){
					if(opciones[i]!=null){
						mj.anadeLinea((i+1)+". "+opciones[i]);
						tam=i;
					}
				}				
				sel = mj.sigInt();
				if(sel < 1 || sel > tam+1)
					mj.anadeLinea("¡Opción no válida!");
			} while (sel < 1 || sel > tam+1);
			res= salidas[sel-1];
			break;
		
		
		case OPCIONES_REQ:
			mj.anadeLinea("\n"+contexto);		
			mj.anadeLinea();
			tam = 0;
			do{			
				for(int i = 0; i<opciones.length; i++){
					if(opciones[i]!=null){
						mj.anadeLinea((i+1)+". "+opciones[i]);
						tam=i;
					}
				}				
				sel = mj.sigInt();
				if(sel == opcReq){
					for(ItemImpl temp: j.getInventario()){
						if(temp instanceof Clave){
							if(temp.equals(item))
								permitir = true;
						}							
					if(!permitir)
						mj.anadeLinea("Necesitas "+item+" para poder hacer eso.");					
					}
				}
				if(sel < 1 || sel > tam+1)
					mj.anadeLinea("¡Opción no válida!");
			} while ((sel < 1 || sel > tam+1) && ((sel == opcReq)&&!permitir));
			res= salidas[sel-1];
			break;	
		
		default:
			mj.anadeLinea("ERROR, la fase "+id+" no está implementada.");
			break;
		}			
		
		return res;	
	}

	

}
