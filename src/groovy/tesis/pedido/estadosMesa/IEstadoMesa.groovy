package tesis.pedido.estadosMesa;

import tesis.pedido.Mesa;

public interface IEstadoMesa {
    void reservar(Mesa mesa);
    void cancelarReserva(Mesa mesa);
    void ocupar(Mesa mesa);
    void liberar(Mesa mesa);
    void habilitar(Mesa mesa);
    void deshabilitar(Mesa mesa);
}
