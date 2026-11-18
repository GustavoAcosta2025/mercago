package com.mercago.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mercago.dominio.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    List<Producto> findByCategoria(String categoria);
}

