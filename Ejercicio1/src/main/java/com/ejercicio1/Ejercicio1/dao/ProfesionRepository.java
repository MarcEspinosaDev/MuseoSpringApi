package com.ejercicio1.Ejercicio1.dao;

import com.ejercicio1.Ejercicio1.entity.Empleado;
import com.ejercicio1.Ejercicio1.entity.Profesion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;

@Repository
@CrossOrigin
public interface ProfesionRepository extends JpaRepository<Profesion, Long> {
    Optional<Profesion> findByNombre(String nombre);
    boolean existsByNombre(String nombre);
}
