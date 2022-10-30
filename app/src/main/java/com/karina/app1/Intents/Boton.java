package com.karina.app1.Intents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.karina.app1.R;

public class Boton extends AppCompatActivity {

    Button regresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boton);

        regresar = findViewById(R.id.BotonReg);

        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                metodoRegresar();
            }
        });


        Intent intentActividad1 = getIntent();
        String nombre = intentActividad1.getStringExtra("nombre");
        int edad = intentActividad1.getIntExtra("edad", 15);

        String texto;

        if (edad >= 18){
            texto = "Mayor de edad";
        } else{
            texto = "Menor de edad";
        }

        Toast.makeText(this, texto, Toast.LENGTH_SHORT).show();
    }
    private void metodoRegresar() {
        Intent intent = new Intent(Boton.this, MainActivity.class);
        startActivity(intent);
        //finish();
    }


}