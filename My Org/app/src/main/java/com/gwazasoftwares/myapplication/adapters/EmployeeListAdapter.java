package com.gwazasoftwares.myapplication.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gwazasoftwares.myapplication.R;
import com.gwazasoftwares.myapplication.models.Employee;
import com.gwazasoftwares.myapplication.tasks.OnEmployeeClickListener;

import java.util.List;

public class EmployeeListAdapter extends RecyclerView.Adapter<EmployeeListAdapter.EmployeeViewHolder> {

    private List<Employee> employeeList;
    private OnEmployeeClickListener onEmployeeClickListener;


    public EmployeeListAdapter(List<Employee> employeeList, OnEmployeeClickListener onEmployeeClickListener) {
        this.employeeList = employeeList;
        this.onEmployeeClickListener = onEmployeeClickListener;
    }

    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_employees_row,parent,false);
        EmployeeViewHolder employeeViewHolder = new EmployeeViewHolder(view, onEmployeeClickListener);

        return employeeViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeViewHolder holder, int position) {
        holder.e_name.setText(employeeList.get(position).getName());
        holder.e_jobGroup.setText(employeeList.get(position).getGroup());
    }

    @Override
    public int getItemCount() {
        return employeeList.size();
    }

    class EmployeeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView e_name, e_jobGroup;
        OnEmployeeClickListener onEmployeeClickListener;

        public EmployeeViewHolder(@NonNull View itemView,  OnEmployeeClickListener onEmployeeClickListener) {
            super(itemView);

            this.onEmployeeClickListener = onEmployeeClickListener;
            e_name = itemView.findViewById(R.id.name);
            e_jobGroup = itemView.findViewById(R.id.jobgroup);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            onEmployeeClickListener.onEmployeeClick(employeeList.get(getAdapterPosition()));
        }
    }
}
