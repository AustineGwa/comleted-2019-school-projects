package com.gwazasoftwares.gwaza.carshare;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.gwazasoftwares.gwaza.carshare.models.Post;

public class PostCar extends AppCompatActivity {
  Button post;
  EditText driverName;
  EditText carOwner;
  EditText size;
  EditText destination, numberPlate;
  EditText dateOfTravel;
  ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_car);

        post = findViewById(R.id.btnpostcar);
        driverName = findViewById(R.id.eddrivername);
        carOwner = findViewById(R.id.edcarowner);
        size = findViewById(R.id.edcarsize);
        destination = findViewById(R.id.eddestination);
        dateOfTravel = findViewById(R.id.eddate);
        numberPlate = findViewById(R.id.ednoplate);


        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("uploading data...");
    }

    @Override
    protected void onStart() {
        super.onStart();

        post.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                String mDriverName = driverName.getText().toString();
                String mCarOwner = carOwner.getText().toString();
                String mSize = size.getText().toString();
                String mDestination = destination.getText().toString();
                String mDateOfTravel = dateOfTravel.getText().toString();
                String mNumberPlate = numberPlate.getText().toString();

                postToDb(mDriverName,mCarOwner,mSize,mDestination,mDateOfTravel,mNumberPlate);
            }
        });
    }

    private void postToDb(String mDriverName, String mCarOwner, String mSize, String mDestination, String mDateOfTravel,String mNumberPlate) {

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference();

        Post post = new Post(mDriverName,mCarOwner,mSize,mDateOfTravel,mDestination,mNumberPlate);
        progressDialog.show();
        databaseReference.child("available-cars").push().setValue(post).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Car posted successfully",Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }
        });



    }
}
