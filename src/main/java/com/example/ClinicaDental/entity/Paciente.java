package com.example.ClinicaDental.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "pacientes")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private String apellido;

    @Column
    private String nombre;

    @Column
    private String email;

    @Column
    private Integer DNI;

    @Column
    private LocalDate fechaIngreso;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "domiciolio_id", referencedColumnName = "id")
    private Domicilio domicilio;

    @OneToMany(mappedBy = "paciente", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Turno> turnos = new HashSet<>();

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
                "\nApellido: " + apellido +
                "\nNombre: " + nombre +
                "\nEmail: " + email +
                "\nDNI: " + DNI +
                "\nFecha de ingreso: " + fechaIngreso + "\n" +
                domicilio.toString();
    }

}
