package com.eloy.code.proyectoonvera_as.data.model;

import java.util.Date;

import lombok.Data;

@Data
public class RecuperacionContrasena {
    private int idSolicitud;
    private int idUsuario;
    private String token;
    private Date fechaSolicitud;
    private Date fechaExpiracion;
    private String estado;
}
