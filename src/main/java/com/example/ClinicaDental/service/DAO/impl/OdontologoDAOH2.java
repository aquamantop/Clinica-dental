package com.example.ClinicaDental.service.DAO.impl;

import com.example.ClinicaDental.model.Odontologo;
import com.example.ClinicaDental.service.DAO.IDAO;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Component
public class OdontologoDAOH2 implements IDAO<Odontologo> {

    public static final Logger logger = Logger.getLogger(DomicilioDAOH2.class);

    public static Connection getConnection() throws Exception {
        Class.forName("org.h2.Driver").newInstance();
        return DriverManager.getConnection("jdbc:h2:~/practica", "sa", "");
    }

    @Override
    public Odontologo guardar(Odontologo odontologo) throws SQLException {
        return null;
    }

    @Override
    public Odontologo eliminar(int id) throws SQLException {
        return null;
    }

    @Override
    public Odontologo buscar(int id) throws SQLException {
        PreparedStatement preparedStatement = null;
        Odontologo o = null;
        try (Connection con = getConnection()) {
            logger.debug("Buscando odontologo...");
            preparedStatement = con.prepareStatement("SELECT * FROM odontologos WHERE ID=?");
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int iden = rs.getInt("ID");
                String apellido = rs.getString("APELLIDO");
                String nombre = rs.getString("NOMBRE");
                int matricula = rs.getInt("MATRICULA");
                o = new Odontologo(apellido, nombre, matricula);
                o.setId(iden);
                logger.info("--Odontologo encontrado--");
                logger.info(o.toString());
            }
        } catch (Exception e) {
            logger.error("Error al buscar odontologo", e);
            e.printStackTrace();
        }
        return o;
    }

    @Override
    public List<Odontologo> listar() {
        PreparedStatement preparedStatement = null;
        List<Odontologo> lista = new ArrayList<>();
        try (Connection con = getConnection()){
            logger.debug("Listando odontologos...");
            preparedStatement = con.prepareStatement("SELECT * FROM odontologos");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                int id = rs.getInt("ID");
                String apellido = rs.getString("APELLIDO");
                String nombre = rs.getString("NOMBRE");
                int matricula = rs.getInt("MATRICULA");
                Odontologo o = new Odontologo(apellido, nombre, matricula);
                o.setId(id);
                lista.add(o);
                logger.info(o.toString());
            }
        } catch (Exception e){
            logger.error("Error al listar odontologos", e);
            e.printStackTrace();
        }
        return lista;
    }
}
