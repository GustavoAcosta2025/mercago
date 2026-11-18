package com.mercago.dominio.estado;

import com.mercago.dominio.Pedido;

/**
 * Contrato del patrón State. Si una operación no está permitida en el estado actual,
 * lanza TransicionInvalidaException y el pedido queda como estaba.
 */
public interface EstadoPedido {
    void despachar(Pedido pedido, int tiempoEstimadoMin);
    void entregar(Pedido pedido);
    void cancelar(Pedido pedido);
    String nombre();
}

