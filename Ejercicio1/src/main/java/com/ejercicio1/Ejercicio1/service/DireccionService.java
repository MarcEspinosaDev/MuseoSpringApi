package com.ejercicio1.Ejercicio1.service;

import com.ejercicio1.Ejercicio1.dao.DireccionRepository;
import com.ejercicio1.Ejercicio1.entity.Direccion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DireccionService {
    @Autowired
    DireccionRepository direccionRepository;
    public List<Direccion> list() { return direccionRepository.findAll(); }
    public Optional<Direccion> getOne(Long id) { return direccionRepository.findById(id); }
    public Optional<Direccion> getByCalle(String calle) { return direccionRepository.findByCalle(calle); }
    public void save(Direccion direccion){ direccionRepository.save(direccion); }
    public void delete(long id) { direccionRepository.deleteById(id); }
    public boolean existsById(long id) { return direccionRepository.existsById(id); }
    public boolean existsByCalle(String calle) { return direccionRepository.existsByCalle(calle); }
}
