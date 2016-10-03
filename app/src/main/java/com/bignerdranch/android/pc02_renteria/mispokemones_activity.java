package com.bignerdranch.android.pc02_renteria;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class mispokemones_activity extends AppCompatActivity {
    int posicioh=1;
    int code;
    String modo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mispokemones_activity);
        final Button bMenu = (Button) findViewById(R.id.butMenu);

        final Button posAdelante = (Button) findViewById(R.id.butSiguiente);
        final Button posAtras = (Button) findViewById(R.id.butAtras);


        new Thread() {
            @Override
            public void run() {
                try {
                    seteardatos();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                bMenu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(mispokemones_activity.this, dashboard_activity.class);
                        startActivity(intent);
                    }
                });
                posAdelante.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        modo = "adelante";
                        posicioh++;
                        try {
                            seteardatos();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
                posAtras.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        modo = "atras";
                        posicioh--;
                        try {
                            seteardatos();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }.start();

    }

        private String obtenerContenido(String
        ruta) throws IOException {
            InputStream is = null;
            try {
                URL url = new URL(ruta);
                HttpURLConnection conn =
                        (HttpURLConnection) url.openConnection();
                conn.setConnectTimeout(5000);
                conn.setReadTimeout(20000);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                conn.setDoOutput(true);

                conn.connect();


                code = conn.getResponseCode();

                if (code == 200){
                    is = conn.getInputStream();

                    return convertInputStreamToString(is);
                }else{
                    return "Error:" + conn.getResponseMessage();
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
                return e.getMessage();
            } finally {
                if (is!= null){
                    is.close();
                }
            }
        }

        private String convertInputStreamToString(InputStream is) throws IOException {
            BufferedReader r =
                    new BufferedReader(new InputStreamReader(is));
            StringBuilder total = new StringBuilder();
            String line;
            while ((line = r.readLine()) != null) {
                total.append(line).append('\n');
            }
            return total.toString();
        }

        private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
            ImageView bmImage;

            public DownloadImageTask(ImageView bmImage) {
                this.bmImage = bmImage;
            }

            protected Bitmap doInBackground(String... urls) {
                String urldisplay = urls[0];
                Bitmap mIcon11 = null;
                try {
                    InputStream in = new java.net.URL(urldisplay).openStream();
                    mIcon11 = BitmapFactory.decodeStream(in);
                } catch (Exception e) {
                    Log.e("Error", e.getMessage());
                    e.printStackTrace();
                }
                return mIcon11;
            }

            protected void onPostExecute(Bitmap result) {
                bmImage.setImageBitmap(result);
            }
        }

        public void seteardatos() throws IOException, JSONException {

            TextView txtpoke = (TextView) findViewById(R.id.tviPokemon);
            TextView lvlpoke = (TextView) findViewById(R.id.tviNivel);
            TextView tipopoke = (TextView) findViewById(R.id.tviTipo);


            String resp = obtenerContenido(
                    "https://ul-pokemon.herokuapp.com/usuarios/"+posicioh+"/pokemones" );
            JSONObject root = new JSONObject(resp);
            String url = root.getString("url");
            String nombre = root.getString("nombre");
            String tipo = root.getString("tipo");
            int nivel = root.getInt("nivel");
            String descpoke = root.getString("descripcion");

            if (code==200) {
                new DownloadImageTask((ImageView) findViewById(R.id.imageView)).execute(url);
                txtpoke.setText(nombre);
                lvlpoke.setText(nivel);
                tipopoke.setText(tipo);

            }else{
                if (modo.equals("adelante")){
                    posicioh--;
                }else{
                    posicioh++;
                }
                posicioh++;




            }
        }

    }

