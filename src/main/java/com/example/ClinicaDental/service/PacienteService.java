package com.example.ClinicaDental.service;

import com.example.ClinicaDental.model.Paciente;
import org.springframework.stereotype.Service;
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
    public Paciente guardar(Paciente p){
        return pacienteIDAO.guardar(p);
    }

    @Override
    public Paciente buscar(int id){
        return pacienteIDAO.buscar(id);
    }

    @Override
    public Paciente buscarPorEmail(String email){
        return pacienteIDAO.buscarPorEmail(email);
    }

    @Override
    public Paciente eliminar(Paciente p){
        return pacienteIDAO.eliminar(p);
    }

}
