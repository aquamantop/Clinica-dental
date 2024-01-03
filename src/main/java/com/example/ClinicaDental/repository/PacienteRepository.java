package com.example.ClinicaDental.repository;

import com.example.ClinicaDental.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    @Query("SELECT p FROM Paciente p WHERE p.email = ?1")
    Optional<Paciente> buscarEmail(String email);

}
