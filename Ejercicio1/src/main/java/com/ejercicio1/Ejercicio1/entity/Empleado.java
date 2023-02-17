package com.ejercicio1.Ejercicio1.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "empleado")
@Getter
@Setter
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "nombre"
)
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "edad")
    private int edad;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    @JsonManagedReference
    private Direccion direccion;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "profesion_id")
    private Profesion profesion;
}
