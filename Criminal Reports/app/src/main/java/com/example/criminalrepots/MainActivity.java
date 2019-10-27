package com.example.criminalrepots;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends Activity {

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mAuth = FirebaseAuth.getInstance();

        final Thread thread = new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    sleep(3000);
                    if(mAuth.getCurrentUser() == null){
                        startActivity(new Intent(getApplicationContext(), Login.class));
                        finish();
                    }else{

                        startActivity(new Intent(getApplicationContext(), UserHome.class));
                        finish();
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        thread.start();
    }
}
