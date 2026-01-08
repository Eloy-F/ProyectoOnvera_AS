package com.eloy.code.proyectoonvera_as.data.repository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.eloy.code.proyectoonvera_as.data.api.CategoriaApi;
import com.eloy.code.proyectoonvera_as.data.api.RetrofitClient;
import com.eloy.code.proyectoonvera_as.data.common.BaseResponse;
import com.eloy.code.proyectoonvera_as.data.model.Categoria;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoriaRepository {

    private final CategoriaApi categoriaApi;
    private final String TAG = CategoriaRepository.class.getSimpleName();

    // 🔹 CONSTRUCTOR CON CONTEXT
    public CategoriaRepository(Context context) {
        categoriaApi = RetrofitClient
                .getRetrofit(context)
                .create(CategoriaApi.class);
    }

    // 🔹 LISTAR CATEGORÍAS
    public LiveData<BaseResponse<List<Categoria>>> listarCategorias() {

        MutableLiveData<BaseResponse<List<Categoria>>> data =
                new MutableLiveData<>();

        categoriaApi.listarCategorias().enqueue(
                new Callback<BaseResponse<List<Categoria>>>() {

                    @Override
                    public void onResponse(
                            Call<BaseResponse<List<Categoria>>> call,
                            Response<BaseResponse<List<Categoria>>> response
                    ) {
                        if (response.isSuccessful() && response.body() != null) {
                            data.setValue(response.body());
                        } else {
                            Log.e(TAG, "Error al listar categorías");
                            data.setValue(
                                    BaseResponse.error("Error al obtener categorías")
                            );
                        }
                    }

                    @Override
                    public void onFailure(
                            Call<BaseResponse<List<Categoria>>> call,
                            Throwable t
                    ) {
                        Log.e(TAG, "Fallo conexión", t);
                        data.setValue(
                                BaseResponse.error("Fallo de conexión")
                        );
                    }
                }
        );

        return data;
    }
}



/*package com.eloy.code.proyectoonvera_as.data.repository;
import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.eloy.code.proyectoonvera_as.data.api.CategoriaApi;
import com.eloy.code.proyectoonvera_as.data.api.RetrofitClient;
import com.eloy.code.proyectoonvera_as.data.common.BaseResponse;
import com.eloy.code.proyectoonvera_as.data.model.Categoria;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class CategoriaRepository {
    private final CategoriaApi categoriaApi;
    private final String TAG= PeliculaRepository.class.getSimpleName();

    public CategoriaRepository(){
        categoriaApi= RetrofitClient.getRetrofit().create(CategoriaApi.class);

    }

    public LiveData<BaseResponse<List<Categoria>>> listarPelicula(){
        Log.i(TAG,"Inciando pedicion de Lista ListarCategoria");
        MutableLiveData<BaseResponse<List<Categoria>>> data= new MutableLiveData<>();
        categoriaApi.listarCategorias().enqueue(new Callback<BaseResponse<List<Categoria>>>(){

            @Override
            public void onResponse(Call<BaseResponse<List<Categoria>>> call, Response<BaseResponse<List<Categoria>>> response){
                if(response.isSuccessful() && response.body() !=null){
                    Log.i(TAG,"Datos recibidos: "    + response.body().getData().size());
                    data.setValue(response.body());
                    return;
                }
                try {
                    String errorJson= response.errorBody() != null
                            ? response.errorBody().string()
                            : "Error desconocido";
                    Log.e(TAG,"API ERROR " + errorJson);
                } catch (Exception e){
                    Log.e(TAG,"Error leyendoBody",e);
                }
                data.setValue(BaseResponse.error("El API devolvió Error"));

            }

            //cuando no hay conexion
            @Override
            public void onFailure(Call<BaseResponse<List<Categoria>>> call, Throwable throwable){
                Log.e(TAG,throwable.toString());
                data.setValue(BaseResponse.error("Fallo la conexion"));
            }

        });
        return data;
    }
}
*/