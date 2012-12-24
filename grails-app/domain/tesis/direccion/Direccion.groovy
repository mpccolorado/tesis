package tesis.direccion;

public class Direccion {
    String calle;
    Integer numero;
    Localidad localidad;

    @Override
    public String toString(){
        return localidad.getNombre() + " - " + calle + " " + numero;
    }
}
