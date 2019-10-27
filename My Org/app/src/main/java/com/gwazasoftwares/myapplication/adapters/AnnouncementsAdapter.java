package com.gwazasoftwares.myapplication.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gwazasoftwares.myapplication.R;
import com.gwazasoftwares.myapplication.models.Announcement;

import java.util.List;

public class AnnouncementsAdapter extends RecyclerView.Adapter<AnnouncementsAdapter.AnnouncementViewHolder> {

    private List<Announcement> announcements;

    public AnnouncementsAdapter(List<Announcement> announcements) {
        this.announcements = announcements;
    }


    @NonNull
    @Override
    public AnnouncementViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_employees_row, parent,false);
        return new AnnouncementViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnnouncementViewHolder holder, int position) {
        holder.e_name.setText(announcements.get(position).getMessage());
    }

    @Override
    public int getItemCount() {
        return announcements.size();
    }

    class AnnouncementViewHolder extends RecyclerView.ViewHolder{
        TextView e_name, e_jobGroup;

        public AnnouncementViewHolder(@NonNull View itemView) {
            super(itemView);

            e_name = itemView.findViewById(R.id.name);
            //e_jobGroup = itemView.findViewById(R.id.jobgroup);

        }

    }
}
