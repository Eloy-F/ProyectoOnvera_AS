package com.eloy.code.proyectoonvera_as.presentacion.Inicio;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import androidx.lifecycle.ViewModelProvider;

import com.eloy.code.proyectoonvera_as.R;
import com.eloy.code.proyectoonvera_as.data.model.Usuario;
import com.eloy.code.proyectoonvera_as.data.request.VerificarUsuarioRequest;
import com.eloy.code.proyectoonvera_as.presentacion.common.Validator;
import com.eloy.code.proyectoonvera_as.presentacion.categorias.activity_ListaCategoria;
import com.eloy.code.proyectoonvera_as.data.model.LoginResponse;
import com.eloy.code.proyectoonvera_as.presentacion.registro.RegistroActivity;

public class InicioActivity extends AppCompatActivity {
    private IncioViewModel inicioViewModel;
    private final String TAG= InicioActivity.class.getSimpleName();
    ///declaramos las variables
    private EditText edtCorreo;
    private EditText edtPassword;
    ////  private Button btnVerificar;
    ///-------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) { //onCreate se encarga de reederizar el layout/iniciar
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        edtCorreo=findViewById(R.id.edt_correo);
        edtPassword=findViewById(R.id.edt_password);
        inicioViewModel =new ViewModelProvider(this).get(IncioViewModel.class);

        Button btnRegistro = findViewById(R.id.btnRegistro);

        btnRegistro.setOnClickListener(v -> {
            Intent intent = new Intent(InicioActivity.this,
                    RegistroActivity.class);
            startActivity(intent);
        });
    }

    public void onClicVerificar(View v) {

        if (!Validator.with(edtCorreo)
                .required()
                .isEmail()
                .validate()) return;
       /* if (!Validator.with(edtPassword)
                .required()
                .length(1)
                .validate()) return;*/
        VerificarUsuarioRequest verificarUsuarioRequest=new VerificarUsuarioRequest();
        verificarUsuarioRequest.setCorreo(edtCorreo.getText().toString());
        verificarUsuarioRequest.setPassword(edtPassword.getText().toString());

        /// llamado de APPI
        //inicioViewModel.verificarUsuario(verificarUsuarioRequest).observe(this,response -> {
        inicioViewModel.login(verificarUsuarioRequest).observe(this, response -> {
            if(!response.isSuccess()){
                Toast.makeText(this, response.getMessage(), Toast.LENGTH_LONG).show();
            }else{
                LoginResponse data = response.getData();
                Usuario p = data.getUsuario();
                String token = data.getToken();

               //GUARDAR TOKEN
                getSharedPreferences("AUTH", MODE_PRIVATE)
                        .edit()
                        .putString("token", token)
                        .apply();

                Toast.makeText(
                        this,
                        "Bienvenido " + p.getNombres() + " a ONVERA",
                        Toast.LENGTH_LONG
                ).show();

                //Intent*/
                Intent intent=new Intent(this, activity_ListaCategoria.class);
                startActivity(intent);
                finish();
            }
        });
    }

}
