package com.gwazasoftwares.myapplication.fragments;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.gwazasoftwares.myapplication.Admin;
import com.gwazasoftwares.myapplication.Home;
import com.gwazasoftwares.myapplication.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFrag extends Fragment {

    private EditText email, password;
    private Button login, reset;
    private ProgressDialog progressDialog;
    FirebaseAuth mAuth;

    public LoginFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_login, container, false);

        email = view.findViewById(R.id.edemail);
        password = view.findViewById(R.id.adpass);
        login = view.findViewById(R.id.btlogin);
        reset = view.findViewById(R.id.btreset);
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("validating user");
        progressDialog.setCanceledOnTouchOutside(false);
        mAuth = FirebaseAuth.getInstance();
        reset.setVisibility(View.GONE);


       return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        reset.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.auth_holder, new ResetFrag());
                transaction.commit();
            }
        });

        login.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userEmail= email.getText().toString();
                String userPassword = password.getText().toString();

                if(TextUtils.isEmpty(userEmail) && TextUtils.isEmpty(userPassword)){
                    Toast.makeText(getActivity(),"Please enter a valid email and password", Toast.LENGTH_SHORT).show();
                }else{
                    progressDialog.show();
                    login(userEmail, userPassword);
                }

            }
        });


    }

    private void login(String userEmail, String userPassword) {

        mAuth.signInWithEmailAndPassword(userEmail, userPassword)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            FirebaseUser incomingUser  = mAuth.getCurrentUser();
                            showLoggedInUserHome(incomingUser.getEmail());
                            getActivity().finish();

                        } else {
                            // If sign in fails, display a message to the user.
                            progressDialog.dismiss();
                            Toast.makeText(getActivity(), "Authentication failed. " + task.getException().getMessage(), Toast.LENGTH_LONG).show();

                        }
                    }
                });
         }

    private void showLoggedInUserHome(String email) {

        if(email.equals("admin@gmail.com")){
            startAdmin();
        }else{
            startHome();
        }
    }

    private void startHome() {
        Intent i = new Intent(getActivity(), Home.class);
        startActivity(i);
    }

    private void startAdmin() {
        Intent i = new Intent(getActivity(), Admin.class);
        startActivity(i);
    }
}
