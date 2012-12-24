package tesis.proveedor;

import tesis.datos.CondicionIVA;
import tesis.persona.Persona;

public class Proveedor extends Persona {
    String razonSocial;
    Boolean esPersonaFisica;
    String nombreFantasia;
    String numeroCuit;
    CondicionIVA condicionIVA;

    @Override
    public String toString(){
        return razonSocial;
    }
}
