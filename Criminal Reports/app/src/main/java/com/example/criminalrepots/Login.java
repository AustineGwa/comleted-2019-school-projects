package com.example.criminalrepots;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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

    EditText email, password;
    Button login, reset;
    ProgressDialog progressDialog;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.edemail);
        password = findViewById(R.id.adpass);
        login = findViewById(R.id.btlogin);
        reset = findViewById(R.id.btreset);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("validating user");
        progressDialog.setCanceledOnTouchOutside(false);
        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    protected void onStart() {
        super.onStart();

        login.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userEmail= email.getText().toString();
                String userPassword = password.getText().toString();

                if(TextUtils.isEmpty(userEmail) && TextUtils.isEmpty(userPassword)){
                    Toast.makeText(getApplicationContext(),"Please enter a valid email and password", Toast.LENGTH_SHORT).show();
                }else{
                    login(userEmail, userPassword);
                }

            }
        });

    }

    private void login(String userEmail, String userPassword) {

        progressDialog.show();
        mAuth.signInWithEmailAndPassword(userEmail, userPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser currentUser = mAuth.getCurrentUser();

                            if(currentUser.getEmail().equals("admin@gmail.com")){
                                startActivity(new Intent(getApplicationContext(), AdminHome.class));
                            }else {
                                startActivity(new Intent(getApplicationContext(), UserHome.class));
                            }
                            finish();

                        } else {
                            // If sign in fails, display a message to the user.

                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Authentication failed. "+task.getException().getMessage(), Toast.LENGTH_LONG).show();

                        }


                    }
                });

    }
}
