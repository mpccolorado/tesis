package tesis.pedido.estadosPedidoSalon;

import tesis.pedido.PedidoDeSalon;

public enum EstadoPedidoSalon implements IEstadoPedidoSalon{
    Abierto(new PedidoSalonAbierto()),
    Cerrado(new PedidoSalonCerrado()),
    Cancelado(new PedidoSalonCancelado()),
    Anulado(new PedidoSalonAnulado());

    private final IEstadoPedidoSalon estado;
    EstadoPedidoSalon(IEstadoPedidoSalon estado) {
        this.estado = estado;
    }

    public void cerrar(PedidoDeSalon pedido){
        estado.cerrar(pedido);
    }

    public void anular(PedidoDeSalon pedido){
        estado.anular(pedido);
    }

    public void cancelar(PedidoDeSalon pedido){
        estado.cancelar(pedido);
    }
}
