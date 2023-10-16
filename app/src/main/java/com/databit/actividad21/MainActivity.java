package com.databit.actividad21;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private EditText ETNombre;
    private Button BTAceptar;
    private Button run;
    private ImageView imageView;
    private SensorManager sensorManager;
    private Sensor sensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(
                Sensor.TYPE_ROTATION_VECTOR);
        BTAceptar = findViewById(R.id.BTAceptar);
        run = findViewById(R.id.run);
        ETNombre = findViewById(R.id.ETNombreFoto);
        imageView = findViewById(R.id.imageView2); // Make sure you have an ImageView with this ID in your layout.

        BTAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String StNombre = ETNombre.getText().toString();
                Intent sIntent = new Intent(MainActivity.this, ResultadoActivity.class);
                sIntent.putExtra("STNombre", StNombre);
                startActivity(sIntent);
            }
        });

        run.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    public void run() {
                        final Bitmap bitmap = processBitMap("https://cdn.wamiz.fr/cdn-cgi/image/format=auto,quality=80,width=1200,height=1200,fit=cover/article/main-picture/5c5328bde77a3957455947.jpg");
                        runOnUiThread(new Runnable() {
                            public void run() {
                                imageView.setImageBitmap(bitmap);
                            }
                        });
                    }
                }).start();
            }
        });

    }

    private Bitmap processBitMap(String imageUrl) {
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(input);
            return bitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}


