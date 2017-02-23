package jaxb;

import java.io.File;


public class PruebasJAXB {

   
    public static void main(String[] args) {
        ManejadorBooks ma = new ManejadorBooks();
        File origen = new File("src\\marcas\\books.xml");
        File destino = new File("src\\marcas\\masatomos.xml");
        // Cargamos el XML mediante unmarshaling
        ma.abrirListaAtomosJAXB(origen);
        // Comprobamos que se ha cargado
        ma.recorreListaAtomos();
        // Ahora vamos a añadir otro átomo
        Book nuevoAtomo = new Atomo();
        nuevoAtomo.setNombre("Luchino");
        nuevoAtomo.setSimbolo("Lu");
        nuevoAtomo.setNumeroAtomico(113);
        ma.anadirAtomo (nuevoAtomo);
        // Y generamos un nuevo XML mediante marshaling
        ma.guardarListaAtomos(destino);
    }
}
