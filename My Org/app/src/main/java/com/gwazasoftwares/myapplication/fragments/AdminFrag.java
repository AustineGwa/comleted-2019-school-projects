package com.gwazasoftwares.myapplication.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gwazasoftwares.myapplication.BranchesViewActivity;
import com.gwazasoftwares.myapplication.EmployeeDetails;
import com.gwazasoftwares.myapplication.EmployeesView;
import com.gwazasoftwares.myapplication.FeedBackViewActivity;
import com.gwazasoftwares.myapplication.Home;
import com.gwazasoftwares.myapplication.R;
import com.gwazasoftwares.myapplication.adapters.TwoItemAdapter;
import com.gwazasoftwares.myapplication.interfaces.OnHomeItemClickListener;
import com.gwazasoftwares.myapplication.models.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AdminFrag extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Service> services;


    public AdminFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_admin, container, false);
        recyclerView = view.findViewById(R.id.adminrecyclerview);
        recyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(getActivity(),3);
        recyclerView.setLayoutManager(layoutManager);
        services = new ArrayList<>();

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        Service Employees = new Service(R.drawable.group, "Employees");
        //Service Hierachy = new Service(R.drawable.group, "Org Hirachy");
        Service Branches = new Service(R.drawable.expense, "Branches");
        Service Feedback = new Service(R.drawable.reports, "Feedback");
        //Service Announcements = new Service(R.drawable.reports, "Announcements");

        OnHomeItemClickListener listener = new OnHomeItemClickListener() {
            @Override
            public void onHomeItemClick(View view, int position) {
                if(position == 0) {
                    startActivity(new Intent(getActivity(), EmployeesView.class));
                }else if(position == 1 ){
                    startActivity(new Intent(getActivity(), BranchesViewActivity.class));

                }else if(position == 2) {
                    startActivity(new Intent(getActivity(), FeedBackViewActivity.class));
                }else if(position == 3) {

                }else if(position == 4) {

                }
            }
        };

        services.clear();
        services.addAll(Arrays.asList(Employees,Branches,Feedback));
        adapter = new TwoItemAdapter(services, listener);
        recyclerView.setAdapter(adapter);
    }
}
