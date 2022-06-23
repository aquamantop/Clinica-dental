package com.example.ClinicaDental.entity;

import javax.persistence.*;
import java.time.LocalDate;

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
    private Domicilio domicilio;

    @ManyToOne
    private Odontologo odontologo;

    public Paciente(String apellido, String nombre, String email, Integer DNI, LocalDate fechaIngreso, Domicilio domicilio, Odontologo odontologo) {
        this.apellido = apellido;
        this.nombre = nombre;
        this.email = email;
        this.DNI = DNI;
        this.fechaIngreso = fechaIngreso;
        this.domicilio = domicilio;
        this.odontologo = odontologo;
    }

    public Paciente(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getDNI() {
        return DNI;
    }

    public void setDNI(Integer DNI) {
        this.DNI = DNI;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Domicilio getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }

    public Odontologo getOdontologo() {
        return odontologo;
    }

    public void setOdontologo(Odontologo odontologo) {
        this.odontologo = odontologo;
    }

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
