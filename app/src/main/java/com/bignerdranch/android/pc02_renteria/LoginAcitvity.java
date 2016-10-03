package com.bignerdranch.android.pc02_renteria;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Clases.Usuario;
import Interfaces.IPokemon;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginAcitvity extends AppCompatActivity {
    EditText musuario,password;
    Button butLogin,butRegistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        musuario=(EditText) findViewById(R.id.usuario);
        password=(EditText) findViewById(R.id.password);
        butLogin=(Button) findViewById(R.id.butLogin);
        butRegistro=(Button) findViewById(R.id.butRegistro);
        butLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://ul-pokemon.herokuapp.com")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                Usuario usuario = new Usuario();

                usuario.setUsername(musuario.getText().toString());
                usuario.setPassword(password.getText().toString());

                IPokemon pokemonservice= retrofit.create(IPokemon.class);
                pokemonservice.basicLogin(usuario).enqueue(new Callback<Usuario>() {
                    @Override
                    public void onResponse(Call<Usuario> call, Response<Usuario> response) {




                                String respuesta=response.toString();
                                if (response.isSuccessful()){

                                    System.out.println("respuesta: " + response.toString());
                                    Log.i("res",respuesta);
                                    Intent intent = new Intent(LoginAcitvity.this, dashboard_activity.class);
                                    startActivity(intent);
                                }else{
                                    Log.i("Error", response.message());
                                }





                    }

                    @Override
                    public void onFailure(Call<Usuario> call, Throwable t) {
                        Log.d("Error", t.getMessage());
                    }
                });

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

}
