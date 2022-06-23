package com.example.ClinicaDental.repository;

import com.example.ClinicaDental.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {

}
