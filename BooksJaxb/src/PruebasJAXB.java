import generated.TipoBook;
import generated.TipoTitle;
import java.io.File;


public class PruebasJAXB {

   
    public static void main(String[] args) {
        ManejadorBooks ma = new ManejadorBooks();
        File origen = new File("src\\marcas\\books.xml");
        File destino = new File("src\\marcas\\booksFinal.xml");
        // Cargamos el XML mediante unmarshaling
        ma.abrirListaAtomosJAXB(origen);
        // Comprobamos que se ha cargado
        ma.recorreListaAtomos();
        // Ahora vamos a a�adir otro book
        TipoBook nuevoBook = new TipoBook();
        TipoTitle nuevoTitulo = new TipoTitle();
        nuevoTitulo.setValue("1984");
        nuevoTitulo.setLang("en");
        nuevoBook.setTitle(nuevoTitulo);
        nuevoBook.setCategory("Political fiction");
        nuevoBook.setPrice(9.99f);
        nuevoBook.getAuthor().add("George Orwell");
        nuevoBook.setYear((short)1949);
        ma.anadirBook(nuevoBook);
        // Y generamos un nuevo XML mediante marshaling
        ma.guardarListaBooks(destino);
    }
}
