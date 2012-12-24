package tesis.pedido.estadosDetalle;

import tesis.pedido.DetalleDePedido;

public class DetallePendiente implements IEstadoDetalleDePedido {

    public void preparar(DetalleDePedido detalle) {
        detalle.addNuevoEstadoAlHistorial(EstadoDetalleDePedido.EnPreparacion);
    }

    public void terminar(DetalleDePedido detalle) {
        ;
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
        return "Pendiente";
    }
}
