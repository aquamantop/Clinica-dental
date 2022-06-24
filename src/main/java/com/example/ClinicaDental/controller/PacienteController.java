package com.example.ClinicaDental.controller;

import com.example.ClinicaDental.entity.Paciente;
import com.example.ClinicaDental.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    PacienteService p;

    @PostMapping("/guardar")
    public ResponseEntity<Paciente> guardar(@RequestBody Paciente paciente){
        ResponseEntity response = null;

        if(paciente != null) {
            response = new ResponseEntity(p.guardar(paciente).toString(), HttpStatus.OK);
        } else response = new ResponseEntity("No se pudo guardar paciente",HttpStatus.NOT_FOUND);

        return response;
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Paciente> eliminar(@PathVariable Long id){
        ResponseEntity response = null;

        if(p.buscar(id).isPresent()){
            response = new ResponseEntity(p.eliminar(id), HttpStatus.OK);
        } else response = new ResponseEntity("No se pudo eliminar paciente", HttpStatus.NOT_FOUND);

        return response;
    }

    @GetMapping("/buscarEmail/{email}")
    public ResponseEntity<Paciente> buscarPorEmail(@PathVariable String email) {
        return null;
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Paciente> buscarID(@PathVariable Long id) {
        ResponseEntity response = null;

        if(id > 0 && p.buscar(id).isPresent()){
            response = new ResponseEntity(p.buscar(id), HttpStatus.OK);
        } else response = new ResponseEntity("No se pudo encontrar paciente", HttpStatus.NOT_FOUND);

        return response;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Paciente>> listarPacientes() {
        ResponseEntity response = null;

        if(p.listar().size() > 0){
            response = new ResponseEntity(p.listar().toString(), HttpStatus.OK);
        } else response = new ResponseEntity("No se pudo encontrar pacientes", HttpStatus.NOT_FOUND);

        return response;
    }

    @PutMapping("/actualizar")
    public ResponseEntity<Paciente> actualizar(@RequestBody Paciente paciente){
        ResponseEntity response = null;

        if(p.buscar(paciente.getId()).isPresent()){
            p.actualizar(paciente);
            Optional<Paciente> p1 = p.buscar(paciente.getId());
            response = new ResponseEntity(p1.toString(), HttpStatus.OK);
        } else response = new ResponseEntity("No se pudo actualizar paciente", HttpStatus.NOT_FOUND);

        return response;
    }

}
