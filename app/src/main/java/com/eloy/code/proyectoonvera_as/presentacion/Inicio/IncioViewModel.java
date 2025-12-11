package com.eloy.code.proyectoonvera_as.presentacion.Inicio;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.eloy.code.proyectoonvera_as.data.common.BaseResponse;
import com.eloy.code.proyectoonvera_as.data.model.Usuario;
import com.eloy.code.proyectoonvera_as.data.repository.UsuarioRepository;
import com.eloy.code.proyectoonvera_as.data.request.VerificarUsuarioRequest;

public class IncioViewModel extends ViewModel {

    private final UsuarioRepository usuarioRepository;

    public IncioViewModel(){//inicializamos usuarioRepository
        usuarioRepository =new UsuarioRepository();
    }

    public LiveData<BaseResponse<Usuario>> verificarUsuario(VerificarUsuarioRequest request){
        return usuarioRepository.ver(request);
    }
}
