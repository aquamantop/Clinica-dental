package com.example.ClinicaDental.controller;

import com.example.ClinicaDental.entity.Odontologo;
import com.example.ClinicaDental.exceptions.ResourceNotFoundException;
import com.example.ClinicaDental.service.OdontologoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {

    @Autowired
    OdontologoService odontologoService;

    public static final Logger logger = Logger.getLogger(OdontologoController.class);

    @PostMapping("/guardar")
    public ResponseEntity<Odontologo> guardar(@RequestBody Odontologo odontologo){
        ResponseEntity<Odontologo> response = null;

        logger.debug("Guardando odontologo...");
        if(odontologo != null){
            response = new ResponseEntity(odontologoService.guardar(odontologo), HttpStatus.CREATED);
            logger.info("Odontologo guardado");
        } else {
            response = new ResponseEntity("No se pudo guardar odontologo", HttpStatus.NOT_FOUND);
            logger.error("Error al guardar odontologo");
        }

        return response;
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) throws ResourceNotFoundException {

        logger.debug("Eliminando odontologo...");
        logger.info("Odontologo eliminado");
        return ResponseEntity.ok(odontologoService.eliminar(id));

    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Odontologo> buscarID(@PathVariable Long id) {
        ResponseEntity response = null;

        logger.debug("Buscando odontologo...");
        if(odontologoService.buscar(id) != null){
            response = new ResponseEntity(odontologoService.buscar(id), HttpStatus.OK);
            logger.info("Odontologo encontrado");
        } else {
            response = new ResponseEntity("No se pudo encontrar odontologo", HttpStatus.NOT_FOUND);
            logger.error("Error al buscar odontologo");
        }

        return response;
    }

    @GetMapping("/listar")
    public ResponseEntity<Odontologo> listarOdontologos() {
        ResponseEntity response = null;

        logger.debug("Listando odontologos...");
        if(odontologoService.listar().size() > 0){
            response = new ResponseEntity(odontologoService.listar(), HttpStatus.OK);
            logger.info("Odontologos listados");
        } else {
            response = new ResponseEntity("No se pudo encontrar odontologos", HttpStatus.NOT_FOUND);
            logger.error("Error al listar odontologos");
        }

        return response;
    }

    @PutMapping("/actualizar")
    public ResponseEntity<Odontologo> actualizar(Model model, @RequestBody Odontologo odontologo){
        ResponseEntity response = null;

        logger.debug("Actualizando odontologo...");
        if(odontologo != null){
            response = new ResponseEntity(odontologoService.actualizar(odontologo), HttpStatus.ACCEPTED);
            logger.info("Odontologo actualizado");
        } else {
            response = new ResponseEntity("No se pudo actualizar odontologo", HttpStatus.NOT_FOUND);
            logger.error("Error al actualizar odontologo");
        }

        return response;
    }

}
