package tesis.caja;

import tesis.empleado.Empleado;

public class CierreCaja {
    Date fecha;
    Caja caja;
    Double montoInicial;
    Double montoCierre;
    Empleado responsable;
    static hasMany = [cobros:Cobro];
}
