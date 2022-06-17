package com.example.ClinicaDental.controller;

import com.example.ClinicaDental.dto.OdontologoDTO;
import com.example.ClinicaDental.entity.Odontologo;
import com.example.ClinicaDental.service.OdontologoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/odontologos")
public class OdontologoController {

    @Autowired
    OdontologoService o;

    @Autowired
    ObjectMapper mapper;

    @PostMapping("/guardar")
    public ResponseEntity<Odontologo> guardar(@RequestBody Odontologo odontologo){
        ResponseEntity<Odontologo> response = null;
        OdontologoDTO dto = mapper.convertValue(odontologo, OdontologoDTO.class);

        o.guardar(odontologo);

        if(odontologo != null){
            response = new ResponseEntity(dto, HttpStatus.OK);
        } else response = new ResponseEntity("No se pudo guardar odontologo", HttpStatus.NOT_FOUND);

        return response;
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Odontologo> eliminar(@PathVariable int id){
        ResponseEntity response = null;
        OdontologoDTO dto = mapper.convertValue(o.buscar(id), OdontologoDTO.class);

        o.eliminar(id);

        if(o.buscar(id) != null){
            response = new ResponseEntity(dto, HttpStatus.OK);
        } else response = new ResponseEntity("No se pudo eliminar odontologo", HttpStatus.NOT_FOUND);

        return response;
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Odontologo> buscarID(@PathVariable int id) {
        ResponseEntity response = null;
        OdontologoDTO dto = mapper.convertValue(o.buscar(id), OdontologoDTO.class);

        o.buscar(id);

        if(id > 0 && o.buscar(id) != null){
            response = new ResponseEntity(dto, HttpStatus.OK);;
        } else response = new ResponseEntity("No se pudo encontrar odontologo", HttpStatus.NOT_FOUND);

        return response;
    }

    @GetMapping("/listar")
    public ResponseEntity<Odontologo> listarOdontologos() {
        ResponseEntity response = null;

        o.listar();

        if(o.listar().size() > 0){
            for (Odontologo odontologo : o.listar()){
                OdontologoDTO dto = mapper.convertValue(odontologo, OdontologoDTO.class);
                response = new ResponseEntity(dto, HttpStatus.OK);
            }
        } else response = new ResponseEntity("No se pudo encontrar odontologos", HttpStatus.NOT_FOUND);

        return response;
    }

    @PutMapping("/actualizar")
    public ResponseEntity<Odontologo> actualizar(Model model, @RequestBody Odontologo odontologo){
        ResponseEntity response = null;
        OdontologoDTO dto = mapper.convertValue(odontologo, OdontologoDTO.class);

        o.actualizar(odontologo);

        if(odontologo != null){
            response = new ResponseEntity(dto, HttpStatus.OK);
        } else response = new ResponseEntity("No se pudo actualizar odontologo", HttpStatus.NOT_FOUND);

        return response;
    }

}
