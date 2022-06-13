package com.example.ClinicaDental.service.DAO.impl;

import com.example.ClinicaDental.model.Turno;
import com.example.ClinicaDental.service.DAO.IDAO;
import java.sql.*;
import java.util.List;

public class TurnoDAOH2 implements IDAO<Turno> {

    public static Connection getConnection() throws Exception {
        Class.forName("org.h2.Driver").newInstance();
        return DriverManager.getConnection("jdbc:h2:~/practica", "sa", "");
    }

    @Override
    public Turno buscar(int id) throws SQLException {
        return null;
    }

    @Override
    public List<Turno> listar() {
        return null;
    }

}

