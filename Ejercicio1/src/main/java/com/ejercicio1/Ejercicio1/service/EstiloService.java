package com.ejercicio1.Ejercicio1.service;

import com.ejercicio1.Ejercicio1.dao.EstiloRepository;
import com.ejercicio1.Ejercicio1.entity.Estilo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EstiloService {
    @Autowired
    EstiloRepository estiloRepository;
    public List<Estilo> list() { return estiloRepository.findAll(); }
    public Optional<Estilo> getOne(Long id) { return estiloRepository.findById(id); }
    public Optional<Estilo> getByNombre(String nombre) { return estiloRepository.findByNombre(nombre); }
    public void save(Estilo estilo){ estiloRepository.save(estilo); }
    public void delete(long id) { estiloRepository.deleteById(id); }
    public boolean existsById(long id) { return estiloRepository.existsById(id); }
    public boolean existsByNombre(String nombre) { return estiloRepository.existsByNombre(nombre); }
}
