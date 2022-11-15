package com.petHomeApps.petHome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;

public class SplashScreen extends AppCompatActivity {
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        mAuth = FirebaseAuth.getInstance();

        if (mAuth.getCurrentUser() == null) {
            Intent signupintent = new Intent(this, MainActivity.class);
            startActivity(signupintent);
            finish();
        } else {
            Intent signupintent = new Intent(this, HomeActivity.class);
            startActivity(signupintent);
            finish();
        }
    }
}