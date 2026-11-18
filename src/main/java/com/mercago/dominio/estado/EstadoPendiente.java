package com.mercago.dominio.estado;

import com.mercago.dominio.Pedido;
import com.mercago.dominio.TransicionInvalidaException;

public class EstadoPendiente implements EstadoPedido {

    @Override
    public void despachar(Pedido pedido, int tiempoEstimadoMin) {
        pedido.setTiempoEstimadoMin(tiempoEstimadoMin);
        pedido.cambiarEstado(new EstadoEnCamino());
    }

    @Override
    public void entregar(Pedido pedido) {
        throw new TransicionInvalidaException("El pedido aún no ha salido en camino");
    }

    @Override
    public void cancelar(Pedido pedido) {
        pedido.cambiarEstado(new EstadoCancelado());
    }

    @Override
    public String nombre() {
        return "Pendiente";
    }
}

