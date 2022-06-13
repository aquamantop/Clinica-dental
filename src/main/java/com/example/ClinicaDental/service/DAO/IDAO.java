package com.example.ClinicaDental.service.DAO;

import java.sql.SQLException;
import java.util.List;

public interface IDAO<T> {

    T buscar(int id) throws SQLException;
    List<T> listar();

}
