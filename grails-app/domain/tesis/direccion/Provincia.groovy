package tesis.direccion;

public class Provincia {
    String nombre;
    static hasMany = [localidades:Localidad]
    Pais pais;

    @Override
    public String toString(){
        return getNombre();
    }
}
