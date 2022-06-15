package com.example.ClinicaDental.repository;

import com.example.ClinicaDental.model.Turno;

public interface ITurnoService {

    Turno eliminar(int id);
    Turno actualizar(Turno t);
    Turno buscar(int id);

}
