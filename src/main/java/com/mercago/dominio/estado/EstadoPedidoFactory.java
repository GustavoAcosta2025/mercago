package com.mercago.dominio.estado;

public final class EstadoPedidoFactory {

    private EstadoPedidoFactory() {
    }

    public static EstadoPedido desde(String nombre) {
        return switch (nombre) {
            case "Pendiente" -> new EstadoPendiente();
            case "En camino" -> new EstadoEnCamino();
            case "Entregado" -> new EstadoEntregado();
            case "Cancelado" -> new EstadoCancelado();
            default -> throw new IllegalStateException("Estado de pedido desconocido: " + nombre);
        };
    }
}

