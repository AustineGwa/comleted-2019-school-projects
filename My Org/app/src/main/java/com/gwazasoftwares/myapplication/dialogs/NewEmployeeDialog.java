package com.gwazasoftwares.myapplication.dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.gwazasoftwares.myapplication.R;
import com.gwazasoftwares.myapplication.models.Employee;

import java.util.Calendar;


public class NewEmployeeDialog extends AppCompatDialogFragment {

    private EditText name, email, password, department;
    private Spinner jobGroup;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.new_employee_dialogue, null);

        name = view.findViewById(R.id.edname);
        email = view.findViewById(R.id.edemail);
        password = view.findViewById(R.id.edpassword);
        department = view.findViewById(R.id.eddepartment);
        jobGroup = view.findViewById(R.id.spjobgroup);

        builder.setView(view)
                .setTitle("New Employee")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener(
                ) {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        final String username = name.getText().toString();
                        final String userPasword = password.getText().toString();
                        final String userEmail = email.getText().toString();
                        final String userDepartment = department.getText().toString();
                        final String userJobGroup = jobGroup.getSelectedItem().toString();

                        Employee employee = new Employee(username,userEmail,userDepartment,userJobGroup);


                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference refference = database.getReference();
                        final FirebaseAuth mAuth = FirebaseAuth.getInstance();

                        refference.child("standau").child("employees").push().setValue(employee).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                mAuth.createUserWithEmailAndPassword(userEmail,userPasword);
                                //Toast.makeText(context,"Gate man created  successfully", Toast.LENGTH_SHORT).show();
                            }
                        });



                    }
                });

        return builder.create();
    }


}
