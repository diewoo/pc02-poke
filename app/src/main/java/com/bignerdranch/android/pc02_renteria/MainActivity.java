package com.bignerdranch.android.pc02_renteria;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ImageView imagen;

            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            imagen=(ImageView)findViewById(R.id.logo);
            new CountDownTimer(3000,1000){
                @Override
                public void onTick(long millisUntilFinished){}

                @Override
                public void onFinish(){


                    Intent intentlogin=new Intent(MainActivity.this, LoginAcitvity.class);
                    startActivity(intentlogin);
                }
            }.start();



        }
}
