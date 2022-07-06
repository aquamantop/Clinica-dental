package com.example.ClinicaDental.service;

import com.example.ClinicaDental.entity.Usuario;
import com.example.ClinicaDental.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements UserDetailsService {
    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUser(username).orElse(null);
        if(usuario != null){
            return usuario;
        } else {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
    }

}
