package com.example.ClinicaDental.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "pacientes")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String apellido;
    private String nombre;
    private String email;
    private Integer DNI;
    private LocalDate fechaIngreso;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "domiciolio_id")
    private Domicilio domicilio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "odontologo_id")
    private Odontologo odontologo;

    public Paciente(String apellido, String nombre, String email, Integer DNI, LocalDate fechaIngreso, Domicilio domicilio) {
        this.apellido = apellido;
        this.nombre = nombre;
        this.email = email;
        this.DNI = DNI;
        this.fechaIngreso = fechaIngreso;
        this.domicilio = domicilio;
    }

    public Paciente(){}

    @Override
    public String toString() {
        return "--Paciente--" +
                "\nApellido: " + apellido + "/" +
                "\nNombre: " + nombre + "/" +
                "\nEmail: " + email + "/" +
                "\nDNI: " + DNI + "/" +
                "\nFecha de ingreso: " + fechaIngreso;
    }

}
