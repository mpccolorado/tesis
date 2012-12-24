package tesis.excepciones;

public class AccionIlegalException extends Exception {
    public AccionIlegalException(){
        super();
    }

    public AccionIlegalException(String message) {
	super(message);
    }

    public AccionIlegalException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccionIlegalException(Throwable cause) {
        super(cause);
    }

}
