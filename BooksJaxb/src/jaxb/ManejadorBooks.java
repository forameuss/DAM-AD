
package jaxb;


import java.io.*;
import javax.xml.bind.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ManejadorBooks {
    BookStore listaAtomos;
    public void abrirListaAtomosJAXB (File archivoXML){
        JAXBContext contexto;
        try {
            contexto = JAXBContext.newInstance(BookStore.class);
            Unmarshaller u = contexto.createUnmarshaller();
            listaAtomos = (BookStore) u.unmarshal(archivoXML);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public void recorreListaAtomos(){
        BookEscribible atomoTuneao;
        List<Book> miListaAtomos = listaAtomos.getAtomo();
        for(Book unAtomo:miListaAtomos){
            atomoTuneao = new BookEscribible(unAtomo);
            System.out.println("\nSiguiente elemento\n----------------------------------------------");
            System.out.println(atomoTuneao.getTodo());
        }
    }
    public void anadirAtomo(Atomo nuevo){
        listaAtomos.getAtomo().add(nuevo);
    }
    public void guardarListaAtomos(File archivoXML){
        JAXBContext contexto;
        try {
            contexto = JAXBContext.newInstance(BookStore.class);
            Marshaller marshalero = contexto.createMarshaller();
            marshalero.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            StringWriter escribiente = new StringWriter();
            marshalero.marshal(listaAtomos, archivoXML);
            // ahora lo marshaleamos a un stream para visualizarlo
            marshalero.marshal(listaAtomos, escribiente);
            System.out.println("-----------------");
            System.out.println("Object2XML:");
            System.out.println(escribiente.toString());
            System.out.println("-----------------");
        } catch (JAXBException ex) {
            Logger.getLogger(ManejadorBooks.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
