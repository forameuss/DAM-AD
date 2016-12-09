package ejemploconexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class GestionBaseDeDatos {

    //Atributos
    private String urlBaseDeDatos;
    private String usuario;
    private String password;
    private Connection connexionBaseDatos;
    private Statement sentencia;

    //Constructores
    public GestionBaseDeDatos(String ip, String usuario, String password) {
        urlBaseDeDatos = "jdbc:sqlserver://" + ip;
        this.usuario = usuario;
        this.password = password;
        conectar();
    }

    public GestionBaseDeDatos() {
        urlBaseDeDatos = "jdbc:sqlserver://localhost";
        usuario = "user";
        password = "pass";
        conectar();
    }

    /**
     * void conectar()
     *
     * Descripción: se conecta con la base de datos Entradas: Salidas:
     * Connection, Statement Entradas/Salidas: Precondiciones: Postcondiciones:
     * Se conecta con la base de datos y actualiza los atributos
     * connexionBaseDatos y sentencia
     */
    public void conectar() {

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
     * Descripción: se desconecta de la base de datos Entradas: Salidas:
     * Connection, Statement Entradas/Salidas: Precondiciones: Postcondiciones:
     * Se desconecta de la base de datos y cierra los atributos
     * connexionBaseDatos y sentencia
     */
    public void desconectar() {
        try {
            sentencia.close();
            connexionBaseDatos.close();
        } catch (SQLException e) {
            System.out.println("ERROR: No se pudo cerrar la conexión.");
        }
    }

    /**
     * ArrayList<String> getPeliculas()
     *
     * Descripción: obtiene un listado de peliculas de la base de datos
     * Entradas: Salidas: ArrayList<String>
     * Entradas/Salidas: Precondiciones: Postcondiciones: Devuelve un ArrayList
     * de string con los nombres de las peliculas
     */
    public ArrayList<String> getPeliculas() {
        ArrayList<String> peliculas = new ArrayList<String>();

        ResultSet rs;

        try {
            rs = sentencia.executeQuery("SELECT * FROM Peliculas");
            rs.next();

            while (rs.next()) {
                peliculas.add(rs.getString("titulo"));
            }

        } catch (SQLException e) {
            //e.printStackTrace();
            System.out.println("ERROR: No se pudo obtener los datos de la fase desde la base de datos.");
        }

        return peliculas;
    }

}
