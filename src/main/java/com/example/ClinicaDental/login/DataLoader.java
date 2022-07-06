package com.example.ClinicaDental.login;

import com.example.ClinicaDental.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner{

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password1 = passwordEncoder.encode("clave1");
        String password2 = passwordEncoder.encode("clave2");

        if(usuarioRepository.findByNombre("Admin").isPresent() && usuarioRepository.findByNombre("Usuario").isPresent()){
            System.out.println("Usuario existente");
        } else {
            usuarioRepository.save(new Usuario("Admin", password1, Rol.ROLE_ADMIN));
            usuarioRepository.save(new Usuario("Usuario", password2, Rol.ROLE_USER));
        }
    }

}
