package tesis.direccion;

public class Pais {
    String nombre
    static hasMany = [provincias:Provincia]

    @Override
    public String toString(){
        return getNombre();
    }
}
