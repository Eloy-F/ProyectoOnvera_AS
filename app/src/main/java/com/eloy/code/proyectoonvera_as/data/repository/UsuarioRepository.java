package com.eloy.code.proyectoonvera_as.data.repository;

import android.os.CpuUsageInfo;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.eloy.code.proyectoonvera_as.data.api.RetrofitClient;
import com.eloy.code.proyectoonvera_as.data.api.UsuarioApi;
import com.eloy.code.proyectoonvera_as.data.common.BaseResponse;
import com.eloy.code.proyectoonvera_as.data.model.Usuario;
import com.eloy.code.proyectoonvera_as.data.request.VerificarUsuarioRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsuarioRepository {
    private final UsuarioApi usuarioApi;
    private final String TAG= UsuarioRepository.class.getSimpleName();

    public UsuarioRepository(){//constructor
        usuarioApi= RetrofitClient.getRetrofit().create(UsuarioApi.class);
    }
    public LiveData<BaseResponse<Usuario>> verificarUsuario(VerificarUsuarioRequest request){
        Log.i(TAG, "Iniciando peticion verificarUsuario");
        Log.i(TAG, "Iniciando peticion con request: "+request.toString());
        MutableLiveData<BaseResponse<Usuario>> data =new MutableLiveData<>();
        usuarioApi.verificarUsuario(request).enqueue(new Callback<BaseResponse<Usuario>>() {
            @Override
            public void onResponse(Call<BaseResponse<Usuario>> call, Response<BaseResponse<Usuario>> response) {
                //cuando tengo respuesta satisfactoria, codigo http 200 o similar
                if(response.isSuccessful() && response.body() !=null){
                    data.setValue(response.body());
                }
                //cuando la respues es error , codigo http 400 o 500
                if(response.errorBody() != null){
                    try {
                        String errorJson= response.errorBody().string();
                        Log.i(TAG, "errorJson: " + errorJson);
                    }catch (Exception e){
                        Log.e(TAG, "Error al convertir error api:"+e.getMessage());
                    }
                    data.setValue(BaseResponse.error("El api devolvió un error"));
                }
            }

            //cuando no hay conexion

            @Override
            public void onFailure(Call<BaseResponse<Usuario>> call, Throwable throwable) {
                //cuando hay un fallo de conexion
                Log.e(TAG,throwable.toString());
                data.setValue(BaseResponse.error("Fallo de conexion"));
            }
        });

        return data;
    }

}
