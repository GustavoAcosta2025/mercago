package com.mercago.dominio;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carritos")
public class CarritoDeCompras {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(name = "usuario_id", unique = true)
    private Usuario usuario;

    @OneToMany(mappedBy = "carrito", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemCarrito> items = new ArrayList<>();

    protected CarritoDeCompras() {
    }

    public CarritoDeCompras(Usuario usuario) {
        this.usuario = usuario;
    }

    public void agregarProducto(Producto producto, int cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor que cero");
        }
        for (ItemCarrito item : items) {
            if (item.getProducto().getId().equals(producto.getId())) {
                item.setCantidad(item.getCantidad() + cantidad);
                return;
            }
        }
        items.add(new ItemCarrito(this, producto, cantidad));
    }

    public void modificarCantidad(Producto producto, int cantidad) {
        if (cantidad <= 0) {
            eliminarProducto(producto);
            return;
        }
        for (ItemCarrito item : items) {
            if (item.getProducto().getId().equals(producto.getId())) {
                item.setCantidad(cantidad);
                return;
            }
        }
    }

    public void eliminarProducto(Producto producto) {
        items.removeIf(i -> i.getProducto().getId().equals(producto.getId()));
    }

    public void vaciarCarrito() {
        items.clear();
    }

    public BigDecimal calcularSubtotal() {
        BigDecimal total = BigDecimal.ZERO;
        for (ItemCarrito item : items) {
            total = total.add(item.calcularSubtotalItem());
        }
        return total;
    }

    public void validarNoVacio() {
        if (items.isEmpty()) {
            throw new IllegalStateException("El carrito está vacío");
        }
    }

    public Long getId() { return id; }
    public Usuario getUsuario() { return usuario; }
    public List<ItemCarrito> getItems() { return items; }
}

