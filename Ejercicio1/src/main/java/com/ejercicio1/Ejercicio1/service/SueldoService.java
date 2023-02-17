package com.ejercicio1.Ejercicio1.service;

import com.ejercicio1.Ejercicio1.dao.SueldoRepository;
import com.ejercicio1.Ejercicio1.entity.Sueldo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SueldoService {
    @Autowired
    SueldoRepository sueldoRepository;
    public List<Sueldo> list() { return sueldoRepository.findAll(); }
    public Optional<Sueldo> getOne(Long id) { return sueldoRepository.findById(id); }
    public void save(Sueldo sueldo){ sueldoRepository.save(sueldo); }
    public void delete(long id) { sueldoRepository.deleteById(id); }
    public boolean existsById(long id) { return sueldoRepository.existsById(id); }
}
