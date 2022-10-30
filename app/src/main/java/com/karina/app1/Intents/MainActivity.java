package com.karina.app1.Intents;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.karina.app1.Authentication.Login;
import com.karina.app1.BottomNavigation.Home;
import com.karina.app1.R;

public class MainActivity extends AppCompatActivity {

    Button explicito, implicito;
    EditText lugar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        explicito = findViewById(R.id.BotonExp);
        lugar = findViewById(R.id.TxtLugar);
        implicito = findViewById(R.id.BotonImp);

        explicito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                metodoExplicito();
            }
        });

        implicito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BuscadorImplicito();
            }
        });

        Log.d("consola", "OnCreate()");
    }
            //Cambio de Pantalla
    private void metodoExplicito() {
        Intent intent = new Intent(MainActivity.this, Boton.class);
        intent.putExtra("nombre","Abraham Navarrete");
        intent.putExtra("edad","22");
        startActivity(intent);
        //finish();

    }


            //BUSCADOR EN MAPA
    private void BuscadorImplicito(){
        /*Uri uri = Uri.parse("geo:0,0?q=" + lugar.getText().toString());
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(uri);
        intent.setPackage("com.google.android.apps.maps");
        startActivity(intent);*/

        /*Intent intent2 = new Intent();
        intent2.setAction(Intent.ACTION_SEND);
        intent2.putExtra(Intent.EXTRA_TEXT,lugar.getText().toString());
        intent2.setType("text/plain");

        startActivity(intent2);*/

        Intent intent3 = new Intent();
        intent3.setAction(Intent.ACTION_DIAL);
        intent3.setData(Uri.parse("tel:" + lugar.getText().toString()));
        startActivity(intent3);

    }







    //CLICLO DE VIDA



    @Override
    protected void onStart() {
        super.onStart();
        Log.d("consola", "OnStart()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("consola", "OnPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("consola", "OnStop()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("consola", "OnResume()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("consola", "OnDestroy()");
    }


}