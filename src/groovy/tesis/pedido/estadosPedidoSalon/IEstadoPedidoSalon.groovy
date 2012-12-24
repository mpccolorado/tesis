package tesis.pedido.estadosPedidoSalon;

import tesis.pedido.PedidoDeSalon;

public interface IEstadoPedidoSalon {
    void anular(PedidoDeSalon pedido);
    void cancelar(PedidoDeSalon pedido);
    void cerrar(PedidoDeSalon pedido);
}
