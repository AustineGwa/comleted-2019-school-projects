package com.gwazasoftwares.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.gwazasoftwares.myapplication.adapters.EmployeeListAdapter;
import com.gwazasoftwares.myapplication.models.Employee;
import com.gwazasoftwares.myapplication.tasks.OnEmployeeClickListener;

import java.util.ArrayList;
import java.util.List;

public class EmployeesView extends AppCompatActivity {
    RecyclerView recyclerView;
    List<Employee> employeeList;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employees_view);

        progressDialog = new ProgressDialog(this);
        // progressDialog.setCancelable(false);
        progressDialog.setMessage("loading data from server...");

        recyclerView = findViewById(R.id.employees_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        employeeList = new ArrayList<>();

    }

    @Override
    protected void onStart() {
        super.onStart();
        loadData();
    }

    public void  loadData(){

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference().child("standau").child("employees");

        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {


                // A new comment has been added, add it to the displayed list
                Employee employee = dataSnapshot.getValue(Employee.class);
                employeeList.add(employee);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {
//                Log.d(TAG, "onChildChanged:" + dataSnapshot.getKey());
//
//                // A comment has changed, use the key to determine if we are displaying this
//                // comment and if so displayed the changed comment.
//                Comment newComment = dataSnapshot.getValue(Comment.class);
//                String commentKey = dataSnapshot.getKey();
//
//                // ...
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
//                Log.d(TAG, "onChildRemoved:" + dataSnapshot.getKey());
//
//                // A comment has changed, use the key to determine if we are displaying this
//                // comment and if so remove it.
//                String commentKey = dataSnapshot.getKey();
//
//                // ...
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String previousChildName) {
//                Log.d(TAG, "onChildMoved:" + dataSnapshot.getKey());
//
//                // A comment has changed position, use the key to determine if we are
//                // displaying this comment and if so move it.
//                Comment movedComment = dataSnapshot.getValue(Comment.class);
//                String commentKey = dataSnapshot.getKey();
//
//                // ...
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
//                Log.w(TAG, "postComments:onCancelled", databaseError.toException());
//                Toast.makeText(mContext, "Failed to load comments.",
//                        Toast.LENGTH_SHORT).show();
            }
        };
        reference.addChildEventListener(childEventListener);

        //visitorList = loadVisitorsFromFirebase();
        EmployeeListAdapter employeeListAdapter = new EmployeeListAdapter(employeeList, new OnEmployeeClickListener() {
            @Override
            public void onEmployeeClick(Employee userClicked) {
                Intent intent = new Intent(getApplicationContext(),EmployeeDetails.class);
                intent.putExtra("employee", userClicked);
                startActivity(intent);
            }
        });

        recyclerView.setAdapter(employeeListAdapter);

    }

}
