package com.example.smartambulance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    Button btnLogin, btnSignUp, btnReset;
    EditText username, password;

    ProgressDialog progressDialog;


    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = findViewById(R.id.btnlogin);
        btnSignUp = findViewById(R.id.btnsignup);
        btnReset = findViewById(R.id.btnreset);

        username=findViewById(R.id.txtloginusername);
        password=findViewById(R.id.txtloginpassword);

        progressDialog= new ProgressDialog(this);


        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);

        btnLogin.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userEmail=username.getText().toString().trim();
                String userPassword=password.getText().toString().trim();

                if(TextUtils.isEmpty(userEmail)){
                    Toast.makeText(getApplicationContext(),"Please Enter Your username",Toast.LENGTH_SHORT).show();
                    return;

                }else  if(TextUtils.isEmpty(userPassword)){
                    Toast.makeText(getApplicationContext(),"Please Enter Your password",Toast.LENGTH_SHORT).show();
                    return;

                }else {

                    progressDialog.setMessage("Verifying  user  credentials...");
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();
                    login(userEmail, userPassword);
                }

            }
        });

        btnSignUp.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(),SignUp.class);
//                startActivity(intent);
//                finish();
            }
        });

//        btnReset.setOnClickListener(new Button.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(),Reset.class);
//                startActivity(intent);
//                finish();
//            }
//        });
    }

    private void updateUI(FirebaseUser currentUser) {
    }

    private void login(String email,String password) {

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG", "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            gotoHome();
                            progressDialog.dismiss();

                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("TAG", "signInWithEmail:failure", task.getException());
                            Toast.makeText(Login.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();

                        }

                        // ...
                    }


                });

    }

    private void gotoHome() {
        Intent intent = new Intent(getApplicationContext(),Home.class);
        startActivity(intent);
        finish();
    }
}
