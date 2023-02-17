package com.ejercicio1.Ejercicio1.dto;

import com.ejercicio1.Ejercicio1.entity.Estilo;
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
public class Cuadrodto {

    @NotBlank
    private String nombre;

    @NotBlank
    private String pintor;

    private Estilo estilo;
}
