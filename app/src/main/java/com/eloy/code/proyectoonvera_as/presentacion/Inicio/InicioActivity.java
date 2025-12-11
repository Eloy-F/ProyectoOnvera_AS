package com.eloy.code.proyectoonvera_as.presentacion.Inicio;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.eloy.code.proyectoonvera_as.R;
import com.eloy.code.proyectoonvera_as.data.model.Usuario;
import com.eloy.code.proyectoonvera_as.data.request.VerificarUsuarioRequest;
import com.eloy.code.proyectoonvera_as.presentacion.common.Validator;

import java.util.Calendar;

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
        // Log.i(TAG,"Ejecutando metodo onStrat");///
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_inicio);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //direccionamos a cada variable
        edtCorreo=findViewById(R.id.edt_correo);
        edtPassword=findViewById(R.id.edt_password);

        inicioViewModel =new ViewModelProvider(this).get(IncioViewModel.class);


    }

    public void onClicVerificar(View v) {

        if (!Validator.with(edtCorreo)
                .required()
                .length(8)
                .validate()) return;

        if (!Validator.with(edtPassword)
                .required()
                .length(1)
                .validate()) return;

        VerificarUsuarioRequest verificarUsuarioRequest=new VerificarUsuarioRequest();
        verificarUsuarioRequest.setCorreo(edtCorreo.getText().toString());
        verificarUsuarioRequest.setPassword(edtCorreo.getText().toString());


        /// llamado de APPI
        inicioViewModel.verificarUsuario(verificarUsuarioRequest).observe(this,response -> {
            if(!response.isSuccess()){
                Toast.makeText(this, response.getMessage(), Toast.LENGTH_LONG).show();
            }else{
                Usuario p = response.getData();
                Toast.makeText(this,"Bienvenido "+p.getNombres()+"al sistema de votos", Toast.LENGTH_LONG);
                //Intent
                Intent intent=new Intent(this, InicioActivity.class);
                startActivity(intent);
            }
        });
    }

}
