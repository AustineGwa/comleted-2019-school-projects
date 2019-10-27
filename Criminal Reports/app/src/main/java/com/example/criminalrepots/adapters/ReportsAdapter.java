package com.example.criminalrepots.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.criminalrepots.R;
import com.example.criminalrepots.models.Complaint;
import com.example.criminalrepots.models.Law;

import java.util.List;

public class ReportsAdapter extends RecyclerView.Adapter<ReportsAdapter.LawsViewHolder> {

    private List<Complaint> complaintList;

    public ReportsAdapter(List<Complaint> complaintList) {
        this.complaintList = complaintList;
    }

    @NonNull
    @Override
    public LawsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.laws_row, parent,false);
        return new LawsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LawsViewHolder holder, int position) {
        holder.crimeType.setText(String.format("%s : %s", complaintList.get(position).getUserMessage(), complaintList.get(position).getComplaintDate()));
        holder.punishment.setText(String.format("%s : %s", complaintList.get(position).getUserEmail(), complaintList.get(position).getReportType()));
    }

    @Override
    public int getItemCount() {
        return complaintList.size();
    }

    class LawsViewHolder extends RecyclerView.ViewHolder{

       private  TextView crimeType;
       private  TextView punishment;
        public LawsViewHolder(@NonNull View itemView) {
            super(itemView);

            crimeType = itemView.findViewById(R.id.crime_type);
            punishment = itemView.findViewById(R.id.punishment);
        }
    }
}
