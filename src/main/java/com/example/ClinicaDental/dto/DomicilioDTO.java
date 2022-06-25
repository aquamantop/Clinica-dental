package com.example.ClinicaDental.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DomicilioDTO {

    private String calle;
    private int numero;
    private String localidad;
    private String provincia;

    public DomicilioDTO() {}

    public DomicilioDTO(String calle, int numero, String localidad, String provincia) {
        this.calle = calle;
        this.numero = numero;
        this.localidad = localidad;
        this.provincia = provincia;
    }
}
