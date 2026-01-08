package com.eloy.code.proyectoonvera_as.data.request;

public class RegistroUsuarioRequest {
    private String nombres;
    private String apellidos;
    private String correo;
    private String password;
    private String celular;

    // 🔹 Constructor
    public RegistroUsuarioRequest() {}

    // 🔹 Setters
    public void setNombres(String nombres) { this.nombres = nombres; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }
    public void setCorreo(String correo) { this.correo = correo; }
    public void setPassword(String password) { this.password = password; }
    public void setCelular(String celular) { this.celular = celular; }

    // Getters
    public String getNombres() { return nombres; }
    public String getApellidos() { return apellidos; }
    public String getCorreo() { return correo; }
    public String getPassword() { return password; }
    public String getCelular() { return celular; }
/*
    public RegistroUsuarioRequest(String nombres,
            String apellidos,String correo,String password,String celular)
    {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.correo = correo;
        this.password = password;
        this.celular = celular;
    }

    // Getters
    public String getNombres() { return nombres; }
    public String getApellidos() { return apellidos; }
    public String getCorreo() { return correo; }
    public String getPassword() { return password; }
    public String getCelular() { return celular; }*/

}
