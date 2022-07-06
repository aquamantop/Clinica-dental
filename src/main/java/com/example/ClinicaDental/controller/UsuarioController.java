package com.example.ClinicaDental.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioController {

    @GetMapping("/")
    public String home() {
        return "<h1>Bienvenido/a</h1>";
    }

    @GetMapping("/usuario")
    public String usuario() {
        return "<h1>Bienvenido/a usuario</h1>";
    }

    @GetMapping("/admin")
    public String admin() {
        return "<h1>Bienvenido/a admin</h1>";
    }

}
