package tesis.pedido.estadosPedidoSalon;

import tesis.pedido.PedidoDeSalon;

public class PedidoSalonAbierto implements IEstadoPedidoSalon {

    public void anular(PedidoDeSalon pedido) {
        pedido.setEstado(EstadoPedidoSalon.Anulado);
    }

    public void cancelar(PedidoDeSalon pedido) {
        pedido.setEstado(EstadoPedidoSalon.Cancelado);
    }

    public void cerrar(PedidoDeSalon pedido) {
        pedido.setEstado(EstadoPedidoSalon.Cerrado);
    }
}
