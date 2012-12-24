package tesis.caja;

public class Cobro {
    static hasMany = [detalles:DetalleDeCobro];
    Date fecha;

    public Double getTotal(){
        Double resultado = 0.0;
        for(DetalleDeCobro detalleDeCobro : detalles)
            resultado += detalleDeCobro.getTotalNeto();
        return resultado;
    }
}
