package tesis.pedido;

import tesis.pedido.estadosPedidoSalon.EstadoPedidoSalon;

public class PedidoDeSalon extends Pedido {
    EstadoPedidoSalon estado;
    Mesa mesa;
    int cantidadDePersonas;

    public PedidoDeSalon() {
        estado = EstadoPedidoSalon.Abierto;
    }

    public void cerrar() {
        estado.cerrar(this);
    }

    public void anular() {
        estado.anular(this);
    }

    public void cancelar() {
        estado.cancelar(this);
    }
}
