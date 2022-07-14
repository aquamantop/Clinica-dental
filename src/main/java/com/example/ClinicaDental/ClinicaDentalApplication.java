package com.example.ClinicaDental;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.sql.SQLException;

@SpringBootApplication
public class ClinicaDentalApplication {

	public static void main(String[] args) throws SQLException {
		SpringApplication.run(ClinicaDentalApplication.class, args);
	}

}
