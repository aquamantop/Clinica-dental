package com.example.ClinicaDental.service;

import com.example.ClinicaDental.model.Paciente;
import org.springframework.stereotype.Service;
import java.sql.SQLException;
import java.util.List;

@Service
public class PacienteService implements IPacienteService {

    private IPacienteService pacienteIDAO;

    public PacienteService(IPacienteService pacienteIDAO) {
        this.pacienteIDAO = pacienteIDAO;
    }

    @Override
    public List<Paciente> listar() {
        return pacienteIDAO.listar();
    }

    @Override
    public Paciente guardar(Paciente p) throws SQLException {
        return pacienteIDAO.guardar(p);
    }

    @Override
    public Paciente buscar(int id) throws SQLException {
        return pacienteIDAO.buscar(id);
    }

    @Override
    public Paciente buscarPorEmail(String email) throws SQLException {
        return pacienteIDAO.buscarPorEmail(email);
    }

}
