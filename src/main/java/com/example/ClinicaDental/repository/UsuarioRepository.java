package com.example.ClinicaDental.repository;

import com.example.ClinicaDental.login.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

    Optional<Usuario> findByNombre(String nombre);

}
