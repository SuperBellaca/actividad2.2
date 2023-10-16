package com.databit.actividad21;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class ResultadoActivity extends AppCompatActivity {
    Button volver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);
        volver=(Button)findViewById(R.id.volver);
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResultadoActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        Intent intent = getIntent();
        if (intent != null) {
            String stNombre = intent.getStringExtra("STNombre");

            // Encuentra el TextView en el layout
            TextView textView = findViewById(R.id.txtViewResultado);

            // Verifica si el TextView existe y establece el valor recibido
            if (textView != null) {
                textView.setText(stNombre);
            }
        }

    }

}