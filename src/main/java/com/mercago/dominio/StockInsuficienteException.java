package com.mercago.dominio;

public class StockInsuficienteException extends RuntimeException {
    public StockInsuficienteException(String producto) {
        super("No hay stock suficiente de: " + producto);
    }
}

