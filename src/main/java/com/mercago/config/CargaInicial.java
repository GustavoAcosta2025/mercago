package com.mercago.config;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.mercago.dominio.Producto;
import com.mercago.dominio.Rol;
import com.mercago.dominio.Usuario;
import com.mercago.repositorio.ProductoRepository;
import com.mercago.repositorio.UsuarioRepository;

@Component
public class CargaInicial implements CommandLineRunner {

    private final UsuarioRepository usuarios;
    private final ProductoRepository productos;

    public CargaInicial(UsuarioRepository usuarios, ProductoRepository productos) {
        this.usuarios = usuarios;
        this.productos = productos;
    }

    @Override
    public void run(String... args) {
        if (usuarios.count() == 0) {
            BCryptPasswordEncoder cifrador = new BCryptPasswordEncoder();
            usuarios.save(new Usuario("Cliente Demo", "cliente@mercago.com",
                    cifrador.encode("cliente123"), "3000000000",
                    "Calle 50 # 40-20, Medellín", Rol.CLIENTE));
            usuarios.save(new Usuario("Administrador", "admin@mercago.com",
                    cifrador.encode("admin123"), "3000000001",
                    "Sede principal", Rol.ADMINISTRADOR));
        }
        if (productos.count() == 0) {
            String fv = "Frutas y verduras";
            String le = "Lácteos y huevos";
            String be = "Bebidas";
            String ah = "Aseo del hogar";
            productos.save(p("Manzana roja (kg)", "Manzana roja fresca", 6900, 50, fv));
            productos.save(p("Banano (kg)", "Banano criollo maduro", 3500, 60, fv));
            productos.save(p("Tomate chonto (kg)", "Tomate chonto para ensalada", 4500, 40, fv));
            productos.save(p("Leche entera 1 L", "Leche entera larga vida", 4200, 80, le));
            productos.save(p("Huevos AA x30", "Cubeta de 30 huevos AA", 17500, 30, le));
            productos.save(p("Queso campesino 500 g", "Queso campesino fresco", 12800, 25, le));
            productos.save(p("Gaseosa 1.5 L", "Gaseosa sabor cola", 5900, 70, be));
            productos.save(p("Agua 600 ml", "Agua sin gas", 2100, 100, be));
            productos.save(p("Jugo de naranja 1 L", "Jugo de naranja natural", 6500, 35, be));
            productos.save(p("Detergente líquido 1 L", "Detergente para ropa", 14900, 30, ah));
            productos.save(p("Lavaplatos 500 g", "Jabón lavaplatos en crema", 6200, 45, ah));
            productos.save(p("Papel higiénico x12", "Paquete de 12 rollos", 21900, 40, ah));
        }
    }

    private Producto p(String nombre, String descripcion, int precio, int stock, String categoria) {
        return new Producto(nombre, descripcion, BigDecimal.valueOf(precio), stock, categoria, 5);
    }
}

