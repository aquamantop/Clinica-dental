package com.example.ClinicaDental.service;

import com.example.ClinicaDental.repository.PacienteRepository;
import com.example.ClinicaDental.entity.Paciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PacienteService {

    @Autowired
    PacienteRepository pacienteRepository;

    public Set<Paciente> listar() {
        List<Paciente> pacientesList = pacienteRepository.findAll();
        return new HashSet<>(pacientesList);
    }

    public Paciente guardar(Paciente p){
        return pacienteRepository.save(p);
    }

    public Paciente actualizar(Paciente p) {
        Paciente paciente  = buscar(p.getId()).get();
        if(p.getNombre() != null) {
            paciente.setNombre(p.getNombre());
        }
        if(p.getApellido() != null){
            paciente.setApellido(p.getApellido());
        }
        if(p.getEmail() != null){
            paciente.setEmail(p.getEmail());
        }
        if(p.getDNI() != null){
            paciente.setDNI(p.getDNI());
        }
        if(p.getFechaIngreso() != null){
            paciente.setFechaIngreso(p.getFechaIngreso());
        }
        if(p.getDomicilio() != null){
            paciente.setDomicilio(p.getDomicilio());
        }
        pacienteRepository.save(paciente);
        return paciente;
    }

    public Optional<Paciente> buscar(Long id){
        return pacienteRepository.findById(id);
    }

    public Optional<Paciente> buscarPorEmail(String email){
        return pacienteRepository.buscarEmail(email);
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
