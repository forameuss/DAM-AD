import java.sql.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import modelos.Actualizacion;
import modelos.Cliente;
import modelos.Enfermedad;
import modelos.MascotaEnfermedad;
import modelos.Mascota;
import modelos.Visita;

public class Main {
    private static SessionFactory sessionFactory = null;

    public static void main(String[] args) {
        Session session = null;
        try {
            try {
                //Crear Session Factory
                sessionFactory = MyFactory.getSessionFactory();
                session = sessionFactory.openSession();

                Transaction trans = session.beginTransaction();
                System.out.println("\nTRANSACCIÓN INICIADA:\n");

                List<Actualizacion> listaActualizaciones;
                listaActualizaciones = (List<Actualizacion>) session.createQuery("from Actualizacion").list();

                Mascota masc;
                Visita vis;
                MascotaEnfermedad mascEnf;

                for(Actualizacion act : listaActualizaciones){


                    //Si la mascota no existe
                    if (act.getRaza()!=null && act.getEspecie()!=null && act.getCodigoPropietario() != null) {
                        masc = new Mascota();
                        masc.setCodigo(act.getCodigoMascota());
                        masc.setRaza(act.getRaza());
                        masc.setEspecie(act.getEspecie());
                        masc.setFechaNacimiento(act.getFechaNacimiento());
                        masc.setFechaFallecimiento(act.getFechaFallecimiento());
                        masc.setAlias(act.getAlias());

                        masc.setCliente(session.get(Cliente.class, act.getCodigoPropietario()));

                        System.out.println("Insertando mascota: "+masc);
                        session.save(masc);

                    //Si la mascota ya existe
                    } else {
                        masc = session.get(Mascota.class, act.getCodigoMascota());
                    }

                    //Si se le ha asignado una enfermedad
                    if (act.getEnfermedad() != null) {

                        mascEnf  = new MascotaEnfermedad();

                        int idEnf = (Integer) session.createQuery("select id from Enfermedad where nombre like'"+ act.getEnfermedad() + "'").uniqueResult();

                        mascEnf.setMascota(masc);
                        mascEnf.setEnfermedad(session.get(Enfermedad.class, idEnf));
                        mascEnf.setFechaInicio(new Date(act.getFecha().getTime()));

                        System.out.println("Insertando mascota_enfermedad: "+mascEnf);
                        session.save(mascEnf);
                    }

                    //Insertar visita
                    vis = new Visita();
                    vis.setFecha(act.getFecha());
                    vis.setTemperatura(act.getTemperatura());
                    vis.setPeso(act.getPeso());
                    vis.setMascota(masc);

                    System.out.println("Insertando visita: "+vis);
                    session.save(vis);
                }

                trans.commit();
                System.out.println("\n¡TRANSACCIÓN COMPLETADA!");

            } catch (Exception e) {
                System.out.println("ERROR: "+e.getMessage()+".");
                System.exit(-1);
            }
        } finally {
            session.close();
            System.exit(0);
        }
    }

}