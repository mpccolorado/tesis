package tesis.caja;

public class Caja {
    Integer id;
    String descripcion;
    CierreCaja cierreActual;
    static hasMany = [cierres:CierreCaja];
}
