package com.example.ClinicaDental.controller;

import com.example.ClinicaDental.entity.Turno;
import com.example.ClinicaDental.exceptions.ResourceNotFoundException;
import com.example.ClinicaDental.service.TurnoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnoController {

    @Autowired
    TurnoService turnoService;

    public static final Logger logger = Logger.getLogger(TurnoController.class);

    @PostMapping("/guardar")
    public ResponseEntity guardar(@RequestBody Turno t) {
        ResponseEntity response = null;

        logger.debug("Guardando turno...");
        if (t != null) {
            if(t.getFechaHora().isAfter(LocalDateTime.now())){
                response = new ResponseEntity(turnoService.guardar(t), HttpStatus.OK);
                logger.info("Turno guardado");
            } else logger.error("Elegir un fecha posterior a la fecha actual");
        } else {
            response = new ResponseEntity("No se guardo el turno", HttpStatus.FORBIDDEN);
            logger.error("Error al guardar turno");
        }

        return response;
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) throws ResourceNotFoundException {

        logger.debug("Eliminando turno...");
        logger.info("Turno eliminado");
        return ResponseEntity.ok(turnoService.eliminar(id));

    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity buscar(@PathVariable Long id){
        ResponseEntity response = null;

        logger.debug("Buscando turno...");
        if (turnoService.buscar(id) != null) {
            response = new ResponseEntity(turnoService.buscar(id), HttpStatus.OK);
            logger.info("Turno encontrado");
        } else {
            response = new ResponseEntity("No se encontró el turno", HttpStatus.NOT_FOUND);
            logger.error("Error al buscar turno");
        }

        return response;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Turno>> listar(){
        ResponseEntity response = null;

        logger.debug("Listando turnos...");
        if (turnoService.listar().size() > 0){
            response = new ResponseEntity(turnoService.listar(), HttpStatus.OK);
            logger.info("Turnos listados");
        } else {
            response = new ResponseEntity("No se encontraron turnos", HttpStatus.NOT_FOUND);
            logger.error("Error al listar turnos");
        }

        return response;
    }

    @PutMapping("/actualizar")
    public ResponseEntity actualizar(@RequestBody Turno t) {
        ResponseEntity response = null;

        logger.debug("Actualizando turno...");
        if (t != null) {
            response = new ResponseEntity(turnoService.actualizar(t), HttpStatus.OK);
            logger.info("Turno actualizado");
        } else {
            response = new ResponseEntity("No se encontró el turno", HttpStatus.NOT_FOUND);
            logger.error("Error al actualizar turno");
        }

        return response;
    }

}
