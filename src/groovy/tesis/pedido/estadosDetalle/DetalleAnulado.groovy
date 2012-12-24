package tesis.pedido.estadosDetalle;

import tesis.pedido.DetalleDePedido;

public class DetalleAnulado implements IEstadoDetalleDePedido {

    public void preparar(DetalleDePedido detalle){
        ;
    }

    public void terminar(DetalleDePedido detalle){
        ;
    }

    public void entregar(DetalleDePedido detalle){
        ;
    }

    public void anular(DetalleDePedido detalle){
        ;
    }

    public void cancelar(DetalleDePedido detalle){
        ;
    }

    @Override
    public String toString() {
        return "Anulado";
    }
}
