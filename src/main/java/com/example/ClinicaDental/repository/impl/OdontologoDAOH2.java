package com.example.ClinicaDental.repository.impl;

import com.example.ClinicaDental.repository.IOdontologoService;
import com.example.ClinicaDental.entity.Odontologo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OdontologoDAOH2 implements IOdontologoService {

    @Autowired
    private static final Logger logger = Logger.getLogger(OdontologoDAOH2.class);

    public static Connection getConnection() throws Exception {
        Class.forName("org.h2.Driver").newInstance();
        return DriverManager.getConnection("jdbc:h2:~/practica", "sa", "");
    }

    @Override
    public Odontologo guardar(Odontologo o){
        try (Connection con = getConnection()) {
            logger.debug("Guardando odontologo...");
            PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO odontologos (APELLIDO, NOMBRE, MATRICULA) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);
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
        Odontologo o = null;
        try (Connection con = getConnection()){
            logger.debug("Eliminando odontologo...");
            PreparedStatement preparedStatement1 = con.prepareStatement("SELECT * FROM odontologos WHERE ID=?");
            preparedStatement1.setInt(1, id);
            PreparedStatement preparedStatement2 = con.prepareStatement("DELETE FROM odontologos WHERE ID=?");
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
        Odontologo o = null;
        try (Connection con = getConnection()) {
            logger.debug("Buscando odontologo...");
            PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM odontologos WHERE ID=?");
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
        List<Odontologo> lista = new ArrayList<>();
        try (Connection con = getConnection()){
            logger.debug("Listando odontologos...");
            PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM odontologos");
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
        try (Connection con = getConnection()) {
            logger.debug("Actualizando odontologo...");
            Odontologo o1 = this.buscar(o.getId());
            PreparedStatement preparedStatement = con.prepareStatement("UPDATE odontologos SET APELLIDO=?, NOMBRE=?, MATRICULA=? WHERE ID=?");

            if(o.getApellido() != null){
                preparedStatement.setString(1, o.getApellido());
            } else preparedStatement.setString(1, o1.getApellido());

            if(o.getNombre() != null){
                preparedStatement.setString(2, o.getNombre());
            } else preparedStatement.setString(2, o1.getNombre());

            if(o.getMatricula() > 0){
                preparedStatement.setInt(3, o.getMatricula());
            } else preparedStatement.setInt(3, o1.getMatricula());

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
