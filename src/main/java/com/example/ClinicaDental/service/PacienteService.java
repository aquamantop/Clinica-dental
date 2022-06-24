package com.example.ClinicaDental.service;

import com.example.ClinicaDental.entity.Odontologo;
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
        Paciente paciente = buscar(p.getId()).get();
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
