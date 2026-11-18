package com.mercago.dominio.estado;

import com.mercago.dominio.Pedido;
import com.mercago.dominio.TransicionInvalidaException;

public class EstadoEnCamino implements EstadoPedido {

    @Override
    public void despachar(Pedido pedido, int tiempoEstimadoMin) {
        throw new TransicionInvalidaException("El pedido ya salió en camino");
    }

    @Override
    public void entregar(Pedido pedido) {
        pedido.cambiarEstado(new EstadoEntregado());
    }

    @Override
    public void cancelar(Pedido pedido) {
        throw new TransicionInvalidaException(
                "Un pedido en camino ya no se puede cancelar (RN-03)");
    }

    @Override
    public String nombre() {
        return "En camino";
    }
}

