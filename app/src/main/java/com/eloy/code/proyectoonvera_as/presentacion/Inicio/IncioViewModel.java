package com.eloy.code.proyectoonvera_as.presentacion.Inicio;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.eloy.code.proyectoonvera_as.data.common.BaseResponse;
import com.eloy.code.proyectoonvera_as.data.model.LoginResponse;
import com.eloy.code.proyectoonvera_as.data.repository.UsuarioRepository;
import com.eloy.code.proyectoonvera_as.data.request.VerificarUsuarioRequest;

public class IncioViewModel extends AndroidViewModel {
    private final UsuarioRepository usuarioRepository;
    public IncioViewModel(@NonNull Application application) {
        super(application);
        usuarioRepository = new UsuarioRepository(application);
    }
    // 🔐 LOGIN CON JWT
    public LiveData<BaseResponse<LoginResponse>> login(
            VerificarUsuarioRequest request
    ) {
        return usuarioRepository.login(request);
    }
}


/*package com.eloy.code.proyectoonvera_as.presentacion.Inicio;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.eloy.code.proyectoonvera_as.data.common.BaseResponse;
import com.eloy.code.proyectoonvera_as.data.model.LoginResponse;
import com.eloy.code.proyectoonvera_as.data.repository.UsuarioRepository;
import com.eloy.code.proyectoonvera_as.data.request.VerificarUsuarioRequest;
public class IncioViewModel extends ViewModel {
    private final UsuarioRepository usuarioRepository;
    public IncioViewModel() {
        usuarioRepository = new UsuarioRepository();
    }
    // METODO PARA LOGIN CON JWT
    public LiveData<BaseResponse<LoginResponse>> login(
            VerificarUsuarioRequest request
    ) {
        return usuarioRepository.login(request);
    }
}*/

