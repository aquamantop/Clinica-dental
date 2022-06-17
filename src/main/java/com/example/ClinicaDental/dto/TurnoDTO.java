package com.example.ClinicaDental.dto;

import com.example.ClinicaDental.entity.Odontologo;
import com.example.ClinicaDental.entity.Paciente;

import java.time.LocalDateTime;

public class TurnoDTO {
    private Odontologo odontologo;
    private Paciente paciente;
    private LocalDateTime fechaHora;

    public TurnoDTO(Odontologo odontologo, Paciente paciente, LocalDateTime fechaHora) {
        this.odontologo = odontologo;
        this.paciente = paciente;
        this.fechaHora = fechaHora;
    }

    public TurnoDTO() {
    }

}
