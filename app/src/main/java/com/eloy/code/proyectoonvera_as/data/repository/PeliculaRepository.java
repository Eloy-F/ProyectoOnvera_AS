package com.eloy.code.proyectoonvera_as.data.repository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.eloy.code.proyectoonvera_as.data.api.PeliculaApi;
import com.eloy.code.proyectoonvera_as.data.api.RetrofitClient;
import com.eloy.code.proyectoonvera_as.data.common.BaseResponse;
import com.eloy.code.proyectoonvera_as.data.model.Pelicula;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PeliculaRepository {
    private final PeliculaApi peliculaApi;
    private final String TAG = PeliculaRepository.class.getSimpleName();

    // CONSTRUCTOR CONTEXT
    public PeliculaRepository(Context context) {
        peliculaApi = RetrofitClient.getRetrofit(context).create(PeliculaApi.class);
    }
    // LISTAR PELÍCULAS
    public LiveData<BaseResponse<List<Pelicula>>> listarPelicula() {
        Log.i(TAG, "Iniciando petición listarPelicula");
        MutableLiveData<BaseResponse<List<Pelicula>>> data = new MutableLiveData<>();

        peliculaApi.listarPeliculas().enqueue(new Callback<BaseResponse<List<Pelicula>>>() {

                    @Override
                    public void onResponse(Call<BaseResponse<List<Pelicula>>> call,
                            Response<BaseResponse<List<Pelicula>>> response) {
                         if (response.isSuccessful() && response.body() != null) {
                            Log.i(TAG,"Datos recibidos: " + response.body().getData().size());
                            data.setValue(response.body());
                            return;
                        }
                        try {
                            String errorJson = response.errorBody() != null
                                    ? response.errorBody().string()
                                    : "Error desconocido";

                            Log.e(TAG, "API ERROR: " + errorJson);

                        } catch (Exception e) {
                            Log.e(TAG, "Error leyendo errorBody", e);
                        }
                        data.setValue(BaseResponse.error("El API devolvió error"));
                    }
                    @Override
                    public void onFailure(Call<BaseResponse<List<Pelicula>>> call, Throwable throwable) {
                        Log.e(TAG, "Fallo conexión", throwable);
                        data.setValue(BaseResponse.error("Fallo la conexión"));
                    }
                }
        );
        return data;
    }
}
