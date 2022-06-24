package com.example.ClinicaDental.service;

import com.example.ClinicaDental.entity.Turno;
import com.example.ClinicaDental.repository.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoService {

    @Autowired
    TurnoRepository turnoRepository;

    public List<Turno> listar() {
        return turnoRepository.findAll();
    }

    public Turno actualizar(Turno t) {
        Turno turno = null;
        if(buscar(t.getId()).isPresent()){
            turno = turnoRepository.save(t);
        }
        return turno;
    }

    public Turno guardar(Turno t){
        return turnoRepository.save(t);
    }

    public Optional<Turno> buscar(Long id){
        return turnoRepository.findById(id);
    }

    public String eliminar(Long id){
        String resultado = "";
        if(turnoRepository.existsById(id)){
            turnoRepository.deleteById(id);
            resultado = "Odontologo eliminado con id: " + id;
        } else resultado = "Error al eliminar";
        return resultado;
    }

}
