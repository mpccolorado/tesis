package tesis.cliente;

import tesis.datos.CondicionIVA;
import tesis.persona.Persona;

public class Cliente extends Persona {
    String razonSocial
    Boolean esPersonaFisica = true
    String numeroDeCuit
    Date fechaNacimiento
    CondicionIVA condicionIVA

    @Override
    public String toString(){
        return apellido + ", " + nombre;
    }
}
