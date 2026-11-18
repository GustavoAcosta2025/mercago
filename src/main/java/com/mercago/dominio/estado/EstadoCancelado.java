package com.mercago.dominio.estado;

import com.mercago.dominio.Pedido;
import com.mercago.dominio.TransicionInvalidaException;

public class EstadoCancelado implements EstadoPedido {

    @Override
    public void despachar(Pedido pedido, int tiempoEstimadoMin) {
        throw new TransicionInvalidaException("Un pedido cancelado no se puede despachar");
    }

    @Override
    public void entregar(Pedido pedido) {
        throw new TransicionInvalidaException("Un pedido cancelado no se puede entregar");
    }

    @Override
    public void cancelar(Pedido pedido) {
        throw new TransicionInvalidaException("El pedido ya está cancelado");
    }

    @Override
    public String nombre() {
        return "Cancelado";
    }
}

