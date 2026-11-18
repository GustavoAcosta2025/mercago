package com.mercago.dominio;

import com.mercago.dominio.estado.EstadoPedido;
import com.mercago.dominio.estado.EstadoPedidoFactory;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Column(nullable = false)
    private LocalDateTime fechaCreacion;

    // Se guarda el nombre del estado; el objeto de estado se reconstruye con la fábrica.
    @Column(nullable = false)
    private String estado;

    @Column(nullable = false)
    private String direccionEntrega;

    @Column(nullable = false)
    private String metodoPago = "Efectivo contra entrega";

    @Column(nullable = false)
    private BigDecimal subtotal;

    @Column(nullable = false)
    private BigDecimal costoDomicilio;

    @Column(nullable = false)
    private BigDecimal totalPagar;

    private Integer tiempoEstimadoMin;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetallePedido> detalles = new ArrayList<>();

    protected Pedido() {
    }

    public static Pedido crearDesde(CarritoDeCompras carrito, String direccionEntrega,
                                    BigDecimal costoDomicilio) {
        Pedido p = new Pedido();
        p.usuario = carrito.getUsuario();
        p.fechaCreacion = LocalDateTime.now();
        p.direccionEntrega = direccionEntrega;
        p.costoDomicilio = costoDomicilio;
        for (ItemCarrito item : carrito.getItems()) {
            p.detalles.add(new DetallePedido(p, item.getProducto(), item.getCantidad(),
                    item.getProducto().getPrecioUnitario()));
        }
        p.subtotal = carrito.calcularSubtotal();
        p.totalPagar = p.subtotal.add(costoDomicilio);
        p.estado = "Pendiente";
        return p;
    }

    private EstadoPedido estadoActual() {
        return EstadoPedidoFactory.desde(estado);
    }

    public void despachar(int tiempoEstimadoMin) {
        estadoActual().despachar(this, tiempoEstimadoMin);
    }

    public void entregar() {
        estadoActual().entregar(this);
    }

    public void cancelar() {
        estadoActual().cancelar(this);
    }

    public void cambiarEstado(EstadoPedido nuevo) {
        this.estado = nuevo.nombre();
    }

    public void setTiempoEstimadoMin(int minutos) {
        this.tiempoEstimadoMin = minutos;
    }

    public String getNombreEstado() { return estado; }
    public Long getId() { return id; }
    public Usuario getUsuario() { return usuario; }
    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public String getDireccionEntrega() { return direccionEntrega; }
    public String getMetodoPago() { return metodoPago; }
    public BigDecimal getSubtotal() { return subtotal; }
    public BigDecimal getCostoDomicilio() { return costoDomicilio; }
    public BigDecimal getTotalPagar() { return totalPagar; }
    public Integer getTiempoEstimadoMin() { return tiempoEstimadoMin; }
    public List<DetallePedido> getDetalles() { return detalles; }
}

