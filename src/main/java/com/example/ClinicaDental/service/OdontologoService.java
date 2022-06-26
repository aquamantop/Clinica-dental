package com.example.ClinicaDental.service;

import com.example.ClinicaDental.entity.Odontologo;
import com.example.ClinicaDental.repository.OdontologoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.Optional;
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
        Odontologo odontologo = buscar(o.getId()).get();
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

    public String eliminar(Long id){
        String resultado = "";
        if(odontologoRepository.existsById(id)){
            odontologoRepository.deleteById(id);
            resultado = "Odontologo eliminado con id: " + id;
        } else resultado = "Error al eliminar";
        return resultado;
    }

    public Optional<Odontologo> buscar(Long id){
        return odontologoRepository.findById(id);
    }

}
