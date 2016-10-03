package com.bignerdranch.android.pc02_renteria;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import Clases.Pokemoneslistar;
import Interfaces.IPokemon;
import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class mispokemones_activity extends AppCompatActivity {
    TextView mnivel;
    TextView tviTipo;
    TextView mDescripcion;
    ImageView img;
    Button butSiguiente;
    Button butAtras;
    int id=0;
    int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mispokemones_activity);
        mnivel = (TextView) findViewById(R.id.tviNivel);
        tviTipo = (TextView) findViewById(R.id.tviTipo);
        mDescripcion = (TextView) findViewById(R.id.tviDescripcion);
        img = (ImageView) findViewById(R.id.imageView);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            id = extras.getInt("id");
        }

        butSiguiente= (Button) findViewById(R.id.butSiguiente);
        butAtras = (Button) findViewById(R.id.butAtras);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://ul-pokemon.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IPokemon usuariosService = retrofit.create(IPokemon.class);

        usuariosService.getPokemones(id).enqueue(new Callback<List<Pokemoneslistar>>() {
            @Override
            public void onResponse(Call<List<Pokemoneslistar>> call, Response<List<Pokemoneslistar>> response) {
                final List<Pokemoneslistar> pokemones = response.body();
                int status = response.code();


                traerInformacion(pokemones);

                butSiguiente.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(i==pokemones.size()-1){
                            new SweetAlertDialog(mispokemones_activity.this)
                                    .setContentText("No hay pokemones!")
                                    .show();
                        }else{

                            i++;
                            traerInformacion(pokemones);
                        }

                    }
                });


                butAtras.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(i==0){
                            new SweetAlertDialog(mispokemones_activity.this)
                                    .setContentText("presionar, para avanzar!")
                                    .show();
                        }else{

                            i--;
                            traerInformacion(pokemones);
                        }

                    }
                });


            }

            @Override
            public void onFailure(Call<List<Pokemoneslistar>> call, Throwable t) {
                Log.e("MisPokemonesActivity",t.getMessage());
            }
        });






        Button butMenu = (Button) findViewById(R.id.butMenu);
        butMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mispokemones_activity.this,dashboard_activity.class);
                startActivity(intent);
            }
        });


    }

    public void traerInformacion(List<Pokemoneslistar> pokemones){
        Picasso.with(this).load(pokemones.get(i).getUrl()).into(img);
        mnivel.setText( pokemones.get(i).getNivel().toString());
        tviTipo.setText(pokemones.get(i).getTipo());
        mDescripcion.setText(pokemones.get(i).getDescripcion());
    }


}

