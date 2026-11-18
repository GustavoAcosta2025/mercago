package com.mercago.servicio;

import com.mercago.dominio.CarritoDeCompras;
import com.mercago.dominio.Producto;
import com.mercago.dominio.Usuario;
import com.mercago.repositorio.CarritoRepository;
import com.mercago.repositorio.ProductoRepository;
import com.mercago.repositorio.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CarritoService {

    private final CarritoRepository carritos;
    private final UsuarioRepository usuarios;
    private final ProductoRepository productos;

    public CarritoService(CarritoRepository carritos, UsuarioRepository usuarios,
                          ProductoRepository productos) {
        this.carritos = carritos;
        this.usuarios = usuarios;
        this.productos = productos;
    }

    @Transactional
    public CarritoDeCompras obtenerCarrito(Long idUsuario) {
        return carritos.findByUsuarioId(idUsuario).orElseGet(() -> {
            Usuario u = usuarios.findById(idUsuario)
                    .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
            return carritos.save(new CarritoDeCompras(u));
        });
    }

    @Transactional
    public CarritoDeCompras agregar(Long idUsuario, Long idProducto, int cantidad) {
        CarritoDeCompras carrito = obtenerCarrito(idUsuario);
        carrito.agregarProducto(buscarProducto(idProducto), cantidad);
        return carrito;
    }

    @Transactional
    public CarritoDeCompras modificarCantidad(Long idUsuario, Long idProducto, int cantidad) {
        CarritoDeCompras carrito = obtenerCarrito(idUsuario);
        carrito.modificarCantidad(buscarProducto(idProducto), cantidad);
        return carrito;
    }

    @Transactional
    public void vaciar(Long idUsuario) {
        obtenerCarrito(idUsuario).vaciarCarrito();
    }

    private Producto buscarProducto(Long idProducto) {
        return productos.findById(idProducto)
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));
    }
}

