package com.bignerdranch.android.pc02_renteria;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Clases.Usuario;
import Clases.Respuesta;
import Interfaces.IPokemon;
import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginAcitvity extends AppCompatActivity {
    EditText musuario,password;
    Button butLogin,butRegistro;
    int id=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if(savedInstanceState!=null){
            id=savedInstanceState.getInt("id");
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        musuario=(EditText) findViewById(R.id.usuario);
        password=(EditText) findViewById(R.id.password);
        butLogin=(Button) findViewById(R.id.butLogin);
        butRegistro=(Button) findViewById(R.id.butRegistro);
        butLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (musuario.getText().toString().equalsIgnoreCase("") || password.getText().toString().equalsIgnoreCase("")) {

                     new SweetAlertDialog(LoginAcitvity.this)
                            .setContentText("Campos vacios, revisar!")
                            .show();

                } else {
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("https://ul-pokemon.herokuapp.com")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    Usuario usuario = new Usuario();

                    usuario.setUsername(musuario.getText().toString());
                    usuario.setPassword(password.getText().toString());


                    IPokemon pokemonservice = retrofit.create(IPokemon.class);
                    Call<Respuesta> usuarioCall = pokemonservice.basicLogin(usuario);



                    usuarioCall.enqueue(new Callback<Respuesta>() {
                        @Override
                        public void onResponse(Call<Respuesta> call, Response<Respuesta> response) {

                            Respuesta rpta = response.body();
                            int estado = response.code();

                            if (rpta.getMsg().equalsIgnoreCase("")) {

                                id = rpta.getUsuario().getId();
                                Intent intent = new Intent(LoginAcitvity.this, dashboard_activity.class);
                                intent.putExtra("id", id);

                                startActivity(intent);

                            } else {
                                new SweetAlertDialog(LoginAcitvity.this)
                                        .setContentText(rpta.getMsg().toString())
                                        .show();
                            }



                        }

                        @Override
                        public void onFailure(Call<Respuesta> call, Throwable t) {

                        }
                    });


                }
            }
        });
        butRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(LoginAcitvity.this,registro_activity.class);
                startActivity(intent);
            }
        });

    }
    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState){
        savedInstanceState.putInt("id", id);
        super.onSaveInstanceState(savedInstanceState);
    }

}
