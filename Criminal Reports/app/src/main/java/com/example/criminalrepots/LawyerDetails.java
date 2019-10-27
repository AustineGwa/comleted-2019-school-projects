package com.example.criminalrepots;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.criminalrepots.models.Lawyer;

public class LawyerDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lawyer_details);
        Lawyer incomingName = (Lawyer) getIntent().getSerializableExtra("ClickedLawyer");
        getSupportActionBar().setTitle(incomingName.getName());
    }
}
