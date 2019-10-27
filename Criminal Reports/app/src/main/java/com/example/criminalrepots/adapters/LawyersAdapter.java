package com.example.criminalrepots.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.criminalrepots.R;
import com.example.criminalrepots.interfaces.SetOnLawyerClicked;
import com.example.criminalrepots.models.Lawyer;

import java.util.List;

public class LawyersAdapter extends RecyclerView.Adapter<LawyersAdapter.LawyersViewHolder> {

    private List<Lawyer> lawyerList;
    private SetOnLawyerClicked setOnLawyerClicked;

    public LawyersAdapter(List<Lawyer> lawyerList, SetOnLawyerClicked setOnLawyerClicked) {
        this.lawyerList = lawyerList;
        this.setOnLawyerClicked = setOnLawyerClicked;
    }

    @NonNull
    @Override
    public LawyersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lawyer_row,parent,false);
        LawyersViewHolder lawyersViewHolder = new LawyersViewHolder(view, setOnLawyerClicked);
        return lawyersViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LawyersViewHolder holder, int position) {
        holder.lawyerName.setText(lawyerList.get(position).getName());
       // holder.yearsOfExperience.setText(lawyerList.get(position).getYearsOfExperience());
        holder.licence.setText(lawyerList.get(position).getLicenced());

    }

    @Override
    public int getItemCount() {
        return lawyerList.size();
    }

    class LawyersViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView lawyerName, yearsOfExperience, licence;
        SetOnLawyerClicked setOnLawyerClicked;

        public LawyersViewHolder(@NonNull View itemView ,SetOnLawyerClicked setOnLawyerClicked) {
            super(itemView);
            lawyerName = itemView.findViewById(R.id.lw_name);
            yearsOfExperience = itemView.findViewById(R.id.lw_experience);
            licence = itemView.findViewById(R.id.lw_Licenced);
            this.setOnLawyerClicked = setOnLawyerClicked;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            setOnLawyerClicked.OnLawyerClicked(lawyerList.get(getAdapterPosition()));

        }
    }
}
