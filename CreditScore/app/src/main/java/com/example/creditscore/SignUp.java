package com.example.creditscore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class SignUp extends AppCompatActivity {

    private EditText username, password, email, confirmpassword;
    private Button submit, signIn;

    private ProgressDialog progressDialog;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        username = findViewById(R.id.txtusername);
        password = findViewById(R.id.txtpassword);
        email = findViewById(R.id.txtemail);
        confirmpassword = findViewById(R.id.txtconfirmpassword);

        submit=findViewById(R.id.btnsubmit);
        signIn = findViewById(R.id.btnsignin);

        progressDialog = new ProgressDialog(this);

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();

        submit.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userEmail = email.getText().toString().trim();
                String userPassword = password.getText().toString().trim();
                String confirmedPassword = confirmpassword.getText().toString().trim();

                if(TextUtils.isEmpty(userEmail)){
                    Toast.makeText(getApplicationContext(),"Please Enter Your email",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(userPassword)){
                    Toast.makeText(getApplicationContext(),"Please Enter Your password",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(userPassword.equals(confirmedPassword)) {

                    submit.setEnabled(false);

                    progressDialog.setMessage("Creating user Account");
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();
                    submit(userEmail, userPassword);
                }else{
                    Toast.makeText(getApplicationContext(),"password entered do not match confirmation password",Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });

        signIn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();

            }
        });

    }

    private void signIn() {
        Intent intent = new Intent(getApplicationContext(),Login.class);
        startActivity(intent);
        finish();
    }

    private void submit(String email, String password) {

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(),"Account Created Successfully you can now login ", Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();
                } else {

                    Toast.makeText(getApplicationContext(),"Error creating the account please try again  ", Toast.LENGTH_LONG).show();

                    progressDialog.dismiss();
                }

            }

        });



    }
}
