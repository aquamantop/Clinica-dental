package com.example.ClinicaDental.repository;

import com.example.ClinicaDental.entity.Domicilio;
import java.util.List;

public interface IDomicilioService {

    Domicilio guardar(Domicilio domicilio);
    void eliminar(int id);
    Domicilio buscar(int id);
    List<Domicilio> listar();
    Domicilio actualizar(Domicilio domicilio);

}
