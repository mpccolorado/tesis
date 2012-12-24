package tesis.pedido.estadosPedidoDelivery;

import tesis.pedido.PedidoParaDelivery;
import tesis.excepciones.AccionIlegalException;

public enum EstadoPedidoDelivery implements IEstadoPedidoDelivery{
    Pendiente(new PedidoDeliveryPendiente()),
    EnPreparacion(new PedidoDeliveryEnPreparacion()),
    ListoParaEntregar(new PedidoDeliveryListoParaEntregar()),
    Despachado(new PedidoDeliveryDespachado()),
    Entregado(new PedidoDeliveryEntregado()),
    Cancelado(new PedidoDeliveryCancelado()),
    Anulado(new PedidoDeliveryAnulado());

    private final IEstadoPedidoDelivery estado;
    EstadoPedidoDelivery(IEstadoPedidoDelivery estado) {
        this.estado = estado;
    }

    public void tomar(PedidoParaDelivery pedido){
        estado.tomar(pedido);
    }

    public void preparar(PedidoParaDelivery pedido){
        estado.preparar(pedido);
    }

    public void terminar(PedidoParaDelivery pedido){
        estado.terminar(pedido);
    }

    public void despachar(PedidoParaDelivery pedido){
        estado.despachar(pedido);
    }

    public void entregar(PedidoParaDelivery pedido){
        estado.entregar(pedido);
    }

    public void anular(PedidoParaDelivery pedido){
        estado.anular(pedido);
    }

    public void cancelar(PedidoParaDelivery pedido){
        estado.cancelar(pedido);
    }
}
