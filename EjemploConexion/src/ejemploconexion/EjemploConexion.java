/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemploconexion;

/**
 *
 * @author anavarro
 */
public class EjemploConexion {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Películas:");
        
        GestionBaseDeDatos gbd = new GestionBaseDeDatos("localhost", "usuario", "contrasenya");
        
        for(String temp: gbd.getPeliculas())       
            System.out.println("- "+temp);
        
        gbd.desconectar();
    }
    
}
