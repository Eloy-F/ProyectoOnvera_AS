package com.eloy.code.proyectoonvera_as.presentacion.perfil;

import android.content.SharedPreferences;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.eloy.code.proyectoonvera_as.R;
import com.eloy.code.proyectoonvera_as.data.api.RetrofitClient;
import com.eloy.code.proyectoonvera_as.data.api.UsuarioApi;
import com.eloy.code.proyectoonvera_as.data.common.BaseResponse;
import com.eloy.code.proyectoonvera_as.data.model.PerfilUsuario;
import com.eloy.code.proyectoonvera_as.presentacion.password.CambioContrasenaActivity;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilActivity extends AppCompatActivity {

    private ImageView imgPerfil;
    private TextView txtNombre, txtCorreo, txtTelefono;
    private Button btnCerrarSesion, btnCambiar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_perfil);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        String token = getSharedPreferences("AUTH", MODE_PRIVATE)
                .getString("token", null);

        Log.i("PERFIL_DEBUG", "TOKEN: " + token);
        // VIEWS
        imgPerfil = findViewById(R.id.imgPerfil);
        txtNombre = findViewById(R.id.txtNombreUsuario);
        txtCorreo = findViewById(R.id.txtCorreo);
        txtTelefono = findViewById(R.id.txtTelefono);
        btnCerrarSesion = findViewById(R.id.btnCerrarSesion);
        btnCambiar = findViewById(R.id.btnCambiarPass);

        cargarPerfil();

        btnCerrarSesion.setOnClickListener(v -> cerrarSesion());

        btnCambiar.setOnClickListener(v ->
                startActivity(new Intent(this, CambioContrasenaActivity.class))
        );
    }

    private void cargarPerfil() {
        UsuarioApi api = RetrofitClient.getRetrofit(this).create(UsuarioApi.class);

        api.obtenerMiPerfil().enqueue(new Callback<BaseResponse<PerfilUsuario>>() {
            @Override
            public void onResponse(Call<BaseResponse<PerfilUsuario>> call,
                                   Response<BaseResponse<PerfilUsuario>> response) {

                Log.i("PERFIL_DEBUG", "STATUS: " + response.code());

                if (response.errorBody() != null) {
                    try {
                        Log.e("PERFIL_DEBUG", "ERROR: " + response.errorBody().string());
                    } catch (Exception ignored) {
                    }
                }

                if (response.body() != null) {
                    Log.i("PERFIL_DEBUG", "DATA: " + new Gson().toJson(response.body()));
                }

                if (response.isSuccessful() && response.body() != null) {
                    PerfilUsuario perfil = response.body().getData();

                    if (perfil == null) {
                        Log.w("PERFIL_DEBUG", "Perfil es NULL");
                        return;
                    }

                    txtNombre.setText(perfil.getNombres() + " " + perfil.getApellidos());
                }


                if (response.isSuccessful() && response.body() != null) {

                    PerfilUsuario perfil = response.body().getData();

                    if (perfil == null) return;

                    txtNombre.setText(perfil.getNombres() + " " + perfil.getApellidos());
                    txtCorreo.setText(perfil.getCorreo());
                    txtTelefono.setText(perfil.getCelular());

                    Glide.with(PerfilActivity.this)
                            .load(perfil.getFotoUrl())
                            .placeholder(R.drawable.logoperfil)
                            .circleCrop()
                            .into(imgPerfil);
                } else {
                    Toast.makeText(PerfilActivity.this,
                            "No se pudo obtener perfil",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<PerfilUsuario>> call, Throwable t) {
                Toast.makeText(PerfilActivity.this,
                        "Error de conexión al cargar perfil",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void cerrarSesion() {
        SharedPreferences prefs = getSharedPreferences("AUTH", MODE_PRIVATE);
        prefs.edit().clear().apply();

        Intent intent = new Intent(
                PerfilActivity.this,
                com.eloy.code.proyectoonvera_as.presentacion.Inicio.InicioActivity.class
        );

        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}




/*package com.eloy.code.proyectoonvera_as.presentacion.perfil;
import android.content.SharedPreferences;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.eloy.code.proyectoonvera_as.R;

import android.widget.ImageView;
import android.widget.TextView;

import com.eloy.code.proyectoonvera_as.data.api.RetrofitClient;
import com.eloy.code.proyectoonvera_as.data.api.UsuarioApi;
import com.eloy.code.proyectoonvera_as.data.common.BaseResponse;
import com.eloy.code.proyectoonvera_as.data.model.PerfilUsuario;
import com.eloy.code.proyectoonvera_as.presentacion.password.CambioContrasenaActivity;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilActivity extends AppCompatActivity {
    private TextView txtNombre, txtCorreo, txtTelefono;
    private ImageView imgPerfil;
    private Button btnCerrarSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_perfil);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        txtNombre = findViewById(R.id.txtNombreUsuario);
        txtCorreo = findViewById(R.id.txtCorreo);
        txtTelefono = findViewById(R.id.txtTelefono);
        btnCerrarSesion = findViewById(R.id.btnCerrarSesion);
        Button btnCambiar = findViewById(R.id.btnCambiarPass);

        //Cargar datos del perfil
        cargarPerfil();
        //cerramos sesiion
        btnCerrarSesion.setOnClickListener(v -> cerrarSesion());

        //rute para ir a cambio de de contraseña
        btnCambiar.setOnClickListener(v -> {
            startActivity(new Intent(this, CambioContrasenaActivity.class));
        });
    }

    private void cargarPerfil() {
        UsuarioApi api = RetrofitClient.getRetrofit(this).create(UsuarioApi.class);
        api.obtenerMiPerfil().enqueue(new Callback<BaseResponse<PerfilUsuario>>() {
            @Override
            public void onResponse(Call<BaseResponse<PerfilUsuario>> call,
                    Response<BaseResponse<PerfilUsuario>> response)
            {
                if (response.isSuccessful() && response.body() != null) {
                    PerfilUsuario perfil = response.body().getData();

                    txtNombre.setText(perfil.getNombres() + " " + perfil.getApellidos());
                    Glide.with(PerfilActivity.this)
                            .load(perfil.getFotoUrl())
                            .placeholder(R.drawable.logoperfil)
                            .circleCrop()
                            .into(imgPerfil);
                }
            }
            @Override
            public void onFailure(Call<BaseResponse<PerfilUsuario>> call, Throwable t) {
                Toast.makeText(PerfilActivity.this,"Error al cargar perfil",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    //METODO CERRAR SESIÓN
    private void cerrarSesion() {
        SharedPreferences prefs = getSharedPreferences("auth", MODE_PRIVATE);
        //Eliminar token
        prefs.edit().clear().apply();
        //Volver al login
        Intent intent = new Intent(
                PerfilActivity.this,
                com.eloy.code.proyectoonvera_as.presentacion.Inicio.InicioActivity.class
        );

        //Evita volver atrás
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }


}*/