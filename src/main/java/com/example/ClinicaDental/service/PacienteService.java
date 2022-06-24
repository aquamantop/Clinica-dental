package com.example.ClinicaDental.service;

import com.example.ClinicaDental.repository.PacienteRepository;
import com.example.ClinicaDental.entity.Paciente;
import org.springframework.beans.factory.annotation.Autowired;
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

    public Paciente guardar(Paciente p){
        return pacienteRepository.save(p);
    }

    public Paciente actualizar(Paciente p) {
        Paciente paciente = null;
        if(buscar(p.getId()).isPresent()){
            paciente = pacienteRepository.save(p);
        }
        return paciente;
    }

    public Optional<Paciente> buscar(Long id){
        return pacienteRepository.findById(id);
    }

    public Paciente buscarPorEmail(String email){
        return null;
    }

    public String eliminar(Long id){
        String resultado = "";
        if(pacienteRepository.existsById(id)){
            pacienteRepository.deleteById(id);
            resultado = "Paciente eliminado con id: " + id;
        } else resultado = "Error al eliminar";
        return resultado;
    }

}
