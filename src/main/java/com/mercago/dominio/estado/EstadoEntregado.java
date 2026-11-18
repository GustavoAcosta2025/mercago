package com.mercago.dominio.estado;

import com.mercago.dominio.Pedido;
import com.mercago.dominio.TransicionInvalidaException;

public class EstadoEntregado implements EstadoPedido {

    @Override
    public void despachar(Pedido pedido, int tiempoEstimadoMin) {
        throw new TransicionInvalidaException("El pedido ya fue entregado");
    }

    @Override
    public void entregar(Pedido pedido) {
        throw new TransicionInvalidaException("El pedido ya fue entregado");
    }

    @Override
    public void cancelar(Pedido pedido) {
        throw new TransicionInvalidaException("Un pedido entregado no se puede cancelar");
    }

    @Override
    public String nombre() {
        return "Entregado";
    }
}

