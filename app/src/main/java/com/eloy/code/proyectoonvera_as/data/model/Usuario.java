package com.eloy.code.proyectoonvera_as.data.model;

import java.util.Date;

import lombok.Data;

@Data
public class Usuario {
    private int idUsuario;
    private String nombres;
    private String apellidos;
    private String correo;
    private String password;
    private String celular;
    private Date fechaNacimiento;
    private Date fechaCreacion;
    private String estado;


}
