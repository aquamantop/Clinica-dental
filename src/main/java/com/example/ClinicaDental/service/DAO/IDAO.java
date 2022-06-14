package com.example.ClinicaDental.service.DAO;

import java.sql.SQLException;
import java.util.List;

public interface IDAO<T> {

    T guardar(T t);
    void eliminar(int id);
    T buscar(int id);
    List<T> listar();

}
