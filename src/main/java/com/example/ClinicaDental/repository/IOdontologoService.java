package com.example.ClinicaDental.repository;

import com.example.ClinicaDental.entity.Odontologo;
import java.util.List;

public interface IOdontologoService {

    Odontologo guardar(Odontologo odontologo);
    Odontologo eliminar(int id);
    Odontologo buscar(int id);
    List<Odontologo> listar();
    Odontologo actualizar(Odontologo odontologo);

}
