package com.eloy.code.proyectoonvera_as.data.repository;
import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.eloy.code.proyectoonvera_as.data.api.RetrofitClient;
import com.eloy.code.proyectoonvera_as.data.api.UsuarioApi;
import com.eloy.code.proyectoonvera_as.data.common.BaseResponse;
import com.eloy.code.proyectoonvera_as.data.model.Usuario;
import com.eloy.code.proyectoonvera_as.data.request.VerificarUsuarioRequest;
import com.eloy.code.proyectoonvera_as.data.model.LoginResponse;
import com.eloy.code.proyectoonvera_as.data.request.RegistroUsuarioRequest;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Context;

public class UsuarioRepository {
    private final UsuarioApi usuarioApi;
    private final String TAG = UsuarioRepository.class.getSimpleName();

    // CONSTRUCTOR CON CONTEXT
    public UsuarioRepository(Context context) {
        usuarioApi = RetrofitClient
                .getRetrofit(context)
                .create(UsuarioApi.class);
    }

    // ========Login===============
    public LiveData<BaseResponse<LoginResponse>> login(VerificarUsuarioRequest request
    ) {
        Log.i(TAG, "Iniciando peticion login");
        Log.i(TAG, "Request login: " + request.toString());

        MutableLiveData<BaseResponse<LoginResponse>> data = new MutableLiveData<>();

        usuarioApi.login(request).enqueue(new Callback<BaseResponse<LoginResponse>>() {
            @Override
            public void onResponse(Call<BaseResponse<LoginResponse>> call,
                    Response<BaseResponse<LoginResponse>> response)
            {
                if (response.isSuccessful() && response.body() != null) {
                    data.setValue(response.body());
                } else {
                    data.setValue(BaseResponse.error("Credenciales incorrectas"));
                }
            }
            //cuando no hay conexion
            @Override
            public void onFailure(Call<BaseResponse<LoginResponse>> call, Throwable throwable) {
                    //cuando hay un fallo de conexion
                Log.e(TAG,throwable.toString());
                data.setValue(BaseResponse.error("Fallo de conexion"));
            }
        });

        return data;
    }

    // =====REGISTRO===========
    public LiveData<BaseResponse<Boolean>> registrarUsuario(RegistroUsuarioRequest request)
    {
        MutableLiveData<BaseResponse<Boolean>> data = new MutableLiveData<>();

        usuarioApi.registrarUsuario(request).enqueue(new Callback<BaseResponse<Boolean>>() {
                    @Override
                    public void onResponse(Call<BaseResponse<Boolean>> call,
                                           Response<BaseResponse<Boolean>> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            data.setValue(response.body());
                        } else {
                            data.setValue(BaseResponse.error("Error al registrar usuario"));
                        }
                    }

                    @Override
                    public void onFailure(Call<BaseResponse<Boolean>> call,Throwable throwable)
                    {
                        Log.e(TAG, throwable.toString());
                        data.setValue(BaseResponse.error("Error de conexión"));
                    }
                }
        );

        return data;
    }

}
