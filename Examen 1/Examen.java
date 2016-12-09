//Alberto Navarro Gordillo

package examen;

public class Examen {

    /**Carga en un ResultSet el contenido de la tablaBI_Actualizaciones
        Recorrer el ResultSet insertando las visitas y las mascotas y enfermedades (cuando proceda) en la base de datos de la siguiente forma:
            ·Las inserciones en la tabla BI_Mascotas han de hacerse mediante un ResultSet actualizable.
            ·Las inserciones en BI_MascotasEnfermedades deben hacerse con una sentencia preparada (Prepared Statement o CallableStatement). Esta sentencia debe ejecutar el procedimiento almacenado del ejercicio 1.
            ·Las inserciones en BI_Visitas las puedes hacer como mejor te parezca.**/
    
    
    public static void main(String[] args) {
                
        GestionBaseDeDatos gbd;
        
        //Iniciar conexion
        System.out.println("INICIANDO CONEXIÓN CON LA BASE DE DATOS...");
        try{
            gbd = new GestionBaseDeDatos("localhost", "bichoAdmin", "pass");
            
            System.out.println("CONEXIÓN ESTABLECIDA, INSERTANDO ELEMENTOS...");            
           
            //Insertar datos
            try{
                gbd.insertarDatos();
                System.out.println("TODOS LOS ELEMENTOS HAN SIDO INSERTADOS.");
                
            }catch(Exception e){
                System.out.println("ERROR, NO SE PUDIERON INSERTAR LOS ELEMENTOS:\n"+e);
            }
            
            
        }catch(Exception e){
            System.out.println("ERROR, NO SE PUDO ESTABLECER LA CONEXION:\n"+e);
        }
        
    }
    
}
