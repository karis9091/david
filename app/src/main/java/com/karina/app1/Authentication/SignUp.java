package com.karina.app1.Authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.karina.app1.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUp extends AppCompatActivity {

    EditText SingUpEmail, SignUpPassword;
    Button SignUpButton, GoToLoginButton;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        SingUpEmail= findViewById(R.id.TxtEmail);
        SignUpPassword= findViewById(R.id.TxtPassword);
        SignUpButton= findViewById(R.id.BtnSignUp);
        GoToLoginButton= findViewById(R.id.BtnGoLogin);
        mAuth = FirebaseAuth.getInstance();

        SignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signup();
            }
        });

        GoToLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUp.this, Login.class));
                //finish();
            }
        });

    }


    private void signup() {
        String email = SingUpEmail.getText().toString();
        String password = SignUpPassword.getText().toString();
        if (TextUtils.isEmpty(password) || (TextUtils.isEmpty(email))) {
            Context context = getApplicationContext();
// message to display
            String text = "campos vacios";
            Toast toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
// to show the toast
            toast.show();

        } else {
            Context context = getApplicationContext();
            String text = "bien hecho";
            Toast toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
            Toast.makeText(SignUp.this, "" + email+" "+password,Toast.LENGTH_SHORT).show();

            mAuth.createUserWithEmailAndPassword(email, password)
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

                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(SignUp.this, "" + e.getMessage(),Toast.LENGTH_SHORT).show();
                            //Log.d("si", "" + e.getMessage());

                        }
                    });
// to show the toast
            toast.show();

        }

    }
    /*private void signUp(){

        String email = SingUpEmail.getText().toString();
        String password = SignUpPassword.getText().toString();

        mAuth.createUserWithEmailAndPassword(email, password)
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
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(SignUp.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("registro", e.getMessage());

            }
        });

    }*/

    private void updateUI(FirebaseUser user) {
        if (user != null) {
            Toast.makeText(SignUp.this, "Verificacion exitosa",
                    Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(SignUp.this, "Error de verificacion",
                    Toast.LENGTH_SHORT).show();
        }
    }
}