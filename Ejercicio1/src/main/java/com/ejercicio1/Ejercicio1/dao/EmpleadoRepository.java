package com.ejercicio1.Ejercicio1.dao;

import com.ejercicio1.Ejercicio1.entity.Cuadro;
import com.ejercicio1.Ejercicio1.entity.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Optional;

@Repository
@CrossOrigin
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
    Optional<Empleado> findByNombre(String nombre);
    boolean existsByNombre(String nombre);
    //6- Buscar empleados cuyo nombre empiece por X
    List<Empleado> findByNombreStartingWith(String nombre);
    //7- Buscar empleados cuyo nombre contenga X
    List<Empleado> findByNombreContaining(String nombre);
    //8- Buscar empleados cuyo nombre termine por X
    List<Empleado> findByNombreEndingWith(String sufijo);
    //9- Buscar empleados cuya edad sea mayor de X
    List<Empleado> findByEdadGreaterThan(int numero);
    //10- Buscar empleados cuya edad sea menor de X
    List<Empleado> findByEdadIsLessThan(int numero);
    //11- Buscar empleados cuya edad esté entre X e Y
    List<Empleado> findByEdadBetween(int numero, int numero2);
    //12- Buscar empleados cuyo nombre contenga X y sean menores de Y
    List<Empleado> findByNombreContainingAndEdadLessThan(String nombre, int numero);
}
