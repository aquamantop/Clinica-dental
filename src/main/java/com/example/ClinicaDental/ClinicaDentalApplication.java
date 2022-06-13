package com.example.ClinicaDental;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SpringBootApplication
public class ClinicaDentalApplication {

	public static final Logger logger = Logger.getLogger(ClinicaDentalApplication.class);

	private static void cargarDB() throws SQLException {
		Connection con = null;
		try {
			logger.debug("Cargando base de datos...");
			Class.forName("org.h2.Driver").newInstance();
			con = DriverManager.getConnection("jdbc:h2:~/practica;INIT=RUNSCRIPT FROM 'create.sql'", "sa", "");
			logger.info("Base de datos cargada con exito");
		} catch (Exception e){
			logger.error("Error al cargar la base de datos", e);
			e.printStackTrace();
		} finally {
			assert con != null;
			con.close();
		}
	}

	public static void main(String[] args) throws SQLException {
		cargarDB();
		SpringApplication.run(ClinicaDentalApplication.class, args);
	}

}
