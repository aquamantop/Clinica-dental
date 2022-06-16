package com.example.ClinicaDental.service;

import com.example.ClinicaDental.model.Domicilio;
import com.example.ClinicaDental.model.Odontologo;
import com.example.ClinicaDental.model.Paciente;
import com.example.ClinicaDental.model.Turno;
import com.example.ClinicaDental.repository.ITurnoService;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TurnoService implements ITurnoService {

    private List<Turno> turnos;

    public void generarTurnos(){
        if(turnos == null) {
            turnos = new ArrayList<>();
            Odontologo julieth = new Odontologo("Julieth", "Ruiz", 5959);
            julieth.setId(1);
            Domicilio d1 = new Domicilio("Pellegrini", 123, "Rosario", "Santa Fe");
            d1.setId(1);
            Paciente justo = new Paciente("Marelli", "Justo", "justo@dh.com", 12354678, LocalDate.of(2020, 1, 1), d1, julieth);
            justo.setId(1);
            Odontologo franco = new Odontologo("Franco", "Rampazzo", 8989);
            franco.setId(2);
            Domicilio d2 = new Domicilio("Callao", 222, "Rosario", "Santa Fe");
            d2.setId(2);
            Paciente gabi = new Paciente("Gabi", "Mateo", "gabi@dh.com", 12354678, LocalDate.of(2022, 1, 1), d2, julieth);
            gabi.setId(2);

            this.turnos.add(new Turno(1, julieth, justo, LocalDateTime.now()));
            this.turnos.add(new Turno(2, franco, gabi, LocalDateTime.now()));
        } else System.out.println("Turno generados");

    }

    public List<Turno> mostrarTurnos() {
        return this.turnos;
    }

    public List<Turno> getTurnos() {
        return turnos;
    }

    public void setTurnos(List<Turno> turnos) {
        this.turnos = turnos;
    }

    @Override
    public Turno eliminar(int id){
        Turno turnoEliminado = null;
        for (int i = 0; i < turnos.size(); i++) {
            Turno turno = turnos.get(i);
            turnoEliminado = turno;
            if (turno.getId() == id) {
                turnos.remove(i);
            }
        }
        return turnoEliminado;
    }


    @Override
    public Turno buscar(int id){
        Turno t = null;
        for (Turno turno:turnos) {
            if (turno.getId() == id) {
                t = turno;
            }
        }
        return t;
    }

    @Override
    public Turno guardar(Turno turno) {

        if(turno.getFechaHora() != null
                && turno.getOdontologo() != null
                && turno.getPaciente() != null) {
            for (Turno t: turnos){
                if(t.getPaciente().getId() == turno.getPaciente().getId()){
                    turno.setPaciente(t.getPaciente());
                }
            }
            for (Turno t: turnos){
                if(t.getOdontologo().getId() == turno.getOdontologo().getId()){
                    turno.setOdontologo(t.getOdontologo());
                }
            }
            turnos.add(turno);
            return turno;
        } else return null;

    }

    @Override
    public Turno actualizar(Turno turno){
        Turno turnoModificado = null;

        if (turno.getId() > 0) {
            int id = turno.getId();
            for (int i = 0; i < turnos.size(); i++) {
                if (turnos.get(i).getId() == id) {
                    turnoModificado = turnos.get(i);
                    Turno turnoViejo = turnos.get(i);

                    if (turno.getFechaHora() != null) {
                        turnoModificado.setFechaHora(turno.getFechaHora());
                    } else turnoModificado.setFechaHora(turnoViejo.getFechaHora());

                    if (turno.getOdontologo() != null) {
                        turnoModificado.setOdontologo(turno.getOdontologo());
                    } else turnoModificado.setOdontologo(turnoViejo.getOdontologo());

                    if (turno.getPaciente() != null) {
                        turnoModificado.setPaciente(turno.getPaciente());
                    } else turnoModificado.setPaciente(turnoViejo.getPaciente());

                    turnos.set(i, turnoModificado);
                }
            }
        }
        return turnoModificado;
    }

}
