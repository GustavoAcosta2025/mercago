package com.mercago.dominio;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "detalles_pedido")
public class DetallePedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "producto_id")
    private Producto producto;

    @Column(nullable = false)
    private int cantidadComprada;

    @Column(nullable = false)
    private BigDecimal precioMomentoCompra;

    protected DetallePedido() {
    }

    public DetallePedido(Pedido pedido, Producto producto, int cantidadComprada,
                         BigDecimal precioMomentoCompra) {
        this.pedido = pedido;
        this.producto = producto;
        this.cantidadComprada = cantidadComprada;
        this.precioMomentoCompra = precioMomentoCompra;
    }

    public BigDecimal calcularSubtotalItem() {
        return precioMomentoCompra.multiply(BigDecimal.valueOf(cantidadComprada));
    }

    public Long getId() { return id; }
    public Producto getProducto() { return producto; }
    public int getCantidadComprada() { return cantidadComprada; }
    public BigDecimal getPrecioMomentoCompra() { return precioMomentoCompra; }
}

