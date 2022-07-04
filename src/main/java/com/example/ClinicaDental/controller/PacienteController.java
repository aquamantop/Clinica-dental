package com.example.ClinicaDental.controller;

import com.example.ClinicaDental.entity.Paciente;
import com.example.ClinicaDental.exceptions.ResourceNotFoundException;
import com.example.ClinicaDental.service.PacienteService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    PacienteService pacienteService;

    public static final Logger logger = Logger.getLogger(PacienteController.class);

    @PostMapping("/guardar")
    public ResponseEntity<Paciente> guardar(@RequestBody Paciente paciente){
        ResponseEntity response = null;

        logger.debug("Guardando paciente...");
        if(paciente != null) {
            paciente.setFechaIngreso(LocalDate.now());
            response = new ResponseEntity(pacienteService.guardar(paciente), HttpStatus.OK);
            logger.info("Paciente guardado");
        } else {
            response = new ResponseEntity("Paciente nulo",HttpStatus.NOT_FOUND);
            logger.error("Paciente nulo");
        }

        return response;
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) throws ResourceNotFoundException {

        logger.debug("Eliminando paciente...");
        logger.info("Paciente eliminado");
        return ResponseEntity.ok(pacienteService.eliminar(id));

    }

    @GetMapping("/buscarEmail/{email}")
    public ResponseEntity<Paciente> buscarPorEmail(@PathVariable String email) {
        ResponseEntity response = null;

        logger.debug("Buscando paciente por email...");
        if(pacienteService.buscarPorEmail(email) != null){
            response = new ResponseEntity(pacienteService.buscarPorEmail(email), HttpStatus.OK);
            logger.info("Paciente encontrado");
        } else {
            response = new ResponseEntity("No se pudo encontrar paciente", HttpStatus.NOT_FOUND);
            logger.error("Error al buscar paciente por email");
        }

        return response;
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Paciente> buscarID(@PathVariable Long id) {
        ResponseEntity response = null;

        logger.debug("Buscando paciente por id...");
        if(pacienteService.buscar(id) != null){
            response = new ResponseEntity(pacienteService.buscar(id), HttpStatus.OK);
            logger.info("Paciente encontrado");
        } else {
            response = new ResponseEntity("No se pudo encontrar paciente", HttpStatus.NOT_FOUND);
            logger.error("Error al buscar paciente por id");
        }

        return response;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Paciente>> listarPacientes() {
        ResponseEntity response = null;

        logger.debug("Listando pacientes...");
        if(pacienteService.listar().size() > 0){
            response = new ResponseEntity(pacienteService.listar(), HttpStatus.OK);
            logger.info("Pacientes listados");
        } else {
            response = new ResponseEntity("No se pudo encontrar pacientes", HttpStatus.NOT_FOUND);
            logger.error("Error al listar pacientes");
        }

        return response;
    }

    @PutMapping("/actualizar")
    public ResponseEntity<Paciente> actualizar(@RequestBody Paciente paciente){
        ResponseEntity response = null;

        logger.debug("Actualizando paciente...");
        if(paciente != null){
            pacienteService.actualizar(paciente);
            response = new ResponseEntity(paciente, HttpStatus.OK);
            logger.info("Paciente con id: " + paciente.getId() + " actualizado");
        } else {
            response = new ResponseEntity("No se pudo actualizar paciente", HttpStatus.NOT_FOUND);
            logger.error("Error al actualizar paciente");
        }

        return response;
    }

}
