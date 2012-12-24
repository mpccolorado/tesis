package tesis.carta;

import tesis.articulo.Articulo;

public class Carta {
    static hasMany = [categorias:Categoria]
    String nombre;
    String descripcion;
    Date fechaDeCreacion;
    Boolean estaVigente;

    public DetalleDeArticulo getDetalleDeArticulo(Articulo articulo){
        for(Categoria categoria : categorias){
            DetalleDeArticulo detalle = categoria.getDetalleDeArticulo(articulo);
            if(detalle != null)
                return detalle;
        }
        return null;
    }

    @Override
    String toString(){
        nombre
    }
}
