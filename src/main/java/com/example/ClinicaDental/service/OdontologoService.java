package com.example.ClinicaDental.service;

import com.example.ClinicaDental.model.Odontologo;
import com.example.ClinicaDental.service.DAO.IDAO;
import org.springframework.stereotype.Service;
import java.sql.SQLException;
import java.util.List;

@Service
public class OdontologoService implements IDAO<Odontologo> {

    private final IDAO<Odontologo> odontologoIDAO;

    public OdontologoService(IDAO<Odontologo> odontologoIDAO) {
        this.odontologoIDAO = odontologoIDAO;
    }

    @Override
    public List<Odontologo> listar() {
        return odontologoIDAO.listar();
    }

    @Override
    public Odontologo guardar(Odontologo odontologo) throws SQLException {
        return odontologoIDAO.guardar(odontologo);
    }

    @Override
    public Odontologo eliminar(int id) throws SQLException {
        return odontologoIDAO.eliminar(id);
    }

    @Override
    public Odontologo buscar(int id) throws SQLException {
        return odontologoIDAO.buscar(id);
    }


}
