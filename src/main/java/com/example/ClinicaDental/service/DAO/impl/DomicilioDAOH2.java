package com.example.ClinicaDental.service.DAO.impl;

import com.example.ClinicaDental.model.Domicilio;
import com.example.ClinicaDental.service.DAO.IDAO;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class DomicilioDAOH2 implements IDAO<Domicilio> {

    public static final Logger logger = Logger.getLogger(DomicilioDAOH2.class);

    public static Connection getConnection() throws Exception {
        Class.forName("org.h2.Driver").newInstance();
        return DriverManager.getConnection("jdbc:h2:~/practica", "sa", "");
    }

    @Override
    public Domicilio guardar(Domicilio domicilio) throws SQLException {
        return null;
    }

    @Override
    public Domicilio eliminar(int id) throws SQLException {
        return null;
    }

    @Override
    public Domicilio buscar(int id) throws SQLException {
        PreparedStatement preparedStatement = null;
        Domicilio d = null;
        try (Connection con = getConnection()){
            logger.debug("Buscando domicilio...");
            preparedStatement = con.prepareStatement("SELECT * FROM domicilios WHERE ID=?");
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                int iden = rs.getInt("ID");
                String calle = rs.getString("CALLE");
                int num = rs.getInt("NUMERO");
                String localidad = rs.getString("LOCALIDAD");
                String prov = rs.getString("PROVINCIA");
                d = new Domicilio(calle, num, localidad, prov);
                d.setId(iden);
                logger.info("--Domicilio encontrado--");
                logger.info(d.toString());
            }
            ResultSet clavegenerada = preparedStatement.getGeneratedKeys();
            if (clavegenerada.next()){
                d.setId(clavegenerada.getInt(1));
            }
        } catch (Exception e){
            logger.error("Error al buscar el domicilio", e);
            e.printStackTrace();
        }
        return d;
    }

    @Override
    public List<Domicilio> listar() {
        PreparedStatement preparedStatement = null;
        List<Domicilio> lista = new ArrayList<>();
        try (Connection con = getConnection()){
            logger.debug("Listando domicilios...");
            preparedStatement = con.prepareStatement("SELECT * FROM domicilios");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                int id = rs.getInt("ID");
                String calle = rs.getString("CALLE");
                int num = rs.getInt("NUMERO");
                String localidad = rs.getString("LOCALIDAD");
                String prov = rs.getString("PROVINCIA");
                Domicilio d = new Domicilio(calle, num, localidad, prov);
                d.setId(id);
                lista.add(d);
                logger.info(d.toString());
            }
        } catch (Exception e){
            logger.error("Error al listar los domicilios", e);
            e.printStackTrace();
        }
        return lista;
    }
}
