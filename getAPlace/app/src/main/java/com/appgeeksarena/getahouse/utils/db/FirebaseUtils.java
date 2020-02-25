package com.appgeeksarena.getahouse.utils.db;

import com.appgeeksarena.getahouse.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseUtils {

    private  FirebaseAuth firebaseAuth;
    private   FirebaseDatabase firebaseDatabase;

    //private static  FirebaseStorage firebaseStorage;


    public FirebaseUtils() {

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
    }

    public FirebaseUser getCurrentUser(){

        return  firebaseAuth.getCurrentUser();
    }

    public FirebaseAuth getFirebaseAuth() {
        return firebaseAuth;
    }

    public  FirebaseDatabase getFirebaseDatabase() {
        return firebaseDatabase;
    }
}
