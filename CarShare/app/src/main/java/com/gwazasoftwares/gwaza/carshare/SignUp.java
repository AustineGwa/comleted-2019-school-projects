package com.gwazasoftwares.gwaza.carshare;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {
    EditText username, password,confirmpass;
    Button createAccount;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        username = findViewById(R.id.edusername);
        password = findViewById(R.id.edpass);
        confirmpass = findViewById(R.id.edconfirmpass);
        createAccount = findViewById(R.id.btcreateuser);

        mAuth = FirebaseAuth.getInstance();




    }

    @Override
    protected void onStart() {
        super.onStart();
        createAccount.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
               String myUsername = username.getText().toString();
               String myPass =  password.getText().toString();
               createNewUser(myUsername,myPass);
            }
        });
    }

    private void createNewUser(String myUsername, String myPass) {

        mAuth.createUserWithEmailAndPassword(myUsername, myPass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(getApplicationContext(),"Account  created  succesfully", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(),"Account  not created "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }


}
