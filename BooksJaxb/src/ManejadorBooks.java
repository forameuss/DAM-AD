import generated.TipoBook;
import generated.TipoBookStore;
import java.io.*;
import javax.xml.bind.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ManejadorBooks {
    TipoBookStore listaBooks;
    public void abrirListaAtomosJAXB (File archivoXML){
        JAXBContext contexto;
        try {
            contexto = JAXBContext.newInstance(TipoBookStore.class);
            Unmarshaller u = contexto.createUnmarshaller();
            listaBooks = (TipoBookStore) u.unmarshal(archivoXML);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public void recorreListaAtomos(){
        BookEscribible bookTuneao;
        List<TipoBook> miListaAtomos = listaBooks.getBook();
        for(TipoBook unAtomo:miListaAtomos){
            bookTuneao = new BookEscribible(unAtomo);
            System.out.println("\nSiguiente elemento\n----------------------------------------------");
            System.out.println(bookTuneao.getTodo());
        }
    }
    
    public void anadirBook(TipoBook nuevo){
        listaBooks.getBook().add(nuevo);
    }    
    
    public void guardarListaBooks(File archivoXML){
        JAXBContext contexto;
        try {
            contexto = JAXBContext.newInstance(TipoBookStore.class);
            Marshaller marshalero = contexto.createMarshaller();
            marshalero.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            StringWriter escribiente = new StringWriter();
            marshalero.marshal(listaBooks, archivoXML);
            // ahora lo marshaleamos a un stream para visualizarlo
            marshalero.marshal(listaBooks, escribiente);
            System.out.println("-----------------");
            System.out.println("Object2XML:");
            System.out.println(escribiente.toString());
            System.out.println("-----------------");
        } catch (JAXBException ex) {
            Logger.getLogger(ManejadorBooks.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
