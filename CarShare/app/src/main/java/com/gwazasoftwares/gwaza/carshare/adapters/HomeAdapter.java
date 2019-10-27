package com.gwazasoftwares.gwaza.carshare.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gwazasoftwares.gwaza.carshare.Home;
import com.gwazasoftwares.gwaza.carshare.R;
import com.gwazasoftwares.gwaza.carshare.interfaces.OnPostClick;
import com.gwazasoftwares.gwaza.carshare.models.Post;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder> {
   private List<Post>  carShared;
   private OnPostClick onPostClick;

    public HomeAdapter(List<Post> carShared, OnPostClick onPostClick) {
        this.carShared = carShared;
        this.onPostClick = onPostClick;
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_row,parent,false);
        HomeViewHolder homeViewHolder = new HomeViewHolder(view, onPostClick);
        return homeViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
//      holder.image.setImageResource(carShared.get(position).getImage());
        holder.image.setImageResource(R.drawable.car);
        holder.destination.setText(carShared.get(position).getDestination());

    }

    @Override
    public int getItemCount() {
        return carShared.size();
    }

    class  HomeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView image;
        TextView  destination;
        OnPostClick onPostClick;
        public HomeViewHolder(View itemView, OnPostClick onPostClick) {
            super(itemView);

            image = itemView.findViewById(R.id.image);
            destination = itemView.findViewById(R.id.txtdestination);
            this.onPostClick = onPostClick;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onPostClick.postClick(v, getAdapterPosition());
        }
    }
}
