package com.appgeeksarena.getahouse.views.general;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.appgeeksarena.getahouse.R;
import com.appgeeksarena.getahouse.utils.db.FirebaseUtils;
import com.google.firebase.auth.FirebaseUser;

public class SplashActivity extends Activity {


    private FirebaseUtils firebaseUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        firebaseUtils = new FirebaseUtils();
    }


    @Override
    protected void onStart() {
        super.onStart();
        updateUI(firebaseUtils.getCurrentUser());
    }

    private void updateUI(final FirebaseUser currentUser) {
        Thread thread  = new Thread(){

            @Override
            public  void run(){
                try {
                    Thread.sleep(3000);
                    if(currentUser != null){
                        startHome();
                        finish();

                    }else{
                        startAuth();
                        finish();
                    }


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }

    private void startAuth() {
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
    }

    private void startHome() {
        Intent i = new Intent(this, Dashboard.class);
        startActivity(i);
    }


}
