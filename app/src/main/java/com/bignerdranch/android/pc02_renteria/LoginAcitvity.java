package com.bignerdranch.android.pc02_renteria;
/**
 * Created by Diego Renteria on 02/10/2016.
 */
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Clases.Respuesta;
import Clases.Usuario;
import Interfaces.IPokemon;
import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.bignerdranch.android.pc02_renteria.R.id.butRegistro;

public class LoginAcitvity extends AppCompatActivity {
    EditText musuario,password;
    Button butLogin,butRegistro;
    String username="";
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
                             .setTitleText("Alerta!")
                            .setContentText("Campos vacios, revisar!")
                            .show();

                } else {
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("https://ulima-parcial.herokuapp.com/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    Usuario usuario = new Usuario();

                    usuario.setUsername(musuario.getText().toString());
                    usuario.setPassword(password.getText().toString());


                    IPokemon usuariosService = retrofit.create(IPokemon.class);
                    Call<Respuesta> usuarioCall = usuariosService.login(usuario);

                    usuarioCall.enqueue(new Callback<Respuesta>() {
                        @Override
                        public void onResponse(Call<Respuesta> call, Response<Respuesta> response) {
                            Respuesta r=response.body();
                            int status = response.code();

                            if(r.getStatus().getCod()==1){
                                username = r.getUser().getUsername();
                                Intent intent= new Intent(LoginAcitvity.this,dashboard_activity.class);
                                intent.putExtra("username",username);
                                Log.i("MainActivity","id"+username);
                                startActivity(intent);

                            }else{
                                new SweetAlertDialog(LoginAcitvity.this)
                                        .setTitleText("Alerta!")
                                        .setContentText(r.getStatus().getMsg().toString())
                                        .show();

                            }
                            Log.d("MainActivity","STATUS: " + status);



                        }

                        @Override
                        public void onFailure(Call<Respuesta> call, Throwable t) {
                            Log.e("MainActivity", t.getMessage());
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
    @Override
    public void onBackPressed() {
        // disable going back to the MainActivity

        moveTaskToBack(true);
    }

}
