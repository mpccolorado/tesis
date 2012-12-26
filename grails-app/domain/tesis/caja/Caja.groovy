package tesis.caja;

public class Caja {
    String descripcion;
    CierreCaja cierreActual;
    static hasMany = [cierres:CierreCaja];
}
