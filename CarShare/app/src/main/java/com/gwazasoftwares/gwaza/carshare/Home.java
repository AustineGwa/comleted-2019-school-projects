package com.gwazasoftwares.gwaza.carshare;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.gwazasoftwares.gwaza.carshare.adapters.HomeAdapter;
import com.gwazasoftwares.gwaza.carshare.interfaces.OnPostClick;
import com.gwazasoftwares.gwaza.carshare.models.Post;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {

    RecyclerView carsRecyclerView;
    RecyclerView.LayoutManager mLayout;
    RecyclerView.Adapter adapter;
    List<Post> posts;
    OnPostClick onPostClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        carsRecyclerView = findViewById(R.id.homerecyclerview);
        mLayout = new LinearLayoutManager(this);

        populateList();

    }

    private void populateList() {
        posts = new ArrayList<>();

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference ref = firebaseDatabase.getReference().child("available-cars");
        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                posts.add(dataSnapshot.getValue(Post.class));
                setUpRv();
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

        carsRecyclerView.setLayoutManager(mLayout);
        carsRecyclerView.setHasFixedSize(true);
    }

    private void setUpRv() {
        OnPostClick onPostClick = new OnPostClick() {
            @Override
            public void postClick(View v, int position) {
                Intent intent = new Intent(getApplicationContext(), Details.class);
                Post currentPost = posts.get(position);
                intent.putExtra("currentPost",currentPost);
                startActivity(intent);
            }
        };
        adapter = new HomeAdapter(posts, onPostClick);
        carsRecyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return true;  //super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if(id == R.id.nav_settings){
//            Intent i = new Intent(getApplicationContext(),SettingsActivity.class);
//            startActivity(i);
        }else if(id == R.id.notification){
            Toast.makeText(getApplicationContext(), "no notifications  available", Toast.LENGTH_SHORT).show();
        }else if(id == R.id.post){
                 Intent i = new Intent(getApplicationContext(),PostCar.class);
                 startActivity(i);
        }else {


         }
        return super.onOptionsItemSelected(item);
    }
}
