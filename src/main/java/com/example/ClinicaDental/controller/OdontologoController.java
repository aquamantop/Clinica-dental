package com.example.ClinicaDental.controller;

import com.example.ClinicaDental.model.Odontologo;
import com.example.ClinicaDental.service.DAO.impl.OdontologoDAOH2;
import com.example.ClinicaDental.service.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OdontologoController {

    @Autowired
    private final OdontologoService o = new OdontologoService(new OdontologoDAOH2());

    @GetMapping("/odontologos")
    public String listarOdontologos(Model model) {
        for (Odontologo odontologo : o.listar()){
            String frase = "Hola paciente " + odontologo.getNombre() + " " + odontologo.getApellido();
            model.addAttribute("frase"+odontologo.getId(), frase);
        }
        return "listar";
    }

}
