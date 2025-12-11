package com.eloy.code.proyectoonvera_as.data.api;

import com.eloy.code.proyectoonvera_as.data.common.BaseResponse;
import com.eloy.code.proyectoonvera_as.data.model.Usuario;
import com.eloy.code.proyectoonvera_as.data.request.VerificarUsuarioRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
public interface UsuarioApi {
    @POST("usuarios/verificar") //ruta del empoy
    Call<BaseResponse<Usuario>> verificarUsuario(@Body VerificarUsuarioRequest request);//como parametro el request

}
