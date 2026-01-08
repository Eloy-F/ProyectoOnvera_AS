package com.eloy.code.proyectoonvera_as.data.api;

import com.eloy.code.proyectoonvera_as.data.common.BaseResponse;
import com.eloy.code.proyectoonvera_as.data.model.Categoria;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CategoriaApi {
    @GET("api/v1/categorias")
    Call<BaseResponse<List<Categoria>>> listarCategorias();

}
