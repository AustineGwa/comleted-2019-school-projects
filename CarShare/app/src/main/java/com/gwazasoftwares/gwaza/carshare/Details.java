package com.gwazasoftwares.gwaza.carshare;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.gwazasoftwares.gwaza.carshare.FirebaseUtils.FirebaseUtils;
import com.gwazasoftwares.gwaza.carshare.models.JoinCarRequest;
import com.gwazasoftwares.gwaza.carshare.models.Post;

public class Details extends AppCompatActivity {

    Button joinCar;
    TextView carType, noPlate, driverName,carOwner,spacesAvailable, date;
    private Post post;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        joinCar = findViewById(R.id.btnjoin);
        noPlate = findViewById(R.id.noplate);
        carType = findViewById(R.id.size);
        driverName = findViewById(R.id.drivername);
        carOwner = findViewById(R.id.owner);
        spacesAvailable = findViewById(R.id.txspaces);
        date = findViewById(R.id.date);

        post = (Post) getIntent().getSerializableExtra("currentPost");
    }

    @Override
    protected void onStart() {
        super.onStart();
        initValues();


        joinCar.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                String myCarOwner = carOwner.getText().toString();
                DatabaseReference databaseReference = FirebaseDatabase.getInstance()
                                                      .getReference().child("car-requests")
                                                      .child(myCarOwner);

                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                String userEmail = mAuth.getCurrentUser().getEmail();

                databaseReference.push().setValue(new JoinCarRequest(userEmail)).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(getApplicationContext(), "Request  was successfull wait for feedback", Toast.LENGTH_LONG).show();
                    }
                });


            }
        });
    }

    private void initValues() {
        carOwner.setText(post.getCarOwner());
        carType.setText("...");
        driverName.setText(post.getDriverName());
        spacesAvailable.setText(post.getSize());
        date.setText(post.getDateOfTravel());
        noPlate.setText(post.getNumberPlate());
    }
}
