package com.example.ClinicaDental;

import org.apache.log4j.PropertyConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.sql.SQLException;

@SpringBootApplication
public class ClinicaDentalApplication {

	public static void main(String[] args) throws SQLException {
		PropertyConfigurator.configure("log4j.properties");
		SpringApplication.run(ClinicaDentalApplication.class, args);
	}

}
