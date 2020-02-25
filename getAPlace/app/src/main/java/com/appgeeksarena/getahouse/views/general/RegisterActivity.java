package com.appgeeksarena.getahouse.views.general;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.appgeeksarena.getahouse.R;
import com.appgeeksarena.getahouse.models.User;
import com.appgeeksarena.getahouse.utils.db.FirebaseUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public class RegisterActivity extends AppCompatActivity {

    private EditText username, email, phone, password, confirmPassword;
    private  Button  register;
    private CheckBox termsCheck;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initComponents();
    }

    private void initComponents() {

        username = findViewById(R.id.ed_username);
        email = findViewById(R.id.ed_email);
        phone = findViewById(R.id.ed_phone);
        password = findViewById(R.id.ed_password);
        confirmPassword = findViewById(R.id.ed_confirm_password);
        termsCheck = findViewById(R.id.terms_check);
        register = findViewById(R.id.bt_register);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("creating account ...");
        progressDialog.setCanceledOnTouchOutside(false);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(inputValidated()){
                    User user = new User(email.getText().toString().trim(), password.getText().toString().trim());
                    registerUser(user);
                }else{
                    Toast.makeText(getApplicationContext(),"Please fill all the fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void registerUser(User user) {

        progressDialog.show();

        FirebaseUtils firebaseUtils = new FirebaseUtils();
        firebaseUtils.getFirebaseAuth().createUserWithEmailAndPassword(user.getEmail(), user.getPassword()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }
        });


    }

    private boolean inputValidated() {


        return !TextUtils.isEmpty(username.getText().toString().trim())
                && !TextUtils.isEmpty(email.getText().toString().trim())
                && !TextUtils.isEmpty(phone.getText().toString().trim())
                && !TextUtils.isEmpty(username.getText().toString().trim())
                && !TextUtils.isEmpty(password.getText().toString().trim())
                && !TextUtils.isEmpty(confirmPassword.getText().toString().trim());
    }
}
