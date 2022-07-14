package com.example.ClinicaDental.service;

import com.example.ClinicaDental.entity.Turno;
import com.example.ClinicaDental.exceptions.ResourceNotFoundException;
import com.example.ClinicaDental.repository.TurnoRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.Set;

@Service
public class TurnoService {

    // Inyectamos dependencia
    @Autowired
    TurnoRepository turnoRepository;

    // instanciamos logger
    public static final Logger logger = Logger.getLogger(TurnoService.class);

    // Metodo buscar todos
    public Set<Turno> listar() {
        return new HashSet<>(turnoRepository.findAll());
    }

    // Metodo guardar
    public Turno guardar(Turno t){
        return turnoRepository.save(t);
    }

    // Metodo actualizar
    public Turno actualizar(Turno t) {
        Turno turno = buscar(t.getId());

        if(t.getFechaHora() != null){
            turno.setFechaHora(t.getFechaHora());
        }
        if(t.getPaciente() != null){
            turno.setPaciente(t.getPaciente());
        }
        if(t.getOdontologo() != null){
            turno.setOdontologo(t.getOdontologo());
        }

        turnoRepository.save(turno);
        return turno;
    }

    // Metodo buscar por id
    public Turno buscar(Long id){
        return turnoRepository.findById(id).orElse(null);
    }

    // Metodo eliminar por id
    public String eliminar(Long id) throws ResourceNotFoundException {
        logger.debug("Eliminando turno...");
        if(buscar(id) != null){
            turnoRepository.deleteById(id);
            logger.info("Turno eliminado con id: " + id);
            return "Turno eliminado con id: " + id;
        } else {
            logger.error("Turno con id " + id + " no encontrado");
            throw new ResourceNotFoundException("Turno con id " + id + " no encontrado");
        }
    }

}
