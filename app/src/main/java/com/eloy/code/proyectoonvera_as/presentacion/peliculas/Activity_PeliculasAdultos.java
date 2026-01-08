package com.eloy.code.proyectoonvera_as.presentacion.peliculas;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.eloy.code.proyectoonvera_as.data.model.Pelicula;
import com.eloy.code.proyectoonvera_as.data.api.PeliculaApi;
import com.eloy.code.proyectoonvera_as.data.api.RetrofitClient;
import com.eloy.code.proyectoonvera_as.data.common.BaseResponse;

import com.eloy.code.proyectoonvera_as.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Activity_PeliculasAdultos extends AppCompatActivity {
    private RecyclerView rvPeliculas;
    private PeliculaAdultoAdapter adapter;
    private List<Pelicula> peliculas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peliculas_adultos);

        rvPeliculas = findViewById(R.id.rvPeliculas);//call xml
        rvPeliculas.setLayoutManager(new GridLayoutManager(this, 2));
        rvPeliculas.setHasFixedSize(true);
        rvPeliculas.setItemViewCacheSize(20);

      cargarPeliculasAdultos();
    }
    private void cargarPeliculasAdultos() {
        PeliculaApi api = RetrofitClient
                .getRetrofit(this)
                .create(PeliculaApi.class);

        api.listarPeliculasPorTipo("ADULTOS")
                .enqueue(new Callback<BaseResponse<List<Pelicula>>>() {
                    @Override
                    public void onResponse(
                            Call<BaseResponse<List<Pelicula>>> call,
                            Response<BaseResponse<List<Pelicula>>> response) {

                        if (response.isSuccessful()
                                && response.body() != null
                                && response.body().isSuccess()) {
                            List<Pelicula> peliculas = response.body().getData();
                            adapter = new PeliculaAdultoAdapter(Activity_PeliculasAdultos.this,
                                    response.body().getData());

                            rvPeliculas.setAdapter(adapter);
                        }
                    }
                    @Override
                    public void onFailure(Call<BaseResponse<List<Pelicula>>> call,Throwable t) {
                        Log.e("PELIS_ERROR", "Error al cargar peliculas ", t);
                    }
                });
    }


}