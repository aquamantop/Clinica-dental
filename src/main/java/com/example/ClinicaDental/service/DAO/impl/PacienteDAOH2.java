package com.example.ClinicaDental.service.DAO.impl;

import com.example.ClinicaDental.model.Domicilio;
import com.example.ClinicaDental.model.Odontologo;
import com.example.ClinicaDental.model.Paciente;
import com.example.ClinicaDental.service.IPacienteService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class PacienteDAOH2 implements IPacienteService {

    public static final Logger logger = Logger.getLogger(DomicilioDAOH2.class);

    public static Connection getConnection() throws Exception {
        Class.forName("org.h2.Driver").newInstance();
        return DriverManager.getConnection("jdbc:h2:~/practica", "sa", "");
    }

    @Override
    public Paciente guardar(Paciente p) {
        PreparedStatement preparedStatement = null;
        try (Connection con = getConnection()){
            logger.debug("Guardando paciente...");
            DomicilioDAOH2 d = new DomicilioDAOH2();
            OdontologoDAOH2 o = new OdontologoDAOH2();
            d.guardar(p.getDomicilio());
            o.guardar(p.getOdontologo());
            preparedStatement = con.prepareStatement("INSERT INTO pacientes (APELLIDO, NOMBRE, EMAIL, DNI, FECHA_INGRESO, DOMICILIO_ID, ODONTOLOGO_ID) VALUES (?,?,?,?,?,?,?)");
            preparedStatement.setString(1, p.getApellido());
            preparedStatement.setString(2, p.getNombre());
            preparedStatement.setString(3, p.getEmail());
            preparedStatement.setInt(4, p.getDNI());
            preparedStatement.setDate(5, Date.valueOf(p.getFechaIngreso()));
            preparedStatement.setInt(6, p.getDomicilio().getId());
            preparedStatement.setInt(6, p.getOdontologo().getId());
            preparedStatement.close();
        } catch (Exception e){
            logger.error("Error al guardar paciente", e);
            e.printStackTrace();
        }
        return p;
    }

    @Override
    public Paciente buscar(int id) {
        PreparedStatement preparedStatement = null;
        Paciente p = null;
        Domicilio d = null;
        Odontologo o = null;
        try (Connection con = getConnection()){
            logger.debug("Buscando paciente por id...");
            DomicilioDAOH2 domicilioDAOH2 = new DomicilioDAOH2();
            OdontologoDAOH2 odontologoDAOH2 = new OdontologoDAOH2();
            preparedStatement = con.prepareStatement("SELECT * FROM pacientes WHERE ID=?");
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                int iden = rs.getInt("ID");
                String apellido = rs.getString("APELLIDO");
                String nombre = rs.getString("NOMBRE");
                String email = rs.getString("EMAIL");
                int dni = rs.getInt("DNI");
                LocalDate fechaIngreso = rs.getDate("FECHA_INGRESO").toLocalDate();
                int d_id = rs.getInt("DOMICILIO_ID");
                int o_id = rs.getInt("ODONTOLOGO_ID");
                d = domicilioDAOH2.buscar(d_id);
                o = odontologoDAOH2.buscar(o_id);
                p = new Paciente(apellido, nombre, email, dni, fechaIngreso, d, o);
                p.setId(iden);
                logger.info("--Paciente encontrado--");
                logger.info(p.toString());
            }
            preparedStatement.close();
        } catch (Exception e){
            logger.error("Error al buscar paciente", e);
            e.printStackTrace();
        }
        return p;
    }

    @Override
    public Paciente buscarPorEmail(String email) {
        PreparedStatement preparedStatement = null;
        Paciente p = null;
        Domicilio d = null;
        Odontologo o = null;
        try (Connection con = getConnection()){
            logger.debug("Buscando paciente por email...");
            DomicilioDAOH2 domicilioDAOH2 = new DomicilioDAOH2();
            OdontologoDAOH2 odontologoDAOH2 = new OdontologoDAOH2();
            preparedStatement = con.prepareStatement("SELECT * FROM pacientes WHERE EMAIL=?");
            preparedStatement.setString(1, email);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                int id = rs.getInt("ID");
                String apellido = rs.getString("APELLIDO");
                String nombre = rs.getString("NOMBRE");
                String datoEmail = rs.getString("EMAIL");
                int dni = rs.getInt("DNI");
                LocalDate fechaIngreso = rs.getDate("FECHA_INGRESO").toLocalDate();
                int d_id = rs.getInt("DOMICILIO_ID");
                int o_id = rs.getInt("ODONTOLOGO_ID");
                d = domicilioDAOH2.buscar(d_id);
                o = odontologoDAOH2.buscar(o_id);
                p = new Paciente(apellido, nombre, datoEmail, dni, fechaIngreso, d, o);
                p.setId(id);
                logger.info("--Paciente encontrado--");
                logger.info(p.toString());
            }
            preparedStatement.close();
        } catch (Exception e){
            logger.error("Error al buscar paciente", e);
            e.printStackTrace();
        }
        return p;
    }

    @Override
    public Paciente eliminar(Paciente p){
        return null;
    }

    @Override
    public List<Paciente> listar() {
        PreparedStatement preparedStatement = null;
        List<Paciente> lista = new ArrayList<>();
        Domicilio d = null;
        Odontologo o = null;
        try (Connection con = getConnection()){
            logger.debug("Listando pacientes...");
            DomicilioDAOH2 domicilioDAOH2 = new DomicilioDAOH2();
            OdontologoDAOH2 odontologoDAOH2 = new OdontologoDAOH2();
            preparedStatement = con.prepareStatement("SELECT * FROM pacientes");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                int id = rs.getInt("ID");
                String apellido = rs.getString("APELLIDO");
                String nombre = rs.getString("NOMBRE");
                String datoEmail = rs.getString("EMAIL");
                int dni = rs.getInt("DNI");
                LocalDate fechaIngreso = rs.getDate("FECHA_INGRESO").toLocalDate();
                int d_id = rs.getInt("DOMICILIO_ID");
                int o_id = rs.getInt("ODONTOLOGO_ID");
                d = domicilioDAOH2.buscar(d_id);
                o = odontologoDAOH2.buscar(o_id);
                Paciente p = new Paciente(apellido, nombre, datoEmail, dni, fechaIngreso, d, o);
                p.setId(id);
                lista.add(p);
                logger.info(p.toString());
            }
            preparedStatement.close();
        } catch (Exception e){
            logger.error("Error al listar pacientes", e);
            e.printStackTrace();
        }
        return lista;
    }
}
