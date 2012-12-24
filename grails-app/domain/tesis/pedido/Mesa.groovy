package tesis.pedido;

import tesis.pedido.estadosMesa.EstadoMesa;
import tesis.excepciones.AccionIlegalException;

public class Mesa {
    String descripcion;
    EstadoMesa estado;

    public Mesa(){
        estado = EstadoMesa.Disponible;
    }

    public void reservar() throws AccionIlegalException{
        estado.reservar(this);
    }

    public void cancelarReserva() throws AccionIlegalException{
        estado.cancelarReserva(this);
    }

    public void ocupar() throws AccionIlegalException{
        estado.ocupar(this);
    }

    public void liberar() throws AccionIlegalException{
        estado.liberar(this);
    }

    public void habilitar() throws AccionIlegalException{
        estado.liberar(this);
    }

    public void deshabilitar() throws AccionIlegalException{
        estado.deshabilitar(this);
    }

    @Override
    public String toString() {
        return descripcion;
    }
}
