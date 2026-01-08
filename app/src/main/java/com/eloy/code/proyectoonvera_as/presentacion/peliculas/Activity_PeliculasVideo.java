package com.eloy.code.proyectoonvera_as.presentacion.peliculas;

import com.eloy.code.proyectoonvera_as.R;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import androidx.appcompat.app.AppCompatActivity;

public class Activity_PeliculasVideo extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pelicula_video);

        String videoUrl = getIntent().getStringExtra("VIDEO_URL");

        WebView webView = findViewById(R.id.webVideo);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);

        if (videoUrl != null) {
            String embedUrl = videoUrl.replace("watch?v=", "embed/");
            webView.loadUrl(embedUrl);
        }
    }

}
