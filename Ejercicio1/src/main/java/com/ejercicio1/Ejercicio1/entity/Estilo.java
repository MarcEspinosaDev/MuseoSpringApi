package com.ejercicio1.Ejercicio1.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "estilo")
@Getter
@Setter
public class Estilo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estilo")
    @JsonIgnore
    private Set<Cuadro> cuadros;
}
