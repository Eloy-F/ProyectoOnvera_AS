package com.eloy.code.proyectoonvera_as.data.model;

import java.util.Date;

import lombok.Data;

@Data
public class Autor {
    private int idAutor;
    private String nombres;
    private String apellidos;
    private String nacionalidad;
    private Date fechaNacimiento;
    private String estado;
}
