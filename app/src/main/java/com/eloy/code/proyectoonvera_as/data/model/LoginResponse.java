package com.eloy.code.proyectoonvera_as.data.model;


import lombok.Data;

@Data
public class LoginResponse {
        private Usuario usuario;
        private String token;

}
