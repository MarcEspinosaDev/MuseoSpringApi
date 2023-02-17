package com.ejercicio1.Ejercicio1.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
@Entity
@Table(name = "direccion")
@Getter
@Setter
public class Direccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "calle")
    private String calle;

    @Column(name = "ciudad")
    private String ciudad;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    @JsonBackReference
    private Empleado empleado;
}
