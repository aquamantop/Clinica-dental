package com.example.ClinicaDental.controller;

import com.example.ClinicaDental.model.Paciente;
import com.example.ClinicaDental.repository.impl.PacienteDAOH2;
import com.example.ClinicaDental.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private final PacienteService p = new PacienteService(new PacienteDAOH2());

    @PostMapping("/guardar")
    public ResponseEntity<Paciente> guardar(@RequestBody Paciente paciente){
        ResponseEntity response = null;

        if(paciente != null) {
            response = new ResponseEntity(p.guardar(paciente), HttpStatus.OK);
        } else response = new ResponseEntity(HttpStatus.FORBIDDEN);

        //model.addAttribute("frase", paciente.toString());
        return response;
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity eliminar(@PathVariable int id){
        ResponseEntity response = null;

        if(p.buscar(id) != null){
            response = new ResponseEntity(p.eliminar(id), HttpStatus.OK);
        } else response = new ResponseEntity(HttpStatus.NOT_FOUND);
        //model.addAttribute("frase", frase);
        return response;
    }

    @GetMapping("/buscarEmail/{email}")
    public ResponseEntity<Paciente> buscarPorEmail(@PathVariable String email) {
        ResponseEntity response = null;

        if(p.buscarPorEmail(email) != null){
            response = new ResponseEntity(p.buscarPorEmail(email), HttpStatus.OK);
        } else response = new ResponseEntity(HttpStatus.NOT_FOUND);

        //model.addAttribute("frase", frase);
        //model.addAttribute("frase2", frase2);

        return response;
    }

    @GetMapping("/buscarID/{id}")
    public ResponseEntity<Paciente> buscarID(@PathVariable int id) {
        ResponseEntity response = null;

        if(id > 0 && p.buscar(id) != null){
            response = new ResponseEntity(p.buscar(id), HttpStatus.OK);
        } else response = new ResponseEntity(HttpStatus.NOT_FOUND);

        //model.addAttribute("frase", frase);
        //model.addAttribute("frase2", frase2);

        return response;
    }

    @GetMapping("/listar")
    public ResponseEntity listarPacientes() {
        ResponseEntity response = null;

        //for (Paciente paciente : p.listar()){
        //String frase = "Hola paciente " + paciente.getNombre() + " " + paciente.getApellido();
        //model.addAttribute("frase"+paciente.getId(), frase);
        //}
        if(p.listar().size() > 0){
            response = new ResponseEntity(p.listar(), HttpStatus.OK);

        } else response = new ResponseEntity(HttpStatus.NO_CONTENT);

        return response;
    }

    @PutMapping("/actualizar")
    public ResponseEntity<Paciente> actualizar(@RequestBody Paciente paciente){
        ResponseEntity response = null;

        if(p.buscar(paciente.getId()) != null){
            p.actualizar(paciente);
            Paciente p1 = p.buscar(paciente.getId());
            response = new ResponseEntity(p1, HttpStatus.OK);
        } else response = new ResponseEntity(HttpStatus.NOT_FOUND);


//        model.addAttribute("frase", p1.toString());

        return response;
    }

}
