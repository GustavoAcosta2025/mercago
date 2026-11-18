package com.mercago.dominio;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    private String descripcion;

    @Column(nullable = false)
    private BigDecimal precioUnitario;

    @Column(nullable = false)
    private int stock;

    @Column(nullable = false)
    private String categoria;

    @Column(nullable = false)
    private int umbralStockCritico;

    protected Producto() {
    }

    public Producto(String nombre, String descripcion, BigDecimal precioUnitario,
                    int stock, String categoria, int umbralStockCritico) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioUnitario = precioUnitario;
        this.stock = stock;
        this.categoria = categoria;
        this.umbralStockCritico = umbralStockCritico;
    }

    public boolean hayStock(int cantidad) {
        return cantidad > 0 && stock >= cantidad;
    }

    public void descontarStock(int cantidad) {
        if (!hayStock(cantidad)) {
            throw new StockInsuficienteException(nombre);
        }
        stock -= cantidad;
    }

    public void devolverStock(int cantidad) {
        stock += cantidad;
    }

    public boolean esStockCritico() {
        return stock <= umbralStockCritico;
    }

    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public BigDecimal getPrecioUnitario() { return precioUnitario; }
    public int getStock() { return stock; }
    public String getCategoria() { return categoria; }
    public int getUmbralStockCritico() { return umbralStockCritico; }
}

