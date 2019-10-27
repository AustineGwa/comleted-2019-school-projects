package com.example.smartambulance;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.smartambulance.models.AccidentLocation;
import com.example.smartambulance.models.AccidentReport;
import com.example.smartambulance.models.Constants;
import com.example.smartambulance.services.FetchAddressIntentService;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity implements
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private Button getLocation, submitLocation;
    private EditText showLocation;
    private FusedLocationProviderClient fusedLocationClient;
    ProgressDialog progressDialog;

    private AccidentLocation myLastLocation;

    protected Location lastLocation;
    private AddressResultReceiver resultReceiver;
    private String addressOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getLocation = findViewById(R.id.mylocation);
        submitLocation = findViewById(R.id.submit_location);
        showLocation = findViewById(R.id.location_display);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        myLastLocation = new AccidentLocation();

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("reporting Accident ....");
        progressDialog.setCanceledOnTouchOutside(false);

        checkAndRequestPermissions();

        submitLocation.setEnabled(false);


    }

    @Override
    protected void onStart() {
        super.onStart();
        getLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               updateUI();
            }
        });

        submitLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressDialog.show();
                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                DatabaseReference databaseReference = firebaseDatabase.getReference();
                AccidentLocation mAccidentLocation = getMyLocation();
                String currentUser = FirebaseAuth.getInstance().getCurrentUser().getEmail();
                AccidentReport accidentReport = new AccidentReport(mAccidentLocation,currentUser);
                databaseReference.child("reports").child("accidentLocations");
                databaseReference.push().setValue(accidentReport).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        progressDialog.dismiss();
                        if(task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Accident  reported successfully an ambulance will be there shortly ", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getApplicationContext(), "Error "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });



            }
        });
    }

    private AccidentLocation getMyLocation() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                checkAndRequestPermissions();
            }
            fusedLocationClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {

                    // Got last known location. In some rare situations this can be null.
                    if (location != null) {
                        myLastLocation = new AccidentLocation(location.getLatitude(), location.getLongitude());


                    }
                    if (!Geocoder.isPresent()) {
                        Toast.makeText(Home.this,
                                "no geo coder available",
                                Toast.LENGTH_LONG).show();
                        return;
                    }

                    // Start service and update UI to reflect new location
                    //startIntentService();
                    updateUI();
                }
            });
        }

        return myLastLocation;
    }

    private void updateUI() {
        AccidentLocation currentLocation = getMyLocation();
        String locationDisplay = "Lattitude : " + currentLocation.getLattitude() + "\n longitude :" + " : " + currentLocation.getLongitude()+"\n Place name : ";
        showLocation.setText(locationDisplay);
        submitLocation.setEnabled(true);
    }

    protected void startIntentService() {
        Intent intent = new Intent(this, FetchAddressIntentService.class);
        intent.putExtra(Constants.RECEIVER, resultReceiver);
        intent.putExtra(Constants.LOCATION_DATA_EXTRA, lastLocation);
        startService(intent);
    }

    private void checkAndRequestPermissions() {
        int permissionWriteStorage = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        int ReadPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);
        List<String> listPermissionsNeeded = new ArrayList<>();
        if (ReadPermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (permissionWriteStorage != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.ACCESS_COARSE_LOCATION);
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[0]), 1);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.nav_logout) {
            FirebaseAuth.getInstance().signOut();
            Toast.makeText(getApplicationContext(), "User logged out successfully", Toast.LENGTH_SHORT).show();
            finish();
        } else if (id == R.id.nav_notification) {
            Toast.makeText(getApplicationContext(), "No notifications available", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


    class AddressResultReceiver extends ResultReceiver {
        public AddressResultReceiver(Handler handler) {
            super(handler);
        }

        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {

            if (resultData == null) {
                return;
            }

            // Display the address string
            // or an error message sent from the intent service.
            addressOutput = resultData.getString(Constants.RESULT_DATA_KEY);
            if (addressOutput == null) {
                addressOutput = "";
            }
            //display address output
            Toast.makeText(getApplicationContext(),addressOutput,Toast.LENGTH_SHORT).show();

            // Show a toast message if an address was found.
            if (resultCode == Constants.SUCCESS_RESULT) {
                Toast.makeText(getApplicationContext(),"address found ",Toast.LENGTH_SHORT).show();
            }

        }
    }
}
