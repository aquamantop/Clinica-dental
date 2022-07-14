package com.example.ClinicaDental.service;

import com.example.ClinicaDental.entity.Usuario;
import com.example.ClinicaDental.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UsuarioService implements UserDetailsService {

    // Inyectamos dependencia
    @Autowired
    UsuarioRepository usuarioRepository;

    // buscamos el usuario por email para validarlo
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(email);

        if(usuario.isPresent()){
            return usuario.get();
        } else {
            throw new UsernameNotFoundException("Email de usuario no encontrado");
        }
    }

}
