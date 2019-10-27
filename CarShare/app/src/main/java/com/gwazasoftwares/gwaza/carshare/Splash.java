package com.gwazasoftwares.gwaza.carshare;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class Splash extends Activity {

    boolean isActive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    checkIfUserActive();
                    if(isActive){
                        goToHome();

                    }else{
                        goToLogin();
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();



    }

    private void goToLogin() {

        Intent i = new Intent(getApplicationContext(), Login.class);
        startActivity(i);
        finish();
    }

    private void goToHome() {
        Intent i = new Intent(getApplicationContext(), Home.class);
        startActivity(i);
        finish();
    }

    private boolean checkIfUserActive() {
        String currentUser = null;
        isActive = currentUser != null;

        return  isActive;
    }
}
