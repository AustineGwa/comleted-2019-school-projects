package com.example.creditscore;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Splash extends Activity {
    private FirebaseAuth mAuth;
    private  boolean isSessionActive;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        isSessionActive = getSesionStatus();

        Thread thread  = new Thread(){

            @Override
            public  void run(){
                try {
                    Thread.sleep(3000);
                    if(isSessionActive){

                        Intent intent = new Intent(getApplicationContext(),UserDataEntry.class);
                        startActivity(intent);
                        finish();

                    }else {
                        Intent intent = new Intent(getApplicationContext(), Login.class);
                        startActivity(intent);
                        finish();
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }

    public boolean getSesionStatus() {

        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        isSessionActive = currentUser != null;
        return isSessionActive;
    }
}
