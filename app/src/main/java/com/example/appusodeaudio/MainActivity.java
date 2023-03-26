package com.example.appusodeaudio;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    // Definit los objetos
    MediaPlayer reproducir;
    ImageView imagen1, imagen2;
    Button remoto, local;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Se referencian los objetos.
        imagen1 = findViewById(R.id.imgBoton);
        imagen2 = findViewById(R.id.imgBotonStop);
        imagen1.setVisibility(View.VISIBLE);
        imagen2.setVisibility(View.VISIBLE);
        remoto = findViewById(R.id.remoto);
        local = findViewById(R.id.local);
        // Función a botón Local.
        local.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(reproducir != null){
                    if(reproducir.isPlaying()){
                        reproducir.release();
                        reproducir = null;
                    }
                }
                reproducir = MediaPlayer.create(MainActivity.this, R.raw.cancion_test);
                reproducir.start();
                imagen1.setVisibility(View.VISIBLE);
                imagen1.setImageResource(R.drawable.boton_pausar);
                imagen2.setVisibility(View.VISIBLE);
            }
        });
        // Función a botón Remoto.
        remoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if(reproducir != null){
                        if(reproducir.isPlaying()){
                            reproducir.release();
                            reproducir = null;
                        }
                    }
                    reproducir = new MediaPlayer();
                    String URL = "https://tonosmovil.net/wp-content/uploads/tonosmovil.net_himno_champions_league.mp3";
                    reproducir.setAudioAttributes(
                            new AudioAttributes
                                    .Builder()
                                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                                    .build());
                    reproducir.setDataSource(URL);
                    reproducir.prepare();
                    reproducir.start();
                    imagen1.setVisibility(View.VISIBLE);
                    imagen1.setImageResource(R.drawable.boton_pausar);
                    imagen2.setVisibility(View.VISIBLE);
                } catch (IOException e) {
                }
            }
        });
        // Función a la imagen1.
        imagen1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(reproducir.isPlaying()){
                    reproducir.pause();
                    imagen1.setImageResource(R.drawable.boton_reproducir);
                    imagen2.setVisibility(View.INVISIBLE);
                }else {
                    reproducir.start();
                    imagen1.setImageResource(R.drawable.boton_pausar);
                    imagen2.setVisibility(View.VISIBLE);
                }
            }
        });
        // Función a la imagen2.
        imagen2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagen1.setImageResource(R.drawable.boton_reproducir);
                reproducir.seekTo(000);
                reproducir.pause();
                imagen2.setVisibility(View.INVISIBLE);
            }
        });
    }
}