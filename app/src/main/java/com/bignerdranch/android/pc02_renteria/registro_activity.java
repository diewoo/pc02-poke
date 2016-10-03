package com.bignerdranch.android.pc02_renteria;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Clases.Respuesta;
import Clases.Usuario;
import Clases.Usuarios;
import Interfaces.IPokemon;
import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class registro_activity extends AppCompatActivity {
    EditText eteRegUsuario;
    EditText eteRegPassword;
    EditText eteRegPassword3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


            super.onCreate(savedInstanceState);
            setContentView(R.layout.registro_activity);
        eteRegUsuario = (EditText) findViewById(R.id.eteRegUsuario);
        eteRegPassword = (EditText) findViewById(R.id.eteRegPassword);
        eteRegPassword3 = (EditText) findViewById(R.id.eteRegPassword2);

            Button butGuardar = (Button) findViewById(R.id.butGuardar);

            butGuardar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(eteRegUsuario.getText().toString().equalsIgnoreCase("") ||
                            eteRegPassword.getText().toString().equalsIgnoreCase("") ||
                            eteRegPassword3.getText().toString().equalsIgnoreCase("")){



                        new SweetAlertDialog(registro_activity.this)
                                .setContentText("Campos vacios, revisar!")
                                .show();

                    }else{

                        if(eteRegUsuario.getText().toString().equalsIgnoreCase("")){

                            new SweetAlertDialog(registro_activity.this)
                                    .setContentText("Ya existe el usuario, revisar!")
                                    .show();
                        }else{

                            if(eteRegPassword.getText().toString().equalsIgnoreCase(eteRegPassword3.getText().toString())){

                                Retrofit retrofit = new Retrofit.Builder()
                                        .baseUrl("https://ul-pokemon.herokuapp.com")
                                        .addConverterFactory(GsonConverterFactory.create())
                                        .build();

                                IPokemon usuariosService = retrofit.create(IPokemon.class);
                                Usuarios usuarios = new Usuarios();
                                Usuario usu= new Usuario();
                                usu.setUsername(eteRegUsuario.getText().toString());
                                usu.setPassword(eteRegPassword.getText().toString());
                                usuarios.setUsuario(usu);

                                Call<Respuesta> usuarioCall = usuariosService.createUser(usuarios);

                                usuarioCall.enqueue(new Callback<Respuesta>() {
                                    @Override
                                    public void onResponse(Call<Respuesta> call, Response<Respuesta> response) {
                                        Respuesta respuesta = response.body();
                                        int status = response.code();

                                        if(respuesta.getStatus().getMsg().equalsIgnoreCase("")){

                                            new SweetAlertDialog(registro_activity.this)
                                                    .setContentText("datos correctos!")
                                                    .show();
                                            Intent intent= new Intent(registro_activity.this,MainActivity.class);
                                            startActivity(intent);
                                        }else{
                                            new SweetAlertDialog(registro_activity.this)
                                                    .setContentText("error!")
                                                    .show();

                                        }


                                    }

                                    @Override
                                    public void onFailure(Call<Respuesta> call, Throwable t) {

                                    }
                                });



                            }else{
                                new SweetAlertDialog(registro_activity.this)
                                        .setContentText("las passwords no coinciden,revisar!    ")
                                        .show();
                            }






                        }

                    }

                }
            });

        }
}