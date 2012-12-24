package tesis.pedido.estadosPedidoDelivery;

import tesis.pedido.PedidoParaDelivery;

public interface IEstadoPedidoDelivery {
    void tomar(PedidoParaDelivery pedido);
    void preparar(PedidoParaDelivery pedido);
    void terminar(PedidoParaDelivery pedido);
    void despachar(PedidoParaDelivery pedido);
    void entregar(PedidoParaDelivery pedido);
    void anular(PedidoParaDelivery pedido);
    void cancelar(PedidoParaDelivery pedido);
}
