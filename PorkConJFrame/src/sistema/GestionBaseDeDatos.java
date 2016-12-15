package sistema;

import java.sql.*;
import fases.*;
import objetos.*;

/**Esta clase es usada para leer y escribir elementos tipo fase en una base de datos*/

public class GestionBaseDeDatos {
	//Atributos
	private String urlBaseDeDatos;
	private String usuario;
	private String password; 
	private Connection connexionBaseDatos;
	private Statement sentencia;
	
	
	//Constructores
	public GestionBaseDeDatos(String ip, String usuario, String password) {
		urlBaseDeDatos = "jdbc:sqlserver://"+ip;
		this.usuario = usuario;
		this.password = password;
		conectar();
	}
	
	
	public GestionBaseDeDatos(){
		urlBaseDeDatos = "jdbc:sqlserver://localhost";
		usuario = "porkUser";
		password = "porkUser";
		conectar();
	}


	/**
	 * void conectar()
	 * 
	 * Descripción: se conecta con la base de datos 
	 * Entradas: 
	 * Salidas: Connection, Statement
	 * Entradas/Salidas: 
	 * Precondiciones: 
	 * Postcondiciones: Se conecta con la base de datos y actualiza los atributos connexionBaseDatos y sentencia
	 */
	public void conectar(){
		
		try {
			connexionBaseDatos = DriverManager.getConnection(urlBaseDeDatos, usuario, password);
			sentencia = connexionBaseDatos.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("ERROR: No se pudo conectar con la Base de Datos.");
		}
	}
	
	
	/**
	 * void desconectar()
	 * 
	 * Descripción: se desconecta de la base de datos 
	 * Entradas: 
	 * Salidas: Connection, Statement
	 * Entradas/Salidas: 
	 * Precondiciones: 
	 * Postcondiciones: Se desconecta de la base de datos y cierra los atributos connexionBaseDatos y sentencia
	 */
	public void desconectar(){		
		try {
			sentencia.close();		
			connexionBaseDatos.close();
		} catch (SQLException e) {
			System.out.println("ERROR: No se pudo cerrar la conexión.");
		}
	}
	


