package tesis.pedido;

import tesis.caja.Cobro;
import tesis.cliente.Cliente;
import tesis.empleado.Empleado;
import tesis.pedido.estadosDetalle.EstadoDetalleDePedido;

public class Pedido {
    Date fecha;
    static hasMany = [detalles:DetalleDePedido]
    Cliente cliente;
    String observaciones;
    TipoDeDescuento tipoDeDescuento;
    Empleado empleado;
    Cobro cobro;

    public Double getSubtotal() {
        Double resultado = 0.0;
        for(DetalleDePedido detalle : detalles){
            if(detalle.getDetalleEstado().getEstado() != EstadoDetalleDePedido.Anulado && detalle.getDetalleEstado().getEstado() != EstadoDetalleDePedido.Cancelado)
                resultado += detalle.getSubtotal();
        }
        return resultado;
    }

    public Double getDescuento() {
        Double descuento = 0.0;
        if(tipoDeDescuento != null)
            descuento = getSubtotal() * tipoDeDescuento.getPorcentaje() / 100;
        return descuento;
    }

    public Double getTotal() {
        Double resultado = getSubtotal();
        resultado = resultado - getDescuento();
        return resultado;
    }
}
