package com.example.ClinicaDental.service.DAO;

import java.sql.SQLException;
import java.util.List;

public interface IDAO<T> {

    T guardar(T t) throws  SQLException;
    T eliminar(int id) throws SQLException;
    T buscar(int id) throws SQLException;
    List<T> listar();

}
