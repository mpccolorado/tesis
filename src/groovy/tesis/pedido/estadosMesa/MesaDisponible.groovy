package tesis.pedido.estadosMesa;

import tesis.pedido.Mesa;
import tesis.excepciones.AccionIlegalException;

public class MesaDisponible implements IEstadoMesa {

    public void reservar(Mesa mesa){
        mesa.setEstado(EstadoMesa.Reservada);
    }

    public void cancelarReserva(Mesa mesa){
        throw new AccionIlegalException();
    }

    public void ocupar(Mesa mesa){
        mesa.setEstado(EstadoMesa.Ocupada);
    }

    public void liberar(Mesa mesa){
        throw new AccionIlegalException();
    }

    public void habilitar(Mesa mesa){
        throw new AccionIlegalException();
    }

    public void deshabilitar(Mesa mesa){
        mesa.setEstado(EstadoMesa.Deshabilitada);
    }
}
