package tesis.caja;

public class DetalleDeCobro {
    Double monto;
    Double cambio;
    MetodoDePago metodo;
    Date fecha;

    public Double getTotalNeto(){
        return monto - cambio;
    }
}
