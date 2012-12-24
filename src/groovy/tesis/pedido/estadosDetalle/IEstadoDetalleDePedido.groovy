package tesis.pedido.estadosDetalle;

import tesis.pedido.DetalleDePedido;

public interface IEstadoDetalleDePedido {
    void preparar(DetalleDePedido detalle);
    void terminar(DetalleDePedido detalle);
    void entregar(DetalleDePedido detalle);
    void anular(DetalleDePedido detalle);
    void cancelar(DetalleDePedido detalle);
}
