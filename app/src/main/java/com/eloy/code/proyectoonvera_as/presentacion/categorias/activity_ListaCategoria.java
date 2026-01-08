package com.eloy.code.proyectoonvera_as.presentacion.categorias;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.eloy.code.proyectoonvera_as.presentacion.peliculas.Activity_PeliculasAdultos;
import com.eloy.code.proyectoonvera_as.presentacion.peliculas.Activity_PeliculasNinos;

import com.eloy.code.proyectoonvera_as.R;

public class activity_ListaCategoria extends AppCompatActivity {

    Button btnAdultos, btnNinos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_categorias);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // 🔹 Referencia al botón
        btnAdultos = findViewById(R.id.btnadultos);
        btnNinos = findViewById(R.id.btnninos);
        // 🔹 Evento click
        btnAdultos.setOnClickListener(v -> {
            Intent intent = new Intent(activity_ListaCategoria
                    .this,Activity_PeliculasAdultos.class
            );
            startActivity(intent);
        });

        btnNinos.setOnClickListener(v->{
            Intent intent= new Intent(activity_ListaCategoria
                            .this,Activity_PeliculasNinos.class
            );
            startActivity(intent);
        });
          //imagen de perfil
        ImageView logoperfil = findViewById(R.id.logoperfil);

        logoperfil.setOnClickListener(v -> {
            Intent intent = new Intent(
                    activity_ListaCategoria.this,
                    com.eloy.code.proyectoonvera_as.presentacion.perfil.PerfilActivity.class
            );
            startActivity(intent);
        });
    }
}