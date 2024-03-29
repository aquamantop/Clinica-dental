package com.example.ClinicaDental.configuration;

import com.example.ClinicaDental.entity.Rol;
import com.example.ClinicaDental.entity.Usuario;
import com.example.ClinicaDental.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        BCryptPasswordEncoder passwordEncoder1 = new BCryptPasswordEncoder();
        String claveUsuario = passwordEncoder1.encode("usuario");

        BCryptPasswordEncoder passwordEncoder2 = new BCryptPasswordEncoder();
        String claveAdmin = passwordEncoder2.encode("admin");

        Usuario usuario = new Usuario("usuario@gmail.com", claveUsuario, Rol.ROLE_USUARIO);
        Usuario admin = new Usuario("admin@gmail.com", claveAdmin, Rol.ROLE_ADMIN);

        if(usuarioRepository.findByEmail(usuario.getEmail()).isEmpty() &&
                usuarioRepository.findByEmail(admin.getEmail()).isEmpty()){
            usuarioRepository.save(usuario);
            usuarioRepository.save(admin);
        } else System.out.println("Usuarios ya cargados");

    }

}
