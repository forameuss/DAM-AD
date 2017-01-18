/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplohibernateempleados;

import modelo.Empleado;
import modelo.PersonFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class EjemploHibernateEmpleados {

   private static SessionFactory sessionFactory = null;
 
    public static void main(String[] args) {
        Session session = null;
        try {
            try {
                sessionFactory = PersonFactory.getSessionFactory();
                session = sessionFactory.openSession();
 
                System.out.println("Insertando registro");
                Transaction tx = session.beginTransaction();
                //Creando un Objeto
                //Empleado employe = new Empleado();
                //employe.setNombre("Juanito");
                //employe.setApellido("De la Vega");
                Empleado fulanito = new Empleado();
                fulanito.setApellido("Perico");
                fulanito.setNombre("Tilla");
                fulanito.setMovil("666111020");
                fulanito.setId(6L);
                //Guardando
                //session.save(employe);
                session.save(fulanito);
                fulanito.setApellido("Liflor");
                tx.commit();
                System.out.println("Finalizado...");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } finally {
            session.close();
        }
    }
}
