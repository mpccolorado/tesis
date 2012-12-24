package tesis.pedido.estadosDetalle;

import tesis.pedido.DetalleDePedido;

public enum EstadoDetalleDePedido implements IEstadoDetalleDePedido{
    Pendiente(new DetallePendiente()),
    EnPreparacion(new DetalleEnPreparacion()),
    ListoParaEntregar(new DetalleListoParaEntregar()),
    Entregado(new DetalleEntregado()),
    Cancelado(new DetalleCancelado()),
    Anulado(new DetalleAnulado());

    private final IEstadoDetalleDePedido estado;
    EstadoDetalleDePedido(IEstadoDetalleDePedido estado) {
        this.estado = estado;
    }

    public void preparar(DetalleDePedido detalle) {
        estado.preparar(detalle);
    }

    public void terminar(DetalleDePedido detalle) {
        estado.terminar(detalle);
    }

    public void entregar(DetalleDePedido detalle) {
        estado.entregar(detalle);
    }

    public void anular(DetalleDePedido detalle) {
        estado.anular(detalle);
    }

    public void cancelar(DetalleDePedido detalle) {
        estado.cancelar(detalle);
    }
}
