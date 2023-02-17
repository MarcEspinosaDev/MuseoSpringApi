package com.ejercicio1.Ejercicio1.service;

import com.ejercicio1.Ejercicio1.dao.ProfesionRepository;
import com.ejercicio1.Ejercicio1.entity.Profesion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProfesionService {
    @Autowired
    ProfesionRepository profesionRepository;
    public List<Profesion> list() { return profesionRepository.findAll(); }
    public Optional<Profesion> getOne(Long id) { return profesionRepository.findById(id); }
    public Optional<Profesion> getByNombre(String nombre) { return profesionRepository.findByNombre(nombre); }
    public void save(Profesion profesion){ profesionRepository.save(profesion); }
    public void delete(long id) { profesionRepository.deleteById(id); }
    public boolean existsById(long id) { return profesionRepository.existsById(id); }
    public boolean existsByNombre(String nombre) { return profesionRepository.existsByNombre(nombre); }

}
