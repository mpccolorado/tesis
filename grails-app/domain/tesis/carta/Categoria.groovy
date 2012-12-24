package tesis.carta;

import tesis.articulo.Articulo;

public class Categoria {
    String nombre
    static hasMany = [detalles:DetalleDeArticulo]

    @Override
    public String toString() {
        return nombre;
    }

    public DetalleDeArticulo getDetalleDeArticulo(Articulo articulo){
        for(DetalleDeArticulo detalle : detalles){
            if(detalle.getArticulo().equals(articulo))
                return detalle;
        }
        return null;
    }
}
