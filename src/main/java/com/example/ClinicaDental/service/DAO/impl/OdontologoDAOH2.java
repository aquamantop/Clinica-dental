package com.example.ClinicaDental.service.DAO.impl;

import com.example.ClinicaDental.model.Odontologo;
import com.example.ClinicaDental.service.IOdontologoService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Component
public class OdontologoDAOH2 implements IOdontologoService {

    public static final Logger logger = Logger.getLogger(DomicilioDAOH2.class);

    public static Connection getConnection() throws Exception {
        Class.forName("org.h2.Driver").newInstance();
        return DriverManager.getConnection("jdbc:h2:~/practica", "sa", "");
    }

    @Override
    public Odontologo guardar(Odontologo o){
        PreparedStatement preparedStatement = null;
        try (Connection con = getConnection()) {
            logger.debug("Guardando odontologo...");
            preparedStatement = con.prepareStatement("INSERT INTO odontologos (APELLIDO, NOMBRE, MATRICULA) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, o.getApellido());
            preparedStatement.setString(2, o.getNombre());
            preparedStatement.setInt(3, o.getMatricula());
            preparedStatement.executeUpdate();
            logger.info("--Odontologo guardado--");
            ResultSet cg = preparedStatement.getGeneratedKeys();
            if (cg.next()){
                o.setId(cg.getInt(1));
            }
            preparedStatement.close();
        } catch (Exception e) {
            logger.error("Error al guardar odontologo", e);
            e.printStackTrace();
        }
        return o;
    }

    @Override
    public Odontologo eliminar(int id){
        PreparedStatement preparedStatement1 = null;
        PreparedStatement preparedStatement2 = null;
        Odontologo o = null;
        try (Connection con = getConnection()){
            logger.debug("Eliminando odontologo...");
            preparedStatement1 = con.prepareStatement("SELECT * FROM odontologos WHERE ID=?");
            preparedStatement1.setInt(1, id);
            preparedStatement2 = con.prepareStatement("DELETE FROM odontologos WHERE ID=?");
            preparedStatement2.setInt(1, id);
            ResultSet rs = preparedStatement1.executeQuery();
            while(rs.next()){
                String apellido = rs.getString("APELLIDO");
                String nombre = rs.getString("NOMBRE");
                int matricula = rs.getInt("MATRICULA");
                o = new Odontologo(apellido, nombre, matricula);
                logger.info("--Paciente eliminado--");
                logger.info(o.toString());
            }
            preparedStatement2.executeUpdate();
            preparedStatement1.close();
            preparedStatement2.close();
        } catch (Exception e){
            logger.error("Error al eliminar odontologo", e);
            e.printStackTrace();
        }
        return o;
    }

    @Override
    public Odontologo buscar(int id){
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

    @Override
    public Odontologo actualizar(Odontologo o) {
        PreparedStatement preparedStatement = null;
        try (Connection con = getConnection()) {
            logger.debug("Actualizando odontologo...");
            preparedStatement = con.prepareStatement("UPDATE odontologos SET APELLIDO=?, NOMBRE=?, MATRICULA=? WHERE ID=?");
            preparedStatement.setString(1, o.getApellido());
            preparedStatement.setString(2, o.getNombre());
            preparedStatement.setInt(3, o.getMatricula());
            preparedStatement.setInt(4, o.getId());
            preparedStatement.executeUpdate();
            logger.info("--Odontologo actualizado--");
            preparedStatement.close();
        } catch (Exception e) {
            logger.error("Error al actualizar odontologo", e);
            e.printStackTrace();
        }
        return o;
    }


}
