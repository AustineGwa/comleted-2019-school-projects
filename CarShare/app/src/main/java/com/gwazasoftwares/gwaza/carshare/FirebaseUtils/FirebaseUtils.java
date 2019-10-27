package com.gwazasoftwares.gwaza.carshare.FirebaseUtils;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseUtils {

    public static DatabaseReference getDbReff(String firebaseReff){

        return FirebaseDatabase.getInstance().getReference(firebaseReff);
    }
}
