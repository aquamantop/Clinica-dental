package com.example.ClinicaDental.repository.impl;

import com.example.ClinicaDental.repository.IPacienteService;
import com.example.ClinicaDental.entity.Domicilio;
import com.example.ClinicaDental.entity.Odontologo;
import com.example.ClinicaDental.entity.Paciente;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PacienteDAOH2 implements IPacienteService {

    public static final Logger logger = Logger.getLogger(DomicilioDAOH2.class);

    public static Connection getConnection() throws Exception {
        Class.forName("org.h2.Driver").newInstance();
        return DriverManager.getConnection("jdbc:h2:~/practica", "sa", "");
    }

    @Override
    public Paciente guardar(Paciente p) {
        try (Connection con = getConnection()){
            logger.debug("Guardando paciente...");
            DomicilioDAOH2 d = new DomicilioDAOH2();
            Domicilio domicilio = d.guardar(p.getDomicilio());
            p.getDomicilio().setId(domicilio.getId());
            PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO pacientes (APELLIDO, NOMBRE, EMAIL, DNI, FECHA_INGRESO, DOMICILIO_ID, ODONTOLOGO_ID) VALUES (?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, p.getApellido());
            preparedStatement.setString(2, p.getNombre());
            preparedStatement.setString(3, p.getEmail());
            preparedStatement.setInt(4, p.getDNI());
            preparedStatement.setDate(5, Date.valueOf(p.getFechaIngreso()));
            preparedStatement.setInt(6, p.getDomicilio().getId());
            if(p.getOdontologo() != null){
                OdontologoDAOH2 o = new OdontologoDAOH2();
                Odontologo odontologo = o.guardar(p.getOdontologo());
                p.getOdontologo().setId(odontologo.getId());
                preparedStatement.setInt(7, p.getOdontologo().getId());
            } else preparedStatement.setInt(7, 0);

            preparedStatement.executeUpdate();
            ResultSet cg = preparedStatement.getGeneratedKeys();
            if(cg.next()){
                p.setId(cg.getInt(1));
            }
            logger.info("--Paciente guardado--");
            logger.info(p.toString());
            preparedStatement.close();
        } catch (Exception e){
            logger.error("Error al guardar paciente", e);
            e.printStackTrace();
        }
        return p;
    }

    @Override
    public Paciente eliminar(int id){
        Paciente p = null;
        try (Connection con = getConnection()){
            logger.debug("Eliminando paciente...");
            DomicilioDAOH2 dDAO = new DomicilioDAOH2();
            OdontologoDAOH2 oDAO = new OdontologoDAOH2();
            PreparedStatement preparedStatement1 = con.prepareStatement("SELECT * FROM pacientes WHERE ID=?");
            preparedStatement1.setInt(1, id);
            PreparedStatement preparedStatement2 = con.prepareStatement("DELETE FROM pacientes WHERE ID=?");
            preparedStatement2.setInt(1, id);
            ResultSet rs = preparedStatement1.executeQuery();
            while(rs.next()){
                String apellido = rs.getString("APELLIDO");
                String nombre = rs.getString("NOMBRE");
                String email = rs.getString("EMAIL");
                int dni = rs.getInt("DNI");
                LocalDate fechaIngreso = rs.getDate("FECHA_INGRESO").toLocalDate();
                int d_id = rs.getInt("DOMICILIO_ID");
                int o_id = rs.getInt("ODONTOLOGO_ID");
                Domicilio d = dDAO.buscar(d_id);
                Odontologo o = oDAO.buscar(o_id);
                p = new Paciente(apellido, nombre, email, dni, fechaIngreso, d, o);
                logger.info("--Paciente eliminado--");
                logger.info(p.toString());
                dDAO.eliminar(p.getDomicilio().getId());
            }
            preparedStatement2.executeUpdate();
            preparedStatement1.close();
            preparedStatement2.close();
        } catch (Exception e){
            logger.error("Error al eliminar paciente", e);
            e.printStackTrace();
        }
        return p;
    }

    @Override
    public Paciente buscar(int id) {
        Paciente p = null;
        try (Connection con = getConnection()){
            logger.debug("Buscando paciente por id...");
            DomicilioDAOH2 domicilioDAOH2 = new DomicilioDAOH2();
            OdontologoDAOH2 odontologoDAOH2 = new OdontologoDAOH2();
            PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM pacientes WHERE ID=?");
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
                Domicilio d = domicilioDAOH2.buscar(d_id);
                Odontologo o = odontologoDAOH2.buscar(o_id);
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
        Paciente p = null;
        try (Connection con = getConnection()){
            logger.debug("Buscando paciente por email...");
            DomicilioDAOH2 domicilioDAOH2 = new DomicilioDAOH2();
            OdontologoDAOH2 odontologoDAOH2 = new OdontologoDAOH2();
            PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM pacientes WHERE EMAIL=?");
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
                Domicilio d = domicilioDAOH2.buscar(d_id);
                Odontologo o = odontologoDAOH2.buscar(o_id);
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
    public List<Paciente> listar() {
        List<Paciente> lista = new ArrayList<>();
        try (Connection con = getConnection()){
            logger.debug("Listando pacientes...");
            DomicilioDAOH2 domicilioDAOH2 = new DomicilioDAOH2();
            OdontologoDAOH2 odontologoDAOH2 = new OdontologoDAOH2();
            PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM pacientes");
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
                Domicilio d = domicilioDAOH2.buscar(d_id);
                Odontologo o = odontologoDAOH2.buscar(o_id);
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

    @Override
    public Paciente actualizar(Paciente p) {
        try (Connection con = getConnection()){
            logger.debug("Actualizando paciente...");
            DomicilioDAOH2 d = new DomicilioDAOH2();
            OdontologoDAOH2 o = new OdontologoDAOH2();
            Paciente p1 = this.buscar(p.getId());
            PreparedStatement preparedStatement = con.prepareStatement("UPDATE pacientes SET APELLIDO=?, NOMBRE=?, EMAIL=?, DNI=?, FECHA_INGRESO=?, DOMICILIO_ID=?, ODONTOLOGO_ID=? WHERE ID=?");

            if(p.getApellido() != null){
                preparedStatement.setString(1, p.getApellido());
            } else preparedStatement.setString(1, p1.getApellido());

            if(p.getNombre() != null) {
                preparedStatement.setString(2, p.getNombre());
            } else preparedStatement.setString(2, p1.getNombre());

            if(p.getEmail() != null) {
                preparedStatement.setString(3, p.getEmail());
            } else preparedStatement.setString(3, p1.getEmail());

            if(p.getDNI() > 0) {
                preparedStatement.setInt(4, p.getDNI());
            } else preparedStatement.setInt(4, p1.getDNI());

            if(p.getFechaIngreso() != null) {
                preparedStatement.setDate(5, Date.valueOf(p.getFechaIngreso()));
            } else preparedStatement.setDate(5, Date.valueOf(p1.getFechaIngreso()));

            if(p.getDomicilio() != null) {
                d.actualizar(p.getDomicilio());
                preparedStatement.setInt(6, p.getDomicilio().getId());
            } preparedStatement.setInt(6, p1.getOdontologo().getId());

            if(p.getOdontologo() != null) {
                o.actualizar(p.getOdontologo());
                preparedStatement.setInt(7, p.getDomicilio().getId());
            } else preparedStatement.setInt(7, p1.getDomicilio().getId());

            preparedStatement.setInt(8, p.getId());
            preparedStatement.executeUpdate();
            logger.info("--Paciente actualizado--");
            logger.info(p.toString());
            preparedStatement.close();
        } catch (Exception e){
            logger.error("Error al guardar paciente", e);
            e.printStackTrace();
        }
        return p;
    }
}
