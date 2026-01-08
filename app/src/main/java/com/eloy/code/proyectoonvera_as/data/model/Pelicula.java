package com.eloy.code.proyectoonvera_as.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

import lombok.Data;

@Data
public class Pelicula {

    @SerializedName("id_pelicula")
    private int idPelicula;

    private String titulo;

    @SerializedName("id_categoria")
    private int idCategoria;

    @SerializedName("imagen_url")
    private String imagenUrl;

    private String descripcion;

    @SerializedName("fecha_creacion")
    private Date fechaCreacion;

    private String estado;

    // 🔥 ESTE ES EL IMPORTANTE
    @SerializedName("video_url")
    private String videoUrl;

    // Getters
    public int getIdPelicula() {
        return idPelicula;
    }
    public String getTitulo() {
        return titulo;
    }
    public int getIdCategoria() {
        return idCategoria;
    }
    public String getImagenUrl() {
        return imagenUrl;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public Date getFechaCreacion() {
        return fechaCreacion;
    }
    public String getEstado() {
        return estado;
    }
    public String getVideoUrl() {
        return videoUrl;
    }

    public Pelicula(int idPelicula, String titulo, String imagenUrl) {
        this.idPelicula = idPelicula;
        this.titulo = titulo;
        this.imagenUrl = imagenUrl;
    }
}

