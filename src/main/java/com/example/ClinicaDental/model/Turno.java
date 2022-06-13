package com.example.ClinicaDental.model;

import java.time.LocalDateTime;

public class Turno {
    private int id;
    private LocalDateTime fechaYHora;
    private Paciente paciente;
    private Odontologo odontologo;

    public Turno(LocalDateTime fechaYHora, Paciente paciente, Odontologo odontologo) {
        this.fechaYHora = fechaYHora;
        this.paciente = paciente;
        this.odontologo = odontologo;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getFechaYHora() {
        return fechaYHora;
    }

    public void setFechaYHora(LocalDateTime fechaYHora) {
        this.fechaYHora = fechaYHora;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Odontologo getOdontologo() {
        return odontologo;
    }

    public void setOdontolog(Odontologo odontologo) {
        this.odontologo = odontologo;
    }

    @Override
    public String toString() {
        return "Turno " + id + "/" +
                "\nFecha y Hora: " + fechaYHora +
                " " +
                paciente.toString();
    }
}
