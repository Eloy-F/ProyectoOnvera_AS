package com.eloy.code.proyectoonvera_as.presentacion.perfil;
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
    private ImageView imgPerfil;
    private TextView txtNombre;
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

        imgPerfil = findViewById(R.id.imgPerfil);
        txtNombre = findViewById(R.id.txtNombreUsuario);
        btnCerrarSesion = findViewById(R.id.btnCerrarSesion);
        Button btnCambiar = findViewById(R.id.btnCambiarPass);

        //Cargar datos del perfil
        cargarPerfil();
        //Cerrar sesión
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
        SharedPreferences prefs = getSharedPreferences("AUTH", MODE_PRIVATE);
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


}