package com.example.ClinicaDental;

import com.example.ClinicaDental.entity.Domicilio;
import com.example.ClinicaDental.entity.Odontologo;
import com.example.ClinicaDental.entity.Paciente;
import com.example.ClinicaDental.entity.Turno;
import com.example.ClinicaDental.exceptions.ResourceNotFoundException;
import com.example.ClinicaDental.service.OdontologoService;
import com.example.ClinicaDental.service.PacienteService;
import com.example.ClinicaDental.service.TurnoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ClinicaDentalApplicationTests {

    @Autowired
    PacienteService pacienteService;

    @Autowired
    OdontologoService odontologoService;

    @Autowired
    TurnoService turnoService;

    @Transactional
	@Test
	void testCrearPaciente() {
        Domicilio domicilio = new Domicilio("Arena", 1010, "Buenos Aires", "Argentina");
        Paciente paciente = new Paciente("Messi", "Lionel", "lionel@gmail.com", 1010, LocalDate.of(2020, 10, 10), domicilio);

        assertNotNull(pacienteService.guardar(paciente));

        assertNotNull(pacienteService.buscarPorEmail("lionel@gmail.com"));
	}

    @Transactional
    @Test
    void testBorrarPaciente() throws ResourceNotFoundException {
        Paciente paciente = pacienteService.buscarPorEmail("lionel@gmail.com");

        assertNull(pacienteService.eliminar(paciente.getId()));
    }

    @Transactional
    @Test
    void testListarOdontologo() {
        Odontologo odontologo = new Odontologo("Gallardo", "Marcelo", 1);

        assertNotNull(odontologoService.guardar(odontologo));

        assertNotNull(odontologoService.listar());
    }

    @Transactional
    @Test
    void testCrearYBuscarTurno() {
        Domicilio domicilio = new Domicilio("Arena", 1010, "Buenos Aires", "Argentina");
        Paciente paciente = new Paciente("Messi", "Lionel", "lionel@gmail.com", 1010, LocalDate.of(2020, 10, 10), domicilio);
        Odontologo odontologo = new Odontologo("Maradona", "Diego", 6666);

        Turno turno = new Turno(odontologo, paciente, LocalDateTime.of(2022,01,01,10,10));

        assertNotNull(turnoService.guardar(turno));

        assertNotNull(turnoService.buscar(turno.getId()));
    }


}
