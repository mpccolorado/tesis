package tesis.pedido.estadosMesa;

import tesis.pedido.Mesa;
import tesis.excepciones.AccionIlegalException;

public class MesaReservada implements IEstadoMesa {

    public void reservar(Mesa mesa){
        throw new AccionIlegalException();
    }

    public void cancelarReserva(Mesa mesa){
        mesa.setEstado(EstadoMesa.Disponible);
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
        throw new AccionIlegalException();
    }
}
