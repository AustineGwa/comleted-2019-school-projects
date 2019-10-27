package com.example.criminalrepots;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;

import com.example.criminalrepots.adapters.ReportsAdapter;
import com.example.criminalrepots.models.Complaint;
import com.example.criminalrepots.models.Law;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AdminHome extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Complaint> complaintList;
    RecyclerView.Adapter adapter;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        adapter = new ReportsAdapter(complaintList);
        recyclerView = findViewById(R.id.user_reports_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        complaintList = new ArrayList<>();

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("loading data....");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reff = database.getReference();

        reff.child("complaints").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Complaint complaint = dataSnapshot.getValue(Complaint.class);
                complaintList.add(complaint);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        adapter = new ReportsAdapter(complaintList);
        recyclerView.setAdapter(adapter);



    }
}
