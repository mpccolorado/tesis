package tesis.pedido;

import tesis.direccion.Direccion;
import tesis.pedido.estadosPedidoDelivery.EstadoPedidoDelivery;

public class PedidoParaDelivery extends Pedido {
    EstadoPedidoDelivery estado;
    Direccion direccion;

    public PedidoParaDelivery(){
        estado = EstadoPedidoDelivery.Pendiente;
    }

    public void tomar(){
        estado.tomar(this);
    }
    
    public void preparar(){
        estado.preparar(this);
    }

    public void terminar(){
        estado.terminar(this);
    }

    public void despachar(){
        estado.despachar(this);
    }

    public void entregar(){
        estado.entregar(this);
    }

    public void anular(){
        estado.anular(this);
    }

    public void cancelar(){
        estado.cancelar(this);
    }
}
