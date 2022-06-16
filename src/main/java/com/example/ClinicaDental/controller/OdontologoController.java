package com.example.ClinicaDental.controller;

import com.example.ClinicaDental.entity.Odontologo;
import com.example.ClinicaDental.service.OdontologoService;
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

    @PostMapping("/guardar")
    public ResponseEntity<Odontologo> guardar(@RequestBody Odontologo odontologo){
        ResponseEntity<Odontologo> response = null;

        if(odontologo != null){
            response = new ResponseEntity(o.guardar(odontologo).toString(), HttpStatus.OK);
        } else response = new ResponseEntity("No se pudo guardar odontologo", HttpStatus.NOT_FOUND);

        //model.addAttribute("frase", odontologo.toString());
        return response;
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Odontologo> eliminar(@PathVariable int id){
        ResponseEntity response = null;

        if(o.buscar(id) != null){
            response = new ResponseEntity(o.eliminar(id).toString(), HttpStatus.OK);
        } else response = new ResponseEntity("No se pudo eliminar odontologo", HttpStatus.NOT_FOUND);
        //model.addAttribute("frase", frase);

        return response;
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Odontologo> buscarID(@PathVariable int id) {
        ResponseEntity response = null;

        if(id > 0 && o.buscar(id) != null){
            response = new ResponseEntity(o.buscar(id).toString(), HttpStatus.OK);
        } else response = new ResponseEntity("No se pudo encontrar odontologo", HttpStatus.NOT_FOUND);
        //model.addAttribute("frase", frase);

        return response;
    }

    @GetMapping("/listar")
    public ResponseEntity<Odontologo> listarOdontologos() {
        ResponseEntity response = null;

        if(o.listar().size() > 0){
            for (Odontologo odontologo : o.listar()){
                response = new ResponseEntity(o.listar().toString(), HttpStatus.OK);
                //model.addAttribute("frase"+odontologo.getId(), frase);
            }
        } else response = new ResponseEntity("No se pudo encontrar odontologos", HttpStatus.NOT_FOUND);

        return response;
    }

    @PutMapping("/actualizar")
    public ResponseEntity<Odontologo> actualizar(Model model, @RequestBody Odontologo odontologo){
        ResponseEntity response = null;

        if(odontologo != null){
            response = new ResponseEntity(o.actualizar(odontologo).toString(), HttpStatus.OK);
        } else response = new ResponseEntity("No se pudo actualizar odontologo", HttpStatus.NOT_FOUND);
        //model.addAttribute("frase", o1.toString());

        return response;
    }

}
