package com.example.creditscore;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class CreditScoreView extends AppCompatActivity {

    TextView score, rating;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_score_view);

        score = findViewById(R.id.tx_score);
        rating = findViewById(R.id.tx_rating);

        score.setText(getIntent().getStringExtra("score"));
        rating.setText(getIntent().getStringExtra("rating"));

       // Toast.makeText(getApplicationContext(),getIntent().getStringExtra("score"), Toast.LENGTH_SHORT).show();
    }
}
