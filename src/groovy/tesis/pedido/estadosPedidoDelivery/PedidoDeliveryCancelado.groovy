package tesis.pedido.estadosPedidoDelivery;

import tesis.pedido.PedidoParaDelivery;
import tesis.excepciones.AccionIlegalException;

public class PedidoDeliveryCancelado implements IEstadoPedidoDelivery {

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
        throw new AccionIlegalException();
    }

    public void anular(PedidoParaDelivery PedidoParaDelivery){
        throw new AccionIlegalException();
    }

    public void cancelar(PedidoParaDelivery PedidoParaDelivery){
        throw new AccionIlegalException();
    }
}
