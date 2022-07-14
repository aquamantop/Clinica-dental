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

    // Inyectamos dependencia
    @Autowired
    UsuarioRepository usuarioRepository;

    // sobreescribimos metodo run
    @Override
    public void run(ApplicationArguments args) throws Exception {
        // clave Usuario
        BCryptPasswordEncoder passwordEncoder1 = new BCryptPasswordEncoder();
        String claveUsuario = passwordEncoder1.encode("usuario");

        // clave Admin
        BCryptPasswordEncoder passwordEncoder2 = new BCryptPasswordEncoder();
        String claveAdmin = passwordEncoder2.encode("admin");

        // instanciamos usuarios
        Usuario usuario = new Usuario("usuario@gmail.com", claveUsuario, Rol.ROLE_USUARIO);
        Usuario admin = new Usuario("admin@gmail.com", claveAdmin, Rol.ROLE_ADMIN);

        // los creamos en la BBDD con uso de IF para que no se repitan los datos
        if(usuarioRepository.findByEmail(usuario.getEmail()).isEmpty() &&
                usuarioRepository.findByEmail(admin.getEmail()).isEmpty()){
            usuarioRepository.save(usuario);
            usuarioRepository.save(admin);
        } else System.out.println("Usuarios ya cargados");

    }

}
