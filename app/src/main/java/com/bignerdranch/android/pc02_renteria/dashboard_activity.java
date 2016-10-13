package com.bignerdranch.android.pc02_renteria;
/**
 * Created by Diego Renteria on 02/10/2016.
 */
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class dashboard_activity extends AppCompatActivity {
    String username="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_activity);
        Button butMisPokemones= (Button) findViewById(R.id.butMisPokemones);
        Button butPokemonesDisponibles = (Button) findViewById(R.id.butPokemonesDisponibles);
        Typeface tf = Typeface.createFromAsset(getAssets(),"font/FFF_Tusj.ttf");
        butMisPokemones.setTypeface(tf);
        butPokemonesDisponibles.setTypeface(tf);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            username = extras.getString("username");
        }
        butMisPokemones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dashboard_activity.this,mispokemones_activity.class);
                intent.putExtra("username",username);
                startActivity(intent);
            }
        });
        butPokemonesDisponibles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dashboard_activity.this,atrapar_activity .class);
                intent.putExtra("username",username);
                startActivity(intent);
            }
        });
    }
    public void onBackPressed() {
        // disable going back to the MainActivity
        new AlertDialog.Builder(this)
                .setTitle("Really Exit?")
                .setMessage("Are you sure you want to exit?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        moveTaskToBack(true);
                    }
                }).create().show();
        moveTaskToBack(true);
    }

    }

