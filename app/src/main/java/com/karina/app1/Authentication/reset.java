package com.karina.app1.Authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.karina.app1.R;

public class reset extends AppCompatActivity {

    EditText emailRes;
    Button Enviar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);

        emailRes= findViewById(R.id.txtResetPassword);
        Enviar= findViewById(R.id.btnEnviar);


        Enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reset();
            }
        });
    }

    private void reset() {


        FirebaseAuth auth = FirebaseAuth.getInstance();
        String emailAddress = emailRes.getText().toString();

        auth.sendPasswordResetEmail(emailAddress)
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful()) {
                            Toast.makeText(reset.this, "Correo registrado, le mandamos un enlace a su correo",
                                    Toast.LENGTH_SHORT).show();

                        } else {
                            // If it fails, display a message to the user.
                            Toast.makeText(reset.this, "Correo No registrado.",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                });





    }

}