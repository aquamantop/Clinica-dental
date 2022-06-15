package com.example.ClinicaDental.Repository.impl;

import com.example.ClinicaDental.Repository.IDomicilioService;
import com.example.ClinicaDental.model.Domicilio;
import com.example.ClinicaDental.model.Odontologo;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class DomicilioDAOH2 implements IDomicilioService {

    public static final Logger logger = Logger.getLogger(DomicilioDAOH2.class);

    public static Connection getConnection() throws Exception {
        Class.forName("org.h2.Driver").newInstance();
        return DriverManager.getConnection("jdbc:h2:~/practica", "sa", "");
    }

    @Override
    public Domicilio guardar(Domicilio d) {
        try (Connection con = getConnection()) {
            logger.debug("Guardando domicilio...");
            PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO domicilios (CALLE, NUMERO, LOCALIDAD, PROVINCIA) VALUES (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, d.getCalle());
            preparedStatement.setInt(2, d.getNumero());
            preparedStatement.setString(3, d.getLocalidad());
            preparedStatement.setString(4, d.getProvincia());
            preparedStatement.executeUpdate();
            logger.info("--Domicilio guardado--");
            ResultSet cg = preparedStatement.getGeneratedKeys();
            if (cg.next()){
               d.setId(cg.getInt(1));
            }
            preparedStatement.close();
        } catch (Exception e) {
            logger.error("Error al guardar domicilio", e);
            e.printStackTrace();
        }
        return d;
    }

    @Override
    public void eliminar(int id) {}

    @Override
    public Domicilio buscar(int id) {
        Domicilio d = null;
        try (Connection con = getConnection()){
            logger.debug("Buscando domicilio...");
            PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM domicilios WHERE ID=?");
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
            preparedStatement.close();
        } catch (Exception e){
            logger.error("Error al buscar el domicilio", e);
            e.printStackTrace();
        }
        return d;
    }

    @Override
    public List<Domicilio> listar() {
        List<Domicilio> lista = new ArrayList<>();
        try (Connection con = getConnection()){
            logger.debug("Listando domicilios...");
            PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM domicilios");
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
            preparedStatement.close();
        } catch (Exception e){
            logger.error("Error al listar los domicilios", e);
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public Domicilio actualizar(Domicilio d) {
        try (Connection con = getConnection()) {
            logger.debug("Actualizando domicilio...");
            Domicilio d1 = this.buscar(d.getId());
            PreparedStatement preparedStatement = con.prepareStatement("UPDATE domicilios SET CALLE=?, NUMERO=?, LOCALIDAD=?, PROVINCIA=? WHERE ID=?");

            if(d.getCalle() != null){
                preparedStatement.setString(1, d.getCalle());
            } else preparedStatement.setString(1, d1.getCalle());

            if(d.getNumero() > 0){
                preparedStatement.setInt(2, d.getNumero());
            } else preparedStatement.setInt(2, d1.getNumero());

            if(d.getLocalidad() != null){
                preparedStatement.setString(3, d.getLocalidad());
            } else preparedStatement.setString(3, d1.getLocalidad());

            if(d.getProvincia() != null){
                preparedStatement.setString(4, d.getProvincia());
            } else preparedStatement.setString(4, d1.getProvincia());

            preparedStatement.setInt(5, d.getId());
            preparedStatement.executeUpdate();
            logger.info("--Domicilio actualizado--");
            preparedStatement.close();
        } catch (Exception e) {
            logger.error("Error al guardar domicilio", e);
            e.printStackTrace();
        }
        return d;
    }
}
