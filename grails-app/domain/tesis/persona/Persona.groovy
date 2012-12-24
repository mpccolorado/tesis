package tesis.persona;

import tesis.datos.Mail;
import tesis.direccion.Direccion;
import tesis.telefono.Telefono;

public class Persona {
    String apellido;
    String nombre;
    static hasMany = [telefonos:Telefono, mails:Mail]
    Direccion direccion;
}
