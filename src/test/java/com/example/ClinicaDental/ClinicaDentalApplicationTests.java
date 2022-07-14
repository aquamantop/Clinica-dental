package com.example.ClinicaDental;

import com.example.ClinicaDental.entity.Domicilio;
import com.example.ClinicaDental.entity.Odontologo;
import com.example.ClinicaDental.entity.Paciente;
import com.example.ClinicaDental.entity.Turno;
import com.example.ClinicaDental.service.OdontologoService;
import com.example.ClinicaDental.service.PacienteService;
import com.example.ClinicaDental.service.TurnoService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ClinicaDentalApplicationTests {

    // Inyectamos dependencias
    @Autowired
    PacienteService pacienteService;
    @Autowired
    OdontologoService odontologoService;
    @Autowired
    TurnoService turnoService;
    @Autowired
    MockMvc mockMvc;

    // Creamos datos para utilizar en los test
    Domicilio domicilio = new Domicilio("Arena", 1010, "Buenos Aires", "Argentina");
    Paciente paciente = new Paciente("Messi", "Lionel", "lionel@gmail.com", 1010, LocalDate.of(2020, 10, 10), domicilio);
    Odontologo odontologo = new Odontologo("Gallardo", "Marcelo", 1);

    // Test Unitario Paciente
    @Transactional
	@Test
    @Order(1)
	void testGuardarPaciente() {
        assertNotNull(pacienteService.guardar(paciente));
        assertNotNull(pacienteService.buscarPorEmail("lionel@gmail.com"));
	}

    // Test Unitario Odontologo
    @Transactional
    @Test
    @Order(2)
    void testGuardarOdontologo() {
        assertNotNull(odontologoService.guardar(odontologo));
        assertNotNull(odontologoService.buscar(odontologo.getId()));
    }

    // Test Unitario Turno
    @Transactional
    @Test
    @Order(3)
    void testCrearYBuscarTurno() {
        Domicilio domicilio = new Domicilio("Arena", 1010, "Buenos Aires", "Argentina");
        Paciente paciente = new Paciente("Messi", "Lionel", "lionel@gmail.com", 1010, LocalDate.of(2020, 10, 10), domicilio);
        Odontologo odontologo = new Odontologo("Gallardo", "Marcelo", 1);
        Turno turno = new Turno(odontologo, paciente, LocalDateTime.of(2024,01,01,10,10));
        pacienteService.guardar(paciente);
        odontologoService.guardar(odontologo);
        assertNotNull(turnoService.guardar(turno));
        assertNotNull(turnoService.buscar(turno.getId()));
    }

    // Test de integracion listando turnos
    @Test
    @Order(4)
    @WithMockUser
    void buscarTurnosTestIntegracion() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/turnos/listar")
                        .secure(true)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


}
