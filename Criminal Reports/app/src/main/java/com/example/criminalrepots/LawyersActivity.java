package com.example.criminalrepots;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.criminalrepots.adapters.LawyersAdapter;
import com.example.criminalrepots.interfaces.SetOnLawyerClicked;
import com.example.criminalrepots.models.Lawyer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LawyersActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<Lawyer> lawList;
    RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lawyers);

        recyclerView = findViewById(R.id.lawyers);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        lawList = new ArrayList<>();

        Lawyer lawyer = new Lawyer("James Orengo",40, "Licenced");
        Lawyer lawyer1 = new Lawyer("John  Kimani",10, "Licenced");
        Lawyer lawyer2 = new Lawyer("Simon Kapelo",5, "Licenced");

        lawList.addAll(Arrays.asList(lawyer,lawyer1,lawyer2));
        adapter = new LawyersAdapter(lawList, new SetOnLawyerClicked() {
            @Override
            public void OnLawyerClicked(Lawyer clickedLawyer) {

                Intent intent = new Intent(getApplicationContext(),LawyerDetails.class);
                intent.putExtra("ClickedLawyer",clickedLawyer );
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);
    }
}
