package com.karina.app1.Authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.karina.app1.BottomNavigation.Home;
import com.karina.app1.Intents.MainActivity;
import com.karina.app1.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class Login extends AppCompatActivity {

    EditText LoginEmail, LoginPassword;
    Button LoginButton, GoToSignUpButton, GoToReset;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        LoginEmail = findViewById(R.id.TxtEmailLogin);
        LoginPassword = findViewById(R.id.TxtPasswordLogin);
        LoginButton = findViewById(R.id.BtnLogin);
        GoToSignUpButton = findViewById(R.id.BtnCrearCuenta);
        GoToReset= findViewById(R.id.BtnReset);
        mAuth = FirebaseAuth.getInstance();

        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(Login.this, MainActivity.class));
            }
        });

        GoToSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, SignUp.class));
                //finish();
            }
        });

        GoToReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(
                        Login.this, reset.class));
                //finish();
            }
        });
    }

    private void login() {
        String email = LoginEmail.getText().toString();
        String password = LoginPassword.getText().toString();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.

                            updateUI(null);
                        }
                    }
                });
    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {
            goToHome();

        }
            /*Toast.makeText(Login.this, "Sesion Iniciada", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(Login.this, Home.class));
        } else {
            Toast.makeText(Login.this, "Usuario No registrado.",
                    Toast.LENGTH_SHORT).show();
        }*/
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            goToHome();
        }
    }

    private void goToHome() {
        startActivity(new Intent(Login.this, Home.class));
        finish();
    }
}