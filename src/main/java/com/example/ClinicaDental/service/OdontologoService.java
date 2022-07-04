package com.example.ClinicaDental.service;

import com.example.ClinicaDental.entity.Odontologo;
import com.example.ClinicaDental.exceptions.ResourceNotFoundException;
import com.example.ClinicaDental.repository.OdontologoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.Set;

@Service
public class OdontologoService {

    @Autowired
    OdontologoRepository odontologoRepository;

    public Set<Odontologo> listar() {
        return new HashSet<>(odontologoRepository.findAll());
    }

    public Odontologo guardar(Odontologo o){
        return odontologoRepository.save(o);
    }

    public Odontologo actualizar(Odontologo o) {
        Odontologo odontologo = buscar(o.getId());
        if(o.getNombre() != null) {
            odontologo.setNombre(o.getNombre());
        }
        if(o.getApellido() != null){
            odontologo.setApellido(o.getApellido());
        }
        if(o.getMatricula() != null){
            odontologo.setMatricula(o.getMatricula());
        }
        odontologoRepository.save(odontologo);
        return odontologo;
    }

    public Odontologo buscar(Long id){
        return odontologoRepository.findById(id).orElse(null);
    }

    public String eliminar(Long id) throws ResourceNotFoundException {
        if(buscar(id) != null){
            odontologoRepository.deleteById(id);
            return "Odontologo eliminado con id: " + id;
        } else {
            throw new ResourceNotFoundException("Odontologo con id " + id + " no encontrado");
        }
    }

}
