package com.eloy.code.proyectoonvera_as.presentacion;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.eloy.code.proyectoonvera_as.R;
import com.eloy.code.proyectoonvera_as.presentacion.Inicio.InicioActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


    @Override
    protected void onStart(){
        super.onStart();
        //Setting equivalente
        new Handler().postDelayed(()->{
            Intent i = new Intent(this, InicioActivity.class);
            startActivity(i);
            this.finish();
        },2000);//tiempo para dejar de mostrar y mostrar la siguiente pantalla
    }

}