package com.ejercicio1.Ejercicio1.controller;

import com.ejercicio1.Ejercicio1.dto.Cuadrodto;
import com.ejercicio1.Ejercicio1.dto.Mensaje;
import com.ejercicio1.Ejercicio1.entity.Cuadro;
import com.ejercicio1.Ejercicio1.entity.Estilo;
import com.ejercicio1.Ejercicio1.service.CuadroService;
import com.ejercicio1.Ejercicio1.service.EstiloService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/cuadro")
@CrossOrigin
public class CuadroController {
    @Autowired
    CuadroService cuadroService;
    @Autowired
    EstiloService estiloService;

    //GET
    @GetMapping("/list")
    public ResponseEntity<List<Cuadro>> List() {
        List<Cuadro> lista = cuadroService.list();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }
    //Paginacion
    @GetMapping("/list/page={page}&size={size}")
    public ResponseEntity<Page<Cuadro>> listPage(@PathVariable("page") int page,
                                                 @PathVariable("size") int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Cuadro> list = cuadroService.listPage(pageable);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    //Paginacion parametrica
    @GetMapping(value = "/list/order/", params = {"page","size","sorted"})
    public ResponseEntity<Page<Cuadro>> listPage(@PathVariable("page") int page,
                                                 @PathVariable("size") int size,
                                                 @PathVariable("sorted") String sorted){
        Pageable pageable = PageRequest.of(page, size, Sort.by(sorted));
        Page<Cuadro> list = cuadroService.listPage(pageable);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    //GET_ID
    @GetMapping("/detail/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") long id){
        if (!cuadroService.existsById(id))
            return new ResponseEntity<>(new Mensaje("el id no existe"), HttpStatus.NOT_FOUND);
        if (cuadroService.getOne(id).isPresent()){
            Cuadro cuadro = cuadroService.getOne(id).get();
            return new ResponseEntity<>(cuadro, HttpStatus.OK);
        }
        return new ResponseEntity<>(new Mensaje("el id no existe"), HttpStatus.NOT_FOUND);
    }
    //GET_Nombre
    @GetMapping("/detailname/{nombre}")
    public ResponseEntity<?> getByNombre(@PathVariable("nombre") String nombre){
        if (!cuadroService.existsByNombre(nombre))
            return new ResponseEntity<>(new Mensaje("el id no existe"), HttpStatus.NOT_FOUND);
        if (cuadroService.getByNombre(nombre).isPresent()){
            Cuadro cuadro = cuadroService.getByNombre(nombre).get();
            return new ResponseEntity<>(cuadro, HttpStatus.OK);
        }
        return new ResponseEntity<>(new Mensaje("el id no existe"), HttpStatus.NOT_FOUND);
    }

    //POST
    @PostMapping("/insert")
    public ResponseEntity<?> create(@RequestBody Cuadrodto cuadrodto){
        if (StringUtils.isBlank(cuadrodto.getNombre()))
            return new ResponseEntity<>(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if (StringUtils.isBlank(cuadrodto.getPintor()))
            return new ResponseEntity<>(new Mensaje("el nombre del pintor es obligatorio"), HttpStatus.BAD_REQUEST);
        if (cuadroService.existsByNombre(cuadrodto.getNombre()))
            return new ResponseEntity<>(new Mensaje("el nombre ya existe"), HttpStatus.BAD_REQUEST);

        Estilo estilo;
        Cuadro cuadro = new Cuadro();
        cuadro.setNombre(cuadrodto.getNombre());
        cuadro.setPintor(cuadrodto.getPintor());

        if (estiloService.getByNombre(cuadrodto.getEstilo().getNombre()).isPresent()){
            estilo = estiloService.getByNombre(cuadrodto.getEstilo().getNombre()).get();
            cuadro.setEstilo(estilo);
        } else {
            if (StringUtils.isBlank(cuadrodto.getEstilo().getNombre()))
                return new ResponseEntity<>(new Mensaje("El estilo es obligatorio"), HttpStatus.BAD_REQUEST);
            Estilo estiloNew = cuadrodto.getEstilo();
            cuadro.setEstilo(estiloNew);
            if (estiloNew.getCuadros() !=null){
                estiloNew.getCuadros().add(cuadro);
            } else {
                Set<Cuadro> cuadros = new HashSet<>();
                cuadros.add(cuadro);
                estiloNew.setCuadros(cuadros);
            }
        }
        cuadroService.save(cuadro);
        return new ResponseEntity<>(new Mensaje("cuadro creado"), HttpStatus.OK);
    }
    //UPDATE
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id")long id, @RequestBody Cuadrodto cuadrodto){
        if (!cuadroService.existsById(id))
            return new ResponseEntity<>(new Mensaje("El cuadro no existe"), HttpStatus.NOT_FOUND);
        if (cuadroService.existsByNombre(cuadrodto.getNombre()) && cuadroService.getByNombre(cuadrodto.getNombre()).get().getId() !=id)
            return new ResponseEntity<>(new Mensaje("El cuadro ya esxiste"), HttpStatus.BAD_REQUEST);
        if (StringUtils.isBlank(cuadrodto.getNombre()))
            return new ResponseEntity<>(new Mensaje("el nombre del cuadro es obligatorio"), HttpStatus.BAD_REQUEST);
        if (StringUtils.isBlank(cuadrodto.getPintor()))
            return new ResponseEntity<>(new Mensaje("el nombre del pintor es obligatorio"), HttpStatus.BAD_REQUEST);

        Cuadro cuadro = cuadroService.getOne(id).get();
        cuadro.setNombre(cuadro.getNombre());
        cuadro.setPintor(cuadro.getPintor());
        cuadro.setEstilo(cuadro.getEstilo());

        cuadroService.save(cuadro);
        return new ResponseEntity<>(new Mensaje("Cuadro actualizado"), HttpStatus.OK);
    }
    //DELETE
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id){
        if (!cuadroService.existsById(id))
            return new ResponseEntity<>(new Mensaje("el id no existe"), HttpStatus.NOT_FOUND);
        cuadroService.delete(id);
        return new ResponseEntity<>(new Mensaje("Cuadro borrado"), HttpStatus.OK);
    }
}
