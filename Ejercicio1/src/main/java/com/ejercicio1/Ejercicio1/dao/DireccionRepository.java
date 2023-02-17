package com.ejercicio1.Ejercicio1.dao;

import com.ejercicio1.Ejercicio1.entity.Direccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;

@Repository
@CrossOrigin
public interface DireccionRepository extends JpaRepository<Direccion, Long> {
    Optional<Direccion> findByCalle (String calle);
    boolean existsByCalle(String calle);
}
