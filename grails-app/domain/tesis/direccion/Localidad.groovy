package tesis.direccion;

public class Localidad {
    String nombre;
    Provincia provincia;

    @Override
    public String toString(){
        return getNombre();
    }
}
