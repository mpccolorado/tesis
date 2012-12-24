package tesis.pedido;

import tesis.articulo.Articulo;
import tesis.pedido.estadosDetalle.EstadoDetalleDePedido;

public class DetalleDePedido {
    Articulo articulo;
    Integer cantidad;
    Double precio;
    DetalleHistorialEstado detalleEstado;
    String observaciones;
    Pedido pedido;
    static hasMany = [historial:DetalleHistorialEstado];


    public DetalleDePedido() {
        cantidad = 1;
        detalleEstado = new DetalleHistorialEstado();
        detalleEstado.setEstado(EstadoDetalleDePedido.Pendiente);
        detalleEstado.setFechaInicio(new Date());
    }

    public void preparar() {
        detalleEstado.getEstado().preparar(this);
    }

    public void terminar() {
        detalleEstado.getEstado().terminar(this);
    }

    public void entregar() {
        detalleEstado.getEstado().entregar(this);
    }

    public void anular() {
        detalleEstado.getEstado().anular(this);
    }

    public void cancelar() {
        detalleEstado.getEstado().cancelar(this);
    }

    public void addNuevoEstadoAlHistorial(EstadoDetalleDePedido estado){
        //Creo un nuevo DetalleHistorialEstado con la fecha actual, y el parámetro estado
        DetalleHistorialEstado nuevoDetalleEstado = new DetalleHistorialEstado();
        nuevoDetalleEstado.setEstado(estado);
        nuevoDetalleEstado.setFechaInicio(new Date());

        //Le pongo fecha de fin al estado actual
        detalleEstado.setFechaFin(new Date());
        //Agrego al historial el estado actual
        historial.add(detalleEstado);
        
        //Reemplazo el estado actual por el nuevo estado
        detalleEstado = nuevoDetalleEstado;
    }
}
