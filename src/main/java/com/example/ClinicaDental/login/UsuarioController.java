package com.example.ClinicaDental.login;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @GetMapping("/usuario")
    public String usuario() {
        return "<h1>Bienvenido usuario</h1>";
    }

    @GetMapping("/admin")
    public String admin() {
        return "<h1>Bienvenido admin</h1>";
    }

}
