package tesis.usuario;

public class VistaNoDisponibleException extends Exception {
    public VistaNoDisponibleException(){
        super();
    }
    public VistaNoDisponibleException(String message){
        super(message);
    }
    public VistaNoDisponibleException(String message, Throwable cause) {
        super(message, cause);
    }
}
