package com.gwazasoftwares.myapplication.dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.gwazasoftwares.myapplication.R;
import com.gwazasoftwares.myapplication.models.LeaveRequestData;

public class RequestLeaveDialog extends AppCompatDialogFragment {

    TextView announcement_tv;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.new_announcement_dialogue, null);

        announcement_tv = view.findViewById(R.id.edannouncement);
        builder.setView(view)
                .setTitle("Request Leave")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference refference = database.getReference();
                        final FirebaseAuth mAuth = FirebaseAuth.getInstance();

                        if (TextUtils.isEmpty(announcement_tv.getText())) {
                            Toast.makeText(getActivity(), "message", Toast.LENGTH_SHORT).show();
                        }

                        String username = mAuth.getCurrentUser().getEmail();
                        String my_message = announcement_tv.getText().toString();
                        LeaveRequestData leaveRequestData = new LeaveRequestData(username,my_message);


                        refference.child("standau").child("leaves").push().setValue(leaveRequestData);


                    }
                });

        return builder.create();
    }

}