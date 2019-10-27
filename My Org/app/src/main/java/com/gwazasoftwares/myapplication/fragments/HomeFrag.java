package com.gwazasoftwares.myapplication.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gwazasoftwares.myapplication.AnnouncementsViewActivity;
import com.gwazasoftwares.myapplication.Home;
import com.gwazasoftwares.myapplication.R;
import com.gwazasoftwares.myapplication.adapters.TwoItemAdapter;
import com.gwazasoftwares.myapplication.dialogs.IssueReportingDialogue;
import com.gwazasoftwares.myapplication.dialogs.RequestLeaveDialog;
import com.gwazasoftwares.myapplication.interfaces.OnHomeItemClickListener;
import com.gwazasoftwares.myapplication.models.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFrag extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Service> services;

    public HomeFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_home, container, false);
       recyclerView = view.findViewById(R.id.homerecyclerview);
       recyclerView.setHasFixedSize(true);
       layoutManager = new GridLayoutManager(getActivity(),3);
       recyclerView.setLayoutManager(layoutManager);
       services = new ArrayList<>();

       return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        Service Announcements = new Service(R.drawable.group,"Announcements");
        Service Reports = new Service(R.drawable.reports, "Issue Reporting");
        Service Leave = new Service(R.drawable.group,"Request Leave");



        OnHomeItemClickListener listener = new OnHomeItemClickListener() {
            @Override
            public void onHomeItemClick(View view, int position) {
                if(position == 0) {
                    startActivity(new Intent(getActivity(), AnnouncementsViewActivity.class));
                }else if(position == 1 ){
                      showCreateDialog(new IssueReportingDialogue());

                }else if(position == 2){
                    showCreateDialog(new RequestLeaveDialog());
                }
            }
        };

        services.clear();
        services.addAll(Arrays.asList(Announcements,Reports,Leave));
        adapter = new TwoItemAdapter(services, listener);
        recyclerView.setAdapter(adapter);


    }

    private void showCreateDialog(DialogFragment dialog) {

        dialog.show(getFragmentManager(),"Dialog");
    }
}
