package tesis.pedido.estadosDetalle;

import tesis.pedido.DetalleDePedido;

public class DetalleEnPreparacion implements IEstadoDetalleDePedido {

    public void preparar(DetalleDePedido detalle) {
        ;
    }

    public void terminar(DetalleDePedido detalle) {
        detalle.addNuevoEstadoAlHistorial(EstadoDetalleDePedido.ListoParaEntregar);
    }

    public void entregar(DetalleDePedido detalle) {
        ;
    }

    public void anular(DetalleDePedido detalle) {
        detalle.addNuevoEstadoAlHistorial(EstadoDetalleDePedido.Anulado);
    }

    public void cancelar(DetalleDePedido detalle) {
        detalle.addNuevoEstadoAlHistorial(EstadoDetalleDePedido.Cancelado);
    }

    @Override
    public String toString() {
        return "En preparación";
    }
}

