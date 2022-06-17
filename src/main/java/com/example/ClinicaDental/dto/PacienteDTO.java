package com.example.ClinicaDental.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PacienteDTO {
    private String apellido;
    private String nombre;
    private String email;
    private int DNI;
    private String calle;
    private int numero;
    private String localidad;
    private String provincia;

    public PacienteDTO(String apellido, String nombre, String email, int DNI, String calle, int numero, String localidad, String provincia) {
        this.apellido = apellido;
        this.nombre = nombre;
        this.email = email;
        this.DNI = DNI;
        this.calle = calle;
        this.numero = numero;
        this.localidad = localidad;
        this.provincia = provincia;
    }

    public PacienteDTO() {
    }

}
