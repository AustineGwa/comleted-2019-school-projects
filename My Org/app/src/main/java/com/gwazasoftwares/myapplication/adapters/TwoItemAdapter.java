package com.gwazasoftwares.myapplication.adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gwazasoftwares.myapplication.R;
import com.gwazasoftwares.myapplication.interfaces.OnHomeItemClickListener;
import com.gwazasoftwares.myapplication.models.Service;

import java.util.List;

public class TwoItemAdapter extends RecyclerView.Adapter<TwoItemAdapter.TwoItemViewHolder> {

    List<Service> services;
    OnHomeItemClickListener listener;

    public TwoItemAdapter(List<Service> services, OnHomeItemClickListener listener) {
        this.services = services;
        this.listener = listener;
    }

    @NonNull
    @Override
    public TwoItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.home_row, viewGroup,false);
        TwoItemViewHolder homeViewHolder = new TwoItemViewHolder(view, listener);
        return homeViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TwoItemViewHolder homeViewHolder, int i) {
        homeViewHolder.serviceImage.setImageResource(services.get(i).getHolderImage());
        homeViewHolder.serviceName.setText(services.get(i).getName());

    }

    @Override
    public int getItemCount() {
        return services.size();
    }

    class TwoItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView serviceImage;
        private TextView serviceName;
        OnHomeItemClickListener listener;
        public TwoItemViewHolder(@NonNull View itemView, OnHomeItemClickListener listener) {
            super(itemView);

            serviceImage = itemView.findViewById(R.id.serviceimage);
            serviceName = itemView.findViewById(R.id.servicename);
            this.listener = listener;
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            listener.onHomeItemClick(v, getAdapterPosition());
        }
    }
}
