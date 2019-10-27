package com.example.criminalrepots.dialogs;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.criminalrepots.R;
import com.example.criminalrepots.models.Complaint;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class ComplaintDialog extends AppCompatDialogFragment {

   private  EditText userComplaint ,typeOfComplaint, date;
   final Calendar myCalendar = Calendar.getInstance();

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.new_animal_entry,null);

        userComplaint = view.findViewById(R.id.edanimname);
        typeOfComplaint= view.findViewById(R.id.edreport_type);
        date = view.findViewById(R.id.edentrydate);


        final DatePickerDialog.OnDateSetListener myDate = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        date.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(getActivity(), myDate, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });




        builder.setView(view)
                .setTitle("New Entry")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener(
                ) {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


                String uEmail = FirebaseAuth.getInstance().getCurrentUser().getEmail();
                String uComplaint = userComplaint.getText().toString();
                String uReportType = typeOfComplaint.getText().toString();
                String entryDate = date.getText().toString();

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference reference = database.getReference().child("complaints");

                Complaint complaint = new Complaint(uEmail,uComplaint,entryDate,uReportType, "not viewed");
                reference.push().setValue(complaint);
                Toast.makeText(getActivity(),"message posted successfully", Toast.LENGTH_SHORT).show();

//                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        if(task.isSuccessful()){
//                            Toast.makeText(getActivity(),"message posted successfully", Toast.LENGTH_SHORT).show();
//                        }else {
//                            Toast.makeText(getActivity(),"message sending complaint "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });



            }
        });

        return builder.create();
    }

    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        date.setText(sdf.format(myCalendar.getTime()));
    }
}
