package com.example.ClinicaDental.controller;

import com.example.ClinicaDental.dto.PacienteDTO;
import com.example.ClinicaDental.entity.Paciente;
import com.example.ClinicaDental.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    PacienteService pacienteService;

    @PostMapping("/guardar")
    public ResponseEntity<Paciente> guardar(@RequestBody Paciente paciente){
        ResponseEntity response = null;

        if(paciente != null) {
            response = new ResponseEntity(pacienteService.guardar(paciente), HttpStatus.OK);
        } else response = new ResponseEntity("No se pudo guardar paciente",HttpStatus.NOT_FOUND);

        return response;
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Paciente> eliminar(@PathVariable Long id){
        ResponseEntity response = null;

        if(pacienteService.buscar(id).isPresent()){
            response = new ResponseEntity(pacienteService.eliminar(id), HttpStatus.OK);
        } else response = new ResponseEntity("No se pudo eliminar paciente", HttpStatus.NOT_FOUND);

        return response;
    }

    @GetMapping("/buscarEmail/{email}")
    public ResponseEntity<Paciente> buscarPorEmail(@PathVariable String email) {
        ResponseEntity response = null;

        if(email != null){
            Optional<Paciente> paciente = pacienteService.buscarPorEmail(email);
            response = new ResponseEntity(paciente, HttpStatus.OK);
        } else response = new ResponseEntity("No se pudo encontrar paciente", HttpStatus.NOT_FOUND);

        return response;
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<PacienteDTO> buscarID(@PathVariable Long id) {
        ResponseEntity response = null;

        if(pacienteService.buscar(id).isPresent()){
            Optional<Paciente> paciente = pacienteService.buscar(id);
            response = new ResponseEntity(paciente, HttpStatus.OK);
        } else response = new ResponseEntity("No se pudo encontrar paciente", HttpStatus.NOT_FOUND);

        return response;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Paciente>> listarPacientes() {
        ResponseEntity response = null;

        if(pacienteService.listar().size() > 0){
            response = new ResponseEntity(pacienteService.listar().toString(), HttpStatus.OK);
        } else response = new ResponseEntity("No se pudo encontrar pacientes", HttpStatus.NOT_FOUND);

        return response;
    }

    @PutMapping("/actualizar")
    public ResponseEntity<Paciente> actualizar(@RequestBody Paciente paciente){
        ResponseEntity response = null;

        if(paciente != null){
            pacienteService.actualizar(paciente);
            response = new ResponseEntity(paciente, HttpStatus.OK);
        } else response = new ResponseEntity("No se pudo actualizar paciente", HttpStatus.NOT_FOUND);

        return response;
    }

}
