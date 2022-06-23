package com.example.ClinicaDental.service;

import com.example.ClinicaDental.repository.PacienteRepository;
import com.example.ClinicaDental.entity.Paciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    @Autowired
    PacienteRepository pacienteRepository;

    public List<Paciente> listar() {
        return pacienteRepository.findAll();
    }

    public Paciente actualizar(Paciente p) {
        return pacienteRepository.save(p);
    }

    public Paciente guardar(Paciente p){
        return pacienteRepository.save(p);
    }

    public Optional<Paciente> buscar(Long id){
        return pacienteRepository.findById(id);
    }

    public Paciente buscarPorEmail(String email){
        return null;
    }

    public void eliminar(Long id){
        pacienteRepository.deleteById(id);
    }

}
