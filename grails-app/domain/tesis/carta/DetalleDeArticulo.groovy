package tesis.carta;

import tesis.articulo.Articulo;

public class DetalleDeArticulo {
    Articulo articulo;
    Double precio;
    String imagen;
    Short orden;

    @Override
    public String toString(){
        return articulo.getNombre() + " \$" + precio;
    }
}
