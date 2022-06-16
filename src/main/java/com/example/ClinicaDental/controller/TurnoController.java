package com.example.ClinicaDental.controller;

import com.example.ClinicaDental.entity.Turno;
import com.example.ClinicaDental.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/turnos")
public class TurnoController {

    @Autowired
    public TurnoService turnoService;

    @PostMapping("/guardar")
    public ResponseEntity guardar(@RequestBody Turno t) {
        ResponseEntity response = null;
        if (turnoService.getTurnos() == null){
            turnoService.generarTurnos();
        }

        Turno turno = turnoService.guardar(t);

        if (turno != null) {
            response = new ResponseEntity(turno.toString(), HttpStatus.OK);
        } else response = new ResponseEntity("No se guardo el turno", HttpStatus.NOT_FOUND);

        return response;
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity eliminar(@PathVariable int id){
        ResponseEntity response = null;
        if (turnoService.getTurnos() == null){
            turnoService.generarTurnos();
        }

        Turno turno = turnoService.eliminar(id);

        if (turno != null) {
            response = new ResponseEntity(turno.toString(), HttpStatus.OK);
        } else response = new ResponseEntity("No se encontró el turno", HttpStatus.NOT_FOUND);

        return response;
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity buscar(@PathVariable int id){
        ResponseEntity response = null;
        if (turnoService.getTurnos() == null){
            turnoService.generarTurnos();
        }

        Turno turno = turnoService.buscar(id);

        if (turno != null) {
            response = new ResponseEntity(turno.toString(), HttpStatus.OK);
        } else response = new ResponseEntity("No se encontró el turno", HttpStatus.NOT_FOUND);

        return response;
    }

    @PutMapping("/actualizar")
    public ResponseEntity actualizar(@RequestBody Turno t) {
        ResponseEntity response = null;
        if (turnoService.getTurnos() == null){
            turnoService.generarTurnos();
        }

        Turno turno = turnoService.actualizar(t);

        if (turno != null) {
            response = new ResponseEntity(turno.toString(), HttpStatus.OK);
        } else response = new ResponseEntity("No se encontró el turno", HttpStatus.NOT_FOUND);

        return response;
    }

    @GetMapping("/listar")
    public ResponseEntity listar(){
        ResponseEntity response = null;
        if (turnoService.getTurnos() == null){
            turnoService.generarTurnos();
            response = new ResponseEntity(turnoService.listar().toString(), HttpStatus.OK);
        } else response = new ResponseEntity(HttpStatus.NOT_FOUND);

        return response;
    }

}
