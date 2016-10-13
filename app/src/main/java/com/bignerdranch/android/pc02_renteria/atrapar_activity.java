package com.bignerdranch.android.pc02_renteria;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import Adapters.RecyclerAdapter;
import Clases.ListPokemones;
import Clases.Pokemon;
import Clases.Pokemoneslistar;
import Interfaces.IPokemon;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class atrapar_activity extends AppCompatActivity {
    private GridLayoutManager mGridLayoutManager;
    private RecyclerView mRecyclerView;
    private RecyclerAdapter mAdapter;

    private String username="";


    ProgressDialog progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atrapar);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            username = extras.getString("username");
        }

        mRecyclerView = (RecyclerView) findViewById(R.id.recycleView);
        mRecyclerView.setHasFixedSize(true);


        if (getResources().getConfiguration().orientation == 1) {
            mGridLayoutManager = new GridLayoutManager(this, 4);
        } else {
            mGridLayoutManager = new GridLayoutManager(this, 6);
        }
        mRecyclerView.setLayoutManager(mGridLayoutManager);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://ulima-parcial.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        IPokemon usuariosService = retrofit.create(IPokemon.class);
        progress = new ProgressDialog(this);
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.show();
        usuariosService.getListPokemones().enqueue(new Callback<ListPokemones>() {


            @Override
            public void onResponse(Call<ListPokemones> call, Response<ListPokemones> response) {
                ListPokemones listPokemones = response.body();
                mAdapter = new RecyclerAdapter((ArrayList<Pokemoneslistar>)listPokemones.getPokemones(),atrapar_activity.this,username);
                mRecyclerView.setAdapter(mAdapter);

                progress.dismiss();
            }
            @Override
            public void onFailure(Call<ListPokemones> call, Throwable t) {
                progress.dismiss();
                Intent intent = new Intent(atrapar_activity.this, dashboard_activity.class);
                startActivity(intent);
                Toast.makeText(atrapar_activity.this, "Hubo un error en la conexión", Toast.LENGTH_SHORT).show();
            }
        });

        }
    }

