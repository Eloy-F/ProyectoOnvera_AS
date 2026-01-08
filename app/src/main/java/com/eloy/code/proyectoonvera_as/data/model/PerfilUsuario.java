package com.eloy.code.proyectoonvera_as.data.model;

import java.util.Date;

import lombok.Data;

@Data
public class PerfilUsuario {
    private String nombres;
    private String apellidos;
    private String correo;
    private String fotoUrl;
}
