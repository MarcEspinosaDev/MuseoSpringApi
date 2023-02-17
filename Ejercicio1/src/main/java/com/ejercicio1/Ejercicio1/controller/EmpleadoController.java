package com.ejercicio1.Ejercicio1.controller;

import com.ejercicio1.Ejercicio1.dto.Empleadodto;
import com.ejercicio1.Ejercicio1.dto.Mensaje;
import com.ejercicio1.Ejercicio1.entity.Cuadro;
import com.ejercicio1.Ejercicio1.entity.Direccion;
import com.ejercicio1.Ejercicio1.entity.Empleado;
import com.ejercicio1.Ejercicio1.entity.Profesion;
import com.ejercicio1.Ejercicio1.service.DireccionService;
import com.ejercicio1.Ejercicio1.service.EmpleadoService;
import com.ejercicio1.Ejercicio1.service.ProfesionService;
import com.ejercicio1.Ejercicio1.service.SueldoService;
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
@RequestMapping("/api/empleado")
@CrossOrigin
public class EmpleadoController {
    @Autowired
    EmpleadoService empleadoService;
    @Autowired
    DireccionService direccionService;
    @Autowired
    ProfesionService profesionService;
    @Autowired
    SueldoService sueldoService;
    //GET
    @GetMapping("/list")
    public ResponseEntity<List<Empleado>> List(){
        List<Empleado> list = empleadoService.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    //Paginacion
    @GetMapping("/list/page={page}&size={size}")
    public ResponseEntity<Page<Empleado>> listPage(@PathVariable("page") int page,
                                                 @PathVariable("size") int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Empleado> list = empleadoService.listPage(pageable);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    //Paginacion parametrica
    @GetMapping(value = "/list/order/", params = {"page","size","sort"})
    public ResponseEntity<Page<Empleado>> listPage(@PathVariable("page") int page,
                                                 @PathVariable("size") int size,
                                                 @PathVariable("sort") String sort){
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        Page<Empleado> list = empleadoService.listPage(pageable);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    //GET_ID
    @GetMapping("/detail/{id}")
    public ResponseEntity<?> getById(@PathVariable("id")long id){
        if (!empleadoService.existsById(id))
            return new ResponseEntity<>(new Mensaje("El empleado no existe"), HttpStatus.NOT_FOUND);
        if (empleadoService.getOne(id).isPresent()){
            Empleado empleado = empleadoService.getOne(id).get();
            return new ResponseEntity<>(empleado, HttpStatus.OK);
        }
        return new ResponseEntity<>(new Mensaje("Empleado no existe"), HttpStatus.NOT_FOUND);
    }//GET_NOMBRE
    @GetMapping("/detalilname/{nombre}")
    public ResponseEntity<?> getByNombre(@PathVariable("nombre")String nombre){
        if (!empleadoService.existsByNombre(nombre))
            return new ResponseEntity<>(new Mensaje("El empleado no existe"), HttpStatus.NOT_FOUND);
        if (empleadoService.getByNombre(nombre).isPresent()){
            Empleado empleado = empleadoService.getByNombre(nombre).get();
            return new ResponseEntity<>(empleado, HttpStatus.OK);
        }
        return new ResponseEntity<>(new Mensaje("Empleado no existe"), HttpStatus.NOT_FOUND);
    }
    //POST
    @PostMapping("/insert")
    public ResponseEntity<?> create(@RequestBody Empleadodto empleadodto) {
        if (StringUtils.isBlank(empleadodto.getNombre()))
            return new ResponseEntity<>(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if (empleadoService.existsByNombre(empleadodto.getNombre()))
            return new ResponseEntity<>(new Mensaje("El nombre del empleado ya existe"), HttpStatus.BAD_REQUEST);
        Direccion direccion;
        Profesion profesion;

        Empleado empleado = new Empleado();
        empleado.setNombre(empleadodto.getNombre());
        empleado.setEdad(empleadodto.getEdad());

        //Direccion
        if (direccionService.existsByCalle(empleadodto.getDireccion().getCalle())) {
            return new ResponseEntity<>(new Mensaje("La calle ya existe"), HttpStatus.BAD_REQUEST);
        } else {
            direccion = empleadodto.getDireccion();
            empleado.setDireccion(direccion);
            direccion.setEmpleado(empleado);
        }
        //Profesion
        if (profesionService.getByNombre(empleadodto.getProfesion().getNombre()).isPresent()){
            profesion = profesionService.getByNombre(empleadodto.getProfesion().getNombre()).get();
            empleado.setProfesion(profesion);
        } else {
            if (StringUtils.isBlank(empleadodto.getProfesion().getNombre()))
                return new ResponseEntity<>(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
            Profesion profesionNew = empleadodto.getProfesion();
            // profesionNew.setSueldo(empleadodto.getProfesion().getSueldo());
            empleado.setProfesion(profesionNew);
            if (profesionNew.getEmpleado() != null){
                profesionNew.getEmpleado().add(empleado);
            } else {
                Set<Empleado> empleados = new HashSet<>();
                empleados.add(empleado);
                profesionNew.setEmpleado(empleados);
            }
        }
        empleadoService.save(empleado);
        return new ResponseEntity<>(new Mensaje("El empleado se ha creado"), HttpStatus.OK);
    }
    //PUT
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id")long id, @RequestBody Empleadodto empleadoDto) {
        if(!empleadoService.existsById(id))
            return new ResponseEntity<>(new Mensaje("El empleado no existe"), HttpStatus.NOT_FOUND);
        if(empleadoService.existsByNombre(empleadoDto.getNombre())
                && empleadoService.getByNombre(empleadoDto.getNombre()).get().getId() != id)
            return new ResponseEntity<>(new Mensaje("El nombre ya existe"), HttpStatus.BAD_REQUEST);
        if( StringUtils.isBlank(empleadoDto.getNombre()))
            return new ResponseEntity<>(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);

        if(direccionService.existsByCalle(empleadoDto.getDireccion().getCalle()))
            return new ResponseEntity<>(new Mensaje("La direccion ya existe"), HttpStatus.BAD_REQUEST);

        Empleado empleado = empleadoService.getOne(id).get();
        empleado.setNombre(empleadoDto.getNombre());
        empleado.setEdad(empleadoDto.getEdad());
        empleado.setDireccion(empleadoDto.getDireccion());
        empleado.setProfesion(empleadoDto.getProfesion());

        empleadoService.save(empleado);
        return new ResponseEntity<>(new Mensaje("El empleado ha sido actualizado"), HttpStatus.OK);
    }
    //DELETE
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")long id){
        if(!empleadoService.existsById(id))
            return new ResponseEntity<>(new Mensaje("El empleado no existe"), HttpStatus.NOT_FOUND);
        empleadoService.delete(id);
        return new ResponseEntity<>(new Mensaje("El empleado ha sido borrado"), HttpStatus.OK);

    }
}
