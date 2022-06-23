package com.example.ClinicaDental.service;

import com.example.ClinicaDental.entity.Odontologo;
import com.example.ClinicaDental.repository.OdontologoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService {

    @Autowired
    OdontologoRepository odontologoRepository;

    public List<Odontologo> listar() {
        return odontologoRepository.findAll();
    }

    public Odontologo actualizar(Odontologo o) {
        return odontologoRepository.save(o);
    }

    public Odontologo guardar(Odontologo o){
        return odontologoRepository.save(o);
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
