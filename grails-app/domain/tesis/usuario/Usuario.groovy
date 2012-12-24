package tesis.usuario;

import tesis.empleado.Empleado;

public class Usuario {
    String nombre;
    String contrasenia;
    transient Sesion sesionActual;
    TipoUsuario tipoUsuario;
    Empleado empleado;
}
