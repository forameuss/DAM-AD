
import generated.TipoBook;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author anavarro
 */
class BookEscribible extends TipoBook{

    public BookEscribible(TipoBook unBook) {
        super();
        this.setTitle(unBook.getTitle());
        this.setCategory(unBook.getCategory());
        this.setPrice(unBook.getPrice());
        this.setYear(unBook.getYear());
        this.getAuthor().clear();
        this.getAuthor().addAll(unBook.getAuthor());
    }

    public String getTodo() {
        String res;
        res = "Titulo: "+this.getTitle().getValue();
        res += "\nAutores: \n";
        for(String s : this.getAuthor())
            res+= "\t"+s+"\n";        
        res += "\nCategoría: "+this.getCategory();
        res += "\nPrecio: "+this.getPrice();
        res += "\nAño: "+this.getYear();        
        return res;
    }
    
}
