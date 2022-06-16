package com.example.ClinicaDental.repository;

import com.example.ClinicaDental.entity.Turno;

import java.util.List;

public interface ITurnoService {

    Turno eliminar(int id);
    Turno actualizar(Turno t);
    Turno buscar(int id);
    Turno guardar(Turno t);
    List<Turno> listar();

}
