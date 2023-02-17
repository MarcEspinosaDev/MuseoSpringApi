package com.ejercicio1.Ejercicio1.dao;

import com.ejercicio1.Ejercicio1.entity.Cuadro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Optional;

@Repository
@CrossOrigin
public interface CuadroRepository extends JpaRepository<Cuadro, Long> {
    Optional<Cuadro> findByNombre(String nombre);
    boolean existsByNombre(String nombre);
    //1- Buscar cuadro por nombre y pintor
    List<Cuadro> findByNombreAndPintor(String nombre, String pintor);
    //2- Buscar cuadro por nombre o pintor
    List<Cuadro> findByNombreOrPintor(String nombre, String pintor);
    //3- Buscar cuadros por nombre que no sean X
    List<Cuadro> findByNombreIsNot(String nombre);
    //4- Buscar el nombre de cuadros que contenga X
    List<Cuadro> findByNombreContaining(String nombre);
    //5- Buscar el pintor de cuadros que empiece por X
    List<Cuadro> findByPintorStartingWith(String prefijo);
}
