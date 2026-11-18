package com.mercago.repositorio;

import com.mercago.dominio.CarritoDeCompras;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CarritoRepository extends JpaRepository<CarritoDeCompras, Long> {
    Optional<CarritoDeCompras> findByUsuarioId(Long usuarioId);
}

