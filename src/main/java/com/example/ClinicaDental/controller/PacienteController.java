package com.example.ClinicaDental.controller;

import com.example.ClinicaDental.model.Paciente;
import com.example.ClinicaDental.service.DAO.impl.PacienteDAOH2;
import com.example.ClinicaDental.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;

@Controller
public class PacienteController {

    @Autowired
    private final PacienteService p = new PacienteService(new PacienteDAOH2());




    @GetMapping("/buscar")
    public String buscar(Model model, @RequestParam("email") String email) throws SQLException {
        Paciente paciente = p.buscarPorEmail(email);
        String frase = "Hola paciente " + paciente.getNombre() + " " + paciente.getApellido();
        String frase2 = "Odontolo asignado con matricula: " + paciente.getOdontologo().getMatricula();
        model.addAttribute("frase", frase);
        model.addAttribute("frase2", frase2);
        return "usuario";
    }

    @GetMapping("/buscar/{id}")
    public String buscarID(Model model, @PathVariable int id) throws SQLException {
        Paciente paciente = p.buscar(id);
        String frase = "Hola paciente " + paciente.getNombre() + " " + paciente.getApellido();
        String frase2 = "Odontolo asignado con matricula: " + paciente.getOdontologo().getMatricula();
        model.addAttribute("frase", frase);
        model.addAttribute("frase2", frase2);
        return "usuario";
    }

    @GetMapping("/listar")
    public String listarPacientes(Model model) {
        for (Paciente paciente : p.listar()){
            String frase = "Hola paciente " + paciente.getNombre() + " " + paciente.getApellido();
            model.addAttribute("frase"+paciente.getId(), frase);
        }
        return "listar";
    }

}
