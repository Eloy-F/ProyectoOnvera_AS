package com.eloy.code.proyectoonvera_as.data.api;

import com.eloy.code.proyectoonvera_as.data.common.BaseResponse;
import com.eloy.code.proyectoonvera_as.data.model.Usuario;
import com.eloy.code.proyectoonvera_as.data.request.VerificarUsuarioRequest;
import com.eloy.code.proyectoonvera_as.data.model.LoginResponse;
import com.eloy.code.proyectoonvera_as.data.model.PerfilUsuario;
import com.eloy.code.proyectoonvera_as.data.request.CambiarPasswordRequest;
import com.eloy.code.proyectoonvera_as.data.request.RegistroUsuarioRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface UsuarioApi {
    @POST("usuarios/verificar") //ruta del empoy
    Call<BaseResponse<Usuario>> verificarUsuario(
            @Body VerificarUsuarioRequest request);//como parametro el request
    @POST("usuarios/login") //ruta del empoy
    Call<BaseResponse<LoginResponse>> login(
            @Body VerificarUsuarioRequest request);//parametro request
    @POST("usuarios/registro")
    Call<BaseResponse<Boolean>> registrarUsuario(
            @Body RegistroUsuarioRequest request
    );
    @GET("perfiles/personal")
    Call<BaseResponse<PerfilUsuario>> obtenerMiPerfil();
    @PUT("usuarios/password")
    Call<BaseResponse<Boolean>> cambiarPassword(
            @Header("Authorization") String token,
            @Body CambiarPasswordRequest request
    );
}
