package com.eloy.code.proyectoonvera_as.presentacion.peliculas;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.eloy.code.proyectoonvera_as.data.model.Pelicula;
import com.eloy.code.proyectoonvera_as.R;

import java.util.List;

public class PeliculaAdultoAdapter extends RecyclerView.Adapter<PeliculaAdultoAdapter.ViewHolder> {
    private final Context context;
    private final List<Pelicula> peliculas;

    public PeliculaAdultoAdapter(Context context, List<Pelicula> peliculas) {
        this.context = context;
        this.peliculas = peliculas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_pelicula, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Pelicula pelicula = peliculas.get(position);
        holder.txtTitulo.setText(pelicula.getTitulo());
        String img = pelicula.getImagenUrl();

        System.out.println("IMG RAW = " + img);
        String url = "http://10.0.2.2:3000" + img;
        System.out.println("IMG FINAL = " + url);

        Glide.with(context)
                .load(url)
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.imgPelicula);

        //aqui renderizamos la ruta de YouTube
        holder.itemView.setOnClickListener(v -> {
            if (pelicula.getVideoUrl() == null || pelicula.getVideoUrl().isEmpty()) {
                Toast.makeText(context, "Video no disponible", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent = new Intent(context, Activity_PeliculasVideo.class);
            intent.putExtra("VIDEO_URL", pelicula.getVideoUrl());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return peliculas.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPelicula;
        TextView txtTitulo;
        ViewHolder(View itemView) {
            super(itemView);
            imgPelicula = itemView.findViewById(R.id.imgPelicula);
            txtTitulo = itemView.findViewById(R.id.txtTitulo);
        }
    }
}
