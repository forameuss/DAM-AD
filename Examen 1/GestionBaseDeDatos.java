//Alberto Navarro Gordillo

package examen;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GestionBaseDeDatos {

    //ATRIBUTOS
    private String urlBaseDeDatos;
    private String usuario;
    private String password;
    private Connection conexionBaseDatos;
    private Statement sentencia;

    //CONSTRUCTORES
    //Constructor vacío, (se usa el servidor local host con el usuario "user" y la contraseña "pass")
    public GestionBaseDeDatos() throws SQLException {
        urlBaseDeDatos = "jdbc:sqlserver://localhost";
        usuario = "user";
        password = "pass";
        conectar();       
    }
    
    public GestionBaseDeDatos(String ip, String usuario, String password) throws SQLException {
        urlBaseDeDatos = "jdbc:sqlserver://" + ip;
        this.usuario = usuario;
        this.password = password;
        conectar();
    }    

    /*conectar:
    Inicializa la conexión con la base de datos
    */
    
    public void conectar() throws SQLException {

        try {
            conexionBaseDatos = DriverManager.getConnection(urlBaseDeDatos, usuario, password);
            sentencia = conexionBaseDatos.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("ERROR: No se pudo conectar con la Base de Datos.");
            throw e;
        }
    }

    /*desconectar:
    Termina la conexión con la base de datos
    */
    public void desconectar() {
        try {
            sentencia.close();
            conexionBaseDatos.close();
        } catch (SQLException e) {
            System.out.println("ERROR: No se pudo cerrar la conexión.");
        }
    }
   
    
    /*insertarDatos:
    Introduce los datos de la tabla BI_Actualizaciones en las tablas: 
    BI_Visitas, BI_Mascotas y BI_Mascotas_Enfermedades
    
    */
    
    public void insertarDatos() {
        ResultSet rsActualizaciones;
        ResultSet rsMascotas; 
        

        try {
            //inicializar los ResultSets
            
            //Statement miSentencia = conexionBaseDatos.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.TYPE_UPDATABLE);
            //rsMascotas = miSentencia.executeQuery("SELECT * FROM BI_Mascotas");
            rsMascotas = sentencia.executeQuery("SELECT * FROM BI_Mascotas");
            rsActualizaciones = sentencia.executeQuery("SELECT * FROM BI_Actualizaciones");
            rsActualizaciones.next();
            
            //inicializar los CallableStatements
            CallableStatement csME = conexionBaseDatos.prepareCall("execute insertaMascotasEnfermedades ?, ?, ?");
            CallableStatement csV = conexionBaseDatos.prepareCall("insert into BI_Visitas (Fecha, Temperatura, Peso, Mascota) Values(?, ?, ?, ?)");

            while (rsActualizaciones.next()) {
                
                //insertar mascotas                
                rsMascotas.moveToInsertRow();
                rsMascotas.updateString("Codigo", rsActualizaciones.getString("CodigoMascota"));
                rsMascotas.updateString("Raza", rsActualizaciones.getString("Raza"));
                rsMascotas.updateString("Especie", rsActualizaciones.getString("Especie"));
                rsMascotas.updateDate("FechaNacimiento", rsActualizaciones.getDate("FechaNacimiento"));
                rsMascotas.updateDate("FechaFallecimiento", rsActualizaciones.getDate("FechaFallecimiento"));
                rsMascotas.updateString("Alias", rsActualizaciones.getString("Alias"));
                rsMascotas.updateString("CodigoPropietario", rsActualizaciones.getString("CodigoPropietario"));
                rsMascotas.insertRow();
                rsMascotas.moveToCurrentRow();
                
                
                //insertar visitas                
                    csV.setDate(1, rsActualizaciones.getDate("Fecha"));
                    csV.setInt(2,rsActualizaciones.getInt("Temperatura"));
                    csV.setInt(3,rsActualizaciones.getInt("Peso"));
                    csV.setString(4, rsActualizaciones.getString("Mascota"));            
                    csV.executeUpdate(); 
                
                //insertar en Mascotas_Enfermedades
                if(rsActualizaciones.getString("Enfermedad")!=null){
                    csME.setString(1, rsActualizaciones.getString("Enfermedad"));
                    csME.setDate(2, rsActualizaciones.getDate("Fecha"));
                    csME.setString(3, rsActualizaciones.getString("CodigoMascota"));
                    csME.executeUpdate();
                }             
                                
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("ERROR: No se pudo obtener los datos desde la base de datos.");
        }
    }   
}