	/**
	 * FaseImpl getFaseBaseDeDatos(int id)
	 * 
	 * Descripción: obtiene una fase de la base de datos 
	 * Entradas: id de Fase
	 * Salidas: FaseImpl 
	 * Entradas/Salidas: 
	 * Precondiciones: 
	 * Postcondiciones: devuelve la fase almacenada en la base de datos que tenga esa id o null
	 * 					si no existe
	 */
	public FaseImpl getFaseBaseDeDatos(int id) {
		//Variables
		FaseImpl res = null;
		String descripcion = null;	
		String[] opciones = new String[5];
		TipoFase tipo;
		int i = 0;
		int[] salidas = new int[5];
		Integer sal;
		EnemigoImpl enemigo = null;
		ItemImpl item = null;
		int opcReq = 0;
		String tipoItem;
		
		ResultSet rs = null;
		
		String dbTipo = "select * from obtenerFase("+id+")";
		String dbTexto = "select * from textosPorId("+id+")";
		String dbEnemigo = "select * from obtenerEnemigo("+id+")";
		String dbItem = "select * from obtenerItem("+id+")";

		try {
			//Obtener tipo
			rs = sentencia.executeQuery(dbTipo);
			rs.next();
			tipo = TipoFase.valueOf(rs.getString("tipo"));
			
			//Obtener textos y descripciones
			rs = sentencia.executeQuery(dbTexto);
			while(rs.next()){				
				sal = rs.getInt("salida");
				if(sal!=0){
					opciones[i] = rs.getString("texto").replace("\\n", "\n");
					salidas[i] = (int) sal;
					i++;
				}					
				else{
					descripcion=rs.getString("texto").replace("\\n", "\n");
				}
				
			}			
			
			//Obtener elementos específicos
			if(tipo==TipoFase.COMBATE){
				rs = sentencia.executeQuery(dbEnemigo);rs.next();
				enemigo = new EnemigoImpl(rs.getString("nombre"),rs.getInt("saludmax"), rs.getInt("ataque"),rs.getInt("nivel"));
			}			
			if(tipo==TipoFase.COMBATE || tipo==TipoFase.OPCIONES_RECOMPENSA || tipo==TipoFase.OPCIONES_REQ){
				rs = sentencia.executeQuery(dbItem);
				rs.next();
				tipoItem = rs.getString("tipo");
				switch(tipoItem){
					case "Pocion":
						item = new Pocion(rs.getString("nombre"), rs.getInt("poder"));
						break;
					case "Arma":
						item = new Arma(rs.getString("nombre"), rs.getInt("poder"));
						break;
					case "Clave":
						item = new Clave(rs.getString("nombre"),rs.getInt("poder"));
						break;
				}
			}
			
			//Crear Fase	
			res = new FaseImpl(id, tipo, descripcion,opciones, salidas, enemigo, item, opcReq);
			
		} catch (SQLException e) {
			//e.printStackTrace();
			System.out.println("ERROR: No se pudo obtener los datos de la fase desde la base de datos.");
		}
		return res;
	}
	/**
	 * void escribeFaseBaseDeDatos(FaseImpl fase)
	 * 
	 * Descripción: escribe una fase en la base de datos 
	 * Entradas: FaseImpl
	 * Salidas:  
	 * Entradas/Salidas: 
	 * Precondiciones: 
	 * Postcondiciones:la fase quedará almacenada en la base de datos
	 */
	public void escribeFaseBaseDeDatos(FaseImpl fase){		
		ResultSet res = null;
		String texto = fase.getContexto();
		int salida = 0;
		String insFase = "insert into Fases values("+fase.getId()+", '"+fase.getTipo().toString()+"')";
		String insText = "insert into Textos values("+fase.getId()+",'"+texto+"',"+salida+")";
		
		try {
			res = sentencia.executeQuery("select * from obtenerFase(" + fase.getId() + ")");
			if (res.next())
				System.out.println("ERROR: el id de esta fase ya existe ("+fase.getId()+")");
			else {
				sentencia.executeUpdate(insFase);

				if (fase.getTipo() == TipoFase.GAME_OVER)
					sentencia.executeUpdate(insText);

				if (fase.getOpciones() != null && fase.getSalidas() != null) {
					sentencia.executeUpdate(insText);
					for (int i = 0; i < fase.getOpciones().length; i++) {
						texto = fase.getOpciones()[i];
						salida = fase.getSalidas()[i];
						insText = "insert into Textos values(" + fase.getId() + ",'" + texto + "'," + salida + ")";
						sentencia.executeUpdate(insText);
					}
				}
				if (fase.getEnemigo() != null) {
					EnemigoImpl e = fase.getEnemigo();
					String obtenerEnemigo = "select * from obtenerEnemigoPorDatos ('" + e.getNombre() + "', "
							+ e.getNivel() + ", " + e.getAtaque() + ", " + e.getSaludMaxima() + " )";
					res = sentencia.executeQuery(obtenerEnemigo);
					//Crear enemigo si no existe
					if (!res.next()) {

						String insEnemigo = "insert into Enemigos values( '" + e.getNombre() + "', " + e.getAtaque()
								+ ", " + e.getNivel() + ", " + e.getSaludMaxima() + ")";
						sentencia.executeUpdate(insEnemigo);

						obtenerEnemigo = "select * from obtenerEnemigoPorDatos ('" + e.getNombre() + "', "
								+ e.getNivel() + ", " + e.getAtaque() + ", " + e.getSaludMaxima() + " )";
						res = sentencia.executeQuery(obtenerEnemigo);
						res.next();
					}

					String insFaseEnemigo = "insert into Fases_Enemigos values( " + fase.getId() + ","
							+ res.getInt("id") + ")";
					sentencia.executeUpdate(insFaseEnemigo);
				}

				if (fase.getItem() != null) {
					ItemImpl i = fase.getItem();
					String tipo = null;

					if (i instanceof Arma)
						tipo = "Arma";
					if (i instanceof Pocion)
						tipo = "Pocion";
					if (i instanceof Clave)
						tipo = "Clave";

					String obtenerItem = "select * from obtenerItemPorDatos ('" + tipo + "','" + i.getNombre() + "',"
							+ i.getPoder() + ")";
					res = sentencia.executeQuery(obtenerItem);
					
					//Crear item si no existe
					if (!res.next()) {
						String insItem = "insert into Items values ( '" + tipo + "', '" + i.getNombre() + "', "
								+ i.getPoder() + ") ";
						sentencia.executeUpdate(insItem);
						res = sentencia.executeQuery(obtenerItem);
						res.next();
					}
					String insFaseItem = "insert into Fases_Items values(" + fase.getId() + "," + res.getInt("id")
							+ ")";
					sentencia.executeUpdate(insFaseItem);

				}
			}
		} catch (SQLException e) {
			//e.printStackTrace();
			System.out.println("ERROR: No se pudieron escribir los datos en la base de datos.");
		}

	}
}
