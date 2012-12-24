package tesis.pedido.estadosPedidoDelivery;

import tesis.pedido.PedidoParaDelivery;
import tesis.excepciones.AccionIlegalException;

public class PedidoDeliveryDespachado implements IEstadoPedidoDelivery {

    public void tomar(PedidoParaDelivery PedidoParaDelivery){
        throw new AccionIlegalException();
    }

    public void preparar(PedidoParaDelivery PedidoParaDelivery){
        throw new AccionIlegalException();
    }

    public void terminar(PedidoParaDelivery PedidoParaDelivery){
        throw new AccionIlegalException();
    }

    public void despachar(PedidoParaDelivery PedidoParaDelivery){
        throw new AccionIlegalException();
    }

    public void entregar(PedidoParaDelivery PedidoParaDelivery){
        PedidoParaDelivery.setEstado(EstadoPedidoDelivery.Entregado);
    }

    public void anular(PedidoParaDelivery PedidoParaDelivery){
        PedidoParaDelivery.setEstado(EstadoPedidoDelivery.Anulado);
    }

    public void cancelar(PedidoParaDelivery PedidoParaDelivery){
        PedidoParaDelivery.setEstado(EstadoPedidoDelivery.Cancelado);
    }
}

