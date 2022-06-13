package com.example.ClinicaDental.service;

import com.example.ClinicaDental.model.Paciente;
import java.sql.SQLException;
import java.util.List;

public interface IPacienteService {

    Paciente buscar(int id) throws SQLException;
    Paciente buscarPorEmail(String email) throws SQLException;
    List<Paciente> listar();

}
