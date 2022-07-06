package com.example.ClinicaDental.repository;

import com.example.ClinicaDental.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

//@Repository
//@Transactional(readOnly = true)
public interface UsuarioRepository  {

//    @Query("SELECT u FROM Usuario u WHERE u.usuario = ?1")
//    Optional<Usuario> findByUser(String usuario);

}
