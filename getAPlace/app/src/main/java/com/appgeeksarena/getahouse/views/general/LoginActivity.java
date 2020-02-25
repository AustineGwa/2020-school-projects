package com.appgeeksarena.getahouse.views.general;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.appgeeksarena.getahouse.R;
import com.appgeeksarena.getahouse.utils.db.FirebaseUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public class LoginActivity extends Activity {

    EditText email, password;
    private FirebaseUtils firebaseUtils;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initComponents();

        firebaseUtils = new FirebaseUtils();
        progressDialog =new ProgressDialog(this);
        progressDialog.setTitle("verifying credentials...");
        progressDialog.setCanceledOnTouchOutside(false);
    }

    private void initComponents() {

        email = findViewById(R.id.ed_email);
        password = findViewById(R.id.ed_password);
    }

    public void loginUser(View view) {

        String mEmail = email.getText().toString().trim();
        String mPassword = password.getText().toString().trim();

        if(!TextUtils.isEmpty(mEmail) && !TextUtils.isEmpty(mPassword)){

            progressDialog.show();

            firebaseUtils.getFirebaseAuth().signInWithEmailAndPassword(mEmail,mPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){

                        startActivity(new Intent(getApplicationContext(), Dashboard.class));
                        finish();
                    }else {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }

                }
            });

        }else{
            Toast.makeText(getApplicationContext(), "Please fill the required details", Toast.LENGTH_SHORT).show();
        }

    }

    public void registerUser(View view) {
        startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
    }
}
