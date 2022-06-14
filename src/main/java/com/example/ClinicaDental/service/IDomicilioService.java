package com.example.ClinicaDental.service;

import com.example.ClinicaDental.model.Domicilio;
import java.util.List;

public interface IDomicilioService {

    Domicilio guardar(Domicilio domicilio);
    void eliminar(int id);
    Domicilio buscar(int id);
    List<Domicilio> listar();

}
