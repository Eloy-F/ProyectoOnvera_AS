package com.eloy.code.proyectoonvera_as.presentacion.peliculas;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.eloy.code.proyectoonvera_as.data.api.PeliculaApi;
import com.eloy.code.proyectoonvera_as.data.api.RetrofitClient;
import com.eloy.code.proyectoonvera_as.data.common.BaseResponse;
import com.eloy.code.proyectoonvera_as.data.model.Pelicula;
import com.eloy.code.proyectoonvera_as.presentacion.peliculas.PeliculaAdultoAdapter;


import com.eloy.code.proyectoonvera_as.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Activity_PeliculasNinos extends AppCompatActivity {
    private RecyclerView rvPeliculas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peliculasninos);

        rvPeliculas = findViewById(R.id.rvPeliculasN);//N de niños
        rvPeliculas.setLayoutManager(new GridLayoutManager(this, 2));
        rvPeliculas.setHasFixedSize(true);
        Button btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());
        cargarPeliculasNinos();
    }

    private void cargarPeliculasNinos() {
        PeliculaApi api = RetrofitClient.getRetrofit(this).create(PeliculaApi.class);
        api.listarPeliculasPorTipo("NIÑOS").enqueue(new Callback<BaseResponse<List<Pelicula>>>() {

          @Override
          public void onResponse(Call<BaseResponse<List<Pelicula>>> call,
                          Response<BaseResponse<List<Pelicula>>> response) {

             if (response.isSuccessful() && response.body() != null
                 && response.body().isSuccess()) {
                  PeliculaAdultoAdapter adapter = new PeliculaAdultoAdapter(Activity_PeliculasNinos.this,
                  response.body().getData());

                 rvPeliculas.setAdapter(adapter);
                        }
                    }

              @Override
              public void onFailure(Call<BaseResponse<List<Pelicula>>> call,
                     Throwable t) {Log.e("PELIS_NINOS", t.getMessage());
                    }
                });
    }
}