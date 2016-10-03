package com.bignerdranch.android.pc02_renteria;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Clases.Usuario;
import Clases.Usuarios;
import Interfaces.iPokemonResgistro;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class registro_activity extends AppCompatActivity {
    EditText eteRegUsuario;
    EditText eteRegPassword;
    EditText eteRegPassword2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro_activity);
        eteRegUsuario = (EditText) findViewById(R.id.eteRegUsuario);
        eteRegPassword = (EditText) findViewById(R.id.eteRegPassword);
        eteRegPassword2 = (EditText) findViewById(R.id.eteRegPassword2);
        Button butGuardar = (Button) findViewById(R.id.butGuardar);
        butGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (eteRegUsuario.getText().toString().equalsIgnoreCase("") ||
                        eteRegPassword.getText().toString().equalsIgnoreCase("") ||
                        eteRegPassword2.getText().toString().equalsIgnoreCase("")) {


                    Toast.makeText(registro_activity.this, "Falta llenar campos", Toast.LENGTH_SHORT).show();
                }else {
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("https://ul-pokemon.herokuapp.com")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    iPokemonResgistro pokemonService = retrofit.create(iPokemonResgistro.class);
                    Usuario user1=new Usuario();
                    user1.setUsername(eteRegUsuario.getText().toString());
                    user1.setPassword(eteRegPassword.getText().toString());

                    Usuarios user=new Usuarios();
                    user.setUsuario(user1);


                    pokemonService.createUser(user).enqueue(new Callback<Usuarios>() {
                        @Override
                        public void onResponse(Call<Usuarios> call, Response<Usuarios> response) {

                            System.out.println("Response: " + response.body()   );
                            System.out.println("Response: " + response.isSuccessful());

                        }

                        @Override
                        public void onFailure(Call<Usuarios> call, Throwable t) {

                        }
                    });
                }
            }
        });
    }
}
