package com.example.ClinicaDental.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "domicilios")
public class Domicilio {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String calle;
    private int numero;
    private String localidad;
    private String provincia;

    public Domicilio(String calle, int numero, String localidad, String provincia) {
        this.calle = calle;
        this.numero = numero;
        this.localidad = localidad;
        this.provincia = provincia;
    }

    public Domicilio() {}

    @Override
    public String toString() {
        return "--Domicilio--" +
                "\nCalle: " + calle + "/" +
                "\nNumero: " + numero + "/" +
                "\nLocalidad: " + localidad + "/" +
                "\nProvincia: " + provincia;
    }
}
