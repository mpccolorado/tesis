package tesis.direccion;

public class Localidad {
    Short id;
    String nombre;
    Provincia provincia;

    @Override
    public String toString(){
        return getNombre();
    }
}
