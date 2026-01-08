package com.eloy.code.proyectoonvera_as.data.api;
import com.eloy.code.proyectoonvera_as.data.model.Pelicula;
import com.eloy.code.proyectoonvera_as.data.common.BaseResponse;
import java.util.List;

import retrofit2.http.GET;
import retrofit2.Call;
import retrofit2.http.Path;

public interface PeliculaApi {
   @GET("peliculas")
    Call<BaseResponse<List<Pelicula>>> listarPeliculas();

    // Películas por tipo: ADULTOS | NIÑOS
    @GET("peliculas/tipo/{tipo}")
    Call<BaseResponse<List<Pelicula>>> listarPeliculasPorTipo(
            @Path("tipo") String tipo);

}

