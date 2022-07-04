package com.example.ClinicaDental.service;

import com.example.ClinicaDental.entity.Turno;
import com.example.ClinicaDental.exceptions.ResourceNotFoundException;
import com.example.ClinicaDental.repository.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.Set;

@Service
public class TurnoService {

    @Autowired
    TurnoRepository turnoRepository;

    public Set<Turno> listar() {
        return new HashSet<>(turnoRepository.findAll());
    }

    public Turno guardar(Turno t){
        return turnoRepository.save(t);
    }

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

    public Turno buscar(Long id){
        return turnoRepository.findById(id).orElse(null);
    }

    public String eliminar(Long id) throws ResourceNotFoundException {
        if(buscar(id) != null){
            turnoRepository.deleteById(id);
            return "Turno eliminado con id: " + id;
        } else {
            throw new ResourceNotFoundException("Turno con id " + id + " no encontrado");
        }
    }

}
