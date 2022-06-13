package com.example.ClinicaDental.controller;

import com.example.ClinicaDental.model.Turno;
import com.example.ClinicaDental.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TurnoController {

//    @Autowired
//    private final TurnoService t = new TurnoService();
//
//    @GetMapping("/turnos")
//    public String listarTurnos(Model model) {
//        for (Turno turno : t.listar()){
//            String frase = turno.toString();
//            model.addAttribute("frase"+turno.getId(), frase);
//        }
//        return "listar";
//    }

}
