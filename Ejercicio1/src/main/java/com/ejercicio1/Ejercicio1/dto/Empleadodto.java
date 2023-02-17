package com.ejercicio1.Ejercicio1.dto;

import com.ejercicio1.Ejercicio1.entity.Direccion;
import com.ejercicio1.Ejercicio1.entity.Profesion;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Empleadodto {
    @NotBlank
    private String nombre;
    private int edad;
    private Direccion direccion;
    private Profesion profesion;
}

