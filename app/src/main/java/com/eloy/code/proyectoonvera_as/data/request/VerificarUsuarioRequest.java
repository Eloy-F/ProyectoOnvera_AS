package com.eloy.code.proyectoonvera_as.data.request;

import lombok.Data;

@Data
public class VerificarUsuarioRequest {
    private String correo;
    private String password;
}
