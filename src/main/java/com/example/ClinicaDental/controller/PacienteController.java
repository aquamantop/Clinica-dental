package com.example.ClinicaDental.controller;

import com.example.ClinicaDental.model.Paciente;
import com.example.ClinicaDental.service.DAO.impl.PacienteDAOH2;
import com.example.ClinicaDental.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private final PacienteService p = new PacienteService(new PacienteDAOH2());

    @PostMapping("/guardar")
    public String guardar(Model model, @RequestBody Paciente paciente){
        p.guardar(paciente);
        model.addAttribute("frase", paciente.toString());
        return "usuario";
    }

    @DeleteMapping("/eliminar/{id}")
    public String eliminar(Model model, @PathVariable int id){
        Paciente paciente = p.eliminar(id);
        String frase = "Paciente eliminado: " + paciente.getNombre() + " " + paciente.getApellido();
        model.addAttribute("frase", frase);
        return "usuario";
    }

    @GetMapping("/buscarEmail/{email}")
    public String buscarPorEmail(Model model, @PathVariable String email) {
        Paciente paciente = p.buscarPorEmail(email);
        String frase = "Hola paciente " + paciente.getNombre() + " " + paciente.getApellido();
        String frase2 = "Odontolo asignado con matricula: " + paciente.getOdontologo().getMatricula();
        model.addAttribute("frase", frase);
        model.addAttribute("frase2", frase2);
        return "usuario";
    }

    @GetMapping("/buscarID/{id}")
    public String buscarID(Model model, @PathVariable int id) {
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
