package tesis.articulo;

public class Articulo{
    String nombre;
    String descripcion;
    Presentacion presentacion;
    LugarDePreparacion lugarDePreparacion;

    @Override
    public String toString(){
        return nombre;
    }
}
