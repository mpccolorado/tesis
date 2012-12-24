package tesis.pedido.estadosMesa;

import tesis.pedido.Mesa;

public enum EstadoMesa implements IEstadoMesa{
    Disponible(new MesaDisponible()),
    Reservada(new MesaReservada()),
    Ocupada(new MesaOcupada()),
    Deshabilitada(new MesaDeshabilitada());

    private final IEstadoMesa estado;
    EstadoMesa(IEstadoMesa estado) {
        this.estado = estado;
    }

    public void reservar(Mesa mesa){
        estado.reservar(mesa);
    }
    public void cancelarReserva(Mesa mesa){
        estado.cancelarReserva(mesa);
    }
    public void ocupar(Mesa mesa){
        estado.ocupar(mesa);
    }
    public void liberar(Mesa mesa){
        estado.liberar(mesa);
    }
    public void habilitar(Mesa mesa){
        estado.liberar(mesa);
    }
    public void deshabilitar(Mesa mesa){
        estado.deshabilitar(mesa);
    }
    
}
