package com.example.ClinicaDental.dto;

import com.example.ClinicaDental.entity.Domicilio;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PacienteDTO {

    private String apellido;
    private String nombre;
    private String email;
    private Integer DNI;
    private Domicilio domicilio;

    public PacienteDTO() {}

    public PacienteDTO(String apellido, String nombre, String email, Integer DNI, Domicilio domicilio) {
        this.apellido = apellido;
        this.nombre = nombre;
        this.email = email;
        this.DNI = DNI;
        this.domicilio = domicilio;
    }

}
