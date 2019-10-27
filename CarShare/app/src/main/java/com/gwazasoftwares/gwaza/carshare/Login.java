package com.gwazasoftwares.gwaza.carshare;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends Activity {

    EditText userName, password;
    Button login, signup;
    FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userName = findViewById(R.id.username);
        password = findViewById(R.id.passwordtxt);
        login = findViewById(R.id.btnlogin);
        signup = findViewById(R.id.btnsignup);

        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    protected void onStart() {
        super.onStart();

        login.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Cuser = userName.getText().toString().trim();
                String Cpass = password.getText().toString().trim();

                login(Cuser,Cpass);

            }
        });
        signup.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SignUp.class));
            }
        });

    }

    private void login(String user, String pass) {
        mAuth.signInWithEmailAndPassword(user, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();

                            Intent intent = new Intent(getApplicationContext(), Home.class);
                            startActivity(intent);
                            finish();

                        } else {

                            Toast.makeText(getApplicationContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }


                    }
                });
    }
}
