package com.bignerdranch.android.pc02_renteria;
/**
 * Created by Diego Renteria on 02/10/2016.
 */
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import Clases.ListPokemones;
import Clases.Pokemon;
import Clases.Pokemoneslistar;
import Clases.ResponsePokemones;
import Interfaces.IPokemon;
import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.bignerdranch.android.pc02_renteria.R.id.tviNivel;
import static com.bignerdranch.android.pc02_renteria.R.id.tviPokemon;

public class mispokemones_activity extends AppCompatActivity {
    TextView mnivel;
    TextView tviTipo;
    TextView mDescripcion;
    TextView tviPokemon;
    ImageView img;
    Button butmenu;
    Button butSiguiente;
    Button butAtras;
    String username="";
    List<Pokemoneslistar> listaPok;
    ProgressDialog progress;
    int size;
    int id=0;
    int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mispokemones_activity);
        Typeface tf = Typeface.createFromAsset(getAssets(),"font/FFF_Tusj.ttf");
        TextView tv = (TextView) findViewById(R.id.mispoke);
        TextView tv1 = (TextView) findViewById(R.id.tviPokemon);
        tviPokemon=(TextView) findViewById(R.id.tviPokemon);
        tv.setTypeface(tf);
        tv1.setTypeface(tf);


        mnivel = (TextView) findViewById(R.id.tviNivel);
        tviTipo = (TextView) findViewById(R.id.tviTipo);
        mDescripcion = (TextView) findViewById(R.id.tviDescripcion);
        img = (ImageView) findViewById(R.id.imageView);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            username = extras.getString("username");
        }

        butSiguiente= (Button) findViewById(R.id.butSiguiente);
        butAtras = (Button) findViewById(R.id.butAtras);
        butmenu=(Button) findViewById(R.id.butMenu);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://ulima-parcial.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();




        final IPokemon usuariosService = retrofit.create(IPokemon.class);

        progress = new ProgressDialog(this);
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.show();
        usuariosService.getMisPokemones(username).enqueue(new Callback<ListPokemones>() {
            @Override
            public void onResponse(Call<ListPokemones> call, Response<ListPokemones> response) {
                final ListPokemones listPokemones = response.body();
                Log.i("rpta",listPokemones.toString());
                listaPok = listPokemones.getPokemones();
                if(listaPok.size()==0){
                    progress.dismiss();
                    Intent intent = new Intent(mispokemones_activity.this, dashboard_activity.class);
                    startActivity(intent);
                    new SweetAlertDialog(mispokemones_activity.this)
                            .setTitleText("Alerta!")
                            .setContentText("Aún no tienes pokemones. Ve a capturar algunos")
                            .show();
                                    }else{
                    cargarInformacion();
                    progress.dismiss();
                }
            }
            @Override
            public void onFailure(Call<ListPokemones> call, Throwable t) {
                progress.dismiss();
                Intent intent = new Intent(mispokemones_activity.this, dashboard_activity.class);
                startActivity(intent);
                new SweetAlertDialog(mispokemones_activity.this)
                        .setTitleText("Alerta!")
                        .setContentText(" Hubo un error en la conexion")
                        .show();

            }
        });
        butSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(i==listaPok.size()-1){
                    new SweetAlertDialog(mispokemones_activity.this)
                            .setTitleText("Alerta!")
                            .setContentText("Ya no hay mas pokemones")
                            .show();

                }else{
                    i++;
                    cargarInformacion();
                }
            }
        });
        butAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(i==0){
                    new SweetAlertDialog(mispokemones_activity.this)
                            .setTitleText("Alerta!")
                            .setContentText("Presionar >>")
                            .show();
                }else{
                    i--;
                    cargarInformacion();
                }
            }
        });
        butmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mispokemones_activity.this,dashboard_activity.class);
                intent.putExtra("username",username);
                startActivity(intent);
            }
        });
    }


    public void cargarInformacion(){
        Log.i("MisPokemonesActivity",listaPok.get(i).getImg());
        Picasso.with(this).load(listaPok.get(i).getImg()).into(img);
        tviPokemon.setText(listaPok.get(i).getName().toString());
        mnivel.setText( listaPok.get(i).getNivel().toString());
        tviTipo.setText(listaPok.get(i).getType());
        mDescripcion.setText(listaPok.get(i).getDescription());
    }

}

