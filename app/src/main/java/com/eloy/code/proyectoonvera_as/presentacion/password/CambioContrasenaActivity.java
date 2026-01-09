package com.eloy.code.proyectoonvera_as.presentacion.password;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.eloy.code.proyectoonvera_as.R;
import com.eloy.code.proyectoonvera_as.data.api.RetrofitClient;
import com.eloy.code.proyectoonvera_as.data.api.UsuarioApi;
import com.eloy.code.proyectoonvera_as.data.common.BaseResponse;
import com.eloy.code.proyectoonvera_as.data.request.CambiarPasswordRequest;
import com.eloy.code.proyectoonvera_as.presentacion.Inicio.InicioActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CambioContrasenaActivity extends AppCompatActivity {
    private EditText edtActual, edtNueva, edtConfirmar;
    private Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambio_contrasena);

        edtActual = findViewById(R.id.edtActual);
        edtNueva = findViewById(R.id.edtNueva);
        edtConfirmar = findViewById(R.id.edtConfirmar);
        btnGuardar = findViewById(R.id.btnCambiarPass);

        btnGuardar.setOnClickListener(v -> validarCampos());
    }
    private void validarCampos() {
        String actual = edtActual.getText().toString();
        String nueva = edtNueva.getText().toString();
        String confirmar = edtConfirmar.getText().toString();

        if (actual.isEmpty() || nueva.isEmpty() || confirmar.isEmpty()) {
            Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!nueva.equals(confirmar)) {
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            return;
        }
        cambiarPassword(actual, nueva);
    }

    private void cambiarPassword(String actual, String nueva) {

        // token correctamente
        String token = getSharedPreferences("auth", MODE_PRIVATE)
                .getString("token", null);

        if (token == null) {
            Toast.makeText(this, "Debe iniciar sesión nuevamente", Toast.LENGTH_SHORT).show();
            return;
        }

        CambiarPasswordRequest req = new CambiarPasswordRequest();
        req.setPasswordActual(actual);
        req.setPasswordNueva(nueva);

        UsuarioApi api = RetrofitClient.getRetrofit(this).create(UsuarioApi.class);

        api.cambiarPassword("Bearer " + token, req).enqueue(new Callback<BaseResponse<Boolean>>() {
            @Override
            public void onResponse(Call<BaseResponse<Boolean>> call, Response<BaseResponse<Boolean>> response) {
                if (response.isSuccessful() && response.body() != null && response.body().isSuccess()) {
                    Toast.makeText(CambioContrasenaActivity.this, "Contraseña actualizada", Toast.LENGTH_SHORT).show();

                    // Cerramos sesión
                    getSharedPreferences("auth", MODE_PRIVATE)
                            .edit()
                            .clear()
                            .apply();

                    Intent intent = new Intent(CambioContrasenaActivity.this, InicioActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                        finish();

                } else {
                    try {
                        String error = response.errorBody() != null
                                ? response.errorBody().string()
                                : "Error desconocido";
                        Log.e("API_ERROR", error);
                    } catch (Exception e) {
                        Log.e("API_ERROR", "No se pudo leer el error", e);
                    }
                    Toast.makeText(CambioContrasenaActivity.this, "Error al actualizar contraseña", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<Boolean>> call, Throwable t) {
                Toast.makeText(CambioContrasenaActivity.this, "Error de conexión", Toast.LENGTH_LONG).show();
            }
        });
    }
}
