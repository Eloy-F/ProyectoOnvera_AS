package com.eloy.code.proyectoonvera_as.presentacion.registro;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.eloy.code.proyectoonvera_as.data.common.BaseResponse;
import com.eloy.code.proyectoonvera_as.data.repository.UsuarioRepository;
import com.eloy.code.proyectoonvera_as.data.request.RegistroUsuarioRequest;
public class RegistroViewModel extends AndroidViewModel {
    private final UsuarioRepository repository;
    public RegistroViewModel(@NonNull Application application) {
        super(application);
        repository = new UsuarioRepository(application);
    }

    public LiveData<BaseResponse<Boolean>> registrar(RegistroUsuarioRequest request){
        return repository.registrarUsuario(request);
    }
}

