package com.example.ClinicaDental.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Turno {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToOne
    private Odontologo odontologo;

    @OneToOne
    private Paciente paciente;

    private LocalDateTime fechaHora;

    public Turno(Odontologo odontologo, Paciente paciente, LocalDateTime fechaHora) {
        this.odontologo = odontologo;
        this.paciente = paciente;
        this.fechaHora = fechaHora;
    }

    public Turno() {}

    @Override
    public String toString() {
        return "--Turno--" +
                "\nFecha y hora: " + fechaHora +
                "\n\n" + paciente.toString() +
                "\n\n" + odontologo.toString();
    }

}
