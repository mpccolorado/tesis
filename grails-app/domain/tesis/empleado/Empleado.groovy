package tesis.empleado;

import tesis.persona.Persona;

public class Empleado extends Persona {
    Cargo cargo;
    Date fechaNacimiento;
    String numeroCuil;

    @Override
    public String toString() {
        return getApellido() + ", " + getNombre();
    }

}
