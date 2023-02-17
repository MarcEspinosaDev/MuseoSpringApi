package com.ejercicio1.Ejercicio1.service;

import com.ejercicio1.Ejercicio1.dao.CuadroRepository;
import com.ejercicio1.Ejercicio1.entity.Cuadro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CuadroService {
    @Autowired
    CuadroRepository cuadroRepository;
    public List<Cuadro> list() { return cuadroRepository.findAll(); }
    public Page<Cuadro> listPage(Pageable pageable){return cuadroRepository.findAll(pageable);}
    public Optional<Cuadro> getOne(Long id) { return cuadroRepository.findById(id); }
    public Optional<Cuadro> getByNombre(String nombre) { return cuadroRepository.findByNombre(nombre); }
    public void save(Cuadro cuadro){ cuadroRepository.save(cuadro); }
    public void delete(long id) { cuadroRepository.deleteById(id); }
    public boolean existsById(long id) { return cuadroRepository.existsById(id); }
    public boolean existsByNombre(String nombre) { return cuadroRepository.existsByNombre(nombre); }
}
