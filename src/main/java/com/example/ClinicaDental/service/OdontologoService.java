package com.example.ClinicaDental.service;

import com.example.ClinicaDental.Repository.IOdontologoService;
import com.example.ClinicaDental.model.Odontologo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OdontologoService implements IOdontologoService {

    private final IOdontologoService odontologoIDAO;

    public OdontologoService(IOdontologoService odontologoIDAO) {
        this.odontologoIDAO = odontologoIDAO;
    }

    @Override
    public List<Odontologo> listar() {
        return odontologoIDAO.listar();
    }

    @Override
    public Odontologo actualizar(Odontologo odontologo) {
        return odontologoIDAO.actualizar(odontologo);
    }

    @Override
    public Odontologo guardar(Odontologo odontologo){
        return odontologoIDAO.guardar(odontologo);
    }

    @Override
    public Odontologo eliminar(int id){
        return odontologoIDAO.eliminar(id);
    }

    @Override
    public Odontologo buscar(int id){
        return odontologoIDAO.buscar(id);
    }


}
