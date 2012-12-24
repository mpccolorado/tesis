package tesis.pedido.estadosMesa;

import tesis.pedido.Mesa;
import tesis.excepciones.AccionIlegalException;

public class MesaOcupada implements IEstadoMesa {

    public void reservar(Mesa mesa){
        throw new AccionIlegalException();
    }

    public void cancelarReserva(Mesa mesa){
        throw new AccionIlegalException();
    }

    public void ocupar(Mesa mesa){
        throw new AccionIlegalException();
    }

    public void liberar(Mesa mesa){
        mesa.setEstado(EstadoMesa.Disponible);
    }

    public void habilitar(Mesa mesa){
        throw new AccionIlegalException();
    }

    public void deshabilitar(Mesa mesa){
        throw new AccionIlegalException();
    }
}
