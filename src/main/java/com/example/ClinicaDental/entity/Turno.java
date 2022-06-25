package com.example.ClinicaDental.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "turnos")
public class Turno {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "odontologo_id", referencedColumnName = "id", nullable = false)
    private Odontologo odontologo;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id", referencedColumnName = "id", nullable = false)
    private Paciente paciente;

    @Column
    private LocalDateTime fechaHora;

    public Turno(Odontologo odontologo, Paciente paciente, LocalDateTime fechaHora) {
        this.odontologo = odontologo;
        this.paciente = paciente;
        this.fechaHora = fechaHora;
    }

    public Turno() {}


}
