package com.eloy.code.proyectoonvera_as.presentacion.registro;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.eloy.code.proyectoonvera_as.R;
import com.eloy.code.proyectoonvera_as.data.request.RegistroUsuarioRequest;
import com.eloy.code.proyectoonvera_as.presentacion.Inicio.InicioActivity;

public class RegistroActivity extends AppCompatActivity {

    private RegistroViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        viewModel = new ViewModelProvider(this)
                .get(RegistroViewModel.class);

        EditText edtNombres = findViewById(R.id.edtNombres);
        EditText edtApellidos = findViewById(R.id.edtApellidos);
        EditText edtCorreo = findViewById(R.id.edtCorreo);
        EditText edtPassword = findViewById(R.id.edtPassword);
        EditText edtCelular = findViewById(R.id.edtCelular);

        Button btnRegistrar = findViewById(R.id.btnRegistrar);
        TextView txtLogin = findViewById(R.id.txtVolverLogin);

        // 🔹 REGISTRAR USUARIO
        btnRegistrar.setOnClickListener(v -> {
            RegistroUsuarioRequest request = new RegistroUsuarioRequest();
            request.setNombres(edtNombres.getText().toString());
            request.setApellidos(edtApellidos.getText().toString());
            request.setCorreo(edtCorreo.getText().toString());
            request.setPassword(edtPassword.getText().toString());
            request.setCelular(edtCelular.getText().toString());

            viewModel.registrar(request).observe(this, response -> {
                if (response.isSuccess()) {
                    Toast.makeText(this,"Registro exitoso, ahora inicia sesión",
                            Toast.LENGTH_LONG).show();

                    startActivity(new Intent(this, InicioActivity.class));
                    finish();
                } else {
                    Toast.makeText(this,response.getMessage(),
                            Toast.LENGTH_LONG).show();
                }
            });
        });

        // VOLVER AL LOGIN
        txtLogin.setOnClickListener(v -> {
            startActivity(new Intent(this, InicioActivity.class));
            finish();
        });
    }
}
