package com.example.ClinicaDental.service;

import com.example.ClinicaDental.exceptions.ResourceNotFoundException;
import com.example.ClinicaDental.repository.PacienteRepository;
import com.example.ClinicaDental.entity.Paciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.Set;
import org.apache.log4j.Logger;

@Service
public class PacienteService {

    // Inyectamos dependencia
    @Autowired
    PacienteRepository pacienteRepository;

    // instanciamos logger
    public static final Logger logger = Logger.getLogger(PacienteService.class);

    // Metodo buscar todos
    public Set<Paciente> listar() {
        return new HashSet<>(pacienteRepository.findAll());
    }

    // Metodo guardar
    public Paciente guardar(Paciente p){
        if(p.getNombre() != null
            && p.getApellido() != null
            && p.getEmail() != null
            && p.getDNI() != null
            && p.getDomicilio() != null) {
            return pacienteRepository.save(p);
        } else return null;
    }

    // Metodo actualizar
    public Paciente actualizar(Paciente p) {
        Paciente paciente  = buscar(p.getId());
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
        if(p.getDomicilio() != null){
            paciente.setDomicilio(p.getDomicilio());
        }

        pacienteRepository.save(paciente);
        return paciente;
    }

    // Metodo bsucar por id
    public Paciente buscar(Long id){
        return pacienteRepository.findById(id).orElse(null);
    }

    // Metodo buscar por email
    public Paciente buscarPorEmail(String email){
        return pacienteRepository.buscarEmail(email).orElse(null);
    }

    // Metodo eliminar
    public String eliminar(Long id) throws ResourceNotFoundException {
        logger.debug("Eliminando pacinete...");
        if(buscar(id) != null){
            logger.info("Paciente eliminado con id: " + id);
            pacienteRepository.deleteById(id);
            return "Paciente eliminado con id: " + id;
        } else {
            logger.error("Paciente con id " + id + " no encontrado");
            throw new ResourceNotFoundException("Paciente con id " + id + " no encontrado");
        }
    }

}
