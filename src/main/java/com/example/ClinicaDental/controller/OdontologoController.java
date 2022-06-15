package com.example.ClinicaDental.controller;

import com.example.ClinicaDental.model.Odontologo;
import com.example.ClinicaDental.Repository.impl.OdontologoDAOH2;
import com.example.ClinicaDental.service.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/odontologos")
public class OdontologoController {

    @Autowired
    private final OdontologoService o = new OdontologoService(new OdontologoDAOH2());

    @PostMapping("/guardar")
    public String guardar(Model model, @RequestBody Odontologo odontologo){
        o.guardar(odontologo);
        model.addAttribute("frase", odontologo.toString());
        return "usuario";
    }

    @DeleteMapping("/eliminar/{id}")
    public String eliminar(Model model, @PathVariable int id){
        Odontologo odontologo = o.eliminar(id);
        String frase = "Odontologo eliminado: " + odontologo.getNombre() + " " + odontologo.getApellido();
        model.addAttribute("frase", frase);
        return "usuario";
    }

    @GetMapping("/buscar/{id}")
    public String buscarID(Model model, @PathVariable int id) {
        Odontologo odontologo = o.buscar(id);
        String frase = "Bienvenido: " + odontologo.toString();
        model.addAttribute("frase", frase);
        return "usuario";
    }

    @GetMapping("/listar")
    public String listarOdontologos(Model model) {
        for (Odontologo odontologo : o.listar()){
            String frase = "Hola paciente " + odontologo.getNombre() + " " + odontologo.getApellido();
            model.addAttribute("frase"+odontologo.getId(), frase);
        }
        return "listar";
    }

    @PutMapping("/actualizar")
    public String actualizar(Model model, @RequestBody Odontologo odontologo){
        o.actualizar(odontologo);
        Odontologo o1 = o.buscar(odontologo.getId());
        model.addAttribute("frase", o1.toString());
        return "usuario";
    }

}
