package com.example.ClinicaDental.service;

import com.example.ClinicaDental.model.Odontologo;
import java.util.List;

public interface IOdontologoService {

    Odontologo guardar(Odontologo odontologo);
    Odontologo eliminar(int id);
    Odontologo buscar(int id);
    List<Odontologo> listar();
    Odontologo actualizar(Odontologo odontologo);

}
