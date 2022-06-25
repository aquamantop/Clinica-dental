package com.example.ClinicaDental.dto;

import com.example.ClinicaDental.entity.Odontologo;
import com.example.ClinicaDental.entity.Paciente;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class TurnoDTO {

    private Odontologo odontologo;
    private Paciente paciente;
    private LocalDateTime fechaHora;

    public TurnoDTO() {}

    public TurnoDTO(Odontologo odontologo, Paciente paciente, LocalDateTime fechaHora) {
        this.odontologo = odontologo;
        this.paciente = paciente;
        this.fechaHora = fechaHora;
    }

}
