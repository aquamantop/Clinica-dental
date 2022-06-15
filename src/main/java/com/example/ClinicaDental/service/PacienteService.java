package com.example.ClinicaDental.service;

import com.example.ClinicaDental.repository.IPacienteService;
import com.example.ClinicaDental.model.Paciente;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PacienteService implements IPacienteService {

    private final IPacienteService pacienteService;

    public PacienteService(IPacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @Override
    public List<Paciente> listar() {
        return pacienteService.listar();
    }

    @Override
    public Paciente actualizar(Paciente p) {
        return pacienteService.actualizar(p);
    }

    @Override
    public Paciente guardar(Paciente p){
        return pacienteService.guardar(p);
    }

    @Override
    public Paciente buscar(int id){
        return pacienteService.buscar(id);
    }

    @Override
    public Paciente buscarPorEmail(String email){
        return pacienteService.buscarPorEmail(email);
    }

    @Override
    public Paciente eliminar(int id){
        return pacienteService.eliminar(id);
    }

}
