package com.ejercicio1.Ejercicio1.service;

import com.ejercicio1.Ejercicio1.dao.EmpleadoRepository;
import com.ejercicio1.Ejercicio1.entity.Empleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EmpleadoService {
    @Autowired
    EmpleadoRepository empleadoRepository;
    public List<Empleado> list() { return empleadoRepository.findAll(); }
    public Page<Empleado> listPage(Pageable pageable){return empleadoRepository.findAll(pageable);}
    public Optional<Empleado> getOne(Long id) { return empleadoRepository.findById(id); }
    public Optional<Empleado> getByNombre(String nombre) { return empleadoRepository.findByNombre(nombre); }
    public void save(Empleado empleado){ empleadoRepository.save(empleado); }
    public void delete(long id) { empleadoRepository.deleteById(id); }
    public boolean existsById(long id) { return empleadoRepository.existsById(id); }
    public boolean existsByNombre(String nombre) { return empleadoRepository.existsByNombre(nombre); }
}
