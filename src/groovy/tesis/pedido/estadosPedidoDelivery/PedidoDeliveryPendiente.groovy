package tesis.pedido.estadosPedidoDelivery;

import tesis.pedido.PedidoParaDelivery;
import tesis.excepciones.AccionIlegalException;

public class PedidoDeliveryPendiente implements IEstadoPedidoDelivery {

    public void tomar(PedidoParaDelivery PedidoParaDelivery) {
        throw new AccionIlegalException();
    }

    public void preparar(PedidoParaDelivery PedidoParaDelivery){
        PedidoParaDelivery.setEstado(EstadoPedidoDelivery.EnPreparacion);
    }

    public void terminar(PedidoParaDelivery PedidoParaDelivery){
        throw new AccionIlegalException();
    }

    public void despachar(PedidoParaDelivery PedidoParaDelivery){
        throw new AccionIlegalException();
    }

    public void entregar(PedidoParaDelivery PedidoParaDelivery) {
        throw new AccionIlegalException();
    }

    public void anular(PedidoParaDelivery PedidoParaDelivery) {
        PedidoParaDelivery.setEstado(EstadoPedidoDelivery.Anulado);
    }

    public void cancelar(PedidoParaDelivery PedidoParaDelivery){
        PedidoParaDelivery.setEstado(EstadoPedidoDelivery.Cancelado);
    }
}
