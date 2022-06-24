package com.example.ClinicaDental.controller;

import com.example.ClinicaDental.entity.Turno;
import com.example.ClinicaDental.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/turnos")
public class TurnoController {

    @Autowired
    TurnoService turnoService;

    @PostMapping("/guardar")
    public ResponseEntity guardar(@RequestBody Turno t) {
        ResponseEntity response = null;

        Turno turno = turnoService.guardar(t);

        if (turno != null) {
            response = new ResponseEntity(turno.toString(), HttpStatus.OK);
        } else response = new ResponseEntity("No se guardo el turno", HttpStatus.NOT_FOUND);

        return response;
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Turno> eliminar(@PathVariable Long id){
        ResponseEntity response = null;

        if(turnoService.buscar(id).isPresent()){
            response = new ResponseEntity(turnoService.eliminar(id), HttpStatus.NO_CONTENT);
        } else response = new ResponseEntity("No se pudo eliminar odontologo", HttpStatus.NOT_FOUND);

        return response;
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity buscar(@PathVariable Long id){
        ResponseEntity response = null;

        if (id > 0) {
            Turno turno = turnoService.buscar(id).get();
            response = new ResponseEntity(turno.toString(), HttpStatus.OK);
        } else response = new ResponseEntity("No se encontró el turno", HttpStatus.NOT_FOUND);

        return response;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Turno>> listar(){
        ResponseEntity response = null;
        if (turnoService.listar().size() > 0){
            response = new ResponseEntity(turnoService.listar().toString(), HttpStatus.OK);
        } else response = new ResponseEntity(HttpStatus.NOT_FOUND);

        return response;
    }

    @PutMapping("/actualizar")
    public ResponseEntity actualizar(@RequestBody Turno t) {
        ResponseEntity response = null;

        if (t != null) {
            turnoService.actualizar(t);
            response = new ResponseEntity(t.toString(), HttpStatus.OK);
        } else response = new ResponseEntity("No se encontró el turno", HttpStatus.NOT_FOUND);

        return response;
    }

}
