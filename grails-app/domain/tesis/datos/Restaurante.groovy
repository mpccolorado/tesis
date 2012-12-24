package tesis.datos;

import tesis.direccion.Direccion;
import tesis.telefono.Telefono;

public class Restaurante {
    String nombreFantasia;
    Direccion direccion;
    Set<Telefono> telefonos;
    static hasMany = [mails:Mail];
    String imagenLogo;

    @Override
    String toString(){
        nombreFantasia
    }
}
