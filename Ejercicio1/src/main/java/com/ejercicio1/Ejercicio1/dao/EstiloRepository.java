package com.ejercicio1.Ejercicio1.dao;

import com.ejercicio1.Ejercicio1.entity.Estilo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;

@Repository
@CrossOrigin
public interface EstiloRepository extends JpaRepository<Estilo, Long> {
    Optional<Estilo> findByNombre(String nombre);
    boolean existsByNombre(String nombre);
}
