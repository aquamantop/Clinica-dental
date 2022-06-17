package com.example.ClinicaDental.dto;

public class OdontologoDTO {
    private String apellido;
    private String nombre;
    private int matricula;

    public OdontologoDTO(String apellido, String nombre, int matricula) {
        this.apellido = apellido;
        this.nombre = nombre;
        this.matricula = matricula;
    }

    public OdontologoDTO() {
    }
}

