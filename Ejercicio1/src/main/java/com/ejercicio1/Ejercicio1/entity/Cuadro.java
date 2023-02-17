package com.ejercicio1.Ejercicio1.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cuadro")
@Getter
@Setter
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "nombre"
)
public class Cuadro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "pintor")
    private String pintor;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "estilo_id", nullable = false)
    private Estilo estilo;
}
