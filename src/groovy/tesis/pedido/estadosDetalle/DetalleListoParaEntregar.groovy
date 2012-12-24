package tesis.pedido.estadosDetalle;

import tesis.pedido.DetalleDePedido;

public class DetalleListoParaEntregar implements IEstadoDetalleDePedido {

    public void preparar(DetalleDePedido detalle) {
        ;
    }

    public void terminar(DetalleDePedido detalle) {
        ;
    }

    public void entregar(DetalleDePedido detalle) {
        detalle.addNuevoEstadoAlHistorial(EstadoDetalleDePedido.Entregado);
    }

    public void anular(DetalleDePedido detalle) {
        detalle.addNuevoEstadoAlHistorial(EstadoDetalleDePedido.Anulado);
    }

    public void cancelar(DetalleDePedido detalle) {
        detalle.addNuevoEstadoAlHistorial(EstadoDetalleDePedido.Cancelado);
    }

    @Override
    public String toString() {
        return "Listo para entregar";
    }
}
