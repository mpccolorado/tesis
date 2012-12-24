package tesis.articulo;

public class Rubro {
    String nombre;
    String descripcion;
    static hasMany = [subrubros:Subrubro]

    @Override
    public String toString(){
        return nombre;
    }
}
