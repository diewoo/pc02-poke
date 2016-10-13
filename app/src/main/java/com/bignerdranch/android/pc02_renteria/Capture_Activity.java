package com.bignerdranch.android.pc02_renteria;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.Serializable;

import Clases.Atrapar;
import Clases.ListPokemones;
import Clases.Pokemoneslistar;
import Clases.Respuesta;
import Interfaces.IPokemon;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Capture_Activity extends AppCompatActivity {
    private ImageView mostrarPoke;
    private String url="";
    private String username="";
    private String nombrePoke="";
    private int nivel=0;
    private String tipo="";
    private String descripcion="";

    private int idPoke=0;
    ProgressDialog progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture_);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            url = extras.getString("url");
            idPoke = extras.getInt("idPoke");
            username = extras.getString("username");
            nombrePoke=extras.getString("nombrepoke");
            tipo=extras.getString("tipo");
            descripcion=extras.getString("descripcion");
            nivel=extras.getInt("nivel");


        }
        final Pokemoneslistar pokemonsito=new Pokemoneslistar();
        pokemonsito.setName(nombrePoke);
        pokemonsito.setType(tipo);
        pokemonsito.setDescription(descripcion);
        pokemonsito.setNivel(nivel);
        pokemonsito.setId(idPoke);
        pokemonsito.setImg(url);


        mostrarPoke = (ImageView)findViewById(R.id.mostrarPoke);
        Picasso.with(this).load(url).into(mostrarPoke);

        mostrarPoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://ulima-parcial.herokuapp.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                final IPokemon usuariosService = retrofit.create(IPokemon.class);
                progress = new ProgressDialog(Capture_Activity.this);
                progress.setTitle("Loading");
                progress.setMessage("Atrapando ...");
                progress.setCancelable(false);
                progress.show();

                usuariosService.registrarPokemon(new Atrapar(username,pokemonsito)).enqueue(new Callback<Respuesta>() {
                    @Override
                    public void onResponse(Call<Respuesta> call, Response<Respuesta> response) {
                        Respuesta respuesta= response.body();
                        Toast.makeText(Capture_Activity.this, respuesta.getStatus().getMsg(), Toast.LENGTH_SHORT).show();
                        Intent intent= new Intent(Capture_Activity.this,dashboard_activity.class);
                        intent.putExtra("username",username);
                        startActivity(intent);
                    }
                    @Override
                    public void onFailure(Call<Respuesta> call, Throwable t) {
                        Log.e("CaptureActivity",t.getMessage());
                        Toast.makeText(Capture_Activity.this, "Hubo un error en la conexión", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
