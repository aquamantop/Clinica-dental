package com.example.ClinicaDental;

import com.example.ClinicaDental.entity.Domicilio;
import com.example.ClinicaDental.entity.Paciente;
import com.example.ClinicaDental.service.OdontologoService;
import com.example.ClinicaDental.service.PacienteService;
import com.example.ClinicaDental.service.TurnoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ClinicaDentalApplicationTests {

    @Autowired
    PacienteService pacienteService;

    @Autowired
    OdontologoService odontologoService;

    @Autowired
    TurnoService turnoService;

	@Test
	void testCrearPaciente() {
        Domicilio domicilio = new Domicilio("Arena", 1010, "Buenos Aires", "Argentina");
        Paciente paciente = new Paciente("Messi", "Lionel", "lionel@gmail.com", 1010, LocalDate.of(2020, 10, 10), domicilio);

        pacienteService.guardar(paciente);

        assertNotNull(pacienteService.buscarPorEmail("lionel@gmail.com"));
	}

    @Test
    void testListarOdontologo() {
        assertNotNull(odontologoService.listar());
    }

    @Test
    void testCrearTurno() {
//        Domicilio domicilio = new Domicilio("Arena", 1010, "Buenos Aires", "Argentina");
//        Paciente paciente = new Paciente("Messi", "Lionel", "lionel@gmail.com", 1010, LocalDate.of(2020, 10, 10), domicilio);
//        Odontologo odontologo = new Odontologo("Maradona", "Diego", 6666);
//        domicilio.setId(2L);
//        paciente.setId(1L);
//        odontologo.setId(1L);
//        Turno turno = new Turno(odontologo, paciente, LocalDateTime.of(2022,01,01,10,10));
//
//        turnoService.guardar(turno);

        assertNotNull(turnoService.listar());
    }


}
