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
        nuevoTitulo.setValue("Espa�a con dos cojones");
        nuevoTitulo.setLang("es");
        nuevoBook.setTitle(nuevoTitulo);
        nuevoBook.setCategory("Educativo");
        nuevoBook.setPrice(9.99f);
        nuevoBook.getAuthor().add("�lvaro Ojeda");
        ma.anadirBook(nuevoBook);
        // Y generamos un nuevo XML mediante marshaling
        ma.guardarListaBooks(destino);
    }
}
