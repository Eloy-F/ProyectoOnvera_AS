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
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();

        new Handler().postDelayed(() -> {
            Intent i = new Intent(this, InicioActivity.class);
            startActivity(i);
            finish();
        }, 2000);
    }

}