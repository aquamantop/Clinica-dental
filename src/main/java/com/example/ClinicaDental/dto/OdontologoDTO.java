package com.example.ClinicaDental.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OdontologoDTO {

    private String apellido;
    private String nombre;
    private Integer matricula;

    public OdontologoDTO() {}

    public OdontologoDTO(String apellido, String nombre, Integer matricula) {
        this.apellido = apellido;
        this.nombre = nombre;
        this.matricula = matricula;
    }

}
