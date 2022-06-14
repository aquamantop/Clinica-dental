package com.example.ClinicaDental.Repository;

import com.example.ClinicaDental.model.Domicilio;
import java.util.List;

public interface IDomicilioService {

    Domicilio guardar(Domicilio domicilio);
    void eliminar(int id);
    Domicilio buscar(int id);
    List<Domicilio> listar();
    Domicilio actualizar(Domicilio domicilio);

}
