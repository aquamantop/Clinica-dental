package com.example.ClinicaDental.service;

import com.example.ClinicaDental.login.Usuario;
import com.example.ClinicaDental.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UsuarioService implements UserDetailsService{

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(email).orElse(null);
        if(usuario != null){
            return usuario;
        } else {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
    }

}
