package com.example.ClinicaDental.controller;

import com.example.ClinicaDental.entity.Odontologo;
import com.example.ClinicaDental.service.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/odontologos")
public class OdontologoController {

    @Autowired
    OdontologoService odontologoService;

    @PostMapping("/guardar")
    public ResponseEntity<Odontologo> guardar(@RequestBody Odontologo odontologo){
        ResponseEntity<Odontologo> response = null;

        if(odontologo != null){
            response = new ResponseEntity(odontologoService.guardar(odontologo).toString(), HttpStatus.CREATED);
        } else response = new ResponseEntity("No se pudo guardar odontologo", HttpStatus.NOT_FOUND);

        return response;
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Odontologo> eliminar(@PathVariable Long id){
        ResponseEntity response = null;

        if(odontologoService.buscar(id).isPresent()){
            response = new ResponseEntity(odontologoService.eliminar(id), HttpStatus.NO_CONTENT);
        } else response = new ResponseEntity("No se pudo eliminar odontologo", HttpStatus.NOT_FOUND);

        return response;
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Odontologo> buscarID(@PathVariable Long id) {
        ResponseEntity response = null;

        if(id > 0){
            Odontologo odontologo = odontologoService.buscar(id).get();
            response = new ResponseEntity(odontologo.toString(), HttpStatus.OK);
        } else response = new ResponseEntity("No se pudo encontrar odontologo", HttpStatus.NOT_FOUND);

        return response;
    }

    @GetMapping("/listar")
    public ResponseEntity<Odontologo> listarOdontologos() {
        ResponseEntity response = null;

        if(odontologoService.listar().size() > 0){
            response = new ResponseEntity(odontologoService.listar().toString(), HttpStatus.OK);
        } else response = new ResponseEntity("No se pudo encontrar odontologos", HttpStatus.NOT_FOUND);

        return response;
    }

    @PutMapping("/actualizar")
    public ResponseEntity<Odontologo> actualizar(Model model, @RequestBody Odontologo odontologo){
        ResponseEntity response = null;

        if(odontologo != null){
            response = new ResponseEntity(odontologoService.actualizar(odontologo).toString(), HttpStatus.ACCEPTED);
        } else response = new ResponseEntity("No se pudo actualizar odontologo", HttpStatus.NOT_FOUND);

        return response;
    }

}
